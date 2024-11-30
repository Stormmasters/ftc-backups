// IDENTIFIERS_USED=back_left_motorAsDcMotor,back_right_motorAsDcMotor,front_left_motorAsDcMotor,front_right_motorAsDcMotor,gamepad1

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  back_left_motorAsDcMotor.setDirection("REVERSE");
  back_right_motorAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      front_left_motorAsDcMotor.setDualPower(gamepad1.getRightStickY(), back_left_motorAsDcMotor, gamepad1.getRightStickY());
      front_right_motorAsDcMotor.setDualPower(gamepad1.getLeftStickY(), back_right_motorAsDcMotor, gamepad1.getLeftStickY());
      back_left_motorAsDcMotor.setDualMaxSpeed(0.4, back_right_motorAsDcMotor, 0.4);
      front_left_motorAsDcMotor.setDualMaxSpeed(0.4, front_right_motorAsDcMotor, 0.4);
      if (gamepad1.getTriangle()) {
        front_left_motorAsDcMotor.setDualPower(0.75, back_right_motorAsDcMotor, -0.75);
        front_left_motorAsDcMotor.setDualPower(0.75, front_right_motorAsDcMotor, -0.75);
      }
      if (gamepad1.getSquare()) {
        back_left_motorAsDcMotor.setDualPower(-0.75, back_right_motorAsDcMotor, 0.75);
        front_left_motorAsDcMotor.setDualPower(-0.75, front_right_motorAsDcMotor, 0.75);
      }
      if (gamepad1.getLeftBumper()) {
        front_right_motorAsDcMotor.setPower(-0.5);
        back_right_motorAsDcMotor.setPower(0.5);
        front_left_motorAsDcMotor.setPower(0.5);
        back_left_motorAsDcMotor.setPower(-0.5);
      }
      if (gamepad1.getRightBumper()) {
        front_right_motorAsDcMotor.setPower(0.5);
        back_right_motorAsDcMotor.setPower(-0.5);
        front_left_motorAsDcMotor.setPower(-0.5);
        back_left_motorAsDcMotor.setPower(0.5);
      }
      telemetry.update();
    }
  }
}
