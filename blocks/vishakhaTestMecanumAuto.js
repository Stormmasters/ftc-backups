/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      BackLeftAsDcMotor.setDirection("REVERSE");
      FrontLeftAsDcMotor.setDirection("REVERSE");
      BackLeftAsDcMotor.setDualPower(1, BackRighrAsDcMotor, 1);
      FrontRightAsDcMotor.setDualPower(1, FrontLeftAsDcMotor, 1);
      linearOpMode.sleep(4000);
      FrontRightAsDcMotor.setDualPower(1, FrontLeftAsDcMotor, -1);
      BackLeftAsDcMotor.setDualPower(1, BackRighrAsDcMotor, -1);
      linearOpMode.sleep(100);
      BackLeftAsDcMotor.setDualPower(1, BackRighrAsDcMotor, 1);
      FrontRightAsDcMotor.setDualPower(1, FrontLeftAsDcMotor, 1);
      linearOpMode.sleep(1000);
      BackLeftAsDcMotor.setDualPower(1, BackRighrAsDcMotor, 1);
      BackLeftAsDcMotor.setDualPower(-1, BackRighrAsDcMotor, -1);
      linearOpMode.sleep(7200);
      BackLeftAsDcMotor.setDualPower(-1, BackRighrAsDcMotor, -1);
      BackLeftAsDcMotor.setDualPower(1, BackRighrAsDcMotor, 1);
      linearOpMode.sleep(7200);
      BackLeftAsDcMotor.setDualPower(-1, BackRighrAsDcMotor, -1);
      FrontRightAsDcMotor.setDualPower(-1, FrontLeftAsDcMotor, -1);
      linearOpMode.sleep(4000);
      FrontRightAsDcMotor.setDualPower(-1, FrontLeftAsDcMotor, 1);
      BackLeftAsDcMotor.setDualPower(-1, BackRighrAsDcMotor, 1);
      linearOpMode.sleep(2000);
    }
    telemetry.update();
  }
}
