/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jotform.pdfconverter.elements;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.*;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example to create radio buttons.
 *
 * @author Tilman Hausherr
 */
public class RadioButton
{


    public static void CreateRadioButton(PDDocument document, PDAcroForm acroForm , PDPage page, List<String> options,
                                         List<Long> widgetCoordinates, List<Long> textCoordinates, String fieldName,
                                         String style  , float[] rgbBackGround, float [] rgbBorder,boolean isReadOnly) throws IOException {


        PDRadioButton radioButton = new PDRadioButton(acroForm);

        radioButton.setReadOnly(isReadOnly);
        radioButton.setPartialName(fieldName);
        radioButton.setExportValues(options);
        radioButton.setRadiosInUnison(false);

        PDAppearanceCharacteristicsDictionary appearanceCharacteristics = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
        appearanceCharacteristics.setBorderColour(new PDColor(rgbBorder, PDDeviceRGB.INSTANCE));
        appearanceCharacteristics.setBackground(new PDColor(rgbBackGround, PDDeviceRGB.INSTANCE));
        // 8 = cross; 4 = checkmark; H = star; u = diamond; n = square, l = dot
        if( !style.equals("noCaption")){
            appearanceCharacteristics.setNormalCaption(style);
        }
        // no caption => round
        Long x_widget = widgetCoordinates.get(0);
        Long y_widget = widgetCoordinates.get(1);
        Long width_widget = widgetCoordinates.get(2);
        Long height_widget = widgetCoordinates.get(3);

        Long tx = textCoordinates.get(0);
        Long ty = textCoordinates.get(1);
        //Coordinates for the radio button
        List<PDAnnotationWidget> widgets = new ArrayList<PDAnnotationWidget>();
        for (int i = 0; i < options.size(); i++)
        {
            PDAnnotationWidget widget = new PDAnnotationWidget();
            //widget.setRectangle( new PDRectangle(x_widget, PDRectangle.A4.getHeight() - y_widget - i * 35, width_widget, height_widget));
            widget.setRectangle( new PDRectangle(x_widget, y_widget +  i * 35, width_widget, height_widget));

            //widget.setRectangle( new PDRectangle(50, PDRectangle.A4.getHeight() - 40 - i * 35, 30, 30));
            widget.setAppearanceCharacteristics(appearanceCharacteristics);
            PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
            borderStyleDictionary.setWidth(1);
            borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
            widget.setBorderStyle(borderStyleDictionary);
            widget.setPage(page);

            COSDictionary apNDict = new COSDictionary();
            //below 2 lines
            apNDict.setItem(COSName.ON, createAppearanceStream(document, widget, true ));
            apNDict.setItem( options.get(i), createAppearanceStream(document, widget, true));

            PDAppearanceDictionary appearance = new PDAppearanceDictionary();
            PDAppearanceEntry appearanceNEntry = new PDAppearanceEntry(apNDict);
            appearance.setNormalAppearance(appearanceNEntry);
            widget.setAppearance(appearance);
            widget.setAppearanceState("On"); // don't forget this, or button will be invisible
            widgets.add(widget);
            page.getAnnotations().add(widget);
            widget.setAnnotationName(fieldName);
        }
        radioButton.setWidgets(widgets);

        acroForm.getFields().add(radioButton);

        // Set the texts
        //Coordinates for the text
        //PDPageContentStream contents = new PDPageContentStream(document, page);
        PDPageContentStream contents = new PDPageContentStream(document, page,  true, false);
        for (int i = 0; i < options.size(); i++) {
            contents.beginText();
            contents.setFont(PDType1Font.HELVETICA, 15);
            //contents.newLineAtOffset(tx, PDRectangle.A4.getHeight() - ty - i * 35);
            contents.newLineAtOffset(tx, ty - i * 35);
            //contents.newLineAtOffset(100, PDRectangle.A4.getHeight() - 30 - i * 35);
            contents.showText(options.get(i));
            contents.endText();
        }
        contents.close();

        radioButton.setValue(options.get(options.size()-1));

       // document.save(file);

       // document.close();
    }

    private static PDAppearanceStream createAppearanceStream(
            final PDDocument document, PDAnnotationWidget widget, boolean on ) throws IOException
    {
        PDRectangle rect = widget.getRectangle();
        PDAppearanceStream onAP = new PDAppearanceStream(document);
        onAP.setBBox(new PDRectangle(rect.getWidth(), rect.getHeight()));
        PDPageContentStream onAPCS = new PDPageContentStream(document, onAP);

        PDAppearanceCharacteristicsDictionary appearanceCharacteristics = widget.getAppearanceCharacteristics();
        PDColor backgroundColor = appearanceCharacteristics.getBackground();
        PDColor borderColor = appearanceCharacteristics.getBorderColour();
        float lineWidth = getLineWidth(widget);
        onAPCS.setLineWidth(lineWidth); // border style (dash) ignored
        onAPCS.setNonStrokingColor(backgroundColor);
        float radius = Math.min(rect.getWidth() / 2, rect.getHeight() / 2);
        drawCircle(onAPCS, rect.getWidth() / 2, rect.getHeight() / 2, radius);
        onAPCS.fill();
        onAPCS.setStrokingColor(borderColor);
        drawCircle(onAPCS, rect.getWidth() / 2, rect.getHeight() / 2, radius - lineWidth / 2);
        onAPCS.stroke();
        if (on)
        {
            onAPCS.setNonStrokingColor(0f);
            drawCircle(onAPCS, rect.getWidth() / 2, rect.getHeight() / 2, (radius - lineWidth) / 2);
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

    private static void drawCircle(PDPageContentStream cs, float x, float y, float r) throws IOException
    {
        // http://stackoverflow.com/a/2007782/535646
        float magic = r * 0.551784f;
        cs.moveTo(x, y + r);
        cs.curveTo(x + magic, y + r, x + r, y + magic, x + r, y);
        cs.curveTo(x + r, y - magic, x + magic, y - r, x, y - r);
        cs.curveTo(x - magic, y - r, x - r, y - magic, x - r, y);
        cs.curveTo(x - r, y + magic, x - magic, y + r, x, y + r);
        cs.closePath();
    }

    public static void main(String[] args) throws IOException {

          //RadioButton(new File("/home/deniz/Masaüstü/jotForm/maven/button.pdf"), 0 );
    }

}
