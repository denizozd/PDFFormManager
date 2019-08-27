import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        File JsonFile = new File(args[0]);
        PDFFormManager pmg = new PDFFormManager(JsonFile);
        pmg.process();
        pmg.saveFile();
    }
}
