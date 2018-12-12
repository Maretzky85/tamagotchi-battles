package Model;

import Model.Gotchi.Action;
import Model.Gotchi.Gotchi;
import Model.Gotchi.Type;

import java.util.ArrayList;
import java.util.Random;

import static Model.Gotchi.Action.*;

public class Battle implements Runnable{

    private boolean working =true;

    private int round = 1;

    static ArrayList<Gotchi> gotchis = new ArrayList<>();


    public void addPlayer(Gotchi gotchi){
        if(gotchis.size() < 2){
            gotchis.add(gotchi);
            System.out.println("Arena: added Gotchi - "+gotchi.getName());
        }else{
            System.out.println("Arena is Full");
        }

    }

    public void battleResults() {
        Action player1action = gotchis.get(0).getAction();
        Action player2action = gotchis.get(1).getAction();

        System.out.println(gotchis.get(0).getName() + " - " + gotchis.get(0).getAction());
        System.out.println(gotchis.get(1).getName() + " - " + gotchis.get(1).getAction());

        // calculates results for both pets attacking
        if (isAttacking(player1action) && isAttacking(player2action)) {
            if (gotchis.get(0).getSpeed() > gotchis.get(1).getSpeed()) {
                dealDamageToEnemy(gotchis.get(0), gotchis.get(1), 0);
                getDamageIfEnemyNotDead(gotchis.get(0), gotchis.get(1));
            } else {
                dealDamageToEnemy(gotchis.get(1), gotchis.get(0), 0);
                getDamageIfEnemyNotDead(gotchis.get(1), gotchis.get(0));
            }
        } else

        // calculates results for one player attacking, second defending or dodging
        if (isAttacking(player1action) && isDefendingOrDodging(player2action) ||
            isAttacking(player2action) && isDefendingOrDodging(player1action)) {
            Gotchi attacker;
            Gotchi defender;
            if (isAttacking(player1action)) {
                attacker = gotchis.get(0);
                defender = gotchis.get(1);
            } else {
                attacker = gotchis.get(1);
                defender = gotchis.get(0);
            }
            if (defender.getAction() == Action.DEFENSE) {
                attackVsDefense(attacker, defender);
            }
            if (defender.getAction() == Action.DODGE) {
                if (isDodgeFailed(attacker, defender)) {
                    dealDamageToEnemy(attacker, defender, 0);
                }
            }
        }else

        // can get removed, nothing happens anyways
        if (isDefendingOrDodging(gotchis.get(0).getAction()) && isDefendingOrDodging(gotchis.get(1).getAction())) {
            System.out.println("Nothing happened, both players went for defensive stance.");
        }
    }

    private void attackVsDefense(Gotchi attacker, Gotchi defender) {
        dealDamageToEnemy(attacker, defender, calculateDefense(defender));
    }

    private boolean isDefendingOrDodging(Action action) {
        return action == Action.DEFENSE || action == Action.DODGE;
    }

    private void getDamageIfEnemyNotDead(Gotchi defender, Gotchi attacker) {
        if (attacker.getHealth() > 0) {
            dealDamageToEnemy(attacker, defender, 0);
        }
    }

    private boolean isAttacking(Action action) {
        return action == ATTACK || action == Action.ATTACKSECONDARY;
    }

    private void dealDamageToEnemy(Gotchi attacker, Gotchi defender, double defenseModifier) {
        double damage = attacker.getAttack() * calculateAttackModifier(getTypeOfAttack(attacker), defender.getPrimary());
        if (damage - defenseModifier > 0) {
            System.out.println(attacker.getName()+ " dealt to " + defender.getName() + " " +damage+" points od damage");
            defender.setHealth(defender.getHealth() - damage + defenseModifier);
        } else {
            System.out.println("Defense higher than attack damage. No damage dealt.");
        }
    }

    private Type getTypeOfAttack(Gotchi gotchi) {
        switch (gotchi.getAction()) {
            case ATTACK:
                return gotchi.getPrimary();
            case ATTACKSECONDARY:
                return gotchi.getSecondary();
        }
        return gotchi.getPrimary();
    }

    private double calculateAttackModifier(Type firstType, Type secondType) {
        if (firstType == Type.EARTH) {
            switch (secondType) {
                case WATER:
                    return 1.25;
                case FIRE:
                    return 0.75;
            }
        }
        if (firstType == Type.FIRE) {
            switch (secondType) {
                case EARTH:
                    return 1.25;
                case WATER:
                    return 0.75;
            }
        }
        if (firstType == Type.WATER) {
            switch (secondType) {
                case FIRE:
                    return 1.25;
                case EARTH:
                    return 0.75;
            }
        }
        return 1;
    }

    private double calculateDefense(Gotchi gotchi) {
        return gotchi.getDefense() * 2;
    }

    private boolean isDodgeFailed(Gotchi attacker, Gotchi defender) {
        boolean isFailed = 0 > attacker.getSpeed() * randomValue() - defender.getSpeed() * randomValue();
        if(isFailed){
            System.out.println(defender.getName()+" dodge failed");
        }else{
            System.out.println(defender.getName()+" dodge");
        }
        return isFailed;
    }

    private double randomValue() {
        Random random = new Random();
        return 0.75 + (1.25 - 0.75) * random.nextDouble();
    }

    private void waitForChange(){
        synchronized (this){
            try {
                this.wait();
            } catch (InterruptedException ignored) {}
        }
    }

    @Override
    public void run() {
        while(working){
            if (gotchis.size() == 2){
                if(gotchis.get(0).getAction() != null && gotchis.get(1).getAction() != null){
                    System.out.println("\n====================================================" +
                            "\nRound "+ round +" start...");
                    battleResults();
                    System.out.println(gotchis.get(0).getName()+" - "+ gotchis.get(0).getHealth());
                    System.out.println(gotchis.get(1).getName()+" - "+ gotchis.get(1).getHealth());
                    round++;
                    for (Gotchi gotchi: gotchis
                         ) {
                        gotchi.resetAction();
                    }
                }else{
                    System.out.println("Waiting for all players action");
                    synchronized (gotchis.get(1)){
                        gotchis.get(1).notify();
                    }
                    waitForChange();
                }
            }else{
                System.out.println("Arena: Waiting for players");
                waitForChange();
            }
        }
    }
}
