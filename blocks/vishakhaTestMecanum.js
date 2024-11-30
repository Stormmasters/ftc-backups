// IDENTIFIERS_USED=BackLeftAsDcMotor,BackRightAsDcMotor,FrontLeftAsDcMotor,FrontRightAsDcMotor,gamepad2

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
      if (gamepad2.getRightBumper()) {
        FrontRightAsDcMotor.setDualPower(1, BackRightAsDcMotor, 1);
        FrontLeftAsDcMotor.setDualPower(-1, BackLeftAsDcMotor, -1);
      }
      if (gamepad2.getLeftBumper()) {
        FrontLeftAsDcMotor.setDualPower(1, BackLeftAsDcMotor, 1);
        FrontRightAsDcMotor.setDualPower(-1, BackRightAsDcMotor, -1);
      }
      if (gamepad2.getDpadRight()) {
        FrontLeftAsDcMotor.setDualPower(-1, BackRightAsDcMotor, 1);
        BackLeftAsDcMotor.setDualPower(1, FrontRightAsDcMotor, -1);
      }
      if (gamepad2.getDpadLeft()) {
        FrontLeftAsDcMotor.setDualPower(1, BackRightAsDcMotor, -1);
        BackLeftAsDcMotor.setDualPower(-1, FrontRightAsDcMotor, 1);
      }
      if (gamepad2.getDpadUp()) {
        FrontLeftAsDcMotor.setDualPower(1, BackLeftAsDcMotor, 1);
      }
      if (gamepad2.getDpadDown()) {
        FrontRightAsDcMotor.setDualPower(1, BackRightAsDcMotor, 1);
      }
      if (gamepad2.getRightStickButton()) {
        while (!gamepad2.getLeftStickButton()) {
          BackLeftAsDcMotor.setDualPower(1, FrontLeftAsDcMotor, 1);
          BackRightAsDcMotor.setDualPower(1, FrontRightAsDcMotor, 1);
        }
      }
    }
    telemetry.update();
  }
}
