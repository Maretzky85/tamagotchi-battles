package Model;

import Controller.Controller;
import Model.Gotchi.Action;
import Model.Gotchi.Type;

import java.util.ArrayList;
import java.util.Random;

public class Battle implements Runnable{

    public Battle(Controller controller, Runnable updater){
        this.controller = controller;
    }

    private Runnable updater;

    private Controller controller;

    static ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(Player player){
        players.add(player);
    }

    public Player getPlayer1(){
        return players.get(0);
    }

    public void battleResults() {
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        Action player1action = player1.getGotchi().getAction();
        Action player2action = player2.getGotchi().getAction();

        // calculates results for both pets attacking
        if (isAttacking(player1action) && isAttacking(player2action)) {
            if (player1.getGotchi().getSpeed() > player2.getGotchi().getSpeed()) {
                dealDamageToEnemy(player1, player2, 0);
                getDamageIfEnemyNotDead(player1, player2);
            } else {
                dealDamageToEnemy(player2, player1, 0);
                getDamageIfEnemyNotDead(player2, player1);
            }
        }

        // calculates results for one player attacking, second defending or dodging
        if (isAttacking(player1action) && isDefendingOrDodging(player2action) ||
            isAttacking(player2action) && isDefendingOrDodging(player1action)) {
            Player attacker;
            Player defender;
            if (isAttacking(player1action)) {
                attacker = player1;
                defender = player2;
            } else {
                attacker = player2;
                defender = player1;
            }
            if (defender.getGotchi().getAction() == Action.DEFENSE) {
                attackVsDefense(attacker, defender);
            }
            if (defender.getGotchi().getAction() == Action.DODGE) {
                if (isDodgeFailed(attacker, defender)) {
                    dealDamageToEnemy(attacker, defender, 0);
                }
            }
        }

        // can get removed, nothing happens anyways
        if (isDefendingOrDodging(player1.getGotchi().getAction()) && isDefendingOrDodging(player2.getGotchi().getAction())) {
            System.out.println("Nothing happened, both players went for defensive stance.");
        }
        updater.run();
    }

    private void attackVsDefense(Player attacker, Player defender) {
        dealDamageToEnemy(attacker, defender, calculateDefense(defender));
    }

    private boolean isDefendingOrDodging(Action action) {
        return action == Action.DEFENSE || action == Action.DODGE;
    }

    private void getDamageIfEnemyNotDead(Player defender, Player attacker) {
        if (attacker.getGotchi().getHealth() > 0) {
            dealDamageToEnemy(attacker, defender, 0);
        }
    }

    private boolean isAttacking(Action action) {
        return action == Action.ATTACK || action == Action.ATTACKSECONDARY;
    }

    private void dealDamageToEnemy(Player attacker, Player defender, double defenseModifier) {
        double damage = attacker.getGotchi().getAttack() * calculateAttackModifier(getTypeOfAttack(attacker), defender.getGotchi().getPrimary());
        if (damage - defenseModifier > 0) {
            defender.getGotchi().setHealth(defender.getGotchi().getHealth() - damage + defenseModifier);
        } else {
            System.out.println("Defense higher than attack damage. No damage dealt.");
        }
    }

    private Type getTypeOfAttack(Player player) {
        switch (player.getGotchi().getAction()) {
            case ATTACK:
                return player.getGotchi().getPrimary();
            case ATTACKSECONDARY:
                return player.getGotchi().getSecondary();
        }
        return player.getGotchi().getPrimary();
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

    private double calculateDefense(Player player) {
        return player.getGotchi().getDefense() * 2;
    }

    private boolean isDodgeFailed(Player attacker, Player defender) {
        return 0 > attacker.getGotchi().getSpeed() * randomValue() - defender.getGotchi().getSpeed() * randomValue();
    }

    private double randomValue() {
        Random random = new Random();
        return 0.75 + (1.25 - 0.75) * random.nextDouble();
    }

    @Override
    public void run() {
        while(true){
            if (controller.player1moved && controller.player2moved){
                battleResults();
                updater.run();
                try {
                    controller.wait();
                } catch (InterruptedException ignored) {}
            }else{
                try {
                    controller.wait();
                } catch (InterruptedException ignored) {}
            }
        }
    }
}
