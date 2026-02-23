package com.kz.tatical_rpg.domain;

public class BarrierTarget implements ISpell{
    private int barrier = 5;
    private String name = "Barrier";

    @Override
    public void spellactive(Entity target, Entity entity_base) {
        if(target.getBarrier() < this.barrier && target.getBarrier_max() > this.barrier) { target.setBarrier(this.barrier); }
        else target.setBarrier_max(this.barrier);
    }

    @Override
    public void description() {
        System.out.println("Select a target to add a Barrier.");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
