package pdf;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import data.User;

public class PdfConsole {

    public static void main(String[] args) {
        /*
        PdfBuilder builder = new PdfBuilder(new User("jka@rn.de",
                "Jakob",
                "Karnatz",
                "Töpferhof 4",
                "21029",
                "Hamburg",
                "Herr",
                1234567890L,
                parseDate("2009-03-05"),
                parseDate("2019-05-05"),
                12,
                2,
                2000.00
        ));

        builder.build();

        System.out.println("Built File: ".concat(builder.getInfo().getFullFilename()));
        */
    }

    private static Date parseDate(String in) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            return format.parse(in);
        } catch (Exception e) {
            System.out.println("error on date: " + e.getMessage());
            return null;
        }
    }
}
