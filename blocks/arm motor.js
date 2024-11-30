var speed, f_and_w;

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  speed = 1;
  f_and_w = 1;
  armAsDcMotor.setDirection("FORWARD");
  rightmotorAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      if (gamepad1.getLeftStickButton()) {
        speed = 0.95;
      } else {
        speed = 1;
        armAsDcMotor.setPower(gamepad1.getLeftStickY() * speed);
        if (gamepad1.getA()) {
          gripAsServo.setPosition(0);
        }
        if (gamepad1.getY()) {
          gripAsServo.setPosition(0);
        }
        if (gamepad1.getB()) {
          gripAsServo.setPosition(0.3);
        }
        if (gamepad2.getLeftBumper()) {
          f_and_w = 1;
        }
        leftmotorAsDcMotor.setDualPower(gamepad2.getLeftStickY() * f_and_w, rightmotorAsDcMotor, gamepad2.getRightStickY());
      }
    }
  }
  gripAsServo.setPosition(0);
  linearOpMode.waitForStart();
  gripAsServo.setPosition(0.5);
}
