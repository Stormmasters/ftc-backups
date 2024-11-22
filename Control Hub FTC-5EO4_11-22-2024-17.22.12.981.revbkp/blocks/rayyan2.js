// IDENTIFIERS_USED=AirplaneAsServo,AzimuthAsCRServo,BLmotorAsDcMotor,BRmotorAsDcMotor,ElevationAsCRServo,ElevationMaximumAsTouchSensor,FLmotorAsDcMotor,FRmotorAsDcMotor,gamepad1,gamepad2,LeftChopStickAsServo,LinArmAsDcMotor,RightChopStickAsServo

var turn, forward2, strafe, turnright, turnleft, denominator, elevationmax, my_0chop, my_1chop, Arm;

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  motor_Mode();
  FRmotorAsDcMotor.setDirection("FORWARD");
  BLmotorAsDcMotor.setDirection("REVERSE");
  FLmotorAsDcMotor.setDirection("REVERSE");
  BRmotorAsDcMotor.setDirection("FORWARD");
  my_0chop = 0;
  my_1chop = 1;
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      motor_Telem();
      if (LeftChopStickAsServo.getPosition() == 0) {
        if (gamepad2.getLeftBumper()) {
          LeftChopStickAsServo.setPosition(my_1chop);
          linearOpMode.sleep(1000);
        }
      }
      if (LeftChopStickAsServo.getPosition() == 1) {
        if (gamepad2.getLeftBumper()) {
          LeftChopStickAsServo.setPosition(my_0chop);
          linearOpMode.sleep(1000);
        }
      }
      if (RightChopStickAsServo.getPosition() == 0) {
        if (gamepad2.getRightBumper()) {
          RightChopStickAsServo.setPosition(my_1chop);
          linearOpMode.sleep(1000);
        }
      }
      if (RightChopStickAsServo.getPosition() == 1) {
        if (gamepad2.getRightBumper()) {
          RightChopStickAsServo.setPosition(my_0chop);
          linearOpMode.sleep(1000);
        }
      }
      if (gamepad2.getB()) {
        AirplaneAsServo.setPosition(1);
      }
      if (gamepad2.getA()) {
        AirplaneAsServo.setPosition(0);
      }
      Arm = -gamepad2.getRightStickY();
      LinArmAsDcMotor.setPower(Arm);
      elevaton();
      azimuth();
      turn = gamepad1.getRightStickX();
      if (turn != 0) {
        turn2();
      } else {
        movement2();
      }
      telemetry.update();
      telemetry.addTextData('forward', String(forward2));
      telemetry.addTextData('strafe', String(strafe));
      telemetry.addTextData('turn', String(turn));
      telemetry.addTextData('denominator', String(denominator));
      elevationmax = 0;
      if (ElevationMaximumAsTouchSensor.getIsPressed()) {
        elevationmax = 1;
      }
      telemetry.addTextData('elevationmax', String(elevationmax));
    }
  }
}

/**
 * Describe this function...
 */
function testAsync() {
  // Get the current time in milliseconds. The value returned represents the number of milliseconds since midnight, January 1, 1970 UTC.
  telemetry.addTextData('async out', String(systemAccess.currentTimeMillis()));
  linearOpMode.sleep(1000);
  // Get the current time in milliseconds. The value returned represents the number of milliseconds since midnight, January 1, 1970 UTC.
  telemetry.addTextData('async out', String(systemAccess.currentTimeMillis()));
  linearOpMode.sleep(1000);
  // Get the current time in milliseconds. The value returned represents the number of milliseconds since midnight, January 1, 1970 UTC.
  telemetry.addTextData('async out', String(systemAccess.currentTimeMillis()));
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
function motor_Mode() {
  BLmotorAsDcMotor.setDualMode("RUN_USING_ENCODER", BRmotorAsDcMotor, "RUN_USING_ENCODER");
  FLmotorAsDcMotor.setDualMode("RUN_USING_ENCODER", FRmotorAsDcMotor, "RUN_USING_ENCODER");
}

/**
 * Describe this function...
 */
function motor_Pos_Zero() {
}

/**
 * Describe this function...
 */
function motor_Telem() {
  telemetry.addTextData('FR Pos', String(FRmotorAsDcMotor.getCurrentPosition()));
  telemetry.addTextData('BR Pos', String(BRmotorAsDcMotor.getCurrentPosition()));
  telemetry.addTextData('BL Pos', String(BLmotorAsDcMotor.getCurrentPosition()));
  telemetry.addTextData('FL Pos', String(FLmotorAsDcMotor.getCurrentPosition()));
}

/**
 * Describe this function...
 */
function movement() {
  forward2 = -gamepad1.getLeftStickY();
  strafe = gamepad1.getLeftStickX();
  turn = gamepad1.getRightStickX();
  if (gamepad1.getLeftBumper()) {
    forward2 = forward2 / 2;
    strafe = forward2 / 2;
    turn = forward2 / 2;
  }
  denominator = Math.max.apply(null, [1, Math.abs(forward2) + Math.abs(strafe) + Math.abs(turn)]);
  FLmotorAsDcMotor.setDualPower((forward2 + strafe + turn) / denominator, BLmotorAsDcMotor, (forward2 - (strafe + turn)) / denominator);
  FRmotorAsDcMotor.setDualPower((forward2 - (strafe - turn)) / denominator, BRmotorAsDcMotor, (forward2 + (strafe - turn)) / denominator);
}

/**
 * Describe this function...
 */
function azimuth() {
  telemetry.addTextData('azimuth', String(gamepad2.getRightStickX()));
  AzimuthAsCRServo.setPower(gamepad2.getRightStickX());
}

/**
 * Describe this function...
 */
function turn2() {
  if (gamepad1.getLeftBumper()) {
    turn = turn / 2;
  }
  turnright = turn;
  turnleft = turn * -1;
  FLmotorAsDcMotor.setDualPower(turnleft, BLmotorAsDcMotor, turnleft);
  FRmotorAsDcMotor.setDualPower(turnright, BRmotorAsDcMotor, turnright);
}

/**
 * Describe this function...
 */
function elevaton() {
  ElevationAsCRServo.setPower(gamepad2.getLeftStickY());
}
