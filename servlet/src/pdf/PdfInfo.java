package pdf;

import java.util.Date;
import data.User;

public class PdfInfo {
    private String filename;
    private User user;
    private String dir;
    private Date date;

    public PdfInfo(String filename, String dir, User user) {
        this.encodeFilename(filename);

        this.dir = dir;
        this.user = user;
        this.date = new Date();
    }

    public String getFullFilename() {
        return this.dir + "/" + this.filename;
    }

    public String getFilename() {
        return filename;
    }

    public String getDir() {
        return dir;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    private void encodeFilename(String filename) {
        try {
            this.filename = java.net.URLEncoder.encode(filename, "UTF-8");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }
}
