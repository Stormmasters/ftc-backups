package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Test12iEncoder", group = "Autonomous")
public class Test12iEncoder extends LinearOpMode {

    // Declare motors
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;

    // Constants for encoder calculations
    static final double TICKS_PER_REV = 537.7; // GoBILDA 5202/3/4 series motor ticks per revolution
    static final double WHEEL_DIAMETER_INCHES = 4.0; // Diameter of the mecanum wheels
    static final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER_INCHES);

    @Override
    public void runOpMode() {
        // Initialize motors
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");

        // Ensure encoders are reset
        resetEncoders();

        telemetry.addData("Status", "Encoders reset. Initialized. Waiting for start...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // Move the front motors forward 5 inches
            moveFrontMotors(5.0, 0.5); // Move 5 inches at 50% power
        }
    }

    /**
     * Moves the front left and front right motors forward a specified distance in inches.
     * Ensures the motors maintain synchronized encoder positions and power distribution.
     *
     * @param inches Distance to move forward in inches
     * @param power  Motor power (0 to 1)
     */
    private void moveFrontMotors(double inches, double power) {
        // Calculate the target position in encoder ticks
        int targetTicks = (int) (inches * TICKS_PER_INCH);

        // Set target positions for front motors
        frontLeftMotor.setTargetPosition(targetTicks);
        frontRightMotor.setTargetPosition(targetTicks);

        // Set motors to RUN_TO_POSITION mode
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set motor power
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);

        // Monitor encoder positions and power while motors are active
        while (opModeIsActive() && (frontLeftMotor.isBusy() || frontRightMotor.isBusy())) {
            // Ensure motors remain synchronized
            int leftPosition = frontLeftMotor.getCurrentPosition();
            int rightPosition = frontRightMotor.getCurrentPosition();

            telemetry.addData("Target Ticks", targetTicks);
            telemetry.addData("Front Left Position", leftPosition);
            telemetry.addData("Front Right Position", rightPosition);
            telemetry.addData("Front Left Power", frontLeftMotor.getPower());
            telemetry.addData("Front Right Power", frontRightMotor.getPower());

            // Check for position mismatch and adjust power if necessary
            if (Math.abs(leftPosition - rightPosition) > 10) { // Allowable threshold
                if (leftPosition > rightPosition) {
                    frontLeftMotor.setPower(power * 0.9); // Reduce left motor power
                    frontRightMotor.setPower(power); // Maintain right motor power
                } else {
                    frontRightMotor.setPower(power * 0.9); // Reduce right motor power
                    frontLeftMotor.setPower(power); // Maintain left motor power
                }
            } else {
                // Maintain even power if positions are synchronized
                frontLeftMotor.setPower(power);
                frontRightMotor.setPower(power);
            }

            telemetry.update();
        }

        // Stop motors
        stopMotors();

        // Reset motors to RUN_USING_ENCODER mode
        resetEncoders();
    }

    /**
     * Stops both front motors.
     */
    private void stopMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
    }

    /**
     * Resets the encoders for both front motors and sets them to RUN_USING_ENCODER mode.
     */
    private void resetEncoders() {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
