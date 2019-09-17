<?php

use function MongoDB\BSON\toJSON;

$workingDirectory = dirname(__FILE__);

$randomFileName = uniqid('pdf_') . '.pdf';
$randomInputName = uniqid('json_') . '.json';
$randomOutputName = uniqid('output_').'.pdf';

$targetPath = $workingDirectory . "/uploads/" . $randomFileName;
$fields = $_POST['fields'];


move_uploaded_file($_FILES['pdffile']['tmp_name'], $targetPath);

$inputData = array();
$inputData = json_decode($fields, true);
$inputData['fileUrl'] = "file://" . $targetPath;
$inputData['fileName'] = $randomOutputName;

file_put_contents($workingDirectory . "/downloads/" . $randomInputName, json_encode($inputData));


$cmd = "java -jar $workingDirectory/downloads/maven-1.0-SNAPSHOT-jar-with-dependencies.jar $workingDirectory/downloads/$randomInputName";

$pdfOutput = exec($cmd, $out);

$downloadPath = "/downloads/";

rename($randomOutputName, $workingDirectory . "/downloads/".$randomOutputName);

unlink($workingDirectory."/uploads/" . $randomFileName);

?>


<html>
<body>
Click here to <a href="<?php echo "downloads/".$randomOutputName; ?>">download</a> your pdf!
</body>
</html>