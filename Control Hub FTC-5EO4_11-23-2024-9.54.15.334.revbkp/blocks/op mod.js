// IDENTIFIERS_USED=BLmotorAsDcMotor,BRmotorAsDcMotor

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  BLmotorAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    BLmotorAsDcMotor.setDualPower(0.5, BRmotorAsDcMotor, 0.5);
    linearOpMode.sleep(2000);
    BLmotorAsDcMotor.setDualPower(0, BRmotorAsDcMotor, 0);
  }
}
