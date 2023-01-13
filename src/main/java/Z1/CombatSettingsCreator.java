package Z1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class CombatSettingsCreator {
    
    //use this class if you want to create a new settings json for whatever reason

    public static void main(String[] args) {

        CombatSettings settingsObject = new CombatSettings();
        settingsObject.rounds = 5;
        settingsObject.hitChance = 70;
        settingsObject.critChance = 25;

        //uncomment and use the code line below if you want to randomize the name of your combatants from a predefined list
        ArrayList<String> optionalRandomNameList = IOManager.getInstance().importNameList();


        Combatant c1 = new Combatant(CombatantManager.getInstance().getRandomName(optionalRandomNameList), 5, 7, UUID.randomUUID());
        Combatant c2 = new Combatant(CombatantManager.getInstance().getRandomName(optionalRandomNameList), 6, 8, UUID.randomUUID());
        Combatant c3 = new Combatant(CombatantManager.getInstance().getRandomName(optionalRandomNameList), 3, 11, UUID.randomUUID());
        Combatant c4 = new Combatant(CombatantManager.getInstance().getRandomName(optionalRandomNameList), 2, 5, UUID.randomUUID());


        ArrayList<Combatant> tempCombatantList = new ArrayList<Combatant>();
        ArrayList<Combatant> optionalCombatantList = new ArrayList<Combatant>();
        tempCombatantList.add(c1);
        tempCombatantList.add(c2);
        tempCombatantList.add(c3);
        tempCombatantList.add(c4);

        settingsObject.combatants = tempCombatantList;
        settingsObject.optionalChampionCombatants = optionalCombatantList;
        ObjectMapper jsonSettingsMapper = new ObjectMapper();

        try {
            jsonSettingsMapper.writeValue(new FileWriter("C:/Java Projects/SymulatorOngoing/combat_settings.json"), settingsObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
