// IDENTIFIERS_USED=BLmotorAsDcMotor,BRmotorAsDcMotor,FLmotorAsDcMotor,FRmotorAsDcMotor

var L_motors_negative, velocity;

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  velocity = -600;
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    L_motors_negative = velocity * -1;
    move();
  }
  while (linearOpMode.opModeIsActive()) {
    telemetry.update();
  }
}

/**
 * Describe this function...
 */
function move() {
  BLmotorAsDcMotor.setVelocity(L_motors_negative);
  FRmotorAsDcMotor.setVelocity(velocity);
  FLmotorAsDcMotor.setVelocity(L_motors_negative);
  BRmotorAsDcMotor.setVelocity(velocity);
}
