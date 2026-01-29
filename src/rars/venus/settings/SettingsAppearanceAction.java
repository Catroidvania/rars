package rars.venus.settings;

import rars.Globals;
import rars.venus.GuiAction;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SettingsAppearanceAction extends GuiAction {

    private JDialog appearanceDialog;

    public SettingsAppearanceAction(String name, Icon icon, String descrip,
                                    Integer mnemonic, KeyStroke accel) {
        super(name, icon, descrip, mnemonic, accel);
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter(".properties", "properties"));
        if (chooser.showOpenDialog(Globals.getGui()) == JFileChooser.APPROVE_OPTION) {
            String fp = chooser.getSelectedFile().getAbsolutePath();
            try {
                InputStream is = new FileInputStream(chooser.getSelectedFile());
                if (setTheme(is)) {
                    Globals.getSettings().setThemePropertiesFile(fp);
                }
            } catch (Exception ex) {
                System.err.println("Could not open file: " + ex.getMessage());
            }
        }
    }

    public static boolean setTheme(InputStream is) {
        try {
            Properties theme = new Properties();
            theme.load(is);
            for (String key : theme.stringPropertyNames()) {
                try {
                    UIManager.put(key, Color.decode(theme.getProperty(key)));
                } catch (Exception ex) {
                    System.err.println("Not a colour: " + ex.getMessage());
                }
            }
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            SwingUtilities.updateComponentTreeUI(Globals.getGui());
            return true;
        } catch (Exception ex) {
            System.err.println("Could not open file: " + ex.getMessage());
        }
        return false;
    }
}
