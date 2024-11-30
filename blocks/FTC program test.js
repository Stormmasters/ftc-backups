/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      if (gamepad1.getDpadRight()) {
        backleftAsDcMotor.setDualPower(1, backrightAsDcMotor, 1);
        toprightAsDcMotor.setDualPower(-1, topleftAsDcMotor, -1);
      }
      if (gamepad1.getDpadLeft()) {
        backleftAsDcMotor.setDualPower(-1, backrightAsDcMotor, -1);
        toprightAsDcMotor.setDualPower(1, topleftAsDcMotor, 1);
      }
      toprightAsDcMotor.setDualPower(gamepad1.getLeftStickY(), backrightAsDcMotor, gamepad1.getLeftStickY());
      topleftAsDcMotor.setDualPower(gamepad1.getRightStickY(), backleftAsDcMotor, gamepad1.getRightStickY());
      telemetry.update();
    }
  }
}
