package com.kz.tatical_rpg.domain;

public class HealTarget implements ISpell{
    private int heal_base = 5;

    @Override
    public void spellactive(Entity target, Entity multiplie) {
        int heal = this.heal_base + multiplie.getHp() / 10;

        target.takeDamage(-heal);
        System.out.println(target.getName() + " heals " + heal + " hp.");
    }

    @Override
    public void description() {
        System.out.println("Select a target to Heal.");
    }
}
