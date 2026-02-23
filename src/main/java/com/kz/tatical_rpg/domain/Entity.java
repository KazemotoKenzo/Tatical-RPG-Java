package com.kz.tatical_rpg.domain;

import com.kz.tatical_rpg.enums.Etag;

import java.util.ArrayList;

public class Entity {
    private Etag tag;
    private String name;
    private int initiative;
    private int actions;
    private int hp;
    private int hp_max;
    private int damage;
    private boolean dead = false;
    private float damage_multiplie;
    private int barrier = 0;
    private int barrier_max = 0;

    private ArrayList<ISpell> spellslots = new ArrayList<>();

    public Entity(Etag tag, String name, int initiative, int hp_max, int damage, int actions){
        this.tag = tag;
        this.name = name;
        this.initiative = initiative;
        this.hp_max = hp_max;
        this.hp = this.hp_max;
        this.damage = damage;
        this.actions = actions;
        this.damage_multiplie = 1;
    }

    @Override
    public String toString(){
        return this.name + (this.dead ? " || DEAD" : "") + " || HP: " + this.hp + "/" + this.hp_max + (this.barrier > 0 ? " |" + this.barrier + "/" + this.barrier_max + "|" : "");
    }

    private boolean isDead(){
        if(this.hp >= this.hp_max)  this.hp = this.hp_max;

        if(this.hp <= 0){
            this.hp = 0;
            this.dead = true;
        }
        return this.dead;
    }

    public Entity(){
        this(Etag.ENEMIE,"Unknown", 0, 0, 0, 1);
        this.damage_multiplie = 1;
    }

    public void cap_multiplie_damage(){
        if(this.damage_multiplie <= 0){
            damage_multiplie = 1;
        }
    }

    public int takeDamage(int damage_, boolean true_damage){
        if(!true_damage && this.barrier > 0){
            int discount = damage_ - this.barrier;
            if (discount < 0) discount = 0;
            this.barrier -= damage_;
            if(this.barrier <= 0) {
                this.barrier_max = 0;
                this.barrier = 0;
            }
            damage_ = discount;
        }

        this.hp -= damage_;

        if(isDead()) System.out.println("The entity has been slayed.");
        return damage_;
    }

    public int getBarrier_max() {
        return barrier_max;
    }

    public void setBarrier_max(int barrier_max) {
        this.barrier_max = barrier_max;
        this.barrier = this.barrier_max;
    }

    public float getDamage_multiplie() {
        return damage_multiplie;
    }

    public void setDamage_multiplie(float damage_multiplie) {
        this.damage_multiplie = damage_multiplie;
        cap_multiplie_damage();
    }

    public String getStatus(){
        return this.name + " || " + this.hp + "/" + this.hp_max;
    }

    public void addSpellslot(ISpell spell){
        this.spellslots.add(spell);
    }

    public ArrayList<ISpell> getSpellslots() {
        return spellslots;
    }

    public void setSpellslots(ArrayList<ISpell> spellslots) {
        this.spellslots = spellslots;
    }

    public Etag getTag() {
        return tag;
    }

    public int getBarrier() {
        return barrier;
    }

    public void setBarrier(int barrier) {
        this.barrier = barrier;
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
