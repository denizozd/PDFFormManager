// Java program to read JSON from a file

import com.jotform.pdfconverter.elements.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PDFFormManager {

    private File PDFFile;

    private PDDocument PDFDocument;

    private JSONArray inputs;

    private String fileName;

    private PDAcroForm acroForm;


    private static PDRectangle Rectangle(JSONArray array) {
        long x1, x2, y1, y2;
        x1 = (long) array.get(0);
        y1 = (long) array.get(1);
        x2 = (long) array.get(2);
        y2 = (long) array.get(3);
        return new PDRectangle(x1, y1, x2, y2);
    }

    private static List<String> Options(JSONArray array) {

        List<String> list = new ArrayList<String>();

        for (int i = 0; i < array.size(); i++) {
            list.add(i, (String) array.get(i));
        }
        return list;
    }

    private static List<Long> Coordinates(JSONArray array) {

        List<Long> list = new ArrayList<Long>();
        long x1 = (long) array.get(0);
        long y1 = (long) array.get(1);
        long x2 = (long) array.get(2);
        long y2 = (long) array.get(3);

        list.add(0, x1);  // adds 1 at 0 index
        list.add(1, y1);  // adds 2 at 1 index
        list.add(2, x2);
        list.add(3, y2);

        return list;
    }

    private static List<Long> TextCoordinates(JSONArray array) {
        List<Long> list = new ArrayList<Long>();
        long x1 = (long) array.get(0);
        long y1 = (long) array.get(1);
        list.add(0, x1);  // adds 1 at 0 index
        list.add(1, y1);  // adds 2 at 1 index

        return list;
    }

    private static float[] RGB(JSONArray array) {
        List<Long> list = new ArrayList<Long>();
        long red = (long) array.get(0);
        long green = (long) array.get(1);
        long blue = (long) array.get(2);

        float[] rgb = new float[]{red, green, blue};

        return rgb;
    }

    public PDFFormManager(File JsonFile) throws IOException, ParseException {
        JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(JsonFile));

        inputs = (JSONArray) jo.get("inputs");

        String url = (String) jo.get("fileUrl");

        fileName = (String) jo.get("fileName");

        PDFFile = ReadFromURL.URLread(url, fileName);

        PDFDocument = PDDocument.load(PDFFile);

        acroForm = new PDAcroForm(PDFDocument);

        acroForm.setAppendOnly(true);

        acroForm.setNeedAppearances(true);

        PDFDocument.getDocumentCatalog().setAcroForm(acroForm);

        acroForm.setDefaultResources(new PDResources());
    }

    public void process() throws IOException {
        for (Object input : this.inputs) {

            JSONObject inp = (JSONObject) input;

            Map MappedInp = (Map) inp;

            System.out.println(inp.get("fieldType"));


            if (inp.get("fieldType").equals("textField")) {

                PDRectangle rectangle = Rectangle((JSONArray) inp.get("fieldRect"));
                Long index = (Long) inp.get("pageIndex");

                float[] rgbBackGround = RGB((JSONArray) inp.get("rgbBackGround"));

                float[] rgbBorder = RGB((JSONArray) inp.get("rgbBorder"));

                PDPage page = PDFDocument.getPage(Math.toIntExact(index));

                boolean isReadOnly = (boolean) inp.get("isReadOnly");

                TextField.TextBox(acroForm, page, (String) inp.get("fieldName"),
                        rectangle, isReadOnly, rgbBackGround, rgbBorder);
            } else if (inp.get("fieldType").equals("radioButton")) {

                List<Long> widget_coordinates = Coordinates((JSONArray) inp.get("coordinates"));

                List<Long> text_coordinates = TextCoordinates((JSONArray) inp.get("text"));

                List<String> options = Options((JSONArray) inp.get("options"));

                float[] rgbBackGround = RGB((JSONArray) inp.get("rgbBackGround"));

                float[] rgbBorder = RGB((JSONArray) inp.get("rgbBorder"));

                boolean isReadOnly = (boolean) inp.get("isReadOnly");

                Long index = (Long) inp.get("pageIndex");

                String style = (String) inp.get("style");

                PDPage page = PDFDocument.getPage(Math.toIntExact(index));

                String fieldName = (String) inp.get("fieldName");

                RadioButton.CreateRadioButton(PDFDocument, acroForm, page, options,
                        widget_coordinates, text_coordinates, fieldName, style, rgbBackGround, rgbBorder,isReadOnly);

            } else if (inp.get("fieldType").equals("checkBox")) {

                List<Long> coordinates = Coordinates((JSONArray) inp.get("coordinates"));

                float[] rgbBackGround = RGB((JSONArray) inp.get("rgbBackGround"));

                float[] rgbBorder = RGB((JSONArray) inp.get("rgbBorder"));

                boolean isReadOnly = (boolean) inp.get("isReadOnly");

                boolean check = (boolean)inp.get("check");

                Long index = (Long) inp.get("pageIndex");

                PDPage page = PDFDocument.getPage(Math.toIntExact(index));

                String style = (String) inp.get("style");

                String fieldName = (String) inp.get("fieldName");

                CheckBox.CreateCheckBox(acroForm, PDFDocument, page, coordinates, fieldName, style, rgbBackGround,
                        rgbBorder,isReadOnly,check);
            } else if (inp.get("fieldType").equals("signatureField")) {

                PDRectangle rectangle = Rectangle((JSONArray) inp.get("fieldRect"));

                float[] rgbBackGround = RGB((JSONArray) inp.get("rgbBackGround"));

                float[] rgbBorder = RGB((JSONArray) inp.get("rgbBorder"));

                boolean isReadOnly = (boolean) inp.get("isReadOnly");

                Long index = (Long) inp.get("pageIndex");

                PDPage page = PDFDocument.getPage(Math.toIntExact(index));

                String fieldName = (String) inp.get("fieldName");

                SignatureField.CreateSignatureField(PDFDocument, acroForm, page, rectangle, fieldName, rgbBackGround,
                        rgbBorder,isReadOnly);
            } else if (inp.get("fieldType").equals("listBox")) {

                List<String> options = Options((JSONArray) inp.get("options"));

                List<Long> coordinates = Coordinates((JSONArray) inp.get("fieldRect"));

                float[] rgbBackGround = RGB((JSONArray) inp.get("rgbBackGround"));

                float[] rgbBorder = RGB((JSONArray) inp.get("rgbBorder"));

                boolean isMultiSelect = (boolean) inp.get("isMultiSelect");

                boolean isReadOnly = (boolean) inp.get("isReadOnly");

                Long index = (Long) inp.get("pageIndex");
                PDPage page = PDFDocument.getPage(Math.toIntExact(index));

                ListBox.CreateListBox(PDFDocument, acroForm, page, coordinates, options, (String) inp.get("fieldName"),
                        rgbBackGround, rgbBorder,isMultiSelect,isReadOnly);
            } else if (inp.get("fieldType").equals("dropdown")) {

                List<String> options = Options((JSONArray) inp.get("options"));

                List<Long> coordinates = Coordinates((JSONArray) inp.get("fieldRect"));

                float[] rgbBackGround = RGB((JSONArray) inp.get("rgbBackGround"));

                float[] rgbBorder = RGB((JSONArray) inp.get("rgbBorder"));

                boolean isMultiSelect = (boolean) inp.get("isMultiSelect");

                boolean isReadOnly = (boolean) inp.get("isReadOnly");

                Long index = (Long) inp.get("pageIndex");
                PDPage page = PDFDocument.getPage(Math.toIntExact(index));

                DropDown.CreateDropDown(acroForm, PDFDocument, page, coordinates, options, (String) inp.get("fieldName"),
                        rgbBackGround, rgbBorder, isMultiSelect,isReadOnly);
            }
        }
    }

    public void saveFile() throws IOException {

        PDFDocument.save(PDFFile);
        PDFDocument.close();
    }


}