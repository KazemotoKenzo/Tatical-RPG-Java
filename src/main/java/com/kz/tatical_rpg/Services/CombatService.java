package com.kz.tatical_rpg.Services;

import com.kz.tatical_rpg.domain.Entity;
import com.kz.tatical_rpg.enums.Etag;
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

        int i = 0;
        while(enemies_hp > 0){
            Entity entity = sequencelist.get(i);
            if(entity.getHp() != 0){
                for(int x = 1; x <= entity.getActions(); x++){
                    int textTemp = (entity.getActions() - x + 1);
                    showSequence();
                    System.out.println( textTemp + (textTemp > 1 ? " actions" : " action") + " left.");
                    if(entity.getTag() == Etag.PLAYER) {
                        selectInput();
                    } else {
                        selectEnemy(0, 1);
                    }
                }
            }
            i++;
            if(i >= sequencelist.toArray().length) i = 0;
        }

        System.out.println("All enemies has been slayed.\nThe game is over.");
    }

    private void showSequence(){
        for(Entity i : sequencelist){ System.out.println("\n========================\n" + i + "\n========================\n"); }
    }

    private void selectInput(){
        while (true){
            try{
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
        selectEnemy(select, 5);
        scanner.nextLine();
    }

    private void selectEnemy(int value, int damage){
        Entity entity = sequencelist.get(value);
        entity.takeDamage(damage);
        if(entity.getTag() == Etag.ENEMIE) enemies_hp -= damage;
        System.out.println(entity.getName() + " was hurt!\nNow it has " + entity.getHp() + "/" + entity.getHp_max() + " hp.");
    }

    private void addSequence(Entity entity){
        sequencelist.add(entity);
        if(entity.getTag() == Etag.ENEMIE) enemies_hp += entity.getHp_max();
        sequencelist.sort(Comparator.comparingInt(Entity::getInitiative));
    }

    private void createEntityTest(){
        Entity entity1 = new Entity(Etag.PLAYER, "Entity 1", 1, 10, 1, 2);
        Entity entity2 = new Entity(Etag.ENEMIE, "Entity 2", 2, 10, 1, 1);
        Entity entity3 = new Entity(Etag.ENEMIE,"Entity 3", 3, 10, 1, 1);
        Entity entity4 = new Entity(Etag.ENEMIE,"Entity 4", 4, 10, 1, 1);

        addSequence(entity4);
        addSequence(entity2);
        addSequence(entity3);
        addSequence(entity1);
    }
}
