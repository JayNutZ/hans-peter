package pdf;

import java.util.ArrayList;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import data.User;

public class PdfBuilder {
    private PdfInfo info;
    private PDDocument document;
    private ArrayList<PDPage> pages;

    public PdfBuilder(User user) {
        this.info = new PdfInfo(user.getFirstname() + user.getLastname() + ".pdf", System.getProperty("user.home") + "/pdfoutput", user);
        this.document = new PDDocument();
    }

    public boolean build() {
        return this.makeFile();
    }

    public PdfInfo getInfo() {
        return this.info;
    }

    private boolean makeFile() {
        this.document = new PDDocument();
        this.pages = new ArrayList<>();

        PDPageContentStream pcs = this.makePage();

        PdfLayout layout = new PdfLayout(this.info, this.document);
        pcs = layout.buildLayout(pcs);

        return this.write(pcs);
    }

    private PDPageContentStream makePage() {
        try {
            this.pages.add(new PDPage(PDRectangle.A4));
            PDPage page = this.getLastPage();

            this.document.addPage(page);

            return new PDPageContentStream(this.document, page, PDPageContentStream.AppendMode.APPEND,true,true);
        } catch (Exception e) {
            return null;
        }
    }

    private boolean write(PDPageContentStream pcs) {
        try {
            pcs.close();

            File tmpDir = new File(this.info.getDir());

            if (!tmpDir.exists()) {
                new File(this.info.getDir()).mkdirs();
            }

            this.document.save(this.info.getFullFilename());
            this.document.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private PDPage getLastPage() {
        return this.pages.get(this.pages.size() - 1);
    }

}
