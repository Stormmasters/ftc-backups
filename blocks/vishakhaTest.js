/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      rightmotorAsDcMotor.setDirection("REVERSE");
      rightmotorAsDcMotor.setPower(gamepad1.getLeftStickY());
      leftmotorAsDcMotor.setPower(gamepad1.getRightStickY());
    }
    if (gamepad1.getA()) {
    }
    telemetry.update();
  }
}
