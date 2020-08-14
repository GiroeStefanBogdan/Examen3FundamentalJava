package Exam1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberHeroes = Integer.parseInt(scanner.nextLine());

        Map<String,List<Integer>> HeroesMap = new LinkedHashMap<>();

        for (int i = 0; i <numberHeroes; i++){
            String[] data  = scanner.nextLine().split("\\s");
            String heroName = data[0];
            int hp = Integer.parseInt(data[1]);
            int mp = Integer.parseInt(data[2]);
            List<Integer> detailsHeros = new ArrayList<>();
            detailsHeros.add(hp);
            detailsHeros.add(mp);

            HeroesMap.put(heroName,detailsHeros);
        }


        String line = scanner.nextLine();

        while(!line.equals("End")){

            String[] data  = line.split("\\s-\\s");

            if(data[0].equals("CastSpell")){

                String heroName = data[1];
                int mpNeeded = Integer.parseInt(data[2]);
                String spellName = data[3];
                List<Integer> detailsHeros = HeroesMap.get(heroName);

                if(detailsHeros.get(1)>=mpNeeded){
                    int decreaseMp = detailsHeros.get(1)-mpNeeded;
                    detailsHeros.set(1,decreaseMp);
                    HeroesMap.put(heroName,detailsHeros);

                    System.out.printf("%s has successfully cast %s and now has %d MP!%n",heroName,spellName,decreaseMp);

                }else{
                    System.out.printf("%s does not have enough MP to cast %s!%n",heroName,spellName);
                }

            } else if(data[0].equals("TakeDamage")){

                String heroName  = data[1];
                int damage = Integer.parseInt(data[2]);
                String nameAttacker = data[3];

                List<Integer> detailsHeros = HeroesMap.get(heroName);

                int decreaseHp = detailsHeros.get(0)-damage;
                detailsHeros.set(0,decreaseHp);
                HeroesMap.put(heroName,detailsHeros);
                if(decreaseHp>=0){
                    //TakeDamage – {hero name} – {damage} – {attacker} <-- Output
                    System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n",heroName,damage,nameAttacker,decreaseHp);
                }else{
                    HeroesMap.remove(heroName);

                    System.out.printf("%s has been killed by %s!%n",heroName,nameAttacker);
                }

            }else if(data[0].equals("Recharge")){
                String heroName  = data[1];
                int amount = Integer.parseInt(data[2]);
                List<Integer> detailsHeros = HeroesMap.get(heroName);

                int mp = detailsHeros.get(1);
                int currentRecharged = 0;
                for (; mp <200 ; mp++) {
                    if(amount==currentRecharged){
                        break;
                    }
                    currentRecharged++;
                }
                detailsHeros.set(1,mp);
                HeroesMap.put(heroName,detailsHeros);

                System.out.printf("%s recharged for %s MP!%n",heroName,currentRecharged);

            }else if(data[0].equals("Heal")){
                String heroName  = data[1];
                int amount = Integer.parseInt(data[2]);
                List<Integer> detailsHeros = HeroesMap.get(heroName);
                int hl = detailsHeros.get(0);
                int currentRechargedHeal = 0;
                for (; hl <100 ; hl++) {

                    if(amount==currentRechargedHeal){
                        break;
                    }
                    currentRechargedHeal++;
                }
                detailsHeros.set(0,hl);
                HeroesMap.put(heroName,detailsHeros);

                System.out.printf("%s healed for %d HP!%n",heroName,currentRechargedHeal);

            }
            line = scanner.nextLine();
        }

        Map<String,List<Integer>> HeroesDescdendigHp = new LinkedHashMap<>();

        HeroesMap
                .entrySet()
                .stream()
                .sorted((a,b)->a.getValue().get(0).compareTo(b.getValue().get(0))).sorted(((o1, o2) -> o2.getKey().compareTo(o1.getKey()))).forEach(e->{
            System.out.println(e.getKey());
            System.out.printf("HP: %d%n",e.getValue().get(0));
            System.out.printf("MP: %d%n",e.getValue().get(1));

        });





    }
}