package com.kz.tatical_rpg.domain;

import com.kz.tatical_rpg.enums.Etag;

import java.util.List;

public class Entity {
    private Etag tag;
    private String name;
    private int initiative;
    private int actions;
    private int hp;
    private int hp_max;
    private int damage;
    private boolean dead = false;

    private List<ISpell> spellslots;

    public Entity(Etag tag, String name, int initiative, int hp_max, int damage, int actions){
        this.tag = tag;
        this.name = name;
        this.initiative = initiative;
        this.hp_max = hp_max;
        this.hp = this.hp_max;
        this.damage = damage;
        this.actions = actions;
    }

    @Override
    public String toString(){
        return this.name + (this.dead ? " || DEAD" : "") + " || HP: " + this.hp + "/" + this.hp_max;
    }

    private boolean isDead(){
        if(this.hp <= 0){
            this.hp = 0;
            this.dead = true;
        }
        return this.dead;
    }

    public Entity(){
        this(Etag.ENEMIE,"Unknown", 0, 0, 0, 1);
    }

    public int takeDamage(int damage_){
        this.hp -= damage_;
        if(isDead()) System.out.println("The entity has been slayed.");
        return damage_;
    }

    public Etag getTag() {
        return tag;
    }

    public void setTag(Etag tag) {
        this.tag = tag;
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
