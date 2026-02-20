package com.kz.tatical_rpg.domain;

public class SelfBuffDamage implements ISpell{
    @Override
    public void spellactive(Entity target, Entity multiplie) {

    }

    @Override
    public void description() {
        System.out.println("Buff your damgae in 30%.");
    }
}
