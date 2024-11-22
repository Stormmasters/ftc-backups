// IDENTIFIERS_USED=AirplaneAsServo,AzimuthAsCRServo,BLmotorAsDcMotor,BRmotorAsDcMotor,ElevationAsCRServo,ElevationStopAsTouchSensor,FLmotorAsDcMotor,FRmotorAsDcMotor,gamepad1,gamepad2,LeftClawAsServo,LinArmAsDcMotor,RightClawAsServo,WristAsServo

var turn, forward2, Arm, power, strafe, motorMoveTics, turnleft, denominator, turnright;

/**
 * Describe this function...
 */
function elevationstop() {
  if (gamepad2.getDpadUp()) {
    if (ElevationStopAsTouchSensor.getIsPressed()) {
      ElevationAsCRServo.setPower(0);
    } else {
      ElevationAsCRServo.setPower(0.1);
    }
  }
  if (gamepad2.getDpadDown()) {
    ElevationAsCRServo.setPower(-0.1);
  }
  if (gamepad2.getDpadLeft()) {
    ElevationAsCRServo.setPower(0);
  }
  if (ElevationStopAsTouchSensor.getIsPressed()) {
    ElevationAsCRServo.setPower(0);
  }
  telemetry.addTextData('ElevationStop pressed', String(ElevationAsCRServo.getPower()));
}

/**
 * Describe this function...
 */
function azimuth() {
  telemetry.addTextData('azimuth', String(gamepad2.getRightStickX()));
  AzimuthAsCRServo.setPower(gamepad2.getRightStickX());
}

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  motorSetup();
  while (linearOpMode.opModeIsActive()) {
    if (linearOpMode.opModeIsActive()) {
      power = 1;
      motorMoveTics = 100;
      motorMoveTics = BLmotorAsDcMotor.getCurrentPosition() + motorMoveTics;
      telemetry.update();
      LinArm();
      Airplane();
      elevationstop();
      elevaton();
      movement2();
      claw();
      turn2();
      wrist();
    }
  }
}

/**
 * Describe this function...
 */
function claw() {
  if (gamepad2.getLeftBumper()) {
    LeftClawAsServo.setPosition(0.5);
  }
  if (gamepad2.getLeftTrigger() > 0) {
    LeftClawAsServo.setPosition(0);
  }
  if (gamepad2.getRightBumper()) {
    RightClawAsServo.setPosition(0.2);
  }
  if (gamepad2.getRightTrigger() > 0) {
    RightClawAsServo.setPosition(0.5);
  }
}

/**
 * G2: A: Fire
 * G2: B: Cock
 */
function Airplane() {
  if (gamepad2.getA()) {
    AirplaneAsServo.setPosition(0);
  }
  if (gamepad2.getB()) {
    AirplaneAsServo.setPosition(0.45);
  }
}

/**
 * Describe this function...
 */
function motorSetup() {
  BLmotorAsDcMotor.setDirection("REVERSE");
  FLmotorAsDcMotor.setDirection("REVERSE");
}

/**
 * Describe this function...
 */
function motorMoveEncoder() {
  if (motorMoveTics > BLmotorAsDcMotor.getCurrentPosition()) {
    power = 0;
  }
  BLmotorAsDcMotor.setDualVelocity(power, BRmotorAsDcMotor, power);
  FLmotorAsDcMotor.setDualVelocity(power, FRmotorAsDcMotor, power);
}

/**
 * Describe this function...
 */
function turn2() {
  turn = gamepad1.getRightStickX();
  if (gamepad1.getLeftBumper()) {
    turn = turn / 2;
  }
  FLmotorAsDcMotor.setDualPower(turnleft, BLmotorAsDcMotor, turnleft);
  FRmotorAsDcMotor.setDualPower(turnright, BRmotorAsDcMotor, turnright);
  turnright = turn;
  turnleft = turn * -1;
}

/**
 * Describe this function...
 */
function wrist() {
  if (gamepad2.getTriangle()) {
    WristAsServo.setPosition(1);
  }
  if (gamepad2.getSquare()) {
    WristAsServo.setPosition(0);
  }
}

/**
 * Describe this function...
 */
function elevaton() {
  ElevationAsCRServo.setPower(gamepad2.getLeftStickY());
}

/**
 * Describe this function...
 */
function movement2() {
  forward2 = gamepad1.getLeftStickY();
  strafe = -gamepad1.getLeftStickX();
  if (gamepad1.getLeftBumper()) {
    forward2 = forward2 / 2;
    strafe = strafe / 2;
  }
  denominator = Math.max.apply(null, [1, Math.abs(forward2) + Math.abs(strafe)]);
  FLmotorAsDcMotor.setDualPower((forward2 + strafe) / denominator, BLmotorAsDcMotor, (forward2 - strafe) / denominator);
  FRmotorAsDcMotor.setDualPower((forward2 - strafe) / denominator, BRmotorAsDcMotor, (forward2 + strafe) / denominator);
}

/**
 * Describe this function...
 */
function motorMovePower() {
  BLmotorAsDcMotor.setPower(power);
  BRmotorAsDcMotor.setPower(power);
  FLmotorAsDcMotor.setPower(power);
  FRmotorAsDcMotor.setPower(power);
}

/**
 * Describe this function...
 */
function LinArm() {
  Arm = -gamepad2.getRightStickY();
  LinArmAsDcMotor.setPower(Arm);
}
