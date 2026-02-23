package com.kz.tatical_rpg.Services;

import com.kz.tatical_rpg.domain.*;
import com.kz.tatical_rpg.enums.Etag;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CombatService {
    private ArrayList<Entity> sequencelist = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private int enemies_hp = 0;

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
                        selectOptions(entity);
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
        for(Entity i : sequencelist){ System.out.println("========================\n" + i + "\n========================"); }
    }

    private int selectInput(String mensage ,int finish){
        int select;

        while (true){
            try{
                System.out.println(mensage);
                select = scanner.nextInt();

                if(select >= 0 && select < finish){
                    break;
                }   else  System.out.println("Error: Try selecting a number between 0 and " + (finish - 1));

            }   catch (InputMismatchException e) {
                System.out.println("Error: Try to input a integer number.");
            }
            scanner.nextLine();
        }

        return select;
    }

    private <T> String genericList(ArrayList<T> items, boolean optional, java.util.function.Function<T, String> nameExtractor) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            message.append("\n").append(optional ? (i + 1) : i)
                    .append(" - ").append(nameExtractor.apply(items.get(i))).append(".");
        }

        String result = optional ? "0 - Return." : "";
        return result + message.toString();
    }

    private void selectOptions(Entity entity){
        boolean exit = false;
        while (!exit){
            ArrayList<ISpell> spell_list = entity.getSpellslots();

            String list_name = genericList(spell_list, false, s -> s.getName());

            int select = selectInput(list_name, spell_list.size());

            ISpell spell = spell_list.get(select);
            spell.description();

            list_name = genericList(sequencelist, true, s -> s.getStatus());
            select = selectInput(list_name, sequencelist.size() + 1);

            if(select == 0) continue;

            select--;

            Entity enemie = sequencelist.get(select);
            spell.spellactive(enemie, entity);
            exit = !exit;
        }
    }

    private void selectEnemy(int value, int damage){
        Entity entity = sequencelist.get(value);
        entity.takeDamage(damage, false);
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

        AttackTarget attack = new AttackTarget();
        HealTarget heal = new HealTarget();
        SelfBuffDamage buff = new SelfBuffDamage();
        BarrierTarget barrier = new BarrierTarget();

        entity1.addSpellslot(attack);
        entity1.addSpellslot(heal);
        entity1.addSpellslot(buff);
        entity1.addSpellslot(barrier);

        addSequence(entity4);
        addSequence(entity2);
        addSequence(entity3);
        addSequence(entity1);
    }
}
