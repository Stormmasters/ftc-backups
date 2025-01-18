package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
@Autonomous(name = "IClawAuton", group = "Autonomous")
public class IClawAuton extends LinearOpMode {

    // Declare a Servo object
    private Servo claw;

    @Override
    public void runOpMode() {
        // Initialize the hardware
        claw = hardwareMap.get(Servo.class, "claw");

        // Wait for the start of the match
        waitForStart();

        if (opModeIsActive()) {
            openClaw(); // Open the claw
            sleep(1000); // Hold position for 1 second
        }
    }

    /**
     * Opens the claw by setting the servo position to 0.0.
     */
    private void openClaw() {
        claw.setPosition(0.5); // Adjust position based on your servo calibration
    }
}