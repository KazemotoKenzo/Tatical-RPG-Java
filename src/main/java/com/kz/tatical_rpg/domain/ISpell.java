package com.kz.tatical_rpg.domain;

public interface ISpell {
    public void spellactive(Entity target, Entity entity_base);

    public void description();

    public String getName();
}
