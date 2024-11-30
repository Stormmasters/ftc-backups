/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  leftmotorAsDcMotor.setDualPower(-1, rightmotorAsDcMotor, 1);
  linearOpMode.sleep(4500);
  leftmotorAsDcMotor.setDualPower(1, rightmotorAsDcMotor, -1);
  linearOpMode.sleep(4500);
}
