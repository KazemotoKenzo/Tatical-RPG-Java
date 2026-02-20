package com.kz.tatical_rpg.domain;

public class SelfBuffDamage implements ISpell{
    private float buff_multiplie = 1.3f;

    @Override
    public void spellactive(Entity target, Entity entity_base) {
        entity_base.setDamage_multiplie(this.buff_multiplie);
    }

    @Override
    public void description() {
        System.out.println("Buff your damgae in 30%.");
    }
}
