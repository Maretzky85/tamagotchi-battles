package Model.Gotchi;

import javafx.scene.image.ImageView;

public class Gotchi extends ImageView {
    private double speed;
    private double attack;
    private double attackSecondary;
    private double defense;
    private double health = 100;
    private double stamina = 100;
    private String name;
    private String imageUrl;
    private Action action;
    private Type primary;
    private Type secondary;

    public Gotchi(double speed, double attack, double defense, String name, String imageUrl, Type primary, Type secondary) {
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.name = name;
        this.imageUrl = imageUrl;
        this.primary = primary;
        this.secondary = secondary;
        this.attackSecondary = attack * 0.75;
    }

    public Type getPrimary() {
        return primary;
    }

    public Type getSecondary() {
        return secondary;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getHealth() {
        return health;
    }

    public double getStamina() {
        return stamina;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void attack() {
        this.action = Action.ATTACK;
    }

    public void attackSecondary() {
        this.action = Action.ATTACKSECONDARY;
    }

    public void defense() {
        this.action = Action.DEFENSE;
    }

    public void rest(){
    }

    public void dodge() {
        this.action = Action.DODGE;
    }

    public Action getAction() {
        return action;
    }

    public void resetAction() {
        this.action = null;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}
