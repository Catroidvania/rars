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

    // base properties
    // scroll down a bit to see the rest of the available properties used by the metal LAF
    private final String
            NAME = "Name",
            PRIMARY1 = "PrimaryDarkShadow",
            PRIMARY2 = "PrimaryShadow",
            PRIMARY3 = "Primary",
            SECONDARY1 = "SecondaryDarkShadow",
            SECONDARY2 = "SecondaryShadow",
            SECONDARY3 = "Secondary",
            WHITE = "White",
            BLACK = "Black";

    // editor/highlighting properties, matches the names of the settings in the normal RARS menu
    // not used by metal LAF but loaded directly into the RARS builtin colour system
    // tried to do it as non destructively as possible so its a bit hacky
    /*
    // editor settings
    EditorBackground
    EditorForeground
    EditorLineHighlight
    EditorSelection
    EditorCaretColor

    // editor syntax highlighting
    SyntaxComment
    SyntaxStringLiteral
    SyntaxCharacterLiteral
    SyntaxInstruction
    SyntaxAssemblerDirective
    SyntaxRegister
    SyntaxLabel
    SyntaxOperator
    SyntaxInvalid
    SyntaxMacroParameter

    // table rows and text colour
    EvenRowBackground
    EvenRowForeground
    OddRowBackground
    OddRowForeground

    // table highlights and text highlighted text colour
    TextSegmentHighlightBackground
    TextSegmentHighlightForeground

    TextSegmentDelaySlotHighlightBackground
    TextSegmentDelaySlotHighlightForeground

    DataSegmentHighlightBackground
    DataSegmentHighlightForeground

    RegisterHighlightBackground
    RegisterHighlightForeground
     */

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
        return this.name;
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

    // outline around selected button
    @Override
    public ColorUIResource getFocusColor() {
        return this.palette.getOrDefault("FocusColor", this.getPrimary2());
    }

    // background behind the text/data segment windows
    @Override
    public ColorUIResource getDesktopColor() {
        return this.palette.getOrDefault("DesktopColor", this.getPrimary2());
    }

    // main background color for all non menu elements
    @Override
    public ColorUIResource getControl() {
        return this.palette.getOrDefault("Control", this.getSecondary3());
    }

    // inactive tab background, table lines, scrollbar accent
    @Override
    public ColorUIResource getControlShadow() {
        return this.palette.getOrDefault("ControlShadow", this.getSecondary2());
    }

    // dark part of window borders, pane borders, button borders, top left bevel lines
    @Override
    public ColorUIResource getControlDarkShadow() {
        return this.palette.getOrDefault("ControlDarkShadow", this.getSecondary1());
    }

    // tab/button/table header text colour, scrollbar triangles
    @Override
    public ColorUIResource getControlInfo() {
        return this.palette.getOrDefault("ControlInfo", this.getBlack());
    }

    // light part of borders, bottom right bevel lines
    @Override
    public ColorUIResource getControlHighlight() {
        return this.palette.getOrDefault("ControlHighlight", this.getWhite());
    }

    // no clue
    @Override
    public ColorUIResource getControlDisabled() {
        return this.palette.getOrDefault("ControlDisabled", this.getSecondary2());
    }

    // dark colour of the little window icons in the bar of the text/data segment windows and file selector folders
    @Override
    public ColorUIResource getPrimaryControl() {
        return this.palette.getOrDefault("PrimaryControl", this.getPrimary3());
    }

    // scrollbar main colour, highlights on the text/data segment window when selected
    @Override
    public ColorUIResource getPrimaryControlShadow() {
        return this.palette.getOrDefault("PrimaryControlShadow", this.getPrimary2());
    }

    // text/data segment window focused border/highlights, also accent colour for the little icons
    @Override
    public ColorUIResource getPrimaryControlDarkShadow() {
        return this.palette.getOrDefault("PrimaryControlDarkShadow", this.getPrimary1());
    }

    // hover tooltip text colour, minor outlines
    @Override
    public ColorUIResource getPrimaryControlInfo() {
        return this.palette.getOrDefault("PrimaryControlInfo", this.getBlack());
    }

    // icon highlight
    @Override
    public ColorUIResource getPrimaryControlHighlight() {
        return this.palette.getOrDefault("PrimaryControlHighlight", this.getWhite());
    }

    // misc text colour, appears on accelerator slider and file chooser
    @Override
    public ColorUIResource getSystemTextColor() {
        return this.palette.getOrDefault("SystemTextColor", this.getBlack());
    }

    // tab name colours, table header and the default colour for addresses in the text/data segment windows
    @Override
    public ColorUIResource getControlTextColor() {
        return this.palette.getOrDefault("ControlTextColor", this.getControlInfo());
    }

    // no idea
    @Override
    public ColorUIResource getInactiveControlTextColor() {
        return this.palette.getOrDefault("InactiveControlTextColor", this.getControlDisabled());
    }

    // no idea
    @Override
    public ColorUIResource getInactiveSystemTextColor() {
        return this.palette.getOrDefault("InactiveSystemTextColor", this.getSecondary2());
    }

    // messages/io pane and help menu text colour
    @Override
    public ColorUIResource getUserTextColor() {
        return this.palette.getOrDefault("UserTextColor", this.getBlack());
    }

    // line highlight in file select and help menu
    @Override
    public ColorUIResource getTextHighlightColor() {
        return this.palette.getOrDefault("TextHighlightColor", this.getPrimary3());
    }

    // text colour for when the above is highlighted
    @Override
    public ColorUIResource getHighlightedTextColor() {
        return this.palette.getOrDefault("HighlightedTextColor", this.getControlTextColor());
    }

    // message/io pane background, text/data segment window address column background
    @Override
    public ColorUIResource getWindowBackground() {
        return this.palette.getOrDefault("WindowBackground", this.getWhite());
    }

    // selected text/data segment window title bar background
    @Override
    public ColorUIResource getWindowTitleBackground() {
        return this.palette.getOrDefault("WindowTitleBackground", this.getPrimary3());
    }

    // selected text/data segment window title bar text colour
    @Override
    public ColorUIResource getWindowTitleForeground() {
        return this.palette.getOrDefault("WindowTitleForeground", this.getBlack());
    }

    // inactive text/data segment window back bg
    @Override
    public ColorUIResource getWindowTitleInactiveBackground() {
        return this.palette.getOrDefault("WindowTitleInactiveBackground", this.getSecondary3());
    }

    // inactive text/data segment window text colour
    @Override
    public ColorUIResource getWindowTitleInactiveForeground() {
        return this.palette.getOrDefault("WindowTitleInactiveForeground", this.getBlack());
    }

    // background colour of the menubar and toolbar
    @Override
    public ColorUIResource getMenuBackground() {
        return this.palette.getOrDefault("MenuBackground", this.getSecondary3());
    }

    // text colour for menu bar and toolbar
    @Override
    public ColorUIResource getMenuForeground() {
        return this.palette.getOrDefault("MenuForeground", this.getBlack());
    }

    // menu option background when hovered
    @Override
    public ColorUIResource getMenuSelectedBackground() {
        return this.palette.getOrDefault("MenuSelectedBackground", this.getPrimary2());
    }

    // text colour of hovered menu option
    @Override
    public ColorUIResource getMenuSelectedForeground() {
        return this.palette.getOrDefault("MenuSelectedForeground", this.getBlack());
    }

    // disabled menu option text colour
    @Override
    public ColorUIResource getMenuDisabledForeground() {
        return this.palette.getOrDefault("MenuDisabledForeground", this.getSecondary2());
    }

    // menu seperator bottom line colour
    @Override
    public ColorUIResource getSeparatorBackground() {
        return this.palette.getOrDefault("SeparatorBackground", this.getWhite());
    }

    // menu seperator top line colour
    @Override
    public ColorUIResource getSeparatorForeground() {
        return this.palette.getOrDefault("SeparatorForeground", this.getPrimary1());
    }

    // menu option shortcut text colour
    @Override
    public ColorUIResource getAcceleratorForeground() {
        return this.palette.getOrDefault("AcceleratorForeground", this.getPrimary1());
    }

    // hovered menu option shortcut text colour
    @Override
    public ColorUIResource getAcceleratorSelectedForeground() {
        return this.palette.getOrDefault("AcceleratorSelectedForeground", this.getBlack());
    }
}
