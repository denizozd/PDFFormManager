package document;
import java.io.File;


import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class AddPage {

    public static void main(String args[]) throws IOException {

        //Loading an existing document
        File file = new File("/home/deniz/Masaüstü/jotForm/maven/src/trial.pdf");

        PDDocument document = PDDocument.load(file);
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //Begin the Content stream
        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        //Setting the position for the line
        contentStream.newLineAtOffset(25, 500);

        String text = "HENLOOOOOOO.";

        //Adding text in the form of string
        contentStream.showText(text);

        //Ending the content stream
        contentStream.endText();

        System.out.println("Content added");

        //Closing the content stream
        contentStream.close();

        //Saving the document
        document.save(new File("/home/deniz/Masaüstü/jotForm/maven/src/demo.pdf"));

        //Closing the document
        document.close();

    }
}