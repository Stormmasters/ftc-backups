// IDENTIFIERS_USED=BLmotorAsDcMotor,BRmotorAsDcMotor,FLmotorAsDcMotor,FRmotorAsDcMotor

var reverse, time, USE_WEBCAM, turn, movePower, motorAdjustBL, motorAdjustBR, motorAdjustFL, motorAdjustFR, motorStartPosBL, myTfodRecognitions, turnright, power, motorStartPosBR, myVisionPortal, myTfodProcessorBuilder, movePowerDefault, turnleft, forwardcount, motorStartPosFL, myTfodProcessor, myTfodRecognition, movePowerOpposite, x, motorStartPosFR, tmp, myVisionPortalBuilder, y, objectXSum, objectXCount;

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  USE_WEBCAM = true;
  // Initialize TFOD before waitForStart.
  initTfod();
  motorSetup();
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      // Share the CPU.
      linearOpMode.sleep(20);
      // Push telemetry to the Driver Station.
      telemetry.update();
      break;
    }
    motorTelemetry();
    // Push telemetry to the Driver Station.
    telemetry.update();
    programRedRight();
    // Push telemetry to the Driver Station.
    telemetry.update();
    // Share the CPU.
    linearOpMode.sleep(3000);
  }
}

/**
 * Describe this function...
 */
function motorSetup() {
  BRmotorAsDcMotor.setDirection("REVERSE");
  FRmotorAsDcMotor.setDirection("REVERSE");
  movePowerDefault = 0.25;
  motorAdjustBL = 1;
  motorAdjustBR = 1;
  motorAdjustFL = 1;
  motorAdjustFR = 1;
  BLmotorAsDcMotor.setZeroPowerBehavior("BRAKE");
  BRmotorAsDcMotor.setZeroPowerBehavior("BRAKE");
  FLmotorAsDcMotor.setZeroPowerBehavior("BRAKE");
  FRmotorAsDcMotor.setZeroPowerBehavior("BRAKE");
}

/**
 * Describe this function...
 */
function motorTelemetry() {
  telemetry.addTextData('BL currentPosition', String(BLmotorAsDcMotor.getCurrentPosition()));
  telemetry.addTextData('BL amps', String(BLmotorAsDcMotor.getCurrent("AMPS")));
  telemetry.addTextData('BL velocity', String(BLmotorAsDcMotor.getVelocity()));
}

/**
 * Describe this function...
 */
function turn2() {
  turn = 1;
  turnright = turn;
  turnleft = turn * -1;
  FLmotorAsDcMotor.setDualPower(turnleft, BLmotorAsDcMotor, turnleft);
  FRmotorAsDcMotor.setDualPower(turnright, BRmotorAsDcMotor, turnright);
}

/**
 * Describe this function...
 */
function motorMovePower() {
  BLmotorAsDcMotor.setPower(power);
  BRmotorAsDcMotor.setPower(power);
  FLmotorAsDcMotor.setPower(power);
  FRmotorAsDcMotor.setPower(power);
}

/**
 * Describe this function...
 */
function forward2() {
  linearOpMode.waitForStart();
  if (forwardcount > 0) {
    forwardcount = (typeof forwardcount == 'number' ? forwardcount : 0) + -1;
    BRmotorAsDcMotor.setDirection("REVERSE");
    BLmotorAsDcMotor.setDualPower(-0.5, BRmotorAsDcMotor, -0.47);
    linearOpMode.sleep(2000);
    BLmotorAsDcMotor.setDualPower(0, BRmotorAsDcMotor, 0);
    linearOpMode.sleep(500);
    BLmotorAsDcMotor.setDualPower(0, BRmotorAsDcMotor, 0);
  }
}

/**
 * Describe this function...
 */
function pivot(reverse, time) {
  telemetry.addLine('pivot');
  telemetry.addTextData('reverse', String(reverse));
  telemetry.addTextData('time', String(time));
  // Push telemetry to the Driver Station.motorAdjust
  telemetry.update();
  movePower = movePowerDefault;
  if (reverse == 1) {
    movePower = movePower * -1;
  }
  movePowerOpposite = movePower * -1;
  BLmotorAsDcMotor.setDualPower(movePower, BRmotorAsDcMotor, movePowerOpposite);
  FLmotorAsDcMotor.setDualPower(movePower, FRmotorAsDcMotor, movePowerOpposite);
  linearOpMode.sleep(time);
  BLmotorAsDcMotor.setDualPower(0, BRmotorAsDcMotor, 0);
  FLmotorAsDcMotor.setDualPower(0, FRmotorAsDcMotor, 0);
}

/**
 * Describe this function...
 */
function strafe(reverse, time) {
  telemetry.addLine('strafe');
  movePower = movePowerDefault;
  if (reverse == 1) {
    movePower = movePower * -1;
  }
  movePowerOpposite = movePower * -1;
  BLmotorAsDcMotor.setDualPower(movePowerOpposite, BRmotorAsDcMotor, movePower);
  FLmotorAsDcMotor.setDualPower(movePower, FRmotorAsDcMotor, movePowerOpposite);
  linearOpMode.sleep(time);
  BLmotorAsDcMotor.setDualPower(0, BRmotorAsDcMotor, 0);
  FLmotorAsDcMotor.setDualPower(0, FRmotorAsDcMotor, 0);
}

/**
 * Describe this function...
 */
function selectspikeline() {
  telemetry.addLine('selectSpikeLine');
  if (x > 510) {
    telemetry.addTextData('turnright', String(turnright));
  }
}

/**
 * Describe this function...
 */
function move(reverse, time) {
  telemetry.addLine('move');
  setMotorStartPosition();
  movePower = movePowerDefault;
  if (reverse == 1) {
    movePower = movePower * -1;
  }
  BLmotorAsDcMotor.setDualPower(movePower, BRmotorAsDcMotor, movePower);
  FLmotorAsDcMotor.setDualPower(movePower, FRmotorAsDcMotor, movePower);
  linearOpMode.sleep(time);
  BLmotorAsDcMotor.setDualPower(0, BRmotorAsDcMotor, 0);
  FLmotorAsDcMotor.setDualPower(0, FRmotorAsDcMotor, 0);
  showMotorDistance();
}

/**
 * Describe this function...
 */
function programCalibration() {
  move(1, 1900);
  // Share the CPU.
  linearOpMode.sleep(1500);
  move(0, 1900);
  // Share the CPU.
  linearOpMode.sleep(1500);
  pivot(0, 1900);
  // Share the CPU.
  linearOpMode.sleep(1500);
  strafe(0, 2400);
  // Share the CPU.
  linearOpMode.sleep(1500);
  strafe(1, 2400);
  // Share the CPU.
  linearOpMode.sleep(1500);
  pivot(1, 1900);
}

/**
 * Describe this function...
 */
function programRedRight() {
  strafe(1, 600);
  // Share the CPU.
  linearOpMode.sleep(1500);
  move(0, 3000);
  // Share the CPU.
  linearOpMode.sleep(1500);
  move(1, 400);
}

/**
 * Describe this function...
 */
function programBlueLeft() {
  strafe(1, 600);
  // Share the CPU.
  linearOpMode.sleep(1500);
  move(0, 3000);
  // Share the CPU.
  linearOpMode.sleep(1500);
  move(1, 400);
}

/**
 * Describe this function...
 */
function showMotorDistance() {
  telemetry.addTextData('BL travel', String(BLmotorAsDcMotor.getCurrentPosition() - motorStartPosBL));
  telemetry.addTextData('BR travel', String(BRmotorAsDcMotor.getCurrentPosition() - motorStartPosBR));
  telemetry.addTextData('FL travel', String(FLmotorAsDcMotor.getCurrentPosition() - motorStartPosFL));
  telemetry.addTextData('FR travel', String(FRmotorAsDcMotor.getCurrentPosition() - motorStartPosFR));
}

/**
 * Describe this function...
 */
function programRedLeft() {
  move(0, 3800);
  // Share the CPU.
  linearOpMode.sleep(1500);
  pivot(0, 1900);
  // Share the CPU.
  linearOpMode.sleep(1500);
  move(0, 5700);
  // Share the CPU.
  linearOpMode.sleep(1500);
  move(1, 600);
}

/**
 * Describe this function...
 */
function programBlueRight() {
  move(0, 3800);
  // Share the CPU.
  linearOpMode.sleep(1500);
  pivot(1, 1900);
  // Share the CPU.
  linearOpMode.sleep(1500);
  move(0, 5700);
  // Share the CPU.
  linearOpMode.sleep(1500);
  move(1, 600);
}

/**
 * Describe this function...
 */
function setMotorStartPosition() {
  motorStartPosBL = BLmotorAsDcMotor.getCurrentPosition();
  motorStartPosBR = BRmotorAsDcMotor.getCurrentPosition();
  motorStartPosFL = FLmotorAsDcMotor.getCurrentPosition();
  motorStartPosFR = FRmotorAsDcMotor.getCurrentPosition();
}

/**
 * Display info (using telemetry) for a detected object
 */
function telemetryTfod() {
  // Get a list of recognitions from TFOD.
  myTfodRecognitions = JSON.parse(tensorFlowAccess.getRecognitions(myTfodProcessor));
  telemetryAddTextData('# Objects Detected', listLength(miscAccess, myTfodRecognitions));
  // Iterate through list and call a function to
  // display info for each recognized object.
  for (var myTfodRecognition_index in myTfodRecognitions) {
    myTfodRecognition = myTfodRecognitions[myTfodRecognition_index];
    telemetry.addLine('');
    // Display the label and confidence for the recognition.
    telemetryAddTextData('Image', [startBlockExecution("TfodRecognition.Label") ? endBlockExecution(myTfodRecognition.Label) : 0,' (',miscAccess.formatNumber((startBlockExecution("TfodRecognition.Confidence") ? endBlockExecution(myTfodRecognition.Confidence) : 0) * 100, 0),' % Conf.)'].join(''));
    x = ((startBlockExecution("TfodRecognition.Left") ? endBlockExecution(myTfodRecognition.Left) : 0) + (startBlockExecution("TfodRecognition.Right") ? endBlockExecution(myTfodRecognition.Right) : 0)) / 2;
    y = ((startBlockExecution("TfodRecognition.Top") ? endBlockExecution(myTfodRecognition.Top) : 0) + (startBlockExecution("TfodRecognition.Bottom") ? endBlockExecution(myTfodRecognition.Bottom) : 0)) / 2;
    if (x) {
      objectXSum = (typeof objectXSum == 'number' ? objectXSum : 0) + x;
      objectXCount = (typeof objectXCount == 'number' ? objectXCount : 0) + 1;
    }
    // Display the position of the center of the detection boundary for the recognition
    telemetryAddTextData('- Position', [miscAccess.formatNumber(x, 0),', ',miscAccess.formatNumber(y, 0)].join(''));
    // Display the size of detection boundary for the recognition
    telemetryAddTextData('- Size', [miscAccess.formatNumber(startBlockExecution("TfodRecognition.Width") ? endBlockExecution(myTfodRecognition.Width) : 0, 0),' x ',miscAccess.formatNumber(startBlockExecution("TfodRecognition.Height") ? endBlockExecution(myTfodRecognition.Height) : 0, 0)].join(''));
  }
}

/**
 * Initialize TensorFlow Object Detection.
 */
function initTfod() {
  myTfodProcessorBuilder = tensorFlowAccess.createBuilder();
  // Set the name of the file where the model can be found.
  tensorFlowAccess.setModelFileName(myTfodProcessorBuilder, 'model_20240216_174729.tflite');
  // Set the full ordered list of labels the model is trained to recognize.
  tensorFlowAccess.setModelLabels(myTfodProcessorBuilder, JSON.stringify(['ball', 'cube']));
  // Set the aspect ratio for the images used when the model was created.
  tensorFlowAccess.setModelAspectRatio(myTfodProcessorBuilder, 16 / 9);
  myTfodProcessor = tensorFlowAccess.build(myTfodProcessorBuilder);
  myVisionPortalBuilder = visionPortalAccess.createBuilder();
  if (USE_WEBCAM) {
    // Use a webcam.
    visionPortalAccess.setCamera(myVisionPortalBuilder, navigationAccess.getWebcamName("Webcam 1"));
  } else {
    // Use the device's back camera.
    visionPortalAccess.setCamera(myVisionPortalBuilder, navigationAccess.getBuiltinCameraDirection("BACK"));
  }
  visionPortalAccess.addProcessor(myVisionPortalBuilder, myTfodProcessor);
  myVisionPortal = visionPortalAccess.build(myVisionPortalBuilder);
}

/**
 * Describe this function...
 */
function motorAdjustmentUpdate() {
  motorAdjustBL = Math.abs(BLmotorAsDcMotor.getCurrentPosition() - motorStartPosBL);
  motorAdjustBR = Math.abs(BRmotorAsDcMotor.getCurrentPosition() - motorAdjustBR);
  motorAdjustFL = Math.abs(FLmotorAsDcMotor.getCurrentPosition() - motorStartPosFL);
  motorAdjustFR = Math.abs(FRmotorAsDcMotor.getCurrentPosition() - motorStartPosFR);
  tmp = 0;
  telemetry.addTextData('tmp', String(tmp));
  tmp = tmp + motorAdjustBL;
  telemetry.addTextData('tmp', String(tmp));
  tmp = tmp + motorAdjustBR;
  telemetry.addTextData('tmp', String(tmp));
  tmp = tmp + motorAdjustFL;
  telemetry.addTextData('tmp', String(tmp));
  tmp = tmp + motorAdjustFR;
  telemetry.addTextData('tmp', String(tmp));
  tmp = tmp / 4;
  telemetry.addTextData('tmp', String(tmp));
  motorAdjustBL = tmp / motorAdjustBL;
  motorAdjustBR = tmp / motorAdjustBR;
  motorAdjustFL = tmp / motorAdjustFL;
  motorAdjustFR = tmp / motorAdjustFR;
}
