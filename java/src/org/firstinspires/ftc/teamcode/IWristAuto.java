package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "IWristAuto", group = "Autonomous")
public class IWristAuto extends LinearOpMode {

    private Servo wristServo;

    @Override
    public void runOpMode() {
        // Initialize the wrist servo
        wristServo = hardwareMap.get(Servo.class, "wrist");

        // Set the initial position of the servo to 0.5 (neutral position)
        wristServo.setPosition(0.5);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (opModeIsActive()) {
            // Move the wrist servo up three times
            for (int i = 0; i < 3; i++) {
                telemetry.addData("Action", "Moving Up (Iteration %d)", i + 1);
                telemetry.update();
                wristServo.setPosition(Math.min(wristServo.getPosition() + 0.1, 1.0)); // Increment position by 0.1, cap at 1.0
                sleep(1000); // Wait for 1 second
            }

            telemetry.addData("Status", "Task Complete");
            telemetry.update();
        }
    }
}




