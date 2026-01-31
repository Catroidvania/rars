package rars.venus;

import rars.Globals;
import rars.venus.editors.jeditsyntax.SyntaxStyle;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CustomMetalTheme extends DefaultMetalTheme {

    private final String
            NAME = "Name",
            PRIMARY1 = "PrimaryDarkShadow",
            PRIMARY2 = "PrimaryShadow",
            PRIMARY3 = "Primary",
            SECONDARY1 = "SecondaryDarkShadow",
            SECONDARY2 = "SecondaryShadow",
            SECONDARY3 = "Secondary",
            WHITE = "White",
            BLACK = "Black";/*,
            FOCUSCOlOUR = "focus",
            DESKTOPCOLOUR = "desktop",
            WINDOWTITLEBACKGROUND = "activeCaption",
            WINDOWTITLEFOREGROUND = "activeCaptionText",
            WINDOWTITLEBORDER = "activeCaptionBorder",
            WINDOWTITLEINACTIVEBACKGROUND = "inactiveCaption",
            WINDOWTITLEINACTIVEFOREGROUND = "inactiveCaptionText",
            WINDOWTITLEINACTIVEBORDER = "inactiveCaptionBorder",
            WINDOWBACKGROUND = "window",
            WINDOWBORDER = "windowBorder",
            WINDOWTEXT = "windowText",
            MENUBACKGROUND = "menu",
            MENUFOREGROUND = "menuText",
            USERTEXTCOLOUR = "textText",
            TEXTHIGHLIGHTCOLOUR = "textHighlight",
            HIGHLIGHTEDTEXTCOLOUR = "textHighlightText",
            INACTIVESYSTEMTEXTCOLOUR = "textInactiveText",
            CONTROL = "control",
            CONTROLTEXTCOLOUR = "controlText",
            CONTROLHIGHLIGHT = "controlHighlight",
            CONTROLLTHIGHLIGHT = "controlLtHighlight",
            CONTROLSHADOW = "controlShadow",
            CONTROLDARKSHADOW = "controlDkShadow",
            SCROLLBAR = "scrollbar",
            PRIMARYCONTROL = "info",
            PRIMARYCONTROLINFO = "infoText"
    ;*/

    private String name;
    private HashMap<String, ColorUIResource> palette;

    public CustomMetalTheme(Properties props) {
        super();
        this.palette = new HashMap<>();
        initDefaultsSteel();

        if (props != null && !props.isEmpty()) {
            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                if (entry.getKey().toString().equals(NAME)) {
                    this.name = entry.getValue().toString();
                }
                try {
                    ColorUIResource color = new ColorUIResource(Integer.decode(entry.getValue().toString()));
                    this.palette.put(entry.getKey().toString(), color);
                    UIManager.put(entry.getKey(), color);
                    //System.out.println(entry.getKey().toString() + ":" + entry.getValue().toString());
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

    public void updateBuiltinColours() {
        for (Map.Entry<String, ColorUIResource> entry : this.palette.entrySet()) {
            Globals.getSettings().setColorSettingByKey(entry.getKey(), entry.getValue());
            SyntaxStyle style = Globals.getSettings().getEditorSyntaxStyleByKey(entry.getKey());
            if (style != null) {
                Globals.getSettings().setEditorSyntaxStyleByKey(entry.getKey(), new SyntaxStyle(entry.getValue(), style.isItalic(), style.isBold()));
            }
        }
    }

    private void initDefaultsOcean() {
        // ocean theme defaults
        this.name = "Ocean";
        this.palette.put(PRIMARY1, new ColorUIResource(0x6382BF));
        this.palette.put(PRIMARY2, new ColorUIResource(0xA3B8CC));
        this.palette.put(PRIMARY3, new ColorUIResource(0xB8CFE5));
        this.palette.put(SECONDARY1, new ColorUIResource(0x7A8A99));
        this.palette.put(SECONDARY2, new ColorUIResource(0xB8CFE5));
        this.palette.put(SECONDARY3, new ColorUIResource(0xEEEEEE));
        this.palette.put(WHITE, new ColorUIResource(0xFFFFFF));
        this.palette.put(BLACK, new ColorUIResource(0x333333));

        // must be overwritten manually in theme if Ocean default is used
        this.palette.put("DesktopColor", new ColorUIResource(0xFFFFFF));
        this.palette.put("InactiveControlTextColor", new ColorUIResource(0x999999));
        this.palette.put("ControlTextColor", new ColorUIResource(0x333333));
        this.palette.put("MenuDisabledForeground", new ColorUIResource(0x999999));
    }

    private void initDefaultsSteel() {
        // metal defaults
        this.name = "Steel";
        this.palette.put(PRIMARY1, new ColorUIResource(0x666699));
        this.palette.put(PRIMARY2, new ColorUIResource(0x9999cc));
        this.palette.put(PRIMARY3, new ColorUIResource(0xccccff));
        this.palette.put(SECONDARY1, new ColorUIResource(0x666666));
        this.palette.put(SECONDARY2, new ColorUIResource(0x999999));
        this.palette.put(SECONDARY3, new ColorUIResource(0xcccccc));
        this.palette.put(WHITE, new ColorUIResource(0xFFFFFF));
        this.palette.put(BLACK, new ColorUIResource(0x000000));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected ColorUIResource getPrimary1() {
        return this.palette.get(PRIMARY1);
    }

    @Override
    protected ColorUIResource getPrimary2() {
        return this.palette.get(PRIMARY2);
    }

    @Override
    protected ColorUIResource getPrimary3() {
        return this.palette.get(PRIMARY3);
    }

    @Override
    protected ColorUIResource getSecondary1() {
        return this.palette.get(SECONDARY1);
    }

    @Override
    protected ColorUIResource getSecondary2() {
        return this.palette.get(SECONDARY2);
    }

    @Override
    protected ColorUIResource getSecondary3() {
        return this.palette.get(SECONDARY3);
    }

    @Override
    protected ColorUIResource getWhite() {
        return this.palette.get(WHITE);
    }

    @Override
    protected ColorUIResource getBlack() {
        return this.palette.get(BLACK);
    }

    // not all values are used by RARS i think
    // might be visible in menus somewhere?
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
        return this.palette.getOrDefault("SeparatorForeground", this.getPrimary1());
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
