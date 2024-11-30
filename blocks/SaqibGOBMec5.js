// IDENTIFIERS_USED=BackLeftAsDcMotor,BackRightAsDcMotor,FrontLeftAsDcMotor,FrontRightAsDcMotor,gamepad1,gamepad2

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      FrontRightAsDcMotor.setDirection("REVERSE");
      BackRightAsDcMotor.setDirection("REVERSE");
      BackLeftAsDcMotor.setDualPower(gamepad2.getLeftStickY(), FrontLeftAsDcMotor, gamepad2.getLeftStickY());
      BackRightAsDcMotor.setDualPower(gamepad2.getRightStickY(), FrontRightAsDcMotor, gamepad2.getRightStickY());
      if (gamepad1.getRightBumper()) {
        FrontRightAsDcMotor.setDualPower(1, BackRightAsDcMotor, 1);
        FrontLeftAsDcMotor.setDualPower(-1, BackLeftAsDcMotor, -1);
      }
      if (gamepad1.getLeftBumper()) {
        FrontLeftAsDcMotor.setDualPower(1, BackLeftAsDcMotor, 1);
        FrontRightAsDcMotor.setDualPower(-1, BackRightAsDcMotor, -1);
      }
      if (gamepad1.getDpadRight()) {
        FrontLeftAsDcMotor.setDualPower(-1, BackRightAsDcMotor, 1);
        BackLeftAsDcMotor.setDualPower(1, FrontRightAsDcMotor, -1);
      }
      if (gamepad1.getDpadLeft()) {
        FrontLeftAsDcMotor.setDualPower(1, BackRightAsDcMotor, -1);
        BackLeftAsDcMotor.setDualPower(-1, FrontRightAsDcMotor, 1);
      }
      if (gamepad1.getDpadUp()) {
        FrontLeftAsDcMotor.setDualPower(1, BackLeftAsDcMotor, 1);
      }
      if (gamepad1.getDpadDown()) {
        FrontRightAsDcMotor.setDualPower(1, BackRightAsDcMotor, 1);
      }
      if (gamepad1.getRightStickButton()) {
        while (!gamepad1.getLeftStickButton()) {
          BackLeftAsDcMotor.setDualPower(1, FrontLeftAsDcMotor, 1);
          BackRightAsDcMotor.setDualPower(1, FrontRightAsDcMotor, 1);
        }
      }
      telemetry.update();
    }
  }
}
