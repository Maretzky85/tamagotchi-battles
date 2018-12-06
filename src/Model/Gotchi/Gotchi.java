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
    private Type primary;
    private Type secondary;

    public Gotchi(int speed, int attack, int defense, String name, String imageUrl, Type primary, Type secondary) {
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.name = name;
        this.imageUrl = imageUrl;
        this.primary = primary;
        this.secondary = secondary;
    }

    public Type getPrimary() {
        return primary;
    }

    public Type getSecondary() {
        return secondary;
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
