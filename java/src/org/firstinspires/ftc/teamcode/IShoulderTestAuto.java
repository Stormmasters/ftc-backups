package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "IShoulderTestAuto", group = "Autonomous")
public class IShoulderTestAuto extends LinearOpMode {

    private Servo shoulderServo;

    @Override
    public void runOpMode() {
        // Initialize the shoulder servo
        shoulderServo = hardwareMap.get(Servo.class, "shoulder");

        // Set the initial position of the servo to 0.5 (neutral position)
        shoulderServo.setPosition(0.5);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (opModeIsActive()) {
            // Move the servo to position 0.0 three times
            for (int i = 0; i < 4; i++) {
                telemetry.addData("Action", "Moving to 0.0 (Iteration %d)", i + 1);
                telemetry.update();
                shoulderServo.setPosition(-1); // Move to the minimum position
                sleep(1000); // Wait for 1 second
            }

            // Keep the servo in the final position for 4 seconds
            telemetry.addData("Action", "Holding final position for 4 seconds");
            telemetry.update();
            sleep(4000); // Wait for 4 seconds

            telemetry.addData("Status", "Task Complete");
            telemetry.update();
        }
    }
}
