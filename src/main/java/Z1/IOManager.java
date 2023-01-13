package Z1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class IOManager {
    ArrayList<String> IOManagerOutput;

    public void setIOManagerOutput(ArrayList<String> IOManagerOutput) {
        this.IOManagerOutput = IOManagerOutput;
    }

    private static IOManager IOManagerInstance;

    private IOManager() {
        IOManagerOutput=new ArrayList<>();
    }

    public static IOManager getInstance() {
        if (IOManagerInstance == null) {
            IOManagerInstance = new IOManager();
        }

        return IOManagerInstance;
    }




    public void collectOutputToArray(String textToCollect) {
        IOManagerOutput.add(textToCollect);
    }

    public void convertToLogFile() {
        try (FileWriter fwForLog = new FileWriter("combat_log.txt", false);
             BufferedWriter bwForLog = new BufferedWriter(fwForLog);
             PrintWriter outForLog = new PrintWriter(bwForLog)) {
            for (var s : IOManagerOutput) {
                outForLog.println(s);
            }
            outForLog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void relayString(String targetString) {
        MessageManager.getInstance().printMessage(targetString);
        collectOutputToArray(targetString);
    }

    public void updateLastWinner(String winner) {
        PrintWriter winnerWriter = null;
        try {
            winnerWriter = new PrintWriter("last_winner.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        winnerWriter.println("The last recorded winner was " + winner);
        winnerWriter.close();
    }



    public void saveWinnerAsJsonFile(Combatant winner) {
        ObjectMapper jsonWinnerMapper = new ObjectMapper();

        try {
            Files.createDirectories(Paths.get("champions"));
            String winnerFileName = winner.getName();
            File savedWinner = new File("champions", winnerFileName + ".json");

            for (int num = 1; savedWinner.exists(); num++) {
            savedWinner = new File("champions", winnerFileName + num + ".json");
            }
            jsonWinnerMapper.writeValue(savedWinner, winner);

//            String winnerFileName = winner.getName();
//            jsonWinnerMapper.writeValue(new FileWriter("C:/Java Projects/SymulatorOngoing/" + winnerFileName + ".json"), winner);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public ArrayList<String> importQuipList() {
        ArrayList<String> listOfQuips = null;
        try {
            BufferedReader importReader = new BufferedReader(new FileReader("C:/Java Projects/SymulatorOngoing/commentary.txt"));
            listOfQuips = new ArrayList<>();
            String quipLine = importReader.readLine();
            while (quipLine != null) {
                listOfQuips.add(quipLine);
                quipLine = importReader.readLine();
            }
            importReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfQuips;

    }

    public ArrayList<String> importThoughtList() {
        ArrayList<String> listOfThoughts = null;
        try {
            BufferedReader importReader = new BufferedReader(new FileReader("C:/Java Projects/SymulatorOngoing/final_thought.txt"));
            listOfThoughts = new ArrayList<>();
            String thoughtLine = importReader.readLine();
            while (thoughtLine != null) {
                listOfThoughts.add(thoughtLine);
                thoughtLine = importReader.readLine();
            }
            importReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfThoughts;

    }

//    public CombatSettings importCombatSettings() {
//        CombatSettings settings = null;
//        try {
//            ObjectMapper settingsImportMapper = new ObjectMapper();
//            settings = settingsImportMapper.readValue(Path.of("combat_settings.json").toFile(), CombatSettings.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return settings;
//    }

    public CombatSettings importCombatSettings(File settingsToImport) {
        CombatSettings settings = null;
        try {
            ObjectMapper settingsImportMapper = new ObjectMapper();
            settings = settingsImportMapper.readValue(settingsToImport , CombatSettings.class);
        } catch (IOException e) {
            GUIManager.getInstance().setupSettingsChooser();
        }
        return settings;
    }


//    public ArrayList<Combatant> importChampionCombatants(File[] championsToImport) {
//            ArrayList<File> championsToConvert = new ArrayList(Arrays.asList(championsToImport));
//            ArrayList<Combatant> importedChampionList = null;
//            ObjectMapper championImportMapper = new ObjectMapper();
//            for (File f : championsToConvert) {
//                try {
//                    Combatant tba = championImportMapper.readValue(f, Combatant.class);
//                    importedChampionList.add(tba);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return importedChampionList;
//
//        }

    public ArrayList<Combatant> importChampionCombatants(boolean importOrNot) {
        if (importOrNot == true) {
            String relayedMessage = null;
            ArrayList<File> championsToConvert = new ArrayList(Arrays.asList(GUIManager.getInstance().setupChampionChooser()));
            ArrayList<Combatant> importedChampionList = new ArrayList<Combatant>();
            ObjectMapper championImportMapper = new ObjectMapper();
            for (File f : championsToConvert) {
                try {
                    Combatant tba = championImportMapper.readValue(f, Combatant.class);
                    relayedMessage = "Imported " + tba.getName() + " (Strength " + tba.getStrength() + ", Toughness " + tba.getToughness() + ")";
                    IOManager.getInstance().relayString(relayedMessage);
                    importedChampionList.add(tba);
                } catch (IOException e) {
                    GUIManager.getInstance().setupChampionChooser();
                }
            }
            return importedChampionList;

        } else {
            return null;
        }

    }


    }





