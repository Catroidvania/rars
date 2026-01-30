package rars.venus;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CustomMetalTheme extends DefaultMetalTheme {

    private String name;
    private HashMap<String, ColorUIResource> palette;

    public CustomMetalTheme(Properties props) {
        super();
        this.palette = new HashMap<>();
        initDefaults();

        if (props != null) {
            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                if (entry.getKey().toString().equals("Name")) {
                    this.name = entry.getValue().toString();
                }
                try {
                    this.palette.put(entry.getKey().toString(), new ColorUIResource(Integer.decode(entry.getValue().toString())));
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

    private void initDefaults() {
        // metal defaults
        /*
        this.name = "Steel";
        this.palette.put("Primary1", new ColorUIResource(0x666699));
        this.palette.put("Primary2", new ColorUIResource(0x9999cc));
        this.palette.put("Primary3", new ColorUIResource(0xccccff));
        this.palette.put("Secondary1", new ColorUIResource(0x666666));
        this.palette.put("Secondary2", new ColorUIResource(0x999999));
        this.palette.put("Secondary3", new ColorUIResource(0xcccccc));
        this.palette.put("White", new ColorUIResource(0xFFFFFF));
        this.palette.put("Black", new ColorUIResource(0x000000));
         */

        // ocean theme defaults
        this.name = "Ocean";
        this.palette.put("Primary1", new ColorUIResource(0x6382BF));
        this.palette.put("Primary2", new ColorUIResource(0xA3B8CC));
        this.palette.put("Primary3", new ColorUIResource(0xB8CFE5));
        this.palette.put("Secondary1", new ColorUIResource(0x7A8A99));
        this.palette.put("Secondary2", new ColorUIResource(0xB8CFE5));
        this.palette.put("Secondary3", new ColorUIResource(0xEEEEEE));
        this.palette.put("White", new ColorUIResource(0xFFFFFF));
        this.palette.put("Black", new ColorUIResource(0x333333));
        this.palette.put("DesktopColor", new ColorUIResource(0xFFFFFF));
        this.palette.put("InactiveControlTextColor", new ColorUIResource(0x999999));
        this.palette.put("ControlTextColor", new ColorUIResource(0x333333));
        this.palette.put("MenuDisabledForeground", new ColorUIResource(0x999999));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected ColorUIResource getPrimary1() {
        return this.palette.get("Primary1");
    }

    @Override
    protected ColorUIResource getPrimary2() {
        return this.palette.get("Primary2");
    }

    @Override
    protected ColorUIResource getPrimary3() {
        return this.palette.get("Primary3");
    }

    @Override
    protected ColorUIResource getSecondary1() {
        return this.palette.get("Secondary1");
    }

    @Override
    protected ColorUIResource getSecondary2() {
        return this.palette.get("Secondary2");
    }

    @Override
    protected ColorUIResource getSecondary3() {
        return this.palette.get("Secondary3");
    }

    @Override
    protected ColorUIResource getWhite() {
        return this.palette.get("White");
    }

    @Override
    protected ColorUIResource getBlack() {
        return this.palette.get("Black");
    }

    @Override
    public ColorUIResource getFocusColor() {
        return this.palette.getOrDefault("FocusColor", this.getPrimary2());
    }

    @Override
    public ColorUIResource getDesktopColor() {
        return this.palette.getOrDefault("DesktopColor", this.getPrimary2());
    }

    @Override
    public ColorUIResource getControl() {
        return this.palette.getOrDefault("Control", this.getSecondary3());
    }

    @Override
    public ColorUIResource getControlShadow() {
        return this.palette.getOrDefault("ControlShadow", this.getSecondary2());
    }

    @Override
    public ColorUIResource getControlDarkShadow() {
        return this.palette.getOrDefault("ControlDarkShadow", this.getSecondary1());
    }

    @Override
    public ColorUIResource getControlInfo() {
        return this.palette.getOrDefault("ControlInfo", this.getBlack());
    }

    @Override
    public ColorUIResource getControlHighlight() {
        return this.palette.getOrDefault("ControlHighlight", this.getWhite());
    }

    @Override
    public ColorUIResource getControlDisabled() {
        return this.palette.getOrDefault("ControlDisabled", this.getSecondary2());
    }

    @Override
    public ColorUIResource getPrimaryControl() {
        return this.palette.getOrDefault("PrimaryControl", this.getPrimary3());
    }

    @Override
    public ColorUIResource getPrimaryControlShadow() {
        return this.palette.getOrDefault("PrimaryControlShadow", this.getPrimary2());
    }

    @Override
    public ColorUIResource getPrimaryControlDarkShadow() {
        return this.palette.getOrDefault("PrimaryControlDarkShadow", this.getPrimary1());
    }

    @Override
    public ColorUIResource getPrimaryControlInfo() {
        return this.palette.getOrDefault("PrimaryControlInfo", this.getBlack());
    }

    @Override
    public ColorUIResource getPrimaryControlHighlight() {
        return this.palette.getOrDefault("PrimaryControlHighlight", this.getWhite());
    }

    @Override
    public ColorUIResource getSystemTextColor() {
        return this.palette.getOrDefault("SystemTextColor", this.getBlack());
    }

    @Override
    public ColorUIResource getControlTextColor() {
        return this.palette.getOrDefault("ControlTextColor", this.getControlInfo());
    }

    @Override
    public ColorUIResource getInactiveControlTextColor() {
        return this.palette.getOrDefault("InactiveControlTextColor", this.getControlDisabled());
    }

    @Override
    public ColorUIResource getInactiveSystemTextColor() {
        return this.palette.getOrDefault("InactiveSystemTextColor", this.getSecondary2());
    }

    @Override
    public ColorUIResource getUserTextColor() {
        return this.palette.getOrDefault("UserTextColor", this.getBlack());
    }

    @Override
    public ColorUIResource getTextHighlightColor() {
        return this.palette.getOrDefault("TextHighlightColor", this.getPrimary3());
    }

    @Override
    public ColorUIResource getHighlightedTextColor() {
        return this.palette.getOrDefault("HighlightedTextColor", this.getControlTextColor());
    }

    @Override
    public ColorUIResource getWindowBackground() {
        return this.palette.getOrDefault("WindowBackground", this.getWhite());
    }

    @Override
    public ColorUIResource getWindowTitleBackground() {
        return this.palette.getOrDefault("WindowTitleBackground", this.getPrimary3());
    }

    @Override
    public ColorUIResource getWindowTitleForeground() {
        return this.palette.getOrDefault("WindowTitleForeground", this.getBlack());
    }

    @Override
    public ColorUIResource getWindowTitleInactiveBackground() {
        return this.palette.getOrDefault("WindowTitleInactiveBackground", this.getSecondary3());
    }

    @Override
    public ColorUIResource getWindowTitleInactiveForeground() {
        return this.palette.getOrDefault("WindowTitleInactiveForeground", this.getBlack());
    }

    @Override
    public ColorUIResource getMenuBackground() {
        return this.palette.getOrDefault("MenuBackground", this.getSecondary3());
    }

    @Override
    public ColorUIResource getMenuForeground() {
        return this.palette.getOrDefault("MenuForeground", this.getBlack());
    }

    @Override
    public ColorUIResource getMenuSelectedBackground() {
        return this.palette.getOrDefault("MenuSelectedBackground", this.getPrimary2());
    }

    @Override
    public ColorUIResource getMenuSelectedForeground() {
        return this.palette.getOrDefault("MenuSelectedForeground", this.getBlack());
    }

    @Override
    public ColorUIResource getMenuDisabledForeground() {
        return this.palette.getOrDefault("MenuDisabledForeground", this.getSecondary2());
    }

    @Override
    public ColorUIResource getSeparatorBackground() {
        return this.palette.getOrDefault("SeparatorBackground", this.getWhite());
    }

    @Override
    public ColorUIResource getSeparatorForeground() {
        return this.palette.getOrDefault("SeparatorForeground", this.getPrimary2());
    }

    @Override
    public ColorUIResource getAcceleratorForeground() {
        return this.palette.getOrDefault("AcceleratorForeground", this.getPrimary1());
    }

    @Override
    public ColorUIResource getAcceleratorSelectedForeground() {
        return this.palette.getOrDefault("AcceleratorSelectedForeground", this.getBlack());
    }
}
