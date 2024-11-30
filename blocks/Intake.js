/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  gripAsServo.setPosition(0);
  linearOpMode.waitForStart();
  gripAsServo.setPosition(-5);
}
