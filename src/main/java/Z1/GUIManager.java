package Z1;

import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

public class GUIManager {

    JFileChooser settingsChooser;
    JFileChooser championChooser;


    private static GUIManager GUIManagerInstance;

    private GUIManager() {
        settingsChooser = new JFileChooser();
        championChooser = new JFileChooser();
    }

    public static GUIManager getInstance() {
        if (GUIManagerInstance == null) {
            GUIManagerInstance = new GUIManager();
        }

        return GUIManagerInstance;
    }


    public File setupSettingsChooser() {

        String programPath = System.getProperty("user.dir");
        settingsChooser.setCurrentDirectory(new File(programPath));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");
        settingsChooser.setFileFilter(filter);
        settingsChooser.setDialogTitle("Choose a .json file containing combat settings to load");


        int returnVal = settingsChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File settingsFile = settingsChooser.getSelectedFile();
            String fileExt = FilenameUtils.getExtension(settingsFile.toString());

            if (!fileExt.equals("json")) {
                return setupSettingsChooser();

            } else {
                return settingsFile;
            }


        } else {
            System.exit(0);
            return null;
        }

    }

    public File[] setupChampionChooser() {

        String programPath = System.getProperty("user.dir");
        championChooser.setMultiSelectionEnabled(true);
        championChooser.setCurrentDirectory(new File(programPath));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");
        championChooser.setFileFilter(filter);
        championChooser.setDialogTitle("Choose all .json files containing additional combatants");

        int returnVal = championChooser.showOpenDialog(null);
        if (returnVal == championChooser.APPROVE_OPTION) {
            File[] chosenChampions = championChooser.getSelectedFiles();

            for (var f : chosenChampions) {
                String fileExt = FilenameUtils.getExtension(f.toString());

                if (!fileExt.equals("json")) {
                    return setupChampionChooser();

                } else {
                    break;
                }
            }
            return chosenChampions;
        } else {
            System.exit(0);
            return null;
        }
    }

//    public File[] askIfChampionsAreUsed() {
//        String[] options = {"Yes", "No"};
//        int returnVal = JOptionPane.showOptionDialog(null, "Do you want to import any of the previous winners " +
//                "as additional combatants?", "Importing champions", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
//                null, options, options[1]);
//        switch (returnVal) {
//            case JOptionPane.YES_OPTION:
//                return setupChampionChooser();
//
//            default:
//                return null;
//        }
//
//
//    }

    public boolean askIfChampionsAreUsed() {
        String[] options = {"Yes", "No"};
        int returnVal = JOptionPane.showOptionDialog(null, "Do you want to import any of the previous winners " +
                        "as additional combatants?", "Importing champions", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);
        switch (returnVal) {
            case JOptionPane.YES_OPTION:
                return true;

            default:
                return false;
        }


    }


}
