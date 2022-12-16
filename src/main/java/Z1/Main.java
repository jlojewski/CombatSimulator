package Z1;

import java.util.ArrayList;

public class Main {

    public static CombatSettings currentSettings = null;
    static final int ROUNDS = 5;
    static final int HIT_CHANCE = 70;
    static final int CRIT_CHANCE = 25;


    public static void main(String[] args) {
        currentSettings = IOManager.getInstance().importCombatSettings();
        String relayedMessage = "Welcome to Murderdome 2000!";
        MessageManager.getInstance().printMessage(relayedMessage);

        Quip.getInstance().initializeQuipList();
        IOManager.getInstance().initializeOutputArray();

        ArrayList<Combatant> combatantList = CombatantManager.createCombatants();

        relayedMessage = "Let's go!";
        IOManager.getInstance().relayString(relayedMessage);
        relayedMessage = "-|------  ------|-";
        IOManager.getInstance().relayString(relayedMessage);

        for (int i = 1; i <= ROUNDS; i++) {
            relayedMessage = "Round " + i + " begins.";
            IOManager.getInstance().relayString(relayedMessage);
            Initiative.rollInitiative(combatantList);
            CombatCalculator.getInstance().performAttackSequence(combatantList);
            if (CombatCalculator.getInstance().checkForVictory(combatantList) == true) {
                IOManager.getInstance().saveWinner(combatantList.get(0).getName());

                break;
            } else if (i == ROUNDS) {
                relayedMessage = "Out of time! No victor has emerged.";
                IOManager.getInstance().relayString(relayedMessage);
                relayedMessage = "It's a draw.";
                IOManager.getInstance().relayString(relayedMessage);

                break;
            }
        }
        relayedMessage = "Thought for the day:";
        IOManager.getInstance().relayString(relayedMessage);
        relayedMessage = Quip.getInstance().printThought(Quip.getInstance().getThoughtList());

        IOManager.getInstance().relayString(relayedMessage);
        IOManager.getInstance().convertToLogFile();

    }

}


