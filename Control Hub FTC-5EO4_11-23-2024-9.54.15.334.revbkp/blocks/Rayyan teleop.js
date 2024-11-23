// IDENTIFIERS_USED=BLmotorAsDcMotor,BRmotorAsDcMotor,gamepad1

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  BLmotorAsDcMotor.setDirection("REVERSE");
  BRmotorAsDcMotor.setDirection("FORWARD");
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      if (gamepad1.getA()) {
        BLmotorAsDcMotor.setPower(0.5);
      }
      if (gamepad1.getB()) {
        BRmotorAsDcMotor.setPower(0.5);
      }
      telemetry.update();
      telemetry.addTextData('Position', String(BLmotorAsDcMotor.getCurrentPosition()));
      telemetry.addTextData('Position', String(BRmotorAsDcMotor.getCurrentPosition()));
    }
  }
}
