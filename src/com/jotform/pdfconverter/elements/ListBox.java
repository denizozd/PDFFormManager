package com.jotform.pdfconverter.elements;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.*;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDListBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListBox{

    public static void CreateListBox(PDDocument document,PDAcroForm acroForm,PDPage page , List<Long> coordinates, List<String> options, String fieldName, float[] rgbBackGround, float [] rgbBorder,boolean isMultiSelect, boolean isReadOnly) throws IOException {

        //new PDDocument();
        //PDDocument document = PDDocument.load(file);

        //PDAcroForm acroForm = new PDAcroForm(document);

        //PDPage page = document.getPage((int) pageIndex);

        float x = coordinates.get(0);
        float y = page.getMediaBox().getHeight() - coordinates.get(1);
        float width = coordinates.get(2);
        float height = coordinates.get(3);

        PDRectangle rectangle = new PDRectangle(x,y,width,height);

        PDListBox listBox = new PDListBox(acroForm);

       // List<String> options = Arrays.asList("a", "b", "c","d","e");
        //change below later!!!!!

       // document.getDocumentCatalog().setAcroForm(acroForm);

        listBox.setPartialName(fieldName);

        PDAnnotationWidget widget = listBox.getWidgets().get(0);
        widget.setPage(page);
        widget.setRectangle(rectangle);
        listBox.setOptions(options);
        listBox.setAlternateFieldName(fieldName);
        listBox.setMultiSelect(isMultiSelect);
        listBox.setReadOnly(isReadOnly);

        widget.setPrinted(true);

        //widget.setRectangle( new PDRectangle(x, PDRectangle.A4.getHeight() - y, width, height));



        //    widget.setRectangle( new PDRectangle(120, PDRectangle.A4.getHeight() - 400, 60, 60));


        PDAppearanceCharacteristicsDictionary appearanceCharacteristics = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
        appearanceCharacteristics.setBorderColour(new PDColor(rgbBorder, PDDeviceRGB.INSTANCE));
        appearanceCharacteristics.setBackground(new PDColor(rgbBackGround, PDDeviceRGB.INSTANCE));
        List<PDAnnotationWidget> widgets = new ArrayList<PDAnnotationWidget>();


        widget.setAppearanceCharacteristics(appearanceCharacteristics);

        PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();

        borderStyleDictionary.setWidth(2);

        borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);

        widget.setBorderStyle(borderStyleDictionary);


        PDAppearanceDictionary appearance = new PDAppearanceDictionary();
        widget.setAppearance(appearance);
        PDAppearanceEntry appearanceNEntry = appearance.getNormalAppearance();
        appearance.setNormalAppearance(appearanceNEntry);

       COSDictionary apNDict = (COSDictionary) appearanceNEntry.getCOSObject();
       apNDict.setItem(COSName.Off, createAppearanceStream(document, widget, false));
       apNDict.setItem(COSName.YES, createAppearanceStream(document, widget, true));


       // COSDictionary apNDict = new COSDictionary();
       // apNDict.setItem(COSName.ON, createAppearanceStream(document, widget, true));
        //apNDict.setItem((String) options.get(i), createAppearanceStream(document, widget, true,page));

        widget.setAppearanceState("On"); // don't forget this, or button will be invisible
        //widgets.add(widget);
        page.getAnnotations().add(widget);

        //listBox.setWidgets(widgets);

        acroForm.getFields().add(listBox);

        // OPTIONS "a","b","c"
      /* PDPageContentStream contents = new PDPageContentStream(document, page,  true, false);
        for (int i = 0; i < options.size(); i++)
        {
            contents.beginText();
            contents.setFont(PDType1Font.HELVETICA, 15);
            //contents.newLineAtOffset(tx, PDRectangle.A4.getHeight() - ty - i * 35);
            contents.newLineAtOffset(100, PDRectangle.A4.getHeight() - 30 - i * 20);
            contents.showText((String) options.get(i));
            contents.endText();
        }
        contents.close();
*/
        //listBox.setValue(options.get(options.size()-1));
       // listBox.setValue();
        //document.save(file);
        //document.close();

    }
    private static PDAppearanceStream createAppearanceStream(
            final PDDocument document, PDAnnotationWidget widget, boolean on ) throws IOException
    {
        PDRectangle rect = widget.getRectangle();

        PDAppearanceCharacteristicsDictionary appearanceCharacteristics = widget.getAppearanceCharacteristics();

        PDAppearanceStream onAP = new PDAppearanceStream(document);

        onAP.setBBox(new PDRectangle(rect.getWidth(), rect.getHeight()));

        onAP.setResources(new PDResources());

        PDPageContentStream onAPCS = new PDPageContentStream(document, onAP);
        //PDPageContentStream onAPCS = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
        PDColor backgroundColor = appearanceCharacteristics.getBackground();
        PDColor borderColor = appearanceCharacteristics.getBorderColour();
        float lineWidth = getLineWidth(widget);
        onAPCS.setLineWidth(lineWidth); // border style (dash) ignored
        onAPCS.setNonStrokingColor(backgroundColor);
        onAPCS.fill();
        onAPCS.setStrokingColor(borderColor);
        onAPCS.stroke();
        if (on)
        {
            onAPCS.setNonStrokingColor(0f);
            onAPCS.fill();
        }

        onAPCS.close();
        return onAP;
    }

    private static float getLineWidth(PDAnnotationWidget widget)
    {
        PDBorderStyleDictionary bs = widget.getBorderStyle();
        if (bs != null)
        {
            return bs.getWidth();
        }
        return 1;
    }


    public static void main(String[] args) throws IOException {

        List<Long> coords = new ArrayList<Long>(Arrays.asList(300L, 300L, 34L,30L));

        PDDocument document = new PDDocument().load(new File("checkbox.pdf"));

        PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();


       // CreateListBox(new File("checkbox.pdf"),0,coords, new ArrayList<String>(Arrays.asList("a","b","c")),"list");
    }

}