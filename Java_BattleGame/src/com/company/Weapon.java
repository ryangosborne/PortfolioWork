package com.company;

public class Weapon {

    // properties
    private int wpnAttackMod;
    private int wpnWeight;
    private String wpnText;

    // constructor
    public Weapon(int wpnAttackMod, int wpnWeight, String wpnText){
        this.setWpnAttackMod(wpnAttackMod);
        this.setWpnWeight(wpnWeight);
        this.wpnText = wpnText;
    }

    // getters
    public int getWpnAttackMod() { return wpnAttackMod; }
    public int getWpnWeight() { return wpnWeight; }
    public String getWpnText() { return wpnText; }

    // setters
    public void setWpnAttackMod(int wpnAttackMod) { this.wpnAttackMod = wpnAttackMod; }
    public void setWpnWeight(int wpnWeight) { this.wpnWeight = wpnWeight; }
    public void setWpnText(String wpnText) { this.wpnText = wpnText; }
}
