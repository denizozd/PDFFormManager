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
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DropDown {

    public static void CreateDropDown(PDAcroForm acroForm, PDDocument document, PDPage page, List<Long> coordinates, List<String> options, String fieldName, float[] rgbBackGround, float[] rgbBorder, boolean isMultiSelect, boolean isReadOnly) throws IOException {

        //new PDDocument();
        //PDDocument document = PDDocument.load(file);

        //PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();

        // PDPage page = document.getPage((int) pageIndex);


        PDComboBox comboBox = new PDComboBox(acroForm);

        //document.getDocumentCatalog().setAcroForm(acroForm);

        comboBox.setPartialName(fieldName);

        comboBox.setOptions(options);

        comboBox.setMultiSelect(isMultiSelect);
        comboBox.setReadOnly(isReadOnly);
        comboBox.setAlternateFieldName(fieldName);

        //List<PDAnnotationWidget> widgets = new ArrayList<PDAnnotationWidget>();
//below line
        PDAnnotationWidget widget = comboBox.getWidgets().get(0);

        //PDAnnotationWidget widget = new PDAnnotationWidget();

        float x = coordinates.get(0);
        float y = page.getMediaBox().getHeight() - coordinates.get(1);
        float width = coordinates.get(2);
        float height = coordinates.get(3);


        widget.setPage(page);

        widget.setRectangle(new PDRectangle(x, PDRectangle.A4.getHeight() - y, width, height));

        widget.setPrinted(true);
        // float[] rgb = new float[]{1,23,4};

        PDAppearanceCharacteristicsDictionary appearanceCharacteristics = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
        appearanceCharacteristics.setBorderColour(new PDColor(rgbBorder, PDDeviceRGB.INSTANCE));
        appearanceCharacteristics.setBackground(new PDColor(rgbBackGround, PDDeviceRGB.INSTANCE));
        widget.setColor(new PDColor(rgbBackGround, PDDeviceRGB.INSTANCE));


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

        widget.setAppearanceState("On"); // don't forget this, or button will be invisible
        //widgets.add(widget);
        page.getAnnotations().add(widget);

        apNDict.setItem(COSName.Off, createAppearanceStream(document, widget, false));
        apNDict.setItem(COSName.YES, createAppearanceStream(document, widget, true));

        //comboBox.setWidgets(widgets);
        //page.getAnnotations().add(comboBox.getWidgets().get(0));
        page.getAnnotations().add(comboBox.getWidgets().get(0));
        acroForm.getFields().add(comboBox);
    }

    private static PDAppearanceStream createAppearanceStream(
            final PDDocument document, PDAnnotationWidget widget, boolean on) throws IOException {
        PDRectangle rect = widget.getRectangle();

        PDAppearanceCharacteristicsDictionary appearanceCharacteristics = widget.getAppearanceCharacteristics();

        PDAppearanceStream onAP = new PDAppearanceStream(document);

        onAP.setBBox(new PDRectangle(rect.getWidth(), rect.getHeight()));

        onAP.setResources(new PDResources());

        PDPageContentStream onAPCS = new PDPageContentStream(document, onAP);

        //PDPageContentStream onAPCS = new PDPageContentStream(document, page, true, false);

        //PDPageContentStream onAPCS = new PDPageContentStream(document, page, true, false);
        PDColor backgroundColor = appearanceCharacteristics.getBackground();
        PDColor borderColor = appearanceCharacteristics.getBorderColour();
        float lineWidth = getLineWidth(widget);
        onAPCS.setLineWidth(lineWidth); // border style (dash) ignored
        onAPCS.setNonStrokingColor(backgroundColor);
        //float radius = Math.min(rect.getWidth() / 2, rect.getHeight() / 2);
        //drawCircle(onAPCS, rect.getWidth() / 2, rect.getHeight() / 2, radius);
        onAPCS.fill();
        onAPCS.setStrokingColor(borderColor);
        //drawCircle(onAPCS, rect.getWidth() / 2, rect.getHeight() / 2, radius - lineWidth / 2);
        onAPCS.stroke();
        if (on) {
            onAPCS.setNonStrokingColor(0f);
            //  drawCircle(onAPCS, rect.getWidth() / 2, rect.getHeight() / 2, (radius - lineWidth) / 2);
            onAPCS.fill();
        }

        onAPCS.close();
        return onAP;
    }

    private static float getLineWidth(PDAnnotationWidget widget) {
        PDBorderStyleDictionary bs = widget.getBorderStyle();
        if (bs != null) {
            return bs.getWidth();
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {

        List<Long> coords = new ArrayList<Long>(Arrays.asList(100L, 20L, 30L, 30L));
        // CreateDropDown(new File("demo.pdf"),0,coords, new ArrayList<String>(Arrays.asList("a","b","c")),"dropDown");
        File file = new File("lb.pdf");
        new PDDocument();
        PDDocument doc = PDDocument.load(file);

        //CreateDropDown( doc , 0 , coords,new ArrayList<String>(Arrays.asList("a","b","c")),"dropDown" );


    }

}