// IDENTIFIERS_USED=backleftAsDcMotor,backrightAsDcMotor,clawAsServo,elevatorAsDcMotor,frontleftAsDcMotor,frontrightAsDcMotor,gamepad1,gamepad2

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  frontrightAsDcMotor.setDirection("REVERSE");
  backrightAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      frontrightAsDcMotor.setDualPower(gamepad1.getRightStickY(), frontleftAsDcMotor, gamepad1.getRightStickY());
      backrightAsDcMotor.setDualPower(gamepad1.getLeftStickY(), backleftAsDcMotor, gamepad1.getLeftStickY());
      if (gamepad1.getSquare()) {
        frontrightAsDcMotor.setPower(-1);
        backrightAsDcMotor.setPower(-1);
        frontleftAsDcMotor.setPower(1);
        backleftAsDcMotor.setPower(1);
      }
      if (gamepad1.getTriangle()) {
        frontrightAsDcMotor.setPower(1);
        backrightAsDcMotor.setPower(1);
        frontleftAsDcMotor.setPower(-1);
        backleftAsDcMotor.setPower(-1);
      }
      if (gamepad1.getLeftBumper()) {
        frontrightAsDcMotor.setPower(-1);
        backrightAsDcMotor.setPower(1);
        frontleftAsDcMotor.setPower(1);
        backleftAsDcMotor.setPower(-1);
      }
      if (gamepad1.getRightBumper()) {
        frontrightAsDcMotor.setPower(1);
        backrightAsDcMotor.setPower(-1);
        frontleftAsDcMotor.setPower(-1);
        backleftAsDcMotor.setPower(1);
      }
      if (gamepad2.getA()) {
        telemetryAddTextData('test A', 'it works');
        clawAsServo.setPosition(0);
      }
      if (gamepad2.getB()) {
        telemetryAddTextData('test B', 'B works');
        clawAsServo.setPosition(0.5);
      }
      elevatorAsDcMotor.setDualPower(gamepad2.getLeftStickY(), elevatorAsDcMotor, gamepad2.getLeftStickY());
      telemetry.update();
    }
  }
}
