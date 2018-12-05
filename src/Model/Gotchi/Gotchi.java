package Model.Gotchi;

import javafx.scene.image.ImageView;

public class Gotchi extends ImageView {
    private int speed;
    private int attack;
    private int defense;
    private int health = 100;
    private int stamina = 100;
    private String name;
    private String imageUrl;
    private Action action;
    private Type type;

    public Gotchi(int speed, int attack, int defense, String name, String imageUrl) {
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getStamina() {
        return stamina;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void attack() {
    }

    public void defense() {
    }

    public void rest(){
    }

    public void dodge() {
    }
}

enum Type{
    WATER,
    FIRE,
    EARTH
}

enum Action {
    ATTACK,
    DEFENSE,
    DODGE,
    REST
}
