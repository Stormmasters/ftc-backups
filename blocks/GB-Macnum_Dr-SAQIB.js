// IDENTIFIERS_USED=BackLeftAsDcMotor,BackRightAsDcMotor,gamepad1

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  // You will have to determine which motor to reverse for your robot.
  // In this example, the right motor was reversed so that positive
  // applied power makes it move the robot in the forward direction.
  BackRightAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      // The Y axis of a joystick ranges from -1 in its topmost position
      // to +1 in its bottommost position. We negate this value so that
      // the topmost position corresponds to maximum forward power.
      BackLeftAsDcMotor.setDualPower(-gamepad1.getLeftStickY(), BackRightAsDcMotor, -gamepad1.getRightStickY());
      telemetry.addNumericData('Left Pow', BackLeftAsDcMotor.getPower());
      telemetry.addNumericData('Right Pow', BackRightAsDcMotor.getPower());
      telemetry.update();
    }
  }
}
