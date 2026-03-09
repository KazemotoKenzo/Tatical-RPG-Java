package com.kz.tatical_rpg.controller;

import com.kz.tatical_rpg.domain.EntityStats;

public class EntityController {
    private EntityController() {}

    public static int takeDamage(EntityStats entity, int damage_, boolean true_damage){
        if(!true_damage && entity.getBarrier() > 0){
            int discount = damage_ - entity.getBarrier();
            if (discount < 0) discount = 0;
            entity.setBarrier(entity.getBarrier() - damage_);
            if(entity.getBarrier() <= 0) {
                entity.setBarrier_max(0);
                entity.setBarrier(0);
            }
            damage_ = discount;
        }

        entity.setHp(entity.getHp() - damage_);

        if(isDead(entity)) System.out.println("The entity has been slayed.");
        return damage_;
    }

    private static boolean isDead(EntityStats entity){
        if(entity.getHp() >= entity.getHp_max()) entity.setHp(entity.getHp_max());

        if(entity.getHp() <= 0){
            entity.setHp(0);
            entity.setDead(true);
        }
        return entity.getDead();
    }
}
