// IDENTIFIERS_USED=colorSensorAsColorSensor,gamepad1,mtrBlAsDcMotor,mtrBrAsDcMotor,mtrFlAsDcMotor,mtrFrAsDcMotor

/**
 * Describe this function...
 */
function colorTelementry() {
  if (1 == colorSensorAsColorSensor.getBlue()) {
    telemetry.addLine('colorSensor: Blue');
  } else if (1 == colorSensorAsColorSensor.getRed()) {
    telemetry.addLine('colorSensor: Red');
  } else {
    telemetry.addLine('colorSensor: None');
  }
}

/**
 * Describe this function...
 */
function mecanumSetup() {
  mtrFrAsDcMotor.setMode("RUN_USING_ENCODER");
  mtrBrAsDcMotor.setMode("RUN_USING_ENCODER");
  mtrBlAsDcMotor.setMode("RUN_USING_ENCODER");
  mtrBrAsDcMotor.setMode("RUN_USING_ENCODER");
}

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  telemetry.addLine('waitForStart');
  mecanumSetup();
  if (linearOpMode.opModeIsActive()) {
    telemetry.addLine('opModelsActive');
    while (linearOpMode.opModeIsActive()) {
      mecanumMove();
      gamepadTelem();
      mtrPosTelem();
      colorTelementry();
      telemetry.update();
    }
  }
}

/**
 * Describe this function...
 */
function mtrPosTelem() {
  telemetry.addTextData('mtrFr', String(mtrFrAsDcMotor.getCurrentPosition()));
  telemetry.addTextData('mtrBr', String(mtrBrAsDcMotor.getCurrentPosition()));
  telemetry.addTextData('mtrBl', String(mtrBlAsDcMotor.getCurrentPosition()));
  telemetry.addTextData('mtrFl', String(mtrFlAsDcMotor.getCurrentPosition()));
}

/**
 * Describe this function...
 */
function mecanumMove() {
  mtrBlAsDcMotor.setDualVelocity(gamepad1.getRightStickY() * -1, mtrBrAsDcMotor, gamepad1.getRightStickY());
}

/**
 * Describe this function...
 */
function gamepadTelem() {
  telemetry.addTextData('GP1: RightStickX', String(gamepad1.getRightStickX()));
  telemetry.addTextData('GP1: RightStickY', String(gamepad1.getRightStickY()));
  telemetry.addTextData('GP1: LeftStickX', String(gamepad1.getLeftStickX()));
  telemetry.addTextData('GP1: LeftStickY', String(gamepad1.getLeftStickY()));
}
