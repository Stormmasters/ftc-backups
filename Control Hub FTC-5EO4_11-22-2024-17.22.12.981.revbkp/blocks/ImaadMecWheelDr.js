// IDENTIFIERS_USED=BLmotorAsDcMotor,BRmotorAsDcMotor,FLmotorAsDcMotor,FRmotorAsDcMotor,gamepad1

var verticle, horizontal, pivot;

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  FRmotorAsDcMotor.setDirection("FORWARD");
  BRmotorAsDcMotor.setDirection("FORWARD");
  BLmotorAsDcMotor.setDirection("REVERSE");
  FLmotorAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    verticle = -gamepad1.getRightStickY();
    horizontal = gamepad1.getRightStickX();
    pivot = gamepad1.getLeftStickX();
    while (linearOpMode.opModeIsActive()) {
      FRmotorAsDcMotor.setPower(-pivot + (verticle - horizontal));
      BRmotorAsDcMotor.setPower(-pivot + verticle + horizontal);
      FLmotorAsDcMotor.setPower(pivot + verticle + horizontal);
      BLmotorAsDcMotor.setPower(pivot + (verticle - horizontal));
      telemetryAddTextData('status', 'running');
      telemetry.update();
    }
  }
}
