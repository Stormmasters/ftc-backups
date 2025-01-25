package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "PIDControllediMove", group = "Autonomous")
public class PIDControllediMove extends LinearOpMode {

    // Declare motors
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    // Constants for encoder calculations
    static final double TICKS_PER_REV = 537.7; // GoBILDA 5202/3/4 series motor ticks per revolution
    static final double WHEEL_DIAMETER_INCHES = 4.0; // Diameter of the mecanum wheels
    static final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER_INCHES);

    // PID constants
    static final double KP = 0.01; // Proportional gain
    static final double KD = 0.005; // Derivative gain

    @Override
    public void runOpMode() {
        // Initialize motors
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");

        // Set motor directions
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        // Reset encoders and set motors to RUN_USING_ENCODER mode
        resetEncoders();

        telemetry.addData("Status", "Encoders reset. Initialized. Waiting for start...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // Move forward 24 inches using PID control
            pidDriveForward(24.0, 0.5); // Move 24 inches with a base power of 50%
        }
    }

    /**
     * Moves the robot forward using PID control for encoder feedback.
     *
     * @param inches    Distance to move forward in inches
     * @param basePower Base motor power (0 to 1)
     */
    private void pidDriveForward(double inches, double basePower) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);

        // Set target positions for all motors
        frontLeftMotor.setTargetPosition(targetTicks);
        frontRightMotor.setTargetPosition(targetTicks);
        backLeftMotor.setTargetPosition(targetTicks);
        backRightMotor.setTargetPosition(targetTicks);

        // Set motors to RUN_TO_POSITION mode
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Variables for PID control
        int previousError = 0;

        while (opModeIsActive() && allMotorsBusy()) {
            // Calculate average encoder positions for left and right sides
            int leftPosition = (frontLeftMotor.getCurrentPosition() + backLeftMotor.getCurrentPosition()) / 2;
            int rightPosition = (frontRightMotor.getCurrentPosition() + backRightMotor.getCurrentPosition()) / 2;

            // Calculate error for both sides
            int leftError = targetTicks - leftPosition;
            int rightError = targetTicks - rightPosition;

            // Calculate derivative
            int leftDerivative = leftError - previousError;
            int rightDerivative = rightError - previousError;

            // Calculate PID adjustments for each side
            double leftAdjustment = (KP * leftError) + (KD * leftDerivative);
            double rightAdjustment = (KP * rightError) + (KD * rightDerivative);

            // Apply PID adjustments to motor power
            double leftPower = basePower + leftAdjustment;
            double rightPower = basePower + rightAdjustment;

            // Apply power to the motors (clamp to ensure no motor receives more than 1.0 or less than -1.0)
            frontLeftMotor.setPower(clamp(leftPower, -1.0, 1.0));
            backLeftMotor.setPower(clamp(leftPower, -1.0, 1.0));
            frontRightMotor.setPower(clamp(rightPower, -1.0, 1.0));
            backRightMotor.setPower(clamp(rightPower, -1.0, 1.0));

            // Update telemetry
            telemetry.addData("Target Ticks", targetTicks);
            telemetry.addData("Left Position", leftPosition);
            telemetry.addData("Right Position", rightPosition);
            telemetry.addData("Left Error", leftError);
            telemetry.addData("Right Error", rightError);
            telemetry.addData("Left Power", leftPower);
            telemetry.addData("Right Power", rightPower);
            telemetry.update();

            // Update previous error for next iteration
            previousError = (leftError + rightError) / 2;
        }

        // Stop motors
        stopAllMotors();
    }

    /**
     * Stops all motors.
     */
    private void stopAllMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    /**
     * Resets the encoders for all motors and sets them to RUN_USING_ENCODER mode.
     */
    private void resetEncoders() {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Checks if all motors are still busy.
     */
    private boolean allMotorsBusy() {
        return frontLeftMotor.isBusy() && frontRightMotor.isBusy() && backLeftMotor.isBusy() && backRightMotor.isBusy();
    }

    /**
     * Clamps a value between a minimum and a maximum.
     */
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
