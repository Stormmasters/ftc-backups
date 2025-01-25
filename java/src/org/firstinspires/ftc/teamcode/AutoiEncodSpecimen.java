package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutoiEncodSpecimen", group = "Autonomous")
public class AutoiEncodSpecimen extends LinearOpMode {

    // Declare motors and servos
    private DcMotor leftBack, rightBack, leftFront, rightFront, leftSlide, rightSlide;
    private CRServo shoulder, wrist;
    private Servo claw;

    // Encoder constants
    static final double TICKS_PER_REV = 537.7; // For GoBILDA 5202/3/4 motors
    static final double WHEEL_DIAMETER_INCHES = 4.0; // Wheel diameter in inches
    static final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER_INCHES);

    // PID constants
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
        pidDriveForward(12, 0.5); // Drive forward 12 inches with PID control
        operateSlidesWithoutEncoders(0.5, 2000); // Lift slides for 2 seconds
        operateClaw(0.2); // Close the claw
        pidDriveBackward(12, 0.5); // Drive backward 12 inches with PID control
        operateClaw(0.8); // Open the claw
    }

    // ===================== HELPER METHODS =====================

    /**
     * Initializes all motors and servos based on the hardware configuration.
     */
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

        // Reverse motor directions as needed
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set slide motors to brake when power is zero
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reset encoders for drive motors
        resetEncoders();
    }

    /**
     * Resets all motor encoders (except slides) and sets them to RUN_USING_ENCODER mode.
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
     * Drives the robot forward a specified distance in inches using PID control.
     */
    private void pidDriveForward(double inches, double basePower) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);

        // Variables for PID control
        int previousError = 0;

        // Calculate encoder target positions
        leftBack.setTargetPosition(leftBack.getCurrentPosition() + targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() + targetTicks);
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + targetTicks);

        // Set motors to RUN_TO_POSITION mode
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Run PID loop
        while (opModeIsActive() && allMotorsBusy()) {
            // Calculate current positions and error
            int currentPosition = (leftBack.getCurrentPosition() + rightBack.getCurrentPosition()
                    + leftFront.getCurrentPosition() + rightFront.getCurrentPosition()) / 4;
            int error = targetTicks - currentPosition;

            // Calculate derivative
            int derivative = error - previousError;

            // Calculate PID adjustment
            double adjustment = (KP * error) + (KD * derivative);

            // Apply power with adjustment
            double adjustedPower = basePower + adjustment;
            setMotorPowers(adjustedPower);

            // Update telemetry
            telemetry.addData("Target Ticks", targetTicks);
            telemetry.addData("Current Position", currentPosition);
            telemetry.addData("Error", error);
            telemetry.addData("Power Adjustment", adjustment);
            telemetry.update();

            // Update previous error
            previousError = error;
        }

        stopMotors();
    }

    /**
     * Drives the robot backward a specified distance in inches using PID control.
     */
    private void pidDriveBackward(double inches, double basePower) {
        pidDriveForward(-inches, basePower);
    }

    /**
     * Operates the slides using time-based control (no encoders).
     *
     * @param power Power to apply to the slide motors.
     * @param duration Duration in milliseconds to run the slide motors.
     */
    private void operateSlidesWithoutEncoders(double power, int duration) {
        leftSlide.setPower(power);
        rightSlide.setPower(power);

        telemetry.addData("Slides", "Running for %d ms at power %.2f", duration, power);
        telemetry.update();

        sleep(duration); // Run the slides for the specified duration

        leftSlide.setPower(0);
        rightSlide.setPower(0);

        telemetry.addData("Slides", "Stopped");
        telemetry.update();
    }

    /**
     * Operates the claw to a specified position.
     */
    private void operateClaw(double position) {
        claw.setPosition(position);
        telemetry.addData("Claw", "Position: %.2f", position);
        telemetry.update();
        sleep(500); // Allow time for the servo to move
    }

    /**
     * Sets power for all drive motors.
     */
    private void setMotorPowers(double power) {
        leftBack.setPower(clamp(power, -1.0, 1.0));
        rightBack.setPower(clamp(power, -1.0, 1.0));
        leftFront.setPower(clamp(power, -1.0, 1.0));
        rightFront.setPower(clamp(power, -1.0, 1.0));
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

    /**
     * Clamps a value between a minimum and a maximum.
     */
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
