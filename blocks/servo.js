/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  gripAsServo.setPosition(0);
  linearOpMode.waitForStart();
  while (linearOpMode.opModeIsActive()) {
    if (gamepad1.getA()) {
      gripAsServo.setPosition(1);
    }
    if (gamepad1.getY()) {
      gripAsServo.setPosition(0);
    }
    if (gamepad1.getB()) {
      gripAsServo.setPosition(0.3);
    }
  }
}
