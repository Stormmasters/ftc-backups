/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  LiftMotorAsDcMotor.setDirection("REVERSE");
  LeftMotorAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  while (linearOpMode.opModeIsActive()) {
    LeftMotorAsDcMotor.setDualPower(0.7 * gamepad1.getRightStickY(), RightMotorAsDcMotor, 0.7 * gamepad1.getLeftStickY());
    LiftMotorAsDcMotor.setPower(-gamepad2.getRightTrigger());
    LiftMotorAsDcMotor.setPower(Math.abs(gamepad2.getRightStickY()));
    SmackerAsServo.setPosition(gamepad2.getLeftTrigger());
  }
}
