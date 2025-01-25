package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "TestAMove", group = "Autonomous")
public class TestAMove extends LinearOpMode {

    // Declare motors
    private DcMotor leftBack, rightBack, leftFront, rightFront;

    // Encoder and PID constants
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

        // Autonomous sequence

        // Example: Turn clockwise 180 degrees
      //  turnClockwise(360, 0.5);

        // Example: Strafe right for 5 inches
       // strafeRight(5, 0.5);

        // Example: Turn counterclockwise 180 degrees
       // turnCounterClockwise(360, 0.5);

        // Example: Strafe left for 5 inches
       strafeLeft(5, 0.5);
    }

    // ===================== HELPER METHODS =====================

    /**
     * Initializes the hardware.
     */
    private void initializeHardware() {
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        // Reverse directions for specific motors if necessary
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();
    }

    /**
     * Resets encoders for all drive motors.
     */
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

    /**
     * Turns the robot clockwise for a specified angle.
     */
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

    /**
     * Turns the robot counterclockwise for a specified angle.
     */
    private void turnCounterClockwise(double degrees, double power) {
        int targetTicks = (int) ((degrees / 360.0) * TICKS_PER_REV * 2);

        leftBack.setTargetPosition(leftBack.getCurrentPosition() - targetTicks);
        leftFront.setTargetPosition(leftFront.getCurrentPosition() - targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() + targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + targetTicks);

        setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
        setMotorPowers(power);

        while (opModeIsActive() && allMotorsBusy()) {
            telemetry.addData("Turning Counterclockwise", "Degrees: %.2f", degrees);
            telemetry.update();
        }

        stopMotors();
    }

    /**
     * Strafes the robot to the right for a specified distance.
     */
    private void strafeRight(double inches, double power) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);

        leftBack.setTargetPosition(leftBack.getCurrentPosition() + targetTicks);
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() + targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - targetTicks);

        setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
        setMotorPowers(power);

        while (opModeIsActive() && allMotorsBusy()) {
            telemetry.addData("Strafing Right", "Inches: %.2f", inches);
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

    /**
     * Sets motor modes for all drive motors.
     */
    private void setMotorModes(DcMotor.RunMode mode) {
        leftBack.setMode(mode);
        rightBack.setMode(mode);
        leftFront.setMode(mode);
        rightFront.setMode(mode);
    }

    /**
     * Sets power for all drive motors.
     */
    private void setMotorPowers(double power) {
        leftBack.setPower(power);
        rightBack.setPower(power);
        leftFront.setPower(power);
        rightFront.setPower(power);
    }

    /**
     * Checks if all motors are busy.
     */
    private boolean allMotorsBusy() {
        return leftBack.isBusy() && rightBack.isBusy() && leftFront.isBusy() && rightFront.isBusy();
    }

    /**
     * Stops all motors.
     */
    private void stopMotors() {
        setMotorPowers(0);
    }
}
