package com.kz.tatical_rpg.domain;

import com.kz.tatical_rpg.enums.Etag;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;

public class EntityStats {
    @Id
    @GeneratedValue()
    private Integer id;

    private Etag tag;
    private String name;
    private Integer initiative;
    private Integer actions;
    private Integer hp;
    private Integer hp_max;
    private Integer damage;
    private boolean dead = false;
    private Float damage_multiplie;
    private Integer barrier = 0;
    private Integer barrier_max = 0;

    private ArrayList<ISpell> spellslots = new ArrayList<>();

    public EntityStats(Etag tag, String name, int initiative, int hp_max, int damage, int actions){
        this.tag = tag;
        this.name = name;
        this.initiative = initiative;
        this.hp_max = hp_max;
        this.hp = this.hp_max;
        this.damage = damage;
        this.actions = actions;
        this.damage_multiplie = 1f;
    }

    public EntityStats(){
        this(Etag.ENEMIE,"Unknown", 0, 0, 0, 1);
        this.damage_multiplie = 1f;
    }

    @Override
    public String toString(){
        return this.name + (this.dead ? " || DEAD" : "") + " || HP: " + this.hp + "/" + this.hp_max + (this.barrier > 0 ? " |" + this.barrier + "/" + this.barrier_max + "|" : "");
    }


    public void setDamage_multiplie(Float damage_multiplie) {
        this.damage_multiplie = damage_multiplie;
        cap_multiplie_damage();
    }

    public Integer getBarrier() {
        return barrier;
    }

    public void setBarrier(Integer barrier) {
        this.barrier = barrier;
    }

    public void setBarrier_max(Integer barrier_max) {
        this.barrier_max = barrier_max;
        this.barrier = this.barrier_max;
    }

    private void cap_multiplie_damage(){
        if(this.damage_multiplie <= 0){
            damage_multiplie = 1f;
        }
    }

    public String getStatus(){
        return this.name + " || " + this.hp + "/" + this.hp_max;
    }

    /*
    private boolean isDead(){
        if(this.hp >= this.hp_max)  this.hp = this.hp_max;

        if(this.hp <= 0){
            this.hp = 0;
            this.dead = true;
        }
        return this.dead;
    }
    */

    /*
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
     */

    public void setTag(Etag tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public Integer getActions() {
        return actions;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getHp_max() {
        return hp_max;
    }

    public Integer getDamage() {
        return damage;
    }

    public Float getDamage_multiplie() {
        return damage_multiplie;
    }

    public Integer getBarrier_max() {
        return barrier_max;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public void setActions(Integer actions) {
        this.actions = actions;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public void setHp_max(Integer hp_max) {
        this.hp_max = hp_max;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public boolean isDead() {
        return dead;
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

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean getDead() {
        return this.dead;
    }

    public Etag getTag() {
        return tag;
    }
}
