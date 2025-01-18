package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "ImaadAuton117", group = "Autonomous")
public class ImaadAuton117 extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor slideMotor1 = null;
    private DcMotor slideMotor2 = null;
    private Servo shoulderServo = null;
    private Servo wristServo = null;
    private Servo clawServo = null;

    @Override
    public void runOpMode() {
        // Initialize hardware
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        slideMotor1 = hardwareMap.get(DcMotor.class, "slide_motor_1");
        slideMotor2 = hardwareMap.get(DcMotor.class, "slide_motor_2");
        shoulderServo = hardwareMap.get(Servo.class, "shoulder_servo");
        wristServo = hardwareMap.get(Servo.class, "wrist_servo");
        clawServo = hardwareMap.get(Servo.class, "claw_servo");

        // Reverse the right motors to ensure correct directional movement
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");



        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (opModeIsActive()) {
            // Move robot forward for a specified time
            double forwardPower = 0.5; // Normal speed
            long forwardDuration = 1500; // Approximate duration in milliseconds to move 3.5 feet

            // Set motor powers to move forward
            frontLeft.setPower(forwardPower);
            frontRight.setPower(forwardPower);
            backLeft.setPower(forwardPower);
            backRight.setPower(forwardPower);

            // Simultaneously raise the slide motors
            slideMotor1.setPower(0.25);
            slideMotor2.setPower(0.25);

            // Move the shoulder servo up by 35 degrees and wrist servo down by 20 degrees
            double shoulderInitialPosition = 0.5; // Assume middle position
            double wristInitialPosition = 0.5; // Assume middle position
            double shoulderTargetPosition = shoulderInitialPosition + (35.0 / 180.0); // Convert degrees to servo range
            double wristTargetPosition = wristInitialPosition - (20.0 / 180.0); // Convert degrees to servo range

            shoulderServo.setPosition(shoulderTargetPosition);
            wristServo.setPosition(wristTargetPosition);

            // Open the claw servo by 15 degrees
            double clawInitialPosition = 0.5; // Assume middle position
            double clawTargetPosition = clawInitialPosition + (15.0 / 180.0); // Convert degrees to servo range
            clawServo.setPosition(clawTargetPosition);

            // Sleep for the duration of forward movement
            sleep(forwardDuration);

            // Stop all motors
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            slideMotor1.setPower(0);
            slideMotor2.setPower(0);

            telemetry.addData("Status", "Task Complete");
            telemetry.update();
        }
    }
}
