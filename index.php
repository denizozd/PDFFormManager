<!doctype html>
<html lang="en">
<head>

    <title>PDFFormManager</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<div class="container" style="display: flex">
    <!-- Content here -->
    <form action="upload.php" method="POST" enctype="multipart/form-data" name="FormData" class="col-md-6">
        <div class="wrapper">
            <div class="alert alert-info" role="alert">
                <div style="style=margin-top: 10px; margin: 10px 0 10px 0;">
                    PDF File:
                </div>
                <div class="w-100"></div>
                <input type="file" name="pdffile">
                <br>
                <div style="align-content: center;margin: 10px 0 10px 0;">
                    JSON Text:
                </div>
                <textarea rows="10" cols="50" name="fields" id="fieldsTextArea"
                          style="margin: 10px 0 10px 0;"></textarea>
                <br/>

                <select class="form-control" id="viewSelector" name="viewSelector">
                    <option value="0">-- Form Fields --</option>
                    <option value="textField">Text field</option>
                    <option value="signatureField">Signature field</option>
                    <option value="dropdown">Dropdown</option>
                    <option value="listBox">List box</option>
                    <option value="radioButton">Radio buttons</option>
                    <option value="checkBox">Check box</option>
                </select>

                <button type="submit" class="btn btn-info" style="margin-top: 10px; margin: 10px 0 10px 0;">SUBMIT!
                </button>
            </div>
        </div>
        Click <a href="<?php echo "https://github.com/denizozd/PDFFormManager/blob/master/readme.md"; ?>">here</a> for more
        information

    </form>
    <div class="alert alert-info col-md-6 float-right"  role="alert" >
        <div class="row justify-content-md-center">
            <div id="textField" style="display: none" class="card">

                <!-- content -->
                <div class="form-group row col-md-auto">
                    <div class="col-md-6">
                        <label for="pageIndex">Page Index</label>
                        <input required type="number" class="form-control" name="pageIndex" value="0"
                               id="pageIndexText">
                    </div>
                    <div class="col-md-6">
                        <label for="fieldName">Field Name</label>
                        <input required type="text" class="form-control" name="fieldName" value="example text field"
                               id="fieldNameText">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates x</label>
                        <input required type="number" class="form-control" name="x" value="70" id="xText">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates y</label>
                        <input required type="number" class="form-control" name="y" value="650" id="yText">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates width</label>
                        <input required type="number" class="form-control" name="width" value="150"
                               id="widthText">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates height</label>
                        <input required type="number" class="form-control" name="height" value="30"
                               id="heightText">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input required type="number" class="form-control" name="borderRed" value="1"
                               id="borderRedText">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input required type="number" class="form-control" name="borderGreen" value="1"
                               id="borderGreenText">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input required type="number" class="form-control" name="borderBlue" value="1"
                               id="borderBlueText">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input required type="number" class="form-control" name="backGroundRed" value="10"
                               id="backGroundRedText">
                    </div>
                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input required type="number" class="form-control" name="backGroundGreen" value="5"
                               id="backGroundGreenText">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input required type="number" class="form-control" name="backGroundBlue" value="0"
                               id="backGroundBlueText">
                    </div>

                    <div class="col-md-3">
                        <label for="isReadOnly">Read Only</label>

                        <select class="form-control" id="readOnlyText" name="readOnly" >
                            <option value="true">True</option>
                            <option value="false">False</option>
                        </select>

                    </div>


                    <div class="col-md-9"></div>

                    <div class="form-group col-md-auto mx-auto">
                        <button id="addTextField" type="button" class="btn btn-info">ADD</button>

                        <button id="removeTextField" type="button" style="display: none" class="btn btn-danger">REMOVE
                        </button>
                    </div>

                </div>
            </div>
            <div id="signatureField" style="display: none" class="card">
                <!-- content -->
                <div class="form-group row col-md-auto">

                    <div class="col-md-4">
                        <label for="pageIndex">Page Index</label>
                        <input type="number" class="form-control" name="pageIndex" value="0"
                               id="signaturePageIndex">
                    </div>

                    <div class="col-md-4">
                        <label for="fieldName">Field Name</label>
                        <input type="text" class="form-control" name="fieldName" value="Sign Here!"
                               id="signatureFieldName">
                    </div>
                    <div class="col-md-4"></div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates x</label>
                        <input type="number" class="form-control" id="xSignature" name="x" value="70">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates y</label>
                        <input type="number" class="form-control" name="y" value="300" id="ySignature">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates width</label>
                        <input type="number" class="form-control" name="width" value="130" id="widthSignature">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates height</label>
                        <input type="number" class="form-control" name="height" value="100"
                               id="heightSignature">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderRed" value="50"
                               id="borderRedSignature">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderGreen" value="5"
                               id="borderGreenSignature">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderBlue" value="5"
                               id="borderBlueSignature">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundRed" value="50"
                               id="backGroundRedSignature">
                    </div>
                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundGreen" value="1"
                               id="backGroundGreenSignature">
                    </div>


                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundBlue" value="15"
                               id="backGroundBlueSignature">
                    </div>
                    <div class="col-md-4">
                        <label for="isReadOnly">Read Only</label>

                        <select class="form-control" id="readOnlySignature" name="readOnly">
                            <option value="false">False</option>
                            <option value="true">True</option>
                        </select>

                    </div>

                    <div class="col-md-4">
                        <label for="isChecked">Already Signed</label>

                        <select class="form-control" id="isSigned" name="readOnly">
                            <option value="true">True</option>
                            <option value="false">False</option>
                        </select>
                    </div>
                    <div class="col-md-4"></div>
                    <div class="form-group col-md-auto mx-auto">
                        <button id="addSignatureField" type="button" class="btn btn-info">ADD</button>

                        <button id="removeSignatureField" type="button" style="display: none" class="btn btn-danger">
                            REMOVE
                        </button>

                    </div>

                </div>

            </div>
            <div id="dropdown" style="display: none" class="card">
                <!-- content -->
                <div class="form-group row col-md-auto">
                    <div class="col-md-4">
                        <label for="pageIndex">Page Index</label>
                        <input type="number" class="form-control" name="pageIndex" value="0"
                               id="pageIndexDropDown">
                    </div>

                    <div class="col-md-4">
                        <label for="fieldName">Field Name</label>
                        <input type="text" class="form-control" name="fieldName" value="dropDown"
                               id="fieldNameDropDown">
                    </div>

                    <div class="col-md-4">
                        <label for="options">Dropdown options</label>
                        <input type="text" class="form-control" name="options" value="option1,option2"
                               id="optionsDropDown">
                    </div>
                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates x</label>
                        <input type="number" class="form-control" id="xDropDown" name="x" value="300">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates y</label>
                        <input type="number" class="form-control" name="y" value="655" id="yDropDown">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates width</label>
                        <input type="number" class="form-control" name="width" value="50" id="widthDropDown">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates height</label>
                        <input type="number" class="form-control" name="height" value="20"
                               id="heightDropDown">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderRed" value="0"
                               id="borderRedDropDown">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderGreen" value="10"
                               id="borderGreenDropDown">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderBlue" value="1"
                               id="borderBlueDropDown">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundRed" value="1"
                               id="backGroundRedDropDown">
                    </div>
                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundGreen" value="1"
                               id="backGroundGreenDropDown">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundBlue" value="1"
                               id="backGroundBlueDropDown">
                    </div>
                    <div class="col-md-4">
                        <label for="isReadOnly">Read Only</label>
                        <select class="form-control" id="readOnlyDropDown" name="readOnly">
                            <option value="false">False</option>
                            <option value="true">True</option>
                        </select>
                    </div>

                    <div class="col-md-4">
                        <label for="isMultiSelect">Multiselect</label>

                        <select class="form-control" id="multiSelectDropDown" name="readOnly">
                            <option value="true">True</option>
                            <option value="false">False</option>
                        </select>
                    </div>
                    <div class="col-md-4"></div>
                    <div class="form-group col-md-auto mx-auto">
                        <button id="addDropDown" type="button" class="btn btn-info">ADD</button>

                        <button id="removeDropDown" type="button" style="display: none" class="btn btn-danger">REMOVE
                        </button>

                    </div>
                </div>
            </div>
            <div id="listBox" style="display: none" class="card">
                <!-- content -->
                <div class="form-group row col-md-auto">
                    <div class="col-md-4">
                        <label for="pageIndexListBox">Page Index</label>
                        <input type="number" class="form-control" name="pageIndex" value="0"
                               id="pageIndexListBox">
                    </div>

                    <div class="col-md-4">
                        <label for="fieldName">Field Name</label>
                        <input type="text" class="form-control" name="fieldName" value="listBox"
                               id="fieldNameListBox">
                    </div>
                    <div class="col-md-4">
                        <label for="optionsListBox">Listbox options</label>
                        <input type="text" class="form-control" name="optionsListBox" value="a,b,c,d,e"
                               id="optionsListBox">
                    </div>
                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates x</label>
                        <input type="number" class="form-control" id="xListBox" name="x" value="300">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates y</label>
                        <input type="number" class="form-control" name="y" value="455" id="yListBox">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates width</label>
                        <input type="number" class="form-control" name="width" value="70" id="widthListBox">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates height</label>
                        <input type="number" class="form-control" name="height" value="70" id="heightListBox">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderRed" value="1"
                               id="borderRedListBox">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderGreen" value="10"
                               id="borderGreenListBox">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderBlue" value="0"
                               id="borderBlueListBox">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundRed" value="5"
                               id="backGroundRedListBox">
                    </div>
                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundGreen" value="1"
                               id="backGroundGreenListBox">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundBlue" value="0"
                               id="backGroundBlueListBox">
                    </div>
                    <div class="col-md-4">
                        <label for="isReadOnly">Read Only</label>

                        <select class="form-control" id="readOnlyListBox" name="readOnly">
                            <option value="false">False</option>
                            <option value="true">True</option>
                        </select>
                    </div>

                    <div class="col-md-4">
                        <label for="isMultiSelect">Multiselect</label>

                        <select class="form-control" id="multiSelectListBox" name="readOnly">
                            <option value="true">True</option>
                            <option value="false">False</option>
                        </select>
                    </div>
                    <div class="col-md-4"></div>
                    <div class="form-group col-md-auto mx-auto">
                        <button id="addListBox" type="button" class="btn btn-info">ADD</button>

                        <button id="removeListBox" type="button" style="display: none" class="btn btn-danger">REMOVE
                        </button>

                    </div>
                </div>
            </div>
            <div id="radioButton" style="display: none" class="card">
                <!-- content -->
                <div class="form-group row col-md-auto">
                    <div class="col-md-4">
                        <label for="pageIndexRadioButton">Page Index</label>
                        <input type="number" class="form-control" name="pageIndex" value="0"
                               id="pageIndexRadioButton">
                    </div>
                    <div class="col-md-4">
                        <label for="fieldNameRadioButton">Field Name</label>
                        <input type="text" class="form-control" name="fieldNameRadioButton" value="radio buttons"
                               id="fieldNameRadioButton">
                    </div>
                    <div class="col-md-4">
                        <label for="styleRadioButton">Caption style</label>
                        <select class="form-control" id="styleRadioButton" name="styleRadioButton">
                            <option value="l">Dot</option>
                            <option value="8">Cross</option>
                            <option value="4">Checkmark</option>
                            <option value="H">Star</option>
                            <option value="n">Square</option>
                            <option value="u">Diamond</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="optionsRadioButton">Button options</label>
                        <input type="text" class="form-control" name="options" value="a,b,c"
                               id="optionsRadioButton">
                    </div>
                    <div class="col-md-6"></div>
                    <div class="col-md-3">
                        <label for="xRadioButton">Coordinates x</label>
                        <input type="number" class="form-control" id="xRadioButton" name="x" value="110">
                    </div>

                    <div class="col-md-3">
                        <label for="yRadioButton">Coordinates y</label>
                        <input type="number" class="form-control" name="y" value="440" id="yRadioButton">
                    </div>

                    <div class="col-md-3">
                        <label for="widthRadioButton">Coordinates width</label>
                        <input type="number" class="form-control" name="width" value="30"
                               id="widthRadioButton">
                    </div>

                    <div class="col-md-3">
                        <label for="heightRadioButton">Coordinates height</label>
                        <input type="number" class="form-control" name="height" value="30"
                               id="heightRadioButton">
                    </div>
                    <div class="col-md-3">
                        <label for="xTextRadioButton">Option Coordinates x</label>
                        <input type="number" class="form-control" name="x" value="70" id="xTextRadioButton">
                    </div>

                    <div class="col-md-3">
                        <label for="yTextRadioButton">Option Coordinates y</label>
                        <input type="number" class="form-control" name="y" value="520" id="yTextRadioButton">
                    </div>
                    <div class="col-md-6"></div>

                    <div class="col-md-4">
                        <label for="borderRedRadioButton">Border Color</label>
                        <input type="number" class="form-control" name="borderRed" value="0"
                               id="borderRedRadioButton">
                    </div>

                    <div class="col-md-4">
                        <label for="borderGreenRadioButton">Border Color</label>
                        <input type="number" class="form-control" name="borderGreen" value="1"
                               id="borderGreenRadioButton">
                    </div>

                    <div class="col-md-4">
                        <label for="borderBlueRadioButton">Border Color</label>
                        <input type="number" class="form-control" name="borderBlue" value="0"
                               id="borderBlueRadioButton">
                    </div>

                    <div class="col-md-4">
                        <label for="backGroundRedRadioButton">Background Color</label>
                        <input type="number" class="form-control" name="backGroundRed" value="0"
                               id="backGroundRedRadioButton">
                    </div>
                    <div class="col-md-4">
                        <label for="backGroundGreenRadioButton">Background Color</label>
                        <input type="number" class="form-control" name="backGroundGreen" value="1"
                               id="backGroundGreenRadioButton">
                    </div>

                    <div class="col-md-4">
                        <label for="backGroundBlueRadioButton">Background Color</label>
                        <input type="number" class="form-control" name="backGroundBlue" value="1"
                               id="backGroundBlueRadioButton">
                    </div>
                    <div class="col-md-4">
                        <label for="isReadOnlyRadioButton">Read Only</label>
                        <select class="form-control" id="readOnlyRadioButton" name="readOnly">

                            <option value="false">False</option>
                            <option value="true">True</option>
                        </select>
                    </div>
                    <div class="col-md-8"></div>

                    <div class="form-group col-md-auto mx-auto">
                        <button id="addRadioButton" type="button" class="btn btn-info">ADD</button>

                        <button id="removeRadioButton" type="button" style="display: none" class="btn btn-danger">REMOVE
                        </button>

                    </div>
                </div>
            </div>
            <div id="checkBox" style="display: none" class="card">
                <!-- content -->
                <div class="form-group row col-md-auto">
                    <div class="col-md-4">
                        <label for="pageIndexListBox">Page Index</label>
                        <input type="number" class="form-control" name="pageIndex" placeholder="Index"
                               id="pageIndexCheckBox">
                    </div>
                    <div class="col-md-4">
                        <label for="fieldName">Field Name</label>
                        <input type="text" class="form-control" name="fieldName" value="checkBox"
                               id="fieldNameCheckBox">
                    </div>
                    <div class="col-md-4"></div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates x</label>
                        <input type="number" class="form-control" id="xCheckBox" name="x" value="400">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates y</label>
                        <input type="number" class="form-control" name="y" value="650" id="yCheckBox">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates width</label>
                        <input type="number" class="form-control" name="width" value="30" id="widthCheckBox">
                    </div>

                    <div class="col-md-3">
                        <label for="fieldRect">Coordinates height</label>
                        <input type="number" class="form-control" name="height" value="30"
                               id="heightCheckBox">
                    </div>
                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderRed" value="0"
                               id="borderRedCheckBox">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderGreen" value="1"
                               id="borderGreenCheckBox">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBorder">Border Color</label>
                        <input type="number" class="form-control" name="borderBlue" value="0"
                               id="borderBlueCheckBox">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundRed" value="50"
                               id="backGroundRedCheckBox">
                    </div>
                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundGreen" value="1"
                               id="backGroundGreenCheckBox">
                    </div>

                    <div class="col-md-4">
                        <label for="rgbBackGround">Background Color</label>
                        <input type="number" class="form-control" name="backGroundBlue" value="15"
                               id="backGroundBlueCheckBox">
                    </div>
                    <div class="col-md-4">
                        <label for="isReadOnly">Read Only</label>
                        <select class="form-control" id="readOnlyCheckBox" name="readOnly">
                            <option value="false">False</option>
                            <option value="true">True</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="isCheckedCheckBox">Already Checked</label>
                        <select class="form-control" id="checkedCheckBox" name="readOnly">
                            <option value="false">False</option>
                            <option value="true">True</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="styleCheckBox">Caption Style</label>

                        <select class="form-control" id="styleCheckBox" name="styleCheckBox">
                            <option value="H">Star</option>
                            <option value="8">Cross</option>
                            <option value="4">Checkmark</option>
                            <option value="n">Square</option>
                            <option value="l">Dot</option>
                            <option value="u">Diamond</option>
                        </select>
                    </div>
                    <div class="form-group col-md-auto mx-auto">
                        <button id="addCheckBox" type="button" class="btn btn-info">ADD</button>
                        <button id="removeCheckBox" type="button" style="display: none" class="btn btn-danger">REMOVE
                        </button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    var textInputs = {"inputs": []};

    function updateFieldsArea() {
        var fields = document.getElementById('fieldsTextArea');
        fields.value = JSON.stringify(textInputs, null, 2);
    }

    function hideRemoveButtons() {
        document.getElementById('removeTextField').style.display = "none";
        document.getElementById('removeSignatureField').style.display = "none";
        document.getElementById('removeDropDown').style.display = "none";
        document.getElementById('removeListBox').style.display = "none";
        document.getElementById('removeCheckBox').style.display = "none";
        document.getElementById('removeRadioButton').style.display = "none";
    }

    $(document).ready(function () {

        $.viewMap = {
            '0': $([]),
            'textField': $('#textField'),
            'signatureField': $('#signatureField'),
            'dropdown': $('#dropdown'),
            'listBox': $('#listBox'),
            'radioButton': $('#radioButton'),
            'checkBox': $('#checkBox')
        };

        $('#viewSelector').change(function () {
            // hide all
            $.each($.viewMap, function () {
                this.hide();
            });
            // show current
            $.viewMap[$(this).val()].show();
        });

        $(document).on('click', '#addSignatureField', function () {
            hideRemoveButtons();
            var fields = {};
            fields.pageIndex = +document.getElementById('signaturePageIndex').value;
            fields.fieldType = 'signatureField';
            fields.fieldName = document.getElementById('signatureFieldName').value;
            fields.isReadOnly = document.getElementById('readOnlySignature').value !== 'false';
            fields.check = document.getElementById('isSigned').value;
            fields.fieldRect = [
                +document.getElementById('xSignature').value,
                +document.getElementById('ySignature').value,
                +document.getElementById('widthSignature').value,
                +document.getElementById('heightSignature').value];
            fields.rgbBorder = [
                +document.getElementById('borderRedSignature').value,
                +document.getElementById('borderGreenSignature').value,
                +document.getElementById('borderBlueSignature').value];
            fields.rgbBackGround = [
                +document.getElementById('backGroundRedSignature').value,
                +document.getElementById('backGroundGreenSignature').value,
                +document.getElementById('backGroundBlueSignature').value,];
            textInputs.inputs.push(fields);
            updateFieldsArea();
            document.getElementById('removeSignatureField').style.display = "inline";
        });
        $(document).on('click', '#addTextField', function () {
            var fields = {};
            hideRemoveButtons();
            fields.pageIndex = +document.getElementById('pageIndexText').value;
            fields.fieldType = 'textField';
            fields.fieldName = document.getElementById('fieldNameText').value;
            fields.isReadOnly = document.getElementById('readOnlyText').value !== 'false';
            fields.fieldRect = [
                +document.getElementById('xText').value,
                +document.getElementById('yText').value,
                +document.getElementById('widthText').value,
                +document.getElementById('heightText').value];
            fields.rgbBorder = [
                +document.getElementById('borderRedText').value,
                +document.getElementById('borderGreenText').value,
                +document.getElementById('borderBlueText').value];
            fields.rgbBackGround = [
                +document.getElementById('backGroundRedText').value,
                +document.getElementById('backGroundGreenText').value,
                +document.getElementById('backGroundBlueText').value,];
            textInputs.inputs.push(fields);
            updateFieldsArea();
            document.getElementById('removeTextField').style.display = "inline";
        });
        $(document).on('click', '#addDropDown', function () {
            var fields = {};
            hideRemoveButtons();
            fields.pageIndex = +document.getElementById('pageIndexDropDown').value;
            fields.fieldType = 'dropdown';
            fields.fieldName = document.getElementById('fieldNameDropDown').value;
            var str = document.getElementById('optionsDropDown').value;
            fields.options = str.split(",");
            fields.isReadOnly = document.getElementById('readOnlyDropDown').value !== 'false';
            fields.isMultiSelect = document.getElementById('multiSelectDropDown').value !== 'false';
            fields.fieldRect = [
                +document.getElementById('xDropDown').value,
                +document.getElementById('yDropDown').value,
                +document.getElementById('widthDropDown').value,
                +document.getElementById('heightDropDown').value];
            fields.rgbBorder = [
                +document.getElementById('borderRedDropDown').value,
                +document.getElementById('borderGreenDropDown').value,
                +document.getElementById('borderBlueDropDown').value];
            fields.rgbBackGround = [
                +document.getElementById('backGroundRedDropDown').value,
                +document.getElementById('backGroundGreenDropDown').value,
                +document.getElementById('backGroundBlueDropDown').value,];
            textInputs.inputs.push(fields);
            updateFieldsArea();

            document.getElementById('removeDropDown').style.display = "inline";
        });
        $(document).on('click', '#addListBox', function () {
            var fields = {};
            hideRemoveButtons();
            fields.pageIndex = +document.getElementById('pageIndexListBox').value;
            fields.fieldType = 'listBox';
            fields.fieldName = document.getElementById('fieldNameListBox').value;
            fields.isReadOnly = document.getElementById('readOnlyListBox').value !== 'false';
            fields.isMultiSelect = document.getElementById('multiSelectListBox').value !== 'false';
            var str = document.getElementById('optionsListBox').value;
            fields.options = str.split(",");
            fields.fieldRect = [
                +document.getElementById('xListBox').value,
                +document.getElementById('yListBox').value,
                +document.getElementById('widthListBox').value,
                +document.getElementById('heightListBox').value];
            fields.rgbBorder = [
                +document.getElementById('borderRedListBox').value,
                +document.getElementById('borderGreenListBox').value,
                +document.getElementById('borderBlueListBox').value];
            fields.rgbBackGround = [
                +document.getElementById('backGroundRedListBox').value,
                +document.getElementById('backGroundGreenListBox').value,
                +document.getElementById('backGroundBlueListBox').value,];
            textInputs.inputs.push(fields);
            updateFieldsArea();

            document.getElementById('removeListBox').style.display = "inline";
        });
        $(document).on('click', '#addCheckBox', function () {
            var fields = {};
            hideRemoveButtons();
            fields.pageIndex = +document.getElementById('pageIndexCheckBox').value;
            fields.fieldType = 'checkBox';
            fields.fieldName = document.getElementById('fieldNameCheckBox').value;
            fields.style = document.getElementById('styleCheckBox').value;
            fields.isReadOnly = document.getElementById('readOnlyCheckBox').value !== 'false';
            fields.check = document.getElementById('checkedCheckBox').value !== 'false';
            fields.coordinates = [
                +document.getElementById('xCheckBox').value,
                +document.getElementById('yCheckBox').value,
                +document.getElementById('widthCheckBox').value,
                +document.getElementById('heightCheckBox').value];
            fields.rgbBorder = [
                +document.getElementById('borderRedCheckBox').value,
                +document.getElementById('borderGreenCheckBox').value,
                +document.getElementById('borderBlueCheckBox').value];
            fields.rgbBackGround = [
                +document.getElementById('backGroundRedCheckBox').value,
                +document.getElementById('backGroundGreenCheckBox').value,
                +document.getElementById('backGroundBlueCheckBox').value,];
            textInputs.inputs.push(fields);
            updateFieldsArea();

            document.getElementById('removeCheckBox').style.display = "inline";
        });
        $(document).on('click', '#addRadioButton', function () {
            var fields = {};
            hideRemoveButtons();
            fields.pageIndex = +document.getElementById('pageIndexRadioButton').value;
            fields.fieldType = 'radioButton';
            fields.fieldName = document.getElementById('fieldNameRadioButton').value;
            var str = document.getElementById('optionsRadioButton').value;
            fields.options = str.split(",");
            fields.style = document.getElementById('styleRadioButton').value;
            fields.isReadOnly = document.getElementById('readOnlyRadioButton').value !== 'false';
            fields.coordinates = [
                +document.getElementById('xRadioButton').value,
                +document.getElementById('yRadioButton').value,
                +document.getElementById('widthRadioButton').value,
                +document.getElementById('heightRadioButton').value];
            fields.text = [+document.getElementById('xTextRadioButton').value,
                +document.getElementById('yTextRadioButton').value];
            fields.rgbBorder = [
                +document.getElementById('borderRedRadioButton').value,
                +document.getElementById('borderGreenRadioButton').value,
                +document.getElementById('borderBlueRadioButton').value];
            fields.rgbBackGround = [
                +document.getElementById('backGroundRedRadioButton').value,
                +document.getElementById('backGroundGreenRadioButton').value,
                +document.getElementById('backGroundBlueRadioButton').value,];
            textInputs.inputs.push(fields);
            updateFieldsArea();
            document.getElementById('removeRadioButton').style.display = "inline";
        });

        $(document).on('click', '#removeTextField', function () {
            textInputs.inputs.pop();
            updateFieldsArea();
        });
        $(document).on('click', '#removeSignatureField', function () {
            textInputs.inputs.pop();
            updateFieldsArea();
        });
        $(document).on('click', '#removeDropDown', function () {
            textInputs.inputs.pop();
            updateFieldsArea();
        });
        $(document).on('click', '#removeListBox', function () {
            textInputs.inputs.pop();
            updateFieldsArea();
        });
        $(document).on('click', '#removeCheckBox', function () {
            textInputs.inputs.pop();
            updateFieldsArea();
        });
        $(document).on('click', '#removeRadioButton', function () {
            textInputs.inputs.pop();
            updateFieldsArea();
        });

    });

</script>
</body>
</html>

