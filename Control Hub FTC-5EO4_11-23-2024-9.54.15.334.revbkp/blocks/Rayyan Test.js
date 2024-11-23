// IDENTIFIERS_USED=BLmotorAsDcMotor,BRmotorAsDcMotor,FLmotorAsDcMotor,FRmotorAsDcMotor,gamepad1

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  // You will have to determine which motor to reverse for your robot.
  // In this example, the right motor was reversed so that positive
  // applied power makes it move the robot in the forward direction.
  FLmotorAsDcMotor.setDirection("REVERSE");
  // You will have to determine which motor to reverse for your robot.
  // In this example, the right motor was reversed so that positive
  // applied power makes it move the robot in the forward direction.
  FRmotorAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      // The Y axis of a joystick ranges from -1 in its topmost position
      // to +1 in its bottommost position. We negate this value so that
      // the topmost position corresponds to maximum forward power.
      FLmotorAsDcMotor.setDualVelocity(-gamepad1.getLeftStickY(), FRmotorAsDcMotor, -gamepad1.getRightStickY());
      telemetry.addTextData('Left Pow', String(BLmotorAsDcMotor.getPower()));
      telemetry.addTextData('Right Pow', String(BRmotorAsDcMotor.getPower()));
      telemetry.update();
    }
  }
}
