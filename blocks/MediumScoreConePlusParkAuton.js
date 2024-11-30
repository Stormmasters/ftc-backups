// IDENTIFIERS_USED=BackLeftAsDcMotor,BackRightAsDcMotor,clawAsServo,ElevatorAsDcMotor,FrontLeftAsDcMotor,FrontRightAsDcMotor

var i, index, recognition, recognitions;

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  vuforiaCurrentGameAccess.initialize_withWebcam_2("Webcam 1", '', true, true, "NONE", 0, 0, 0, "XZY", 90, 90, 0, true);
  // Set isModelTensorFlow2 to true if you used a TensorFlow
  // 2 tool, such as ftc-ml, to create the model.
  //
  // Set isModelQuantized to true if the model is
  // quantized. Models created with ftc-ml are quantized.
  //
  // Set inputSize to the image size corresponding to the model.
  // If your model is based on SSD MobileNet v2
  // 320x320, the image size is 300 (srsly!).
  // If your model is based on SSD MobileNet V2 FPNLite 320x320, the image size is 320.
  // If your model is based on SSD MobileNet V1 FPN 640x640 or
  // SSD MobileNet V2 FPNLite 640x640, the image size is 640.
  tfodAccess.useModelFromFile('finalsleeve.tflite', JSON.stringify(['CS1', 'CS2', 'CS3']), true, true, 320);
  tfodAccess.initialize(vuforiaCurrentGameAccess, 0.7, true, true);
  tfodAccess.setClippingMargins(0, 80, 0, 0);
  tfodAccess.activate();
  tfodAccess.setZoom(1, 16 / 9);
  telemetryAddTextData('DS preview on/off', '3 dots, Camera Stream');
  telemetryAddTextData('>', 'Press Play to start');
  telemetry.update();
  clawAsServo.setPosition(1);
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      // Get a list of recognitions from TFOD.
      recognitions = JSON.parse(tfodAccess.getRecognitions());
      // If list is empty, inform the user. Otherwise, go
      // through list and display info for each recognition.
      if (listLength(miscAccess, recognitions) == 0) {
        telemetryAddTextData('TFOD', 'No items detected.');
      } else {
        index = 0;
        // Iterate through list and call a function to
        // display info for each recognized object.
        for (var recognition_index in recognitions) {
          recognition = recognitions[recognition_index];
          displayInfo(index);
          index = index + 1;
        }
      }
      if (listLength(miscAccess, recognitions) == 0) {
        telemetryAddTextData('TFOD', 'No items detected.');
      } else if ('CS2' == (startBlockExecution("Recognition.Label") ? endBlockExecution(recognition.Label) : 0)) {
        clawAsServo.setPosition(1);
        FrontLeftAsDcMotor.setDirection("REVERSE");
        BackLeftAsDcMotor.setDirection("REVERSE");
        clawAsServo.setPosition(1);
        telemetryAddTextData('ID:', 'CS2');
        BackLeftAsDcMotor.setPower(0.5);
        FrontRightAsDcMotor.setPower(0.5);
        FrontLeftAsDcMotor.setPower(0.5);
        BackRightAsDcMotor.setPower(0.5);
        telemetryAddTextData('ID:', 'CS2');
        linearOpMode.sleep(760);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(800);
        FrontRightAsDcMotor.setPower(-0.5);
        BackRightAsDcMotor.setPower(0.5);
        FrontLeftAsDcMotor.setPower(0.5);
        BackLeftAsDcMotor.setPower(-0.5);
        linearOpMode.sleep(550);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(800);
        ElevatorAsDcMotor.setPower(-0.62);
        linearOpMode.sleep(7900);
        ElevatorAsDcMotor.setPower(0);
        linearOpMode.sleep(10);
        BackLeftAsDcMotor.setPower(0.5);
        FrontRightAsDcMotor.setPower(0.5);
        FrontLeftAsDcMotor.setPower(0.5);
        BackRightAsDcMotor.setPower(0.5);
        linearOpMode.sleep(50);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(50);
        ElevatorAsDcMotor.setPower(0.62);
        linearOpMode.sleep(1000);
        ElevatorAsDcMotor.setPower(0);
        clawAsServo.setPosition(-0.5);
        BackLeftAsDcMotor.setPower(-1);
        FrontLeftAsDcMotor.setPower(-1);
        BackRightAsDcMotor.setPower(-1);
        FrontRightAsDcMotor.setPower(-1);
        linearOpMode.sleep(50);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(100);
        FrontRightAsDcMotor.setPower(0.5);
        BackRightAsDcMotor.setPower(-0.5);
        FrontLeftAsDcMotor.setPower(-0.5);
        BackLeftAsDcMotor.setPower(0.5);
        linearOpMode.sleep(480);
        clawAsServo.setPosition(1);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(80);
        tfodAccess.deactivate();
        telemetry.update();
      } else if ('CS3' == (startBlockExecution("Recognition.Label") ? endBlockExecution(recognition.Label) : 0)) {
        clawAsServo.setPosition(1);
        FrontLeftAsDcMotor.setDirection("REVERSE");
        BackLeftAsDcMotor.setDirection("REVERSE");
        clawAsServo.setPosition(1);
        telemetryAddTextData('ID:', 'CS2');
        BackLeftAsDcMotor.setPower(0.5);
        FrontRightAsDcMotor.setPower(0.5);
        FrontLeftAsDcMotor.setPower(0.5);
        BackRightAsDcMotor.setPower(0.5);
        telemetryAddTextData('ID:', 'CS2');
        linearOpMode.sleep(760);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(800);
        FrontRightAsDcMotor.setPower(-0.5);
        BackRightAsDcMotor.setPower(0.5);
        FrontLeftAsDcMotor.setPower(0.5);
        BackLeftAsDcMotor.setPower(-0.5);
        linearOpMode.sleep(550);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(800);
        ElevatorAsDcMotor.setPower(-0.62);
        linearOpMode.sleep(7900);
        ElevatorAsDcMotor.setPower(0);
        linearOpMode.sleep(10);
        BackLeftAsDcMotor.setPower(0.5);
        FrontRightAsDcMotor.setPower(0.5);
        FrontLeftAsDcMotor.setPower(0.5);
        BackRightAsDcMotor.setPower(0.5);
        linearOpMode.sleep(50);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(50);
        ElevatorAsDcMotor.setPower(0.62);
        linearOpMode.sleep(1000);
        ElevatorAsDcMotor.setPower(0);
        clawAsServo.setPosition(-0.5);
        BackLeftAsDcMotor.setPower(-1);
        FrontLeftAsDcMotor.setPower(-1);
        BackRightAsDcMotor.setPower(-1);
        FrontRightAsDcMotor.setPower(-1);
        linearOpMode.sleep(50);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(100);
        FrontRightAsDcMotor.setPower(0.5);
        BackRightAsDcMotor.setPower(-0.5);
        FrontLeftAsDcMotor.setPower(-0.5);
        BackLeftAsDcMotor.setPower(0.5);
        linearOpMode.sleep(480);
        clawAsServo.setPosition(1);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(80);
        FrontRightAsDcMotor.setPower(-0.3);
        BackRightAsDcMotor.setPower(0.3);
        FrontLeftAsDcMotor.setPower(0.3);
        BackLeftAsDcMotor.setPower(-0.3);
        linearOpMode.sleep(2300);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        tfodAccess.deactivate();
        telemetry.update();
      } else {
        clawAsServo.setPosition(1);
        FrontLeftAsDcMotor.setDirection("REVERSE");
        BackLeftAsDcMotor.setDirection("REVERSE");
        clawAsServo.setPosition(1);
        telemetryAddTextData('ID:', 'CS2');
        BackLeftAsDcMotor.setPower(0.5);
        FrontRightAsDcMotor.setPower(0.5);
        FrontLeftAsDcMotor.setPower(0.5);
        BackRightAsDcMotor.setPower(0.5);
        telemetryAddTextData('ID:', 'CS2');
        linearOpMode.sleep(760);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(800);
        FrontRightAsDcMotor.setPower(-0.5);
        BackRightAsDcMotor.setPower(0.5);
        FrontLeftAsDcMotor.setPower(0.5);
        BackLeftAsDcMotor.setPower(-0.5);
        linearOpMode.sleep(530);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(800);
        ElevatorAsDcMotor.setPower(-0.62);
        linearOpMode.sleep(7900);
        ElevatorAsDcMotor.setPower(0);
        linearOpMode.sleep(10);
        BackLeftAsDcMotor.setPower(0.5);
        FrontRightAsDcMotor.setPower(0.5);
        FrontLeftAsDcMotor.setPower(0.5);
        BackRightAsDcMotor.setPower(0.5);
        linearOpMode.sleep(50);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(50);
        ElevatorAsDcMotor.setPower(0.62);
        linearOpMode.sleep(1000);
        ElevatorAsDcMotor.setPower(0);
        clawAsServo.setPosition(-0.5);
        BackLeftAsDcMotor.setPower(-1);
        FrontLeftAsDcMotor.setPower(-1);
        BackRightAsDcMotor.setPower(-1);
        FrontRightAsDcMotor.setPower(-1);
        linearOpMode.sleep(50);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(100);
        FrontRightAsDcMotor.setPower(0.5);
        BackRightAsDcMotor.setPower(-0.5);
        FrontLeftAsDcMotor.setPower(-0.5);
        BackLeftAsDcMotor.setPower(0.5);
        linearOpMode.sleep(480);
        clawAsServo.setPosition(1);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        linearOpMode.sleep(80);
        FrontRightAsDcMotor.setPower(0.5);
        BackRightAsDcMotor.setPower(-0.5);
        FrontLeftAsDcMotor.setPower(-0.5);
        BackLeftAsDcMotor.setPower(0.5);
        linearOpMode.sleep(1000);
        BackLeftAsDcMotor.setPower(0);
        BackRightAsDcMotor.setPower(0);
        FrontLeftAsDcMotor.setPower(0);
        FrontRightAsDcMotor.setPower(0);
        tfodAccess.deactivate();
        telemetry.update();
      }
      telemetry.update();
    }
  }
  tfodAccess.deactivate();
}

function mathMean(myList) {
  return myList.reduce(function(x, y) {return x + y;}) / myList.length;
}

/**
 * Describe this function...
 */
function displayInfo(i) {
  // Display the location of the top left corner
  // of the detection boundary for the recognition
  telemetryAddTextData(['Label: ',startBlockExecution("Recognition.Label") ? endBlockExecution(recognition.Label) : 0,', Confidence: ',startBlockExecution("Recognition.Confidence") ? endBlockExecution(recognition.Confidence) : 0].join(''), ['X: ',Math.round(mathMean([miscAccess.roundDecimal(startBlockExecution("Recognition.Left") ? endBlockExecution(recognition.Left) : 0, 0), miscAccess.roundDecimal(startBlockExecution("Recognition.Right") ? endBlockExecution(recognition.Right) : 0, 0)])),', Y: ',Math.round(mathMean([miscAccess.roundDecimal(startBlockExecution("Recognition.Top") ? endBlockExecution(recognition.Top) : 0, 0), miscAccess.roundDecimal(startBlockExecution("Recognition.Bottom") ? endBlockExecution(recognition.Bottom) : 0, 0)]))].join(''));
}
