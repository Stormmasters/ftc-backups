var tgtpower;

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  LeftMotorAsDcMotor.setDirection("REVERSE");
  SmackerAsServo.setPosition(0);
  linearOpMode.waitForStart();
  while (linearOpMode.opModeIsActive()) {
    tgtpower = -gamepad2.getLeftStickY();
    SmackerAsServo.setPosition(tgtpower);
    tgtpower = -gamepad1.getLeftStickY();
    LeftMotorAsDcMotor.setPower(tgtpower);
    tgtpower = -gamepad1.getRightStickY();
    RightMotorAsDcMotor.setPower(tgtpower);
    tgtpower = -gamepad2.getRightStickY();
    LiftMotorAsDcMotor.setPower(tgtpower);
    tgtpower = -gamepad1.getRightTrigger();
    DuckingMotorAsDcMotor.setPower(tgtpower);
    telemetry.addNumericData('Target Power', tgtpower);
    telemetry.addNumericData('Motor Power', LeftMotorAsDcMotor.getPower());
    telemetry.addNumericData('Right Motor Power', RightMotorAsDcMotor.getPower());
    telemetry.addNumericData('Servo Position', SmackerAsServo.getPosition());
    if (gamepad2.getX()) {
      for (var count = 0; count < 8; count++) {
        LeftMotorAsDcMotor.setDualPower(0.3, RightMotorAsDcMotor, -0.3);
        linearOpMode.sleep(200);
        LeftMotorAsDcMotor.setDualPower(0, RightMotorAsDcMotor, 0);
        LeftMotorAsDcMotor.setDualPower(0.2, RightMotorAsDcMotor, -0.2);
        DuckingMotorAsDcMotor.setPower(-1);
        linearOpMode.sleep(3000);
        DuckingMotorAsDcMotor.setPower(0);
        linearOpMode.sleep(300);
      }
    }
    if (gamepad2.getX()) {
      rollerAsServo.setPosition(0.75);
    }
    if (gamepad2.getY()) {
      rollerAsServo.setPosition(0.5);
    }
    if (gamepad2.getB()) {
      rollerAsServo.setPosition(-0.5);
    }
    telemetry.update();
  }
}
