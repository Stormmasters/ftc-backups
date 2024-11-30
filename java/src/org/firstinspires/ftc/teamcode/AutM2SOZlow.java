package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class AutM2SOZlow extends LinearOpMode{

    private DcMotor back_left_motor;
    private DcMotor back_right_motor;
    private DcMotor front_left_motor;
    private DcMotor front_right_motor;
    private DcMotor left_slide;
    private DcMotor right_slide;
    private Servo shoulder;
    private Servo claw;
    private Servo wrist;


    @Override
    public void runOpMode(){

        back_left_motor = hardwareMap.get(DcMotor.class,("back_left_motor"));
        back_right_motor = hardwareMap.get(DcMotor.class,("back_right_motor"));
        front_left_motor = hardwareMap.get(DcMotor.class,("front_left_motor"));
        front_right_motor = hardwareMap.get(DcMotor.class,("front_right_motor"));
        left_slide = hardwareMap.get(DcMotor.class,("left_slide"));
        right_slide = hardwareMap.get(DcMotor.class,("right_slide"));
        shoulder = hardwareMap.get(Servo.class,("shoulder"));
        claw= hardwareMap.get(Servo.class,("claw"));
        wrist = hardwareMap.get(Servo.class,("wrist"));

        front_left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        right_slide.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        back_left_motor.setPower(0.3);
        back_right_motor.setPower(-0.3);
        front_left_motor.setPower(-0.3);
        front_right_motor.setPower(0.3);

        sleep(1000);

        back_left_motor.setPower(0.2);
        back_right_motor.setPower(0.2);
        front_left_motor.setPower(0.2);
        front_right_motor.setPower(0.2);
        shoulder.setPosition(0.2);
        wrist.setPosition(0.15);

        sleep(700);

        back_left_motor.setPower(0.2);
        back_right_motor.setPower(0.2);
        front_left_motor.setPower(0.2);
        front_right_motor.setPower(0.2);
        shoulder.setPosition(0.4);
        wrist.setPosition(0.1);

        sleep(2000);

        back_left_motor.setPower(-0.3);
        back_right_motor.setPower(-0.3);
        front_left_motor.setPower(-0.3);
        front_right_motor.setPower(-0.3);
        wrist.setPosition(0.1);
        shoulder.setPosition(0);

        sleep(300);

        back_left_motor.setPower(-0.4);
        back_right_motor.setPower(-0.4);
        front_left_motor.setPower(-0.4);
        front_right_motor.setPower(-0.4);
        wrist.setPosition(0.1);
        shoulder.setPosition(0.3);
        claw.setPosition(0.4);

        sleep(500);

        back_left_motor.setPower(-0.4);
        back_right_motor.setPower(-0.4);
        front_left_motor.setPower(-0.4);
        front_right_motor.setPower(-0.4);

        sleep(2500);

    }
}
