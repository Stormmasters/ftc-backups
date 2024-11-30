// IDENTIFIERS_USED=BackLeftAsDcMotor,BackRightAsDcMotor,FrontLeftAsDcMotor,FrontRightAsDcMotor

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    BackLeftAsDcMotor.setDualPower(1, BackRightAsDcMotor, 1);
    FrontLeftAsDcMotor.setDualPower(1, FrontRightAsDcMotor, 1);
    telemetry.update();
    while (linearOpMode.opModeIsActive()) {
    }
  }
}
