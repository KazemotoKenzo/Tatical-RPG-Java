package com.kz.tatical_rpg.Services;

import com.kz.tatical_rpg.domain.Entity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class CombatService {
    private ArrayList<Entity> sequencelist = new ArrayList<>();

    @PostConstruct
    public void mainRunner(){
        createEntityTest();
    }

    private void addSequence(Entity entity){
        sequencelist.add(entity);
        sequencelist.sort(Comparator.comparingInt(Entity::getInitiative));
    }

    private void createEntityTest(){
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        Entity entity3 = new Entity();
        Entity entity4 = new Entity();

        entity1.setInitiative(1);
        entity1.setName("entity 1");

        entity2.setInitiative(2);
        entity2.setName("entity 2");

        entity3.setInitiative(3);
        entity3.setName("entity 3");

        entity4.setInitiative(4);
        entity4.setName("entity 4");


        addSequence(entity4);
        addSequence(entity2);
        addSequence(entity3);
        addSequence(entity1);

        for(Entity i : sequencelist){
            System.out.println(i.getName());
        }
    }
}
