package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutonMeet2A extends LinearOpMode {
    private DcMotor back_left_motor;
    private DcMotor front_left_motor;
    private DcMotor back_right_motor;
    private DcMotor front_right_motor;
    private Servo shoulder;
    @Override
    public void runOpMode() {
        back_left_motor = hardwareMap.get(DcMotor.class, "back_left_motor");
        front_left_motor = hardwareMap.get(DcMotor.class, "front_left_motor");
        back_right_motor = hardwareMap.get(DcMotor.class, "back_right_motor");
        front_right_motor = hardwareMap.get(DcMotor.class, "front_right_motor");
        shoulder = hardwareMap.get(Servo.class,"shoulder");

        front_left_motor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        back_left_motor.setPower(0.5);
        back_right_motor.setPower(-0.5);
        front_left_motor.setPower(-0.5);
        front_right_motor.setPower(0.5);

        sleep(900);

        back_left_motor.setPower(-0.4);
        back_right_motor.setPower(-0.4);
        front_left_motor.setPower(-0.4);
        front_right_motor.setPower(-0.4);
        shoulder.setPosition(0);

        sleep(2600);

        back_left_motor.setPower(0.5);
        back_right_motor.setPower(-0.5);
        front_left_motor.setPower(-0.5);
        front_right_motor.setPower(0.5);
        shoulder.setPosition(0);

        sleep(1500);

        back_left_motor.setPower(0.3);
        back_right_motor.setPower(0.3);
        front_left_motor.setPower(0.3);
        front_right_motor.setPower(0.3);
        shoulder.setPosition(0.1);

        sleep(1100);
    }
}