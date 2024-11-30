package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class AlexTeleOpM2 extends OpMode {
    double LX, LY, RX, sensitivity = 0.5;
    DcMotor back_left_motor, front_left_motor, front_right_motor, back_right_motor, left_slide, right_slide;
    Servo shoulder, claw, wrist;

    @Override
    public void init() {
        back_left_motor = hardwareMap.dcMotor.get("back_left_motor");
        front_left_motor = hardwareMap.dcMotor.get("front_left_motor");
        front_right_motor = hardwareMap.dcMotor.get("front_right_motor");
        back_right_motor = hardwareMap.dcMotor.get("back_right_motor");
        left_slide = hardwareMap.dcMotor.get("left_slide");
        right_slide = hardwareMap.dcMotor.get("right_slide");

        left_slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        front_left_motor.setDirection(REVERSE);

        claw = hardwareMap.get(Servo.class, "claw");
        shoulder = hardwareMap.get(Servo.class, "shoulder");
        wrist = hardwareMap.get(Servo.class, "wrist");
    }

    @Override
    public void loop() {
        telemetry.addLine("Controls:");

        LX = -gamepad1.left_stick_x * sensitivity;
        LY = -gamepad1.left_stick_y * sensitivity;
        RX = -gamepad1.right_stick_x * sensitivity;

        if (sensitivity < 1 && gamepad1.dpad_up) {
            sensitivity += 0.01;
        } else if (sensitivity > 0 && gamepad1.dpad_down) {
            sensitivity -= 0.01;
        }

        telemetry.addLine("Move dpad up and dpad down to raise and lower sensitivity");
        telemetry.addLine("Move right stick up and down to move wrist.");
        telemetry.addLine("Press Y to open claw, and X to close claw.");

        back_left_motor.setPower(LY + LX - RX);
        front_left_motor.setPower(LY - LX - RX);
        front_right_motor.setPower(LY + LX + RX);
        back_right_motor.setPower(LY - LX + RX);

        telemetry.addLine("Slide position: " + left_slide.getCurrentPosition());

        double slidePower = (gamepad2.left_trigger - gamepad2.right_trigger) * 0.4;
        left_slide.setPower(-slidePower);
        right_slide.setPower(slidePower);

        if (gamepad2.x) {
            claw.setPosition(Math.max(0, claw.getPosition() - 0.005));
        } else if (gamepad2.y) {
            claw.setPosition(Math.min(1, claw.getPosition() + 0.005));
        }
        if (!gamepad2.x && !gamepad2.y){
            claw.setPosition(claw.getPosition());
        }
        

        if (Math.abs(gamepad2.left_stick_y) > 0.1) {
            wrist.setPosition(Math.max(0, Math.min(1, wrist.getPosition() + gamepad2.left_stick_y * 0.005)));
        } else {
            // Hold position when joystick is in the neutral zone
            wrist.setPosition(wrist.getPosition());
        }

        // Dead zone to prevent jittering when joystick is near center
        final double DEAD_ZONE = 0.1;

// Control the wrist servo with the joystick
        if (Math.abs(gamepad2.left_stick_y) > DEAD_ZONE) {
            double newPosition = wrist.getPosition() + gamepad2.left_stick_y * 0.005;
            wrist.setPosition(Math.max(0, Math.min(1, newPosition))); // Clamp position to [0, 1]
        }

// Control the claw servo with buttons
        if (gamepad2.x) {
            double newPosition = claw.getPosition() - 0.005;
            claw.setPosition(Math.max(0, newPosition)); // Clamp position to [0, 1]
        } else if (gamepad2.y) {
            double newPosition = claw.getPosition() + 0.005;
            claw.setPosition(Math.min(1, newPosition)); // Clamp position to [0, 1]
        }



        telemetry.addData("Wrist Servo Position", wrist.getPosition());
        telemetry.addData("Joystick Input", gamepad2.left_stick_y);
        telemetry.addData("Claw Servo Position", claw.getPosition());
        telemetry.update();

    }
}
