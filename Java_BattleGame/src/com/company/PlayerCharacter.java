package com.company;

public class PlayerCharacter extends Character {

    // properties
    private String description;

    // constructor
    public PlayerCharacter(String name, int hitPoints, int defense, int agility, int baseAttack, String description) {
        super(name, hitPoints, defense, agility, baseAttack);
        this.description = description;
    }

    // methods
    public void rerollAll(){
        rerollHitPoints();
        rerollDefense();
        rerollAgility();
        rerollBaseAttack();
    }

    public void rerollHitPoints() {
        int max = (getHitPoints() + 25);
        int min = (getHitPoints() - 25);
        int newHitPoints = (int) Math.floor(Math.random() * (max - min + 1) + min);
        setHitPoints(newHitPoints);
    }

    public void rerollDefense() {
        int max = (getDefense() + 4);
        int min = (getDefense() - 4);
        int newDefense = (int) Math.floor(Math.random() * (max - min + 1) + min);
        setDefense(newDefense);
    }

    public void rerollAgility(){
        int max = (getAgility() + 6);
        int min = (getAgility() - 6);
        int newAgility = (int) Math.floor(Math.random() * (max - min + 1) + min);
        if (newAgility < 0) {
            newAgility = 0;
        }
        setAgility(newAgility);
    }

    public void rerollBaseAttack(){
        int max = (getBaseAttack() + 6);
        int min = (getBaseAttack() - 6);
        int newBaseAttack = (int) Math.floor(Math.random() * (max - min + 1) + min);
        setBaseAttack(newBaseAttack);
    }

    public String getDescription() {
        return description;
    }
}