//Problem 3. Heroes of Code and Logic VII
//You got your hands on the most recent update on the best MMORPG of all time – Heroes of Code and Logic. You want to play it all day long! So cancel all other arrangements and create your party!
//
//On the first line of the standard input you will receive an integer n – the number of heroes that you can choose for your party. On the next n lines, the heroes themselves will follow with their hit points and mana points separated by empty space in the following format:
//{hero name} {HP} {MP}
//-where HP stands for hit points and MP for mana points
//-a hero can have a maximum of 100 HP and 200 MP
//After you have successfully picked your heroes, you can start playing the game.  You will be receiving different commands, each on a new line, separated by " – ", until the "End" command is given.
//There are several actions that can be performed by the heroes:
//CastSpell – {hero name} – {MP needed} – {spell name}
//If the hero has the required MP, he casts the spell, thus reducing his MP. Print this message:
//o"{hero name} has successfully cast {spell name} and now has {mana points left} MP!"
//If the hero is unable to cast the spell print:
//o"{hero name} does not have enough MP to cast {spell name}!"
//TakeDamage – {hero name} – {damage} – {attacker}
//Reduce the hero HP by the given damage amount. If the hero is still alive (his HP is greater than 0) print:
//o"{hero name} was hit for {damage} HP by {attacker} and now has {current HP} HP left!"
//If the hero has died, remove him from your party and print:
//o"{hero name} has been killed by {attacker}!"
//Recharge – {hero name} – {amount}
//The hero increases his MP. If a command is given that would bring the MP of the hero above 200, MP is increased so that it reaches the maximum. Print the following message:
//o"{hero name} recharged for {amount recovered} MP!"
//Heal – {hero name} – {amount}
//The hero increases his HP. If a command is given that would bring the HP of the hero above 100, HP is increased so that it reaches the maximum. Print the following message:
//o"{hero name} healed for {amount recovered} HP!"
//Input
//On the first line of the standard input you will receive an integer n
//On the next n lines, the heroes themselves will follow with their hit points and mana points separated by empty space in the following format
//You will be receiving different commands, each on a new line, separated by " – ", until the "End" command is given
//Output
//Print all members of your party who are still alive, sorted by their HP in descending order, then by their name in ascending order, in the following format (their HP/MP need to be indented 2 spaces):
//"{hero name}
// 	 HP: {current HP}
// MP: {current MP}
// ..."
//Constraints
//The starting HP/MP of the heroes will be valid, 32-bit integers, will never be negative or exceed the respective limits.
//The HP/MP amounts in the commands will never be negative.
//The hero names in the commands will always be valid members of your party. No need to check that explicitly
//Examples
//Input	                                                    Output
//2                                                         Solmyr healed for 10 HP!
//Solmyr 85 120                                             Solmyr recharged for 50 MP!
//Kyrre 99 50                                               Kyrre was hit for 66 HP by Orc and now has 33 HP left!
//Heal - Solmyr - 10                                        Kyrre has successfully cast ViewEarth and now has 35 MP!
//Recharge - Solmyr - 50                                    Solmyr
//TakeDamage - Kyrre - 66 - Orc                               HP: 95
//CastSpell - Kyrre - 15 - ViewEarth                          MP: 170
//End                                                       Kyrre
//                                                             HP: 33
//                                                             MP: 35

//Comments
//These are examples of successful actions. The different colors denote the commands and their respective messages.


//Examples
//Input	                                                    Output
//4                                                         SirMullich healed for 30 HP!
//Adela 90 150                                              Adela recharged for 50 MP!
//SirMullich 70 40
//Ivor 1 111
//Tyris 94 61
//Heal - SirMullich - 50
//Recharge - Adela - 100
//CastSpell - Tyris - 1000 - Fireball
//TakeDamage - Tyris - 99 - Fireball
//TakeDamage - Ivor - 3 - Mosquito
//End

//Coments
//Heal – SirMullich healed for 30 HP due to the HP max limit.
//Recharge – Adela recharged for 50 MP due to the MP max limit.
//CastSpell – Tyris does not have enough MP to cast the spell.
//TakeDamage – Tyris`s HP is reduced by 99, thus becoming -5, which means that he is dead.
//TakeDamage – Ivor`s HP is now -2, so he is dead too.
//After the "End" command we print the remaining living heroes, sorted by their HP in reverse order.
package Exam1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class HeroesOfCode {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        LinkedHashMap<String, ArrayList<Integer>> heroes = new LinkedHashMap<>();
        for (int i = 0; i <=n-1; i++) {
            String[] line = scanner.nextLine().split(" ");

            ArrayList<Integer> list = new ArrayList<>();

            list.add(Integer.parseInt(line[1]));
            list.add(Integer.parseInt(line[2]));

            heroes.put(line[0], list);
        }

        String input = scanner.nextLine();

        while(!input.equals("End")){
            String [] tockens = input.split("\\s-\\s");

            //CastSpell – {hero name} – {MP needed} – {spell name}
            //If the hero has the required MP, he casts the spell, thus reducing his MP. Print this message:
            //o"{hero name} has successfully cast {spell name} and now has {mana points left} MP!"
            //If the hero is unable to cast the spell print:
            //o"{hero name} does not have enough MP to cast {spell name}!"
            if(tockens[0].equals("CastSpell")){
                String heroName = tockens[1];
                int MPneeded = Integer.parseInt(tockens[2]);
                String spellName = tockens[3];

                if(heroes.get(heroName).get(1)>= MPneeded){
                    ArrayList<Integer> HPMP = heroes.get(heroName);
                    HPMP.set(1, heroes.get(heroName).get(1) - MPneeded);
                    heroes.put(heroName, HPMP);
                    System.out.printf("%s has successfully cast %s and now has %d MP!%n", heroName, spellName, heroes.get(heroName).get(1));
                }else{
                    System.out.printf("%s does not have enough MP to cast %s!%n", heroName, spellName);
                }
            }

            //TakeDamage – {hero name} – {damage} – {attacker}
            //Reduce the hero HP by the given damage amount. If the hero is still alive (his HP is greater than 0) print:
            //o"{hero name} was hit for {damage} HP by {attacker} and now has {current HP} HP left!"
            //If the hero has died, remove him from your party and print:
            //o"{hero name} has been killed by {attacker}!"
            if(tockens[0].equals("TakeDamage")){
                String heroName = tockens[1];
                int damage = Integer.parseInt(tockens[2]);
                String attacker = tockens[3];

                ArrayList<Integer> HPandMP = heroes.get(heroName);

                HPandMP.set(0, HPandMP.get(0) - damage);

                heroes.put(heroName, HPandMP);

                if(heroes.get(heroName).get(0)>0){
                    System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n", heroName, damage, attacker, heroes.get(heroName).get(0));
                }else{
                    heroes.remove(heroName);
                    System.out.printf("%s has been killed by %s!%n", heroName, attacker);
                }
            }
            //Recharge – {hero name} – {amount}
            //The hero increases his MP. If a command is given that would bring the MP of the hero above 200, MP is increased so that it reaches the maximum. Print the following message:
            //o"{hero name} recharged for {amount recovered} MP!"
            if(tockens[0].equals("Recharge")){
                String heroName = tockens[1];
                int amont = Integer.parseInt(tockens[2]);

                ArrayList<Integer> hpAndMP = heroes.get(heroName);

                int difference = amont;

                int valueMP = hpAndMP.get(1) + amont;

                if(valueMP>200){
                    difference = amont - (valueMP - 200);
                    valueMP = 200;
                }

                hpAndMP.set(1, valueMP);

                System.out.printf("%s recharged for %d MP!%n", heroName, difference);
            }
            //Heal – {hero name} – {amount}
            //The hero increases his HP. If a command is given that would bring the HP of the hero above 100, HP is increased so that it reaches the maximum. Print the following message:
            //o"{hero name} healed for {amount recovered} HP!"
            if(tockens[0].equals("Heal")){
                String heroName = tockens[1];
                int amont = Integer.parseInt(tockens[2]);

                ArrayList<Integer> HPandMP = heroes.get(heroName);

                int difference = amont;

                int valueHP = HPandMP.get(0) + amont;

                if(valueHP>100){
                    difference = amont - (valueHP - 100);
                    valueHP = 100;
                }

                HPandMP.set(0, valueHP);

                heroes.put(heroName, HPandMP);

                System.out.printf("%s healed for %d HP!%n", heroName, difference);

            }


            input = scanner.nextLine();
        }

        heroes
                .entrySet()
                .stream()
                .sorted((a, b) ->{
                    int rezult = b.getValue().get(0).compareTo(a.getValue().get(0));
                    if(rezult == 0){
                        rezult = a.getKey().compareTo(b.getKey());
                    }
                    return rezult;
                })
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    entry
                            .getValue()
                            .forEach(e -> {
                                String a = "";
                                int contor = -1;
                                contor ++;
                                if(contor%2==0){
                                    a = "HP";
                                }else{
                                    a = "MP";
                                }
                                System.out.printf("  %s: %d%n", a,e);
                            });
                });


    }
}
