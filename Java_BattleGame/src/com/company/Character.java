package com.company;

public abstract class Character {

    // properties
    private String name;
    private int hitPoints;
    private int defense;
    private int agility;
    private int baseAttack;


    // constructor
    public Character(String name, int hitPoints, int defense, int agility, int baseAttack) {
        this.setName(name);
        this.setHitPoints(hitPoints);
        this.setDefense(defense);
        this.setAgility(agility);
        this.setBaseAttack(baseAttack);
    }



    public int getHitPoints() { return hitPoints; }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}