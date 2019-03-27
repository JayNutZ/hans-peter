package pdf;

import java.awt.Color;

public class PdfColors {
    static Color getColor(ColorTypes type) {
        switch (type) {
            case JJTL_BLUE:
                return Color.decode("#0b5394");
            case TEXT_DARK:
                return Color.decode("#333333");
            case TEXT_LIGHT:
                return Color.decode("#999999");
            case GREY_LIGHT:
                return Color.decode("#BBBBBB");
            case LIGHT_BLUE:
                return Color.decode("#d0e0e3");
            case WHITE:
                return Color.decode("#ffffff");
            default:
                return Color.DARK_GRAY;
        }
    }
}

enum ColorTypes {
    TEXT_DARK,
    TEXT_LIGHT,
    JJTL_BLUE,
    LIGHT_BLUE,
    GREY_LIGHT,
    WHITE
}