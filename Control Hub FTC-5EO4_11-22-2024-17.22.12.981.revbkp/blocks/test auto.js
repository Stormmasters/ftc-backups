/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  LeftMotorAsDcMotor.setDirection("FORWARD");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    for (var count = 0; count < 1; count++) {
      LeftMotorAsDcMotor.setDualPower(0.3, RightMotorAsDcMotor, -0.3);
      linearOpMode.sleep(250);
      LeftMotorAsDcMotor.setDualPower(0, RightMotorAsDcMotor, 0);
      LeftMotorAsDcMotor.setDualPower(0.2, RightMotorAsDcMotor, -0.2);
      DuckingMotorAsDcMotor.setPower(-1);
      linearOpMode.sleep(5000);
      DuckingMotorAsDcMotor.setPower(0);
      linearOpMode.sleep(300);
      RightMotorAsDcMotor.setDualPower(1, LeftMotorAsDcMotor, 1);
      linearOpMode.sleep(300);
      RightMotorAsDcMotor.setDualPower(0, LeftMotorAsDcMotor, 0);
    }
    RightMotorAsDcMotor.setDualPower(1, LeftMotorAsDcMotor, -1);
  }
}
