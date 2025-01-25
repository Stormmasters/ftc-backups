package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "TestiEncoder", group = "Autonomous")
public class TestiEncoder extends LinearOpMode {

    // Declare motors
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    // Constants for encoder calculations
    static final double TICKS_PER_REV = 537.7; // GoBILDA 5202/3/4 series motor ticks per revolution
    static final double WHEEL_DIAMETER_INCHES = 4.0; // Diameter of the mecanum wheels
    static final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER_INCHES);

    @Override
    public void runOpMode() {
        // Initialize motors
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");

        // Reset encoders and set motors to RUN_USING_ENCODER mode
        resetEncoders();

        telemetry.addData("Status", "Initialized. Waiting for start...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // Test each motor sequentially
            testMotor(frontLeftMotor, "Front Left Motor");
            testMotor(frontRightMotor, "Front Right Motor");
            testMotor(backLeftMotor, "Back Left Motor");
            testMotor(backRightMotor, "Back Right Motor");
        }
    }

    /**
     * Tests a single motor by moving it forward for a small distance and displaying telemetry.
     *
     * @param motor  The motor to test
     * @param name   The name of the motor (for telemetry)
     */
    private void testMotor(DcMotor motor, String name) {
        // Distance to move (in inches)
        double testDistance = 2.0; // Move 2 inches
        double power = 0.5; // Motor power (50%)

        // Calculate the target position in encoder ticks
        int targetTicks = (int) (testDistance * TICKS_PER_INCH);

        // Set the target position and mode
        motor.setTargetPosition(motor.getCurrentPosition() + targetTicks);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Apply power to the motor
        motor.setPower(power);

        // Wait until the motor reaches the target position
        while (opModeIsActive() && motor.isBusy()) {
            telemetry.addData("Testing", name);
            telemetry.addData("Target Ticks", targetTicks);
            telemetry.addData("Current Position", motor.getCurrentPosition());
            telemetry.addData("Power", motor.getPower());
            telemetry.update();
        }

        // Stop the motor
        motor.setPower(0);

        // Reset the motor to RUN_USING_ENCODER mode
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Pause between tests
        sleep(1000); // 1-second pause for clarity
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
}
