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
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;

import java.io.IOException;

/**
 * An example of creating an AcroForm and an empty signature field from scratch.
 * <p>
 * An actual signature can be added by clicking on it in Adobe Reader.
 */
public final class SignatureField {
    public static void CreateSignatureField(PDDocument document, PDAcroForm acroForm, PDPage page, PDRectangle rectangle, String fieldName, float[] rgbBackGround, float [] rgbBorder,boolean isReadOnly) throws IOException {

        // Create a new document with an empty page.
        //new PDDocument();
        //PDDocument document = PDDocument.load(file);
        //PDPage page = new PDPage(PDRectangle.A4);
        //document.addPage(page);
        //PDPage page = document.getPage(Math.toIntExact(pageIndex));
        // Adobe Acrobat uses Helvetica as a default font and
        // stores that under the name '/Helv' in the resources dictionary
        PDFont font = PDType1Font.HELVETICA;

        PDResources resources = new PDResources();

        resources.put(COSName.getPDFName("Helv"), font);

        // Add a new AcroForm and add that to the document
        //PDAcroForm acroForm = new PDAcroForm(document);
        //document.getDocumentCatalog().setAcroForm(acroForm);

        // Add and set the resources and default appearance at the form level
        //acroForm.setDefaultResources(resources);

        // Acrobat sets the font size on the form level to be
        // auto sized as default. This is done by setting the font size to '0'
        String defaultAppearanceString = "/Helv 0 Tf 0 g";

        acroForm.setDefaultAppearance(defaultAppearanceString);

        // --- end of general AcroForm stuff ---

        // Create empty signature field, it will get the name "Signature1"
        PDSignatureField signatureField = new PDSignatureField(acroForm);
        PDAnnotationWidget widget = signatureField.getWidgets().get(0);
        signatureField.setAlternateFieldName(fieldName);
        //PDRectangle rect = new PDRectangle(50, 650, 200, 50);
        widget.setRectangle(rectangle);
        widget.setPage(page);
        page.getAnnotations().add(widget);
        signatureField.setReadOnly(isReadOnly);
        acroForm.getFields().add(signatureField);

        PDAppearanceCharacteristicsDictionary fieldAppearance
                = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
        fieldAppearance.setBorderColour(new PDColor(rgbBorder, PDDeviceRGB.INSTANCE));
        fieldAppearance.setBackground(new PDColor(rgbBackGround, PDDeviceRGB.INSTANCE));
        widget.setAppearanceCharacteristics(fieldAppearance);

        //document.save(args[0]);
        //document.save(file);
        //document.close();
    }

    public static void main(String[] args) throws IOException {
        //PDRectangle rectangle = new PDRectangle( 50,50,30,40);
        //CreateSignatureField(new File("demo.pdf"),0,rectangle);
    }


}
