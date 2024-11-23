// IDENTIFIERS_USED=BLmotorAsDcMotor,BRmotorAsDcMotor

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  BRmotorAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    linearOpMode.sleep(10000);
    BLmotorAsDcMotor.setDualPower(0.5, BRmotorAsDcMotor, 0.47);
    linearOpMode.sleep(4500);
    BLmotorAsDcMotor.setDualPower(0, BRmotorAsDcMotor, 0);
    BLmotorAsDcMotor.setDualPower(-0.3, BRmotorAsDcMotor, -0.3);
    linearOpMode.sleep(500);
    BLmotorAsDcMotor.setDualPower(0, BRmotorAsDcMotor, 0);
    while (linearOpMode.opModeIsActive()) {
      telemetry.update();
    }
  }
}
