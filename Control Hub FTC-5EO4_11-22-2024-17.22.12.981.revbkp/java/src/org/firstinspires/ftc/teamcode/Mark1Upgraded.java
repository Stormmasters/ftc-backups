package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Mark1Upgraded extends LinearOpMode{
    private DcMotor BackLeft;
    private DcMotor FrontLeft;
    private DcMotor FrontRight;
    private DcMotor BackRight;
    private Servo Arm;
    private double arm = 0.0;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        BackLeft = hardwareMap.get(DcMotor.class, "back_left_motor");
        FrontLeft = hardwareMap.get(DcMotor.class, "front_left_motor");
        FrontRight = hardwareMap.get(DcMotor.class, "front_right_motor");
        BackRight = hardwareMap.get(DcMotor.class, "back_right_motor");
        Arm = hardwareMap.get(Servo.class, "arm");

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                FrontRight.setPower(-gamepad1.left_stick_y);
                BackRight.setPower(-gamepad1.left_stick_y);
                FrontLeft.setPower(gamepad1.left_stick_y);
                BackLeft.setPower(gamepad1.left_stick_y);
                FrontRight.setPower(-gamepad1.left_stick_x);
                BackRight.setPower(gamepad1.left_stick_x);
                FrontLeft.setPower(-gamepad1.left_stick_x);
                BackLeft.setPower(gamepad1.left_stick_x);
                FrontRight.setPower(-gamepad1.right_stick_x);
                BackRight.setPower(-gamepad1.right_stick_x);
                FrontLeft.setPower(-gamepad1.right_stick_x);
                BackLeft.setPower(-gamepad1.right_stick_x);
                if (gamepad1.dpad_up){
                    Arm.setPosition(arm + 0.0002);
                    arm += 0.0002;
                }
                else if (gamepad1.dpad_down){
                    Arm.setPosition(arm - 0.0002);
                    arm -= 0.0002;
                }
                while (gamepad1.right_bumper) {
                    FrontRight.setPower(-gamepad1.left_stick_y);
                    BackRight.setPower(-gamepad1.left_stick_y);
                    FrontLeft.setPower(gamepad1.left_stick_y);
                    BackLeft.setPower(gamepad1.left_stick_y);
                    FrontRight.setPower(-gamepad1.left_stick_x);
                    BackRight.setPower(gamepad1.left_stick_x);
                    FrontLeft.setPower(-gamepad1.left_stick_x);
                    BackLeft.setPower(gamepad1.left_stick_x);
                    FrontRight.setPower(-gamepad1.right_stick_x);
                    BackRight.setPower(-gamepad1.right_stick_x);
                    FrontLeft.setPower(-gamepad1.right_stick_x);
                    BackLeft.setPower(-gamepad1.right_stick_x);
                }
                telemetry.update();
            }
        }
    }
}    