/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      LeftMotorAsDcMotor.setDualPower(0.5, RightMotorAsDcMotor, -0.5);
      linearOpMode.sleep(300);
      LeftMotorAsDcMotor.setDualPower(0, RightMotorAsDcMotor, 0);
      telemetry.update();
    }
  }
}
