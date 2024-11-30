/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  leftintakeAsServo.setPosition(0);
  rightintakeAsServo.setPosition(0);
  linearOpMode.waitForStart();
  leftintakeAsServo.setPosition(10);
  rightintakeAsServo.setPosition(10);
  linearOpMode.sleep(1000);
  leftintakeAsServo.setPosition(0);
  rightintakeAsServo.setPosition(0);
}
