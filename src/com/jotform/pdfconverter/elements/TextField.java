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
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import java.io.IOException;


/**
 * An example of creating an AcroForm and a form field from scratch.
 * <p>
 * The form field is created with properties similar to creating
 * a form with default settings in Adobe Acrobat.
 */
public class TextField {

    public static void TextBox(PDAcroForm acroForm, PDPage page, String fieldName, PDRectangle rectangle, boolean isReadOnly, float[] rgbBackGround, float [] rgbBorder) throws IOException {

        //PDDocument document = PDDocument.load(file);

        PDFont font = PDType1Font.HELVETICA;

        PDResources resources = new PDResources();

        resources.put(COSName.getPDFName("Helv"), font);

        // Acrobat sets the font size on the form level to be
        // auto sized as default. This is done by setting the font size to '0'
        String defaultAppearanceString = "/Helv 0 Tf 0 g";

        acroForm.setDefaultAppearance(defaultAppearanceString);

        // Add a form field to the form.
        PDTextField textBox = new PDTextField(acroForm);

        textBox.setPartialName(fieldName);

        textBox.setReadOnly(isReadOnly);
        defaultAppearanceString = "/Helv 12 Tf 1 0 1 rg";

        textBox.setDefaultAppearance(defaultAppearanceString);

        // add the field to the acroform
        acroForm.getFields().add(textBox);

        // Specify the widget annotation associated with the field
        PDAnnotationWidget widget = textBox.getWidgets().get(0);

        widget.setRectangle(rectangle);
        PDAppearanceCharacteristicsDictionary fieldAppearance
                = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
        fieldAppearance.setBorderColour(new PDColor(rgbBorder, PDDeviceRGB.INSTANCE));
        fieldAppearance.setBackground(new PDColor(rgbBackGround, PDDeviceRGB.INSTANCE));
        widget.setAppearanceCharacteristics(fieldAppearance);

        // make sure the widget annotation is visible on screen and paper
        widget.setPrinted(true);

        // Add the widget annotation to the page
        page.getAnnotations().add(widget);

        // set the field value
        textBox.setValue(fieldName);

        textBox.setDefaultValue(fieldName);
        textBox.setAlternateFieldName(fieldName);

    }

    public static void main(String[] args) throws IOException {

        //TextBox( "Inputinputinput",0, new PDRectangle(50,240,230,150), new File("text.pdf"));
    }


}