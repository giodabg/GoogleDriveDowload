/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbcircolari2calendar;

/**
 *
 * @author scuola
 */
// https://o7planning.org/en/11889/manipulating-files-and-folders-on-google-drive-using-java#a20602553
import java.io.IOException;
import java.util.List;

import com.google.api.services.drive.model.File;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.TaggedPdfReaderTool;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class NBCircolari2Calendar {

    // Directory to store downloade file for this application.
    private static final java.io.File DOCUMENTS_FOLDER //
            = new java.io.File(System.getProperty("user.home"), "TapSchoolDocuments");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            List<File> rootGoogleFolders
                    = GoogleDriveUtils.getGoogleFilesByIdDir("1zPYIo-Df3KxqVWZQcwGs7UhkBiUioCo_",
                            DOCUMENTS_FOLDER.getAbsolutePath());
            for (File file : rootGoogleFolders) {

                System.out.println("Mime Type: " + file.getMimeType() + " --- Name: " + file.getName());
                String SOURCE = DOCUMENTS_FOLDER.getAbsolutePath() + "\\" + file.getName();
                String RESULT = SOURCE.replace("pdf", "xml");
                TaggedPdfReaderTool reader = new TaggedPdfReaderTool();
                reader.convertToXml(new PdfReader(SOURCE), new FileOutputStream(RESULT));
                Eventi consigli = new Eventi();
                ParserConsigli dom = new ParserConsigli();
                consigli = dom.parseDocument(RESULT);
                String CALENDARCSV = DOCUMENTS_FOLDER.getAbsolutePath() + "\\CALENDAR.CSV";
                FileUtil.ScriviCSV(consigli, CALENDARCSV, true);
            }

            System.out.println("Done!");
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(NBCircolari2Calendar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
