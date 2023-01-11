package Z1;

import java.util.ArrayList;
import java.util.Scanner;

public class CombatantManager {

    private static CombatantManager CombatantManagerInstance;

    private CombatantManager() {
    }

    public static CombatantManager getInstance() {
        if (CombatantManagerInstance == null) {
            CombatantManagerInstance = new CombatantManager();
        }

        return CombatantManagerInstance;
    }


//    public ArrayList createCombatantsBasedOnInput() {
//        String relayedMessage = null;
//        relayedMessage = "How many combatants will participate?";
//        IOManager.getInstance().relayString(relayedMessage);
//        Scanner statScanner = new Scanner(System.in);
//        Scanner nameScanner = new Scanner(System.in);
//        int statInputNumberOfCombatants;
//        while (true) {
//            while (!statScanner.hasNextInt()) {
//                relayedMessage = "How many combatants will participate?";
//                MessageManager.getInstance().printMessage(relayedMessage);
//                statScanner.nextLine();
//            }
//            statInputNumberOfCombatants = statScanner.nextInt();
//            if (statInputNumberOfCombatants >= 2) {
//                break;
//            }
//            IOManager.getInstance().relayString(relayedMessage);
//            statScanner.nextLine();
//        }
//        ArrayList<Combatant> combatants = new ArrayList<>();
//
//        for (int i = 1; i <= statInputNumberOfCombatants; i++) {
//            relayedMessage = "Insert name for combatant" + i;
//            IOManager.getInstance().relayString(relayedMessage);
//
//            String statInputName = nameScanner.next();
//
//            int statInputStr;
//            relayedMessage = "Insert the Strength value of combatant " + i;
//            IOManager.getInstance().relayString(relayedMessage);
//            while (true) {
//                while (!statScanner.hasNextInt()) {
//                    MessageManager.getInstance().printMessage(relayedMessage);
//                    statScanner.nextLine();
//                }
//                statInputStr = statScanner.nextInt();
//                if (statInputStr >= 0) {
//                    break;
//                }
//                relayedMessage = "Insert the Strength value of combatant " + i;
//                MessageManager.getInstance().printMessage(relayedMessage);
//                statScanner.nextLine();
//            }
//
//            int statInputTough;
//            relayedMessage = "Insert the Toughness value of combatant " + i;
//            IOManager.getInstance().relayString(relayedMessage);
//            while (true) {
//                while (!statScanner.hasNextInt()) {
//                    MessageManager.getInstance().printMessage(relayedMessage);
//                    statScanner.nextLine();
//                }
//                statInputTough = statScanner.nextInt();
//                if (statInputTough >= 1) {
//                    break;
//                }
//                MessageManager.getInstance().printMessage(relayedMessage);
//                System.out.println("Insert the Toughness value of combatant " + i);
//                statScanner.nextLine();
//            }
//            combatants.add(new Combatant(statInputName, statInputStr, statInputTough, i));
//
//        }
//        return combatants;
//    }

    public ArrayList createCombatantsBasedOnSettings(CombatSettings settingsToWorkOn) {
        String relayedMessage = null;
        relayedMessage = "Creating combatants based on settings:";
        IOManager.getInstance().relayString(relayedMessage);
        ArrayList<Combatant> combatants = new ArrayList<Combatant>(settingsToWorkOn.combatants);
        for(Combatant c : combatants) {
            relayedMessage = "Created " + c.getName() + " (Strength " + c.getStrength() + ", Toughness " + c.getToughness() + ")";
            IOManager.getInstance().relayString(relayedMessage);
        }

        return combatants;
    }

    public ArrayList<Combatant> prepareFinalCombatantList(ArrayList<Combatant> initialPool, ArrayList<Combatant> championPool) {
        if (championPool != null) {
            ArrayList<Combatant> mergedCombatantList = new ArrayList<>();
            mergedCombatantList.addAll(initialPool);
            mergedCombatantList.addAll(championPool);

            return mergedCombatantList;
        } else {
            return initialPool;
        }
    }



}
