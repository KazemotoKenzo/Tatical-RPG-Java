package com.kz.tatical_rpg.domain;

public class Entity {
    private String name;
    private int initiative;
    private int actions;
    private int hp;
    private int hp_max;
    private int damage;

    public Entity(String name, int initiative, int hp_max, int damage){
        this.name = name;
        this.initiative = initiative;
        this.hp_max = hp_max;
        this.hp = this.hp_max;
        this.damage = damage;
    }

    public Entity(){
        this("Unknown", 0, 0, 0);
    }

    @Override
    public String toString(){
        return this.name + "\nHP: " + this.hp + "/" + this.hp_max;
    }

    public int takeDamage(int damage_){
        this.hp -= damage_;
        return damage_;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp_max() {
        return hp_max;
    }

    public void setHp_max(int hp_max) {
        this.hp_max = hp_max;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
}
