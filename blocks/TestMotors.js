// IDENTIFIERS_USED=BackLeftAsDcMotor,BackRightAsDcMotor,clawAsServo,ElevatorAsDcMotor,FrontLeftAsDcMotor,FrontRightAsDcMotor

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  clawAsServo.setPosition(1);
  telemetry.update();
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    clawAsServo.setPosition(1);
    FrontLeftAsDcMotor.setDirection("REVERSE");
    BackLeftAsDcMotor.setDirection("REVERSE");
    clawAsServo.setPosition(1);
    telemetryAddTextData('ID:', 'CS2');
    BackLeftAsDcMotor.setPower(0.5);
    FrontRightAsDcMotor.setPower(0.5);
    FrontLeftAsDcMotor.setPower(0.5);
    BackRightAsDcMotor.setPower(0.5);
    telemetryAddTextData('ID:', 'CS2');
    linearOpMode.sleep(780);
    BackLeftAsDcMotor.setPower(0);
    BackRightAsDcMotor.setPower(0);
    FrontLeftAsDcMotor.setPower(0);
    FrontRightAsDcMotor.setPower(0);
    linearOpMode.sleep(800);
    FrontRightAsDcMotor.setPower(-0.5);
    BackRightAsDcMotor.setPower(0.5);
    FrontLeftAsDcMotor.setPower(0.5);
    BackLeftAsDcMotor.setPower(-0.5);
    linearOpMode.sleep(560);
    BackLeftAsDcMotor.setPower(0);
    BackRightAsDcMotor.setPower(0);
    FrontLeftAsDcMotor.setPower(0);
    FrontRightAsDcMotor.setPower(0);
    linearOpMode.sleep(800);
    ElevatorAsDcMotor.setPower(-0.62);
    linearOpMode.sleep(7900);
    ElevatorAsDcMotor.setPower(0);
    linearOpMode.sleep(10);
    BackLeftAsDcMotor.setPower(0.5);
    FrontRightAsDcMotor.setPower(0.5);
    FrontLeftAsDcMotor.setPower(0.5);
    BackRightAsDcMotor.setPower(0.5);
    linearOpMode.sleep(50);
    BackLeftAsDcMotor.setPower(0);
    BackRightAsDcMotor.setPower(0);
    FrontLeftAsDcMotor.setPower(0);
    FrontRightAsDcMotor.setPower(0);
    linearOpMode.sleep(50);
    ElevatorAsDcMotor.setPower(0.62);
    linearOpMode.sleep(1000);
    ElevatorAsDcMotor.setPower(0);
    clawAsServo.setPosition(-0.5);
    telemetry.update();
  }
}
