// IDENTIFIERS_USED=backleftAsDcMotor,backrightAsDcMotor,frontleftAsDcMotor,frontrightAsDcMotor

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  frontrightAsDcMotor.setDirection("FORWARD");
  backrightAsDcMotor.setDirection("REVERSE");
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    frontrightAsDcMotor.setPower(1);
    frontleftAsDcMotor.setPower(1);
    linearOpMode.sleep(800);
    frontrightAsDcMotor.setPower(0);
    frontleftAsDcMotor.setPower(0);
    linearOpMode.sleep(80);
    frontrightAsDcMotor.setPower(1);
    backleftAsDcMotor.setPower(-1);
    frontleftAsDcMotor.setPower(-1);
    backrightAsDcMotor.setPower(-1);
    linearOpMode.sleep(100);
    frontrightAsDcMotor.setPower(0);
    frontleftAsDcMotor.setPower(0);
    backleftAsDcMotor.setPower(0);
    backrightAsDcMotor.setPower(0);
    linearOpMode.sleep(70);
    frontrightAsDcMotor.setPower(-1);
    backleftAsDcMotor.setPower(1);
    frontleftAsDcMotor.setPower(1);
    backrightAsDcMotor.setPower(1);
    linearOpMode.sleep(60);
    frontrightAsDcMotor.setPower(0);
    frontleftAsDcMotor.setPower(0);
    backleftAsDcMotor.setPower(0);
    backrightAsDcMotor.setPower(0);
    linearOpMode.sleep(70);
    frontrightAsDcMotor.setPower(-1);
    frontleftAsDcMotor.setPower(-1);
    linearOpMode.sleep(600);
    frontrightAsDcMotor.setPower(0);
    frontleftAsDcMotor.setPower(0);
    linearOpMode.sleep(100);
    frontrightAsDcMotor.setPower(1);
    backleftAsDcMotor.setPower(-1);
    frontleftAsDcMotor.setPower(-1);
    backrightAsDcMotor.setPower(-1);
    linearOpMode.sleep(2300);
    frontrightAsDcMotor.setPower(0);
    backleftAsDcMotor.setPower(0);
    frontleftAsDcMotor.setPower(0);
    backrightAsDcMotor.setPower(0);
    telemetry.update();
  }
}
