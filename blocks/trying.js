var speed;

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  speed = 1;
  rightmotorAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      telemetry.update();
      if (gamepad1.getLeftBumper()) {
        speed = 0.5;
      } else {
        speed = 1;
      }
      leftmotorAsDcMotor.setDualPower(gamepad1.getLeftStickY() * speed, rightmotorAsDcMotor, gamepad1.getRightStickY());
    }
  }
}
