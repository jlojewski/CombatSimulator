package Z1;

import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class GUIManager {

    JFileChooser settingsChooser;

    private static GUIManager GUIManagerInstance;

    private GUIManager() {
        settingsChooser = new JFileChooser();
    }

    public static GUIManager getInstance() {
        if (GUIManagerInstance == null) {
            GUIManagerInstance = new GUIManager();
        }

        return GUIManagerInstance;
    }




    public File setupSettingsChooser() {

        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");
        settingsChooser.setFileFilter(filter);


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


}
