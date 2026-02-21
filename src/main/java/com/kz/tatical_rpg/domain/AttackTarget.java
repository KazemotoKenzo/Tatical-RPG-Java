package com.kz.tatical_rpg.domain;

public class AttackTarget implements ISpell{
    private int spell_base_damage = 3;

    private String name = "Attack";

    @Override
    public void spellactive(Entity target, Entity entity_base) {
        int damage = (int) ((entity_base.getDamage() + this.spell_base_damage) * entity_base.getDamage_multiplie());

        target.takeDamage(damage);
        System.out.println(target.getName() + " has taked " + damage + " damage.");
    }

    @Override
    public void description() {
        System.out.println("Select a target to Attack.");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
