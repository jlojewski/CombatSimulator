package Z1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public final class CombatCalculator {

    private static CombatCalculator calcInstance;

    private CombatCalculator() {
    }

    public static CombatCalculator getInstance() {
        if (calcInstance == null) {
            calcInstance = new CombatCalculator();
        }

        return calcInstance;
    }


    public void performAttackSequence(ArrayList<Combatant> attackSequenceList, int seqHitChance, int seqCritChance) {

        for (int i = 0; i < attackSequenceList.size(); i++) {
            String relayedMessage = null;
            Combatant a = attackSequenceList.get(i);
            relayedMessage = "Combatant " + a.getName() + " acts.";
            IOManager.getInstance().relayString(relayedMessage);
            Combatant b = pickTarget(attackSequenceList, a);
            relayedMessage = "Combatant " + a.getName() + " attacks " + b.getName() + "!";
            IOManager.getInstance().relayString(relayedMessage);

            if (performHitSequence(attackSequenceList, a, seqHitChance, seqCritChance) == true) {
                boolean critHappens = performCritSequence(attackSequenceList, a, seqCritChance);
                performDamageSequence(attackSequenceList, a, b, critHappens);
                killCombatantOrNot(attackSequenceList, b);
            }

        }
    }


    public Combatant pickTarget(ArrayList<Combatant> combatantList, Combatant attacker) {
        UUID attackerId = attacker.getCombatantId();
        var targetList =
                combatantList.stream()
                        .filter(r -> r.getCombatantId() != (attacker.getCombatantId()))
                        .collect(toList());
        int randomPickRoll = ThreadLocalRandom.current().nextInt(0, targetList.size());


        return targetList.get(randomPickRoll);

    }


    public boolean performHitSequence(ArrayList<Combatant> combatantList, Combatant attacker, int hitChance, int critChance) {
        int hitRoll = ThreadLocalRandom.current().nextInt(1, 101);
        String relayedMessage = null;
        if (hitRoll <= hitChance) {
            relayedMessage = "Combatant " + attacker.getName() + " rolls " + hitRoll + " (chance to hit: " + hitChance + "%)." + " It's a hit!";
            IOManager.getInstance().relayString(relayedMessage);


            return true;

        } else {
            relayedMessage = "Combatant " + attacker.getName() + " rolls " + hitRoll + " (chance to hit: " + hitChance + "%)." + " It's a miss.";
            IOManager.getInstance().relayString(relayedMessage);

            return false;
        }
    }

    public boolean performCritSequence(ArrayList<Combatant> combatantList, Combatant attacker, int critChance) {

        int critRoll = ThreadLocalRandom.current().nextInt(1, 101);
        String relayedMessage = null;
        if (critRoll <= critChance) {
            relayedMessage = "Combatant " + attacker.getName() + " rolls " + critRoll + " (chance to crit: " + critChance + "%)." + " Critical strike!";
            IOManager.getInstance().relayString(relayedMessage);

            return true;

        } else {
            relayedMessage = "Combatant " + attacker.getName() + " rolls " + critRoll + " (chance to crit: " + critChance + "%)." + " No crit.";
            IOManager.getInstance().relayString(relayedMessage);

            return false;
        }
    }

    public void performDamageSequence(ArrayList<Combatant> combatantList, Combatant attacker, Combatant defender, boolean critHappens) {
        int atStr = attacker.getStrength();
        int defTgh = defender.getToughness();
        int damageDealt;
        String relayedMessage = null;
        if (critHappens == true) {
            damageDealt = 2 * atStr;
            relayedMessage = "Combatant " + attacker.getName() + " deals " + damageDealt + " critical damage (2 x Strength " + atStr + ") to " + defender.getName() + ".";
            IOManager.getInstance().relayString(relayedMessage);
            defender.setToughness(defTgh - damageDealt);
            relayedMessage = "Combatant " + defender.getName() + "'s Toughness is reduced to " + defender.getToughness() + ".";
            IOManager.getInstance().relayString(relayedMessage);
            ;

        } else {
            damageDealt = atStr;
            relayedMessage = "Combatant " + attacker.getName() + " deals " + damageDealt + " damage to " + defender.getName() + ".";
            IOManager.getInstance().relayString(relayedMessage);
            defender.setToughness((defender.getToughness()) - damageDealt);
            relayedMessage = "Combatant " + defender.getName() + "'s Toughness is reduced to " + defender.getToughness() + ".";
            IOManager.getInstance().relayString(relayedMessage);
            ;
        }
    }


    public void killCombatantOrNot(ArrayList<Combatant> potentialCasualtyList, Combatant defender) {
        String relayedMessage = null;
        if (defender.getToughness() <= 0) {
            relayedMessage = defender.getName() + " died! " + Quip.getInstance().printQuip(Quip.getInstance().quipList);
            IOManager.getInstance().relayString(relayedMessage);

            defender.setDead(true);
            trimCombatantPool(potentialCasualtyList);
        }
    }


    public void trimCombatantPool(ArrayList<Combatant> listToTrim) {
        listToTrim.removeIf(c -> c.isDead == true);
    }


    public boolean checkForVictory(ArrayList<Combatant> menStanding) {
        String relayedMessage;
        if (menStanding.size() <= 1) {
            Combatant victor = menStanding.get(0);
            relayedMessage = victor.getName() + " is the last man standing!";
            IOManager.getInstance().relayString(relayedMessage);
            relayedMessage = victor.getName() + " wins the game!";
            IOManager.getInstance().relayString(relayedMessage);
            victor.promoteCombatant(1, 1);

            return true;

        } else {
            return false;
        }
    }
}