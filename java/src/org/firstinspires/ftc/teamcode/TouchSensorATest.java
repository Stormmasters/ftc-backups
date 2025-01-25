package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@Autonomous(name = "TouchSensorATest", group = "Autonomous")
public class TouchSensorATest extends LinearOpMode {

    // Declare motors and sensors
    private DcMotor par0, par1; // Slide motors
    private CRServo shoulder; // Shoulder motor
    private DigitalChannel slideT, armT; // Touch sensors

    @Override
    public void runOpMode() {
        // Initialize hardware
        initializeHardware();

        telemetry.addData("Status", "Initialized. Waiting for start...");
        telemetry.update();

        waitForStart();

        // Example loop for autonomous control
        while (opModeIsActive()) {
            // Check touch sensors and stop corresponding motors
            stopSlidesIfTouchSensorPressed();
            stopShoulderIfTouchSensorPressed();

            // Example: Run motors to simulate operation
            par0.setPower(0.5); // Move slides
            par1.setPower(0.5);
            shoulder.setPower(0.5); // Move shoulder

            telemetry.addData("slideT Pressed", slideT.getState() == false);
            telemetry.addData("armT Pressed", armT.getState() == false);
            telemetry.update();
        }

        // Stop all motors at the end of the autonomous period
        stopAllMotors();
    }

    // ===================== HELPER METHODS =====================

    /**
     * Initializes the hardware for motors and sensors.
     */
    private void initializeHardware() {
        par0 = hardwareMap.get(DcMotor.class, "par0");
        par1 = hardwareMap.get(DcMotor.class, "par1");
        shoulder = hardwareMap.get(CRServo.class, "shoulder");
        slideT = hardwareMap.get(DigitalChannel.class, "slideT");
        armT = hardwareMap.get(DigitalChannel.class, "armT");

        // Set the touch sensors to input mode
        slideT.setMode(DigitalChannel.Mode.INPUT);
        armT.setMode(DigitalChannel.Mode.INPUT);

        // Set slide motors to brake when power is zero
        par0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        par1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Stops slide motors (par0 and par1) if the slideT touch sensor is pressed.
     */
    private void stopSlidesIfTouchSensorPressed() {
        if (!slideT.getState()) { // If slideT is pressed (false means pressed for REV sensors)
            par0.setPower(0);
            par1.setPower(0);
            telemetry.addData("Slides", "Stopped (Touch Sensor Pressed)");
        }
    }

    /**
     * Stops the shoulder motor if the armT touch sensor is pressed.
     */
    private void stopShoulderIfTouchSensorPressed() {
        if (!armT.getState()) { // If armT is pressed (false means pressed for REV sensors)
            shoulder.setPower(0);
            telemetry.addData("Shoulder", "Stopped (Touch Sensor Pressed)");
        }
    }

    /**
     * Stops all motors.
     */
    private void stopAllMotors() {
        par0.setPower(0);
        par1.setPower(0);
        shoulder.setPower(0);
        telemetry.addData("Motors", "All stopped");
        telemetry.update();
    }
}
