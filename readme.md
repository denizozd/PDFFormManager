# PDFFormManager
> PDFFormManager is an open source Java tool for working with PDF documents. This project allows manipulation of existing documents and creating different form features on existing documents.

## Table of contents

* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## Technologies
* Java 11.0.3
* Maven ?
* Apache PDFBox 2.0.14

## Setup

Input data is provided as a JSON File which is given as the first argument of main function.

Input JSON File contains URL of the pdf document to be modified, file name that we want to save the document to our local environment and inputs that describe form fields.

Input example:---

//add screenshots here

Output example:---

## Code Examples
Show examples of usage:
`put-your-code-here`

## Features

#### Field Types and certain options that can be created by this project
* textField
* radioButton
* checkBox
* dropDown
* listBox
* signatureField
##### Input options
* fieldName : field name, string type. Available for all field types
* pageIndex : integer. Available for all field types.
* rgbBorder and rgbBackGround : Integer Array to specify background and border color. [red,green,blue]. Available for all field types.

* fieldRect : Integer Array [x,y,width,height]
* isReadOnly : boolean
* isMultiselect : boolean
* style : caption style for radio buttons and checkbox.
8 = cross,4 = checkmark,"H"=star,"n"=square,"l"=dot,"u"=diamond.

* coordinates : widget coordinates for checkBox and radioButton.
* text : text (option) coordinates for radioButton 
* options : a string array to set options for radioButton and dropDown. 
## Status
Project is: _in progress_

## Inspiration
Add here credits. Project inspired by..., based on...

## Contact
Created by [@flynerdpl](https://www.flynerd.pl/) - feel free to contact me!
-
