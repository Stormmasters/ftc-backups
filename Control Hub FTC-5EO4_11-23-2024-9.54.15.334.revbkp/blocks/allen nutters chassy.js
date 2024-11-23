// IDENTIFIERS_USED=BackLeftAsDcMotor,BackRightAsDcMotor,FrontLeftAsDcMotor,FrontRightAsDcMotor,gamepad1

var Velocity, stop2, logic1;

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  Velocity = 1000;
  stop2 = 0;
  logic1 = 0;
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      telemetry.update();
      if (gamepad1.getA()) {
        logic1 = logic1 + 1;
        if (logic1 % 2 == 0) {
          stop3();
        } else {
          forward2();
        }
      }
    }
  }
}

/**
 * Describe this function...
 */
function forward2() {
  BackRightAsDcMotor.setDirection("REVERSE");
  BackRightAsDcMotor.setVelocity(Velocity);
  FrontRightAsDcMotor.setDirection("FORWARD");
  FrontRightAsDcMotor.setVelocity(Velocity);
  FrontLeftAsDcMotor.setDirection("FORWARD");
  FrontLeftAsDcMotor.setVelocity(Velocity);
  BackLeftAsDcMotor.setDirection("REVERSE");
  BackLeftAsDcMotor.setVelocity(Velocity);
}

/**
 * Describe this function...
 */
function stop3() {
  BackRightAsDcMotor.setVelocity(stop2);
  FrontRightAsDcMotor.setVelocity(stop2);
  FrontLeftAsDcMotor.setVelocity(stop2);
  BackLeftAsDcMotor.setVelocity(stop2);
}
