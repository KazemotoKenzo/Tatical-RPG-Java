package com.kz.tatical_rpg.domain;

public class AttackTarget implements ISpell{
    private int damage_multiplie;

    @Override
    public void spellactive(Entity target, Entity multiplie) {
        int damage = multiplie.getDamage() * this.damage_multiplie;

        target.takeDamage(damage);
        System.out.println(target.getName() + " has taked " + damage + " damage.");
    }

    @Override
    public void description() {
        System.out.println("Select a target to Attack.");
    }
}
