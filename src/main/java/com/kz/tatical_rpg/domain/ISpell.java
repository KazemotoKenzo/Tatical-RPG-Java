package com.kz.tatical_rpg.domain;

public interface ISpell {
    public void spellactive(EntityStats target, EntityStats entity_base);

    public void description();

    public String getName();
}
