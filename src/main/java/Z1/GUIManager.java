package Z1;

import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class GUIManager {
    public GUIManager() {
        settingsChooser = new JFileChooser();
    }

    JFileChooser settingsChooser;



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
