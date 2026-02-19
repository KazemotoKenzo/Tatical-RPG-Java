package com.kz.tatical_rpg.Services;

import com.kz.tatical_rpg.domain.Entity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class CombatService {
    private ArrayList<Entity> sequencelist = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private int enemies_hp = 0;
    int select;

    @PostConstruct
    public void mainRunner(){
        createEntityTest();
        selectInput();
    }

    private void selectInput(){
        while (true){
            try{
                for(Entity i : sequencelist){ System.out.println("\n========================\n" + i + "\n========================\n"); }
                System.out.println("Select a entity to deal damage: ");
                select = scanner.nextInt();

                if(select >= 0 && select < sequencelist.toArray().length){
                    break;
                }   else { System.out.println("Error: Try selecting a number between 0 and " + (sequencelist.toArray().length-1)); scanner.nextLine(); }

            }   catch (InputMismatchException e) {
                System.out.println("Error: Try to input a integer number.");
                scanner.nextLine();
            }
        }
        selectEnemy(select);
        scanner.nextLine();
    }

    private void selectEnemy(int value){
        int damage = 1;

        Entity entity = sequencelist.get(value);
        entity.takeDamage(damage);
        enemies_hp -= damage;
        System.out.println(entity.getName() + " was hurt!\n Now it has " + entity.getHp() + "/" + entity.getHp_max() + " hp.");
    }

    private void addSequence(Entity entity){
        sequencelist.add(entity);
        enemies_hp += entity.getHp_max();
        sequencelist.sort(Comparator.comparingInt(Entity::getInitiative));
    }

    private void createEntityTest(){
        Entity entity1 = new Entity("Entity 1", 1, 10, 1);
        Entity entity2 = new Entity("Entity 2", 2, 10, 1);
        Entity entity3 = new Entity("Entity 3", 3, 10, 1);
        Entity entity4 = new Entity("Entity 4", 4, 10, 1);

        addSequence(entity4);
        addSequence(entity2);
        addSequence(entity3);
        addSequence(entity1);

        for(Entity i : sequencelist){ System.out.println("\n========================\n" + i + "\n========================\n"); }
    }
}
