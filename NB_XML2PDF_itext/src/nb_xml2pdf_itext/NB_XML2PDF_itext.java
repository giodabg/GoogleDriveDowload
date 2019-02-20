/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

// https://git.itextsupport.com/projects/I5JS/repos/book/browse/src/part4/chapter15/ParseTaggedPdf.java?at=58c120917d3f9a5528aae3f7837acda0bf203222

package nb_xml2pdf_itext;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.TaggedPdfReaderTool;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giodabg
 */
public class NB_XML2PDF_itext {

     /** The resulting XML file. */
    public static final String SOURCE
        = "Documents\\test.pdf";
     /** The resulting XML file. */
    
    public static final String RESULT
        = "Documents\\test.xml";

    /**
     * Parses the PDF file.
     * @param    args    no arguments needed
     */
    public static void main(String[] args) {
        try {
            TaggedPdfReaderTool reader = new TaggedPdfReaderTool();
            reader.convertToXml(new PdfReader(SOURCE), new FileOutputStream(RESULT));
        } catch (IOException ex) {
            Logger.getLogger(NB_XML2PDF_itext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
