package Z1;

import java.util.ArrayList;

public class Main {



    public static void main(String[] args) {
        String relayedMessage = "Welcome to Murderdome 2000!";
        MessageManager.getInstance().printMessage(relayedMessage);

        CombatSettings currentCombatSettings = IOManager.getInstance().importCombatSettings(GUIManager.getInstance().setupSettingsChooser());



//        CombatSettings currentCombatSettings = IOManager.getInstance().importCombatSettings();


        Quip.getInstance().initializeQuipList();

        ArrayList<Combatant> combatantList = CombatantManager.getInstance()
                .prepareFinalCombatantList(
                        CombatantManager.getInstance().createCombatantsBasedOnSettings(currentCombatSettings),
                        IOManager.getInstance().importChampionCombatants(GUIManager.getInstance().askIfChampionsAreUsed())
                        );

//        ArrayList<Combatant> combatantList = CombatantManager.getInstance().createCombatantsBasedOnSettings(currentCombatSettings);


        relayedMessage = "Let's go!";
        IOManager.getInstance().relayString(relayedMessage);
        relayedMessage = "-|------  ------|-";
        IOManager.getInstance().relayString(relayedMessage);

        for (int i = 1; i <= currentCombatSettings.getRounds(); i++) {
            relayedMessage = "Round " + i + " begins.";
            IOManager.getInstance().relayString(relayedMessage);
            Initiative.rollInitiative(combatantList);
            CombatCalculator.getInstance().performAttackSequence(combatantList, currentCombatSettings.getHitChance(), currentCombatSettings.getCritChance());
            if (CombatCalculator.getInstance().checkForVictory(combatantList) == true) {
                IOManager.getInstance().updateLastWinner(combatantList.get(0).getName());
                IOManager.getInstance().saveWinnerAsJsonFile(combatantList.get(0));

                break;
            } else if (i == currentCombatSettings.getRounds()) {
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


