package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "IDrop", group = "Autonomous")
public class IDrop extends LinearOpMode {

    // Declare Servo objects
    private CRServo shoulder;
    private CRServo wrist;
    private Servo claw;

    @Override
    public void runOpMode() {
        // Initialize the hardware
        shoulder = hardwareMap.get(CRServo.class, "shoulder");
        wrist = hardwareMap.get(CRServo.class, "wrist");
        claw = hardwareMap.get(Servo.class, "claw");

        // Wait for the start of the autonomous period
        waitForStart();

        if (opModeIsActive()) {
            // Run IShoulderTestAuto actions
            moveShoulder();

            // Run IWristTestAuto actions concurrently
            new Thread(this::moveWrist).start();

            // Wait 3 seconds before running IClawAuto actions
            sleep(3000);
            moveClaw();
        }
    }

    /**
     * Moves the shoulder servo to a specific position.
     */
    private void moveShoulder() {
        for (int i = 0; i < 4; i++) {
            telemetry.addData("Action", "Moving shoulder (Iteration %d)", i + 1);
            telemetry.update();
            shoulder.setPower(-1); // Move to the minimum position
            sleep(1000); // Wait for 1 second
        }
        shoulder.setPower(0); // Stop the servo
    }

    /**
     * Moves the wrist servo to a specific position.
     */
    private void moveWrist() {
        for (int i = 0; i < 1; i++) {
            telemetry.addData("Action", "Moving wrist up (Iteration %d)", i + 1);
            telemetry.update();
            wrist.setPower(1.0); // Adjust power for the desired movement // Increment position by 0.1, cap at 1.0
         //   sleep(1000); // Wait for 1 second
        }
    }

    /**
     * Opens the claw by setting the servo position to 0.0.
     */
    private void moveClaw() {
        telemetry.addData("Action", "Opening claw");
        telemetry.update();
        claw.setPosition(0.0); // Adjust position based on your servo calibration
        sleep(1000); // Hold position for 1 second
    }
}


