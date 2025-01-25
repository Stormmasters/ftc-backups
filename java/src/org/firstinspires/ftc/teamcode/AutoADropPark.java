package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutoADropPark", group = "Autonomous")
public class AutoADropPark extends LinearOpMode {

    // Declare motors and servos
    private DcMotor leftBack, rightBack, leftFront, rightFront, leftSlide, rightSlide;
    private CRServo shoulder, wrist;
    private Servo claw;

    // Encoder constants
    static final double TICKS_PER_REV = 537.7; // For GoBILDA 5202/3/4 motors
    static final double WHEEL_DIAMETER_INCHES = 4.0; // Wheel diameter in inches
    static final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER_INCHES);
    static final double KP = 0.01; // Proportional gain
    static final double KD = 0.005; // Derivative gain

    @Override
    public void runOpMode() {
        // Initialize hardware
        initializeHardware();

        telemetry.addData("Status", "Initialized. Waiting for start...");
        telemetry.update();

        waitForStart();

        // Step 1: Drive forward 24 inches using PID control with 60% power
        pidDriveForward(26, 0.3);

        // Step 2: Turn slightly left for 10 inches
        turnLeft(20, -0.5);

        // Step 3: Operate slides, shoulder, and wrist concurrently
        Thread shoulderAndWristTask = new Thread(() -> {
            moveShoulder(-0.5, 6500); // Move shoulder for 6 seconds at 50% power
            moveWrist(0.6, 2000);    // Move wrist for 2 seconds at 60% power
            operateClaw(0.5); // Open the claw
        });
        operateSlidesWithoutEncoders(0.7, 20000); // Lift slides for 10 seconds
       
        shoulderAndWristTask.start();
        try {
            shoulderAndWristTask.join(); // Wait for the shoulder and wrist task to complete
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Thread interrupted");
        }

        // Wait for 1 seconds
        sleep(1000);

        // Parking sequence 
        
        // Step 4: Turn robot clockwise for 360 degrees with 50% power
       turnClockwise(360, 0.4);
       sleep(1000);
       // Step 5: Strafe left for 24 inches with 40% power
       // strafeLeft(24, 0.4);
       
        // Step 6: Move forward 7 inches with 50% power
        pidDriveForward(18, 0.3);
        sleep(1000);

        // Step 7: Turn robot clockwise for another 360 degrees with 50% power
       turnClockwise(200, 0.5);
       sleep(1000);
            moveWrist(-0.4, 2000);    // Move wrist for 2 seconds at 60% power

        // Step 8: Operate slides without encoders for 10 seconds
       // operateSlidesWithoutEncoders(0.6, 10000);

        // Step 9: Move forward 12 inches with 50% power and stop the robot
      //  pidDriveForward(12, 0.5);
    }

    // =====================  METHODS =====================

    private void initializeHardware() {
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftSlide = hardwareMap.get(DcMotor.class, "par0");
        rightSlide = hardwareMap.get(DcMotor.class, "par1");
        shoulder = hardwareMap.get(CRServo.class, "shoulder");
        wrist = hardwareMap.get(CRServo.class, "wrist");
        claw = hardwareMap.get(Servo.class, "claw");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoders();
    }

    private void resetEncoders() {
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void pidDriveForward(double inches, double basePower) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);
        int previousError = 0;

        leftBack.setTargetPosition(leftBack.getCurrentPosition() + targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() + targetTicks);
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + targetTicks);

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive() && allMotorsBusy()) {
            int currentPosition = (leftBack.getCurrentPosition() + rightBack.getCurrentPosition()
                    + leftFront.getCurrentPosition() + rightFront.getCurrentPosition()) / 4;
            int error = targetTicks - currentPosition;
            int derivative = error - previousError;
            double adjustment = (KP * error) + (KD * derivative);
            double adjustedPower = basePower + adjustment;

            setMotorPowers(adjustedPower);
            telemetry.addData("Target Ticks", targetTicks);
            telemetry.addData("Current Position", currentPosition);
            telemetry.addData("Error", error);
            telemetry.addData("Power Adjustment", adjustment);
            telemetry.update();
            previousError = error;
        }

        stopMotors();
    }
private void turnLeft(int inches, double power) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);

        leftBack.setTargetPosition(leftBack.getCurrentPosition() - targetTicks);
        leftFront.setTargetPosition(leftFront.getCurrentPosition() - targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() + targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + targetTicks);

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setMotorPowers(power);

        while (opModeIsActive() && allMotorsBusy()) {
            telemetry.addData("Turning Left", "Target Ticks: %d", targetTicks);
            telemetry.update();
        }

        stopMotors();
    }
    /**
     * Strafes the robot to the left for a specified distance.
     */
    private void strafeLeft(double inches, double power) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);

        leftBack.setTargetPosition(leftBack.getCurrentPosition() - targetTicks);
        leftFront.setTargetPosition(leftFront.getCurrentPosition() - targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() - targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + targetTicks);

        setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
        setMotorPowers(power);

        while (opModeIsActive() && allMotorsBusy()) {
            telemetry.addData("Strafing Left", "Inches: %.2f", inches);
            telemetry.update();
        }

        stopMotors();
    }

    private void turnClockwise(double degrees, double power) {
        int targetTicks = (int) ((degrees / 360.0) * TICKS_PER_REV * 2);

        leftBack.setTargetPosition(leftBack.getCurrentPosition() + targetTicks);
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() - targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - targetTicks);

        setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
        setMotorPowers(power);

        while (opModeIsActive() && allMotorsBusy()) {
            telemetry.addData("Turning Clockwise", "Degrees: %.2f", degrees);
            telemetry.update();
        }

        stopMotors();
    }

    private void operateSlidesWithoutEncoders(double power, int duration) {
        leftSlide.setPower(power);
        rightSlide.setPower(power);
        sleep(duration);
        leftSlide.setPower(0);
        rightSlide.setPower(0);
    }

    private void moveShoulder(double power, int duration) {
        shoulder.setPower(power);
        sleep(duration);
        shoulder.setPower(0);
    }

    private void moveWrist(double power, int duration) {
        wrist.setPower(power);
        sleep(duration);
        wrist.setPower(0);
    }

    private void operateClaw(double position) {
        claw.setPosition(position);
        telemetry.addData("Claw", "Position: %.2f", position);
        telemetry.update();
        sleep(500);
    }

    private void setMotorModes(DcMotor.RunMode mode) {
        leftBack.setMode(mode);
        rightBack.setMode(mode);
        leftFront.setMode(mode);
        rightFront.setMode(mode);
    }

    private void setMotorPowers(double power) {
        leftBack.setPower(power);
        rightBack.setPower(power);
        leftFront.setPower(power);
        rightFront.setPower(power);
    }

    private boolean allMotorsBusy() {
        return leftBack.isBusy() && rightBack.isBusy() && leftFront.isBusy() && rightFront.isBusy();
    }

    private void stopMotors() {
        setMotorPowers(0);
    }
}
