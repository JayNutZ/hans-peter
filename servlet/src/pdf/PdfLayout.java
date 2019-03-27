package pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfLayout {
    private PdfInfo info;
    private PDDocument document;
    private final String path = "/Users/timhuhndorf/Desktop/jjtl/hans-peter/servlet/src/res";

    public PdfLayout(PdfInfo info, PDDocument document) {
        this.info = info;
        this.document = document;
    }

    public PDPageContentStream buildLayout(PDPageContentStream pcs) {
        int y = this.getHeight() - 517;

        pcs = this.addRect(new PdfFormat(20, 100, 300, 555), PdfColors.getColor(ColorTypes.LIGHT_BLUE), pcs);
        pcs = this.addImage(new PdfFormat(157, y, 497, 418), path + "/cover.jpg", pcs);
        pcs = this.addImage(new PdfFormat(500, y + 20, 46, 46), path + "/logo.png", pcs);
        pcs = this.addRect(new PdfFormat(140, 300, 70, 140), PdfColors.getColor(ColorTypes.JJTL_BLUE), pcs);

        pcs = this.addText("Bequem.", 12, PdfColors.getColor(ColorTypes.WHITE), true, 150, 315, pcs);
        pcs = this.addText("Zusammengefasst.", 12, PdfColors.getColor(ColorTypes.WHITE), true, 150, 331, pcs);
        pcs = this.addText("Meine Daten.", 12, PdfColors.getColor(ColorTypes.WHITE), true, 150, 347, pcs);

        pcs = this.buildContact(pcs);
        pcs = this.buildDetails(pcs);
        pcs = this.buildFooter(pcs);

        return pcs;
    }

    public PDPageContentStream buildContact(PDPageContentStream pcs) {
        try {
            int top = this.getHeight();

            pcs = this.addText(this.info.getUser().getGender(), 8, PdfColors.getColor(ColorTypes.TEXT_DARK), false, 20,top - 80, pcs);

            pcs = this.addText(this.info.getUser().getFirstname(), 10, PdfColors.getColor(ColorTypes.TEXT_DARK), false, 20, top - 100, pcs);
            pcs = this.addText(this.info.getUser().getLastname(), 10, PdfColors.getColor(ColorTypes.TEXT_DARK), false, 20, top - 110, pcs);

            pcs = this.addText(this.info.getUser().getAddress(), 8, PdfColors.getColor(ColorTypes.TEXT_DARK), false, 20,top - 128, pcs);
            pcs = this.addText(this.info.getUser().getZipcode() + " " + this.info.getUser().getCity(), 8, PdfColors.getColor(ColorTypes.TEXT_DARK), false, 20,top - 136, pcs);

            pcs = this.addText("+" + this.info.getUser().getPhone(), 8, PdfColors.getColor(ColorTypes.TEXT_DARK), false, 20,top - 150, pcs);
            pcs = this.addText(this.info.getUser().getEmail(), 8, PdfColors.getColor(ColorTypes.TEXT_DARK), false, 20,top - 158, pcs);

        } catch (Exception e) {
            System.out.println("error on contact: " + e.getMessage());
        }

        return pcs;
    }

    public PDPageContentStream buildFooter(PDPageContentStream pcs) {
        try {
            Color color = PdfColors.getColor(ColorTypes.TEXT_LIGHT);
            int top = this.getHeight();

            pcs = this.addText("JJTL Group GmbH", 10, color, false, 45, 80, pcs);
            pcs = this.addText("Dratelnstraße 26", 8, color, false, 50, 65, pcs);
            pcs = this.addText("21109 Hamburg", 8, color, false, 50, 55, pcs);
            pcs = this.addText("jjtl.group", 8, color, false, 60, 45, pcs);

            pcs = this.addText("echt Hamburg", 10, color, false, 260, 80, pcs);
            pcs = this.addText("Am Kontor 11", 8, color, false, 268, 65, pcs);
            pcs = this.addText("20321 Hamburg", 8, color, false, 262, 55, pcs);
            pcs = this.addText("ehh.de", 8, color, false, 278, 45, pcs);

            pcs = this.addText("Stand", 10, color, false, 490, 80, pcs);
            pcs = this.addText(this.formatDate(new Date()), 8, color, false, 485, 65, pcs);

        } catch (Exception e) {
            System.out.println("error on footer: " + e.getMessage());
        }

        return pcs;
    }

    public PDPageContentStream buildDetails(PDPageContentStream pcs) {
        try {
            String[][] row1 = {
                    {"Gebuchte Events:  ", this.info.getUser().getEventsBooked().toString()},
                    {"Erworbene Produkte:  ", this.info.getUser().getSalesCount().toString()},
                    {null},
                    {"Erzielter Umsatz:", String.format("%.2f", this.info.getUser().getSalesValue()) + "€"},
            };

            String[][] row2 = {
                    {"Aktiv seit:", this.formatDate(this.info.getUser().getActiveSince())},
                    {"Zuletzt aktiv:", this.formatDate(this.info.getUser().getActiveLast())},
            };

            String[][] row3 = {
                    {"Hierbei handelt es sich um sämtliche"},
                    {"erhobenen Daten aus ihrem Kundenkonto."},
                    {null},
                    {"Falls sie das Löschen ihrer Daten"},
                    {"beantragen wollen, schreiben sie uns eine"},
                    {"E-Mail an:"},
                    {null},
                    {"datenschutz@eHH.de"},
                    {null},
                    {"Im Namen von echt Hamburg,"},
                    {null},
                    {"Jakob Karnatz und Tim Huhndorf,", ""},
                    {null},
                    {"JJTL Group"},
                    {"Sicherheitsbeauftragte"}
            };

            pcs = this.buildDetail1(row1, pcs);
            pcs = this.buildDetail2(row2, pcs);
            pcs = this.buildDetail3(row3, pcs);

        } catch (Exception e) {
            System.out.println("error on details: " + e.getMessage());
        }

        return pcs;
    }

    private PDPageContentStream buildDetail1(String[][] rows, PDPageContentStream pcs) {
        int top = 280;
        int left = 30;
        int font_size = 8;
        int line_height = 10;

        for (int i = 0; i < rows.length; i++) {
            int top_now = top - (line_height * i);
            String[] row = rows[i];

            pcs = this.buildRow(row, left, top_now, font_size, pcs);
        }

        return pcs;
    }

    private PDPageContentStream buildDetail2(String[][] rows, PDPageContentStream pcs) {
        int top = 280;
        int left = 191;
        int font_size = 8;
        int line_height = 10;

        for (int i = 0; i < rows.length; i++) {
            int top_now = top - (line_height * i);
            String[] row = rows[i];

            pcs = this.buildRow(row, left, top_now, font_size, pcs);
        }

        return pcs;
    }

    private PDPageContentStream buildDetail3(String[][] rows, PDPageContentStream pcs) {
        int top = 280;
        int left = 382;
        int font_size = 8;
        int line_height = 10;

        for (int i = 0; i < rows.length; i++) {
            int top_now = top - (line_height * i);
            String[] row = rows[i];

            pcs = this.buildRow(row, left, top_now, font_size, pcs);
        }

        return pcs;
    }

    private PDPageContentStream buildRow(String[] row, int left, int top_now, int font_size, PDPageContentStream pcs) {
        if (row[0] != null) {
            if (row.length == 1) {
                pcs = this.addText(row[0], font_size, PdfColors.getColor(ColorTypes.TEXT_DARK), false, left, top_now, pcs);
            } else {
                pcs = this.addText(row[0], font_size, PdfColors.getColor(ColorTypes.TEXT_DARK), true, left, top_now, pcs);

                int from_left = (row[0].length() * 4) + 4;

                pcs = this.addText(row[1], font_size, PdfColors.getColor(ColorTypes.TEXT_DARK), false, left + from_left, top_now, pcs);
            }
        }

        return pcs;
    }

    private PDPageContentStream addRect(PdfFormat format, Color background, PDPageContentStream pcs) {
        try {
            pcs.setNonStrokingColor(background);
            pcs.addRect(format.getX(), format.getY(), format.getWidth(), format.getHeight());
            pcs.fill();
        } catch (Exception e) {
            System.out.println("error on rect: " + e.getMessage());
        }

        return pcs;
    }

    private PDPageContentStream addImage(PdfFormat fromat, String file, PDPageContentStream pcs) {
        try {
            PDImageXObject pdImage = PDImageXObject.createFromFile(file, this.document);

            pcs.drawImage(pdImage, fromat.getX(), fromat.getY(), fromat.getWidth(), fromat.getHeight());
        } catch (Exception e) {
            System.out.println("error on image import: " + e.getMessage());
        }

        return pcs;
    }

    private PDPageContentStream addText(String text, int size, Color color, Boolean bold, int x, int y, PDPageContentStream pcs) {
        try {
            PDFont font;

            if (bold) {
                font = this.getFontBold();
            } else {
                font = this.getFont();
            }

            pcs.beginText();
            pcs.newLineAtOffset(x, y);

            pcs.setFont(font, size);
            pcs.setNonStrokingColor(color);

            pcs.showText(text);

            pcs.endText();
        } catch (Exception e) {
            System.out.println("error on text: " + e.getMessage());
        }
        return pcs;
    }

    private PDFont getFont() {
        try {
            InputStream imported = PdfBuilder.class.getResourceAsStream(path + "/OpenSans.ttf");

            return PDType0Font.load(this.document, imported, true);
        } catch (Exception e) {
            return PDType1Font.HELVETICA;
        }
    }

    private PDFont getFontBold() {
        try {
            InputStream imported = PdfBuilder.class.getResourceAsStream(path + "/OpenSans-Bold.ttf");

            return PDType0Font.load(this.document, imported, true);
        } catch (Exception e) {
            return PDType1Font.HELVETICA_BOLD;
        }
    }

    private int getHeight() {
        return (int)PDRectangle.A4.getHeight();
    }

    private int getWidth() {
        return (int)PDRectangle.A4.getWidth();
    }

    private String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }
}
