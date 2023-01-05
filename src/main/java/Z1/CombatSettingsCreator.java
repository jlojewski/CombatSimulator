package Z1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CombatSettingsCreator {
    
    //use this if you want to create a new settings json for whatever reason

    public static void main(String[] args) {

        CombatSettings settingsObject = new CombatSettings();
        settingsObject.rounds = 5;
        settingsObject.hitChance = 70;
        settingsObject.critChance = 25;

        Combatant c1 = new Combatant("Jan", 5, 7, 1);
        Combatant c2 = new Combatant("Zdzich", 6, 8, 2);
        Combatant c3 = new Combatant("Siwy", 3, 11, 3);
        Combatant c4 = new Combatant("Bolo", 2, 5, 4);


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
