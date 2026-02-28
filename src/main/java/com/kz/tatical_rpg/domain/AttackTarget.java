package com.kz.tatical_rpg.domain;

import com.kz.tatical_rpg.controller.EntityController;

public class AttackTarget implements ISpell{
    private int spell_base_damage = 3;
    private boolean true_damage = false;

    private String name = "Attack";

    @Override
    public void spellactive(Entity target, Entity entity_base) {
        int damage = (int) ((entity_base.getDamage() + this.spell_base_damage) * entity_base.getDamage_multiplie());

        EntityController.takeDamage(target, damage, true_damage);
        //target.takeDamage(damage, true_damage);
        System.out.println(target.getName() + " has taked " + damage + " damage.\nNow it has " + target.getHp() + "/" + target.getHp_max() + " hp.");
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
