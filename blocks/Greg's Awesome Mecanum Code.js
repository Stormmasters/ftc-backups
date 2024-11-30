/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      FrontRightAsDcMotor.setDirection("REVERSE");
      BackRighrAsDcMotor.setDirection("REVERSE");
      BackLeftAsDcMotor.setDualPower(gamepad1.getLeftStickY(), FrontLeftAsDcMotor, gamepad1.getLeftStickY());
      BackRighrAsDcMotor.setDualPower(gamepad1.getRightStickY(), FrontRightAsDcMotor, gamepad1.getRightStickY());
      if (gamepad1.getDpadLeft()) {
        BackLeftAsDcMotor.setDualPower(-1, FrontRightAsDcMotor, 1);
        BackRighrAsDcMotor.setDualPower(-1, FrontLeftAsDcMotor, 1);
      }
      if (gamepad1.getDpadRight()) {
        BackRighrAsDcMotor.setDualPower(1, FrontLeftAsDcMotor, -1);
        BackLeftAsDcMotor.setDualPower(1, FrontRightAsDcMotor, -1);
      }
      if (gamepad1.getRightBumper()) {
        BackRighrAsDcMotor.setDualPower(1, FrontRightAsDcMotor, 1);
        BackLeftAsDcMotor.setDualPower(-1, FrontLeftAsDcMotor, -1);
      }
      if (gamepad1.getLeftBumper()) {
        BackLeftAsDcMotor.setDualPower(1, FrontLeftAsDcMotor, 1);
        BackRighrAsDcMotor.setDualPower(-1, FrontRightAsDcMotor, -1);
      }
    }
    telemetry.update();
  }
}
