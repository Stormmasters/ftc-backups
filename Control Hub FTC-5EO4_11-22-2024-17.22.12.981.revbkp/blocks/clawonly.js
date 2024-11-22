// IDENTIFIERS_USED=gamepad1,oneAsServo,twoAsServo

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  telemetry.addLine('Initialization');
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      telemetry.addLine('in main loop');
      claw();
      telemetry.addTextData('two', String(twoAsServo.getPosition()));
      telemetry.addTextData('one', String(oneAsServo.getPosition()));
      telemetry.addTextData('left trigger', String(gamepad1.getLeftTrigger()));
      telemetry.addTextData('roghttrigger', String(gamepad1.getRightTrigger()));
      telemetry.update();
    }
  }
}

/**
 * Describe this function...
 */
function claw() {
  if (gamepad1.getLeftBumper()) {
    twoAsServo.setPosition(0.5);
  }
  if (gamepad1.getLeftTrigger() > 0) {
    twoAsServo.setPosition(0);
  }
  if (gamepad1.getRightBumper()) {
    oneAsServo.setPosition(0.2);
  }
  if (gamepad1.getRightTrigger() > 0) {
    oneAsServo.setPosition(0.5);
  }
}
