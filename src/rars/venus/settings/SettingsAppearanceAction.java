package rars.venus.settings;

import rars.Globals;
import rars.venus.CustomMetalTheme;
import rars.venus.GuiAction;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
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
        JFileChooser chooser = new JFileChooser(Globals.getGui().getEditor().getCurrentOpenDirectory());
        chooser.setFileFilter(new FileNameExtensionFilter("Java property files", "properties"));
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
            CustomMetalTheme cmt = new CustomMetalTheme(theme);
            MetalLookAndFeel.setCurrentTheme(cmt);
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            cmt.updateEditorHighlights();
            SwingUtilities.updateComponentTreeUI(Globals.getGui());
            return true;
        } catch (Exception ex) {
            System.err.println("Could not open file: " + ex.getMessage());
        }
        return false;
    }
}
