package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class AutM2SOZ extends LinearOpMode {

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
        wrist.setPosition(0);
        shoulder.setPosition(0.4);
        claw.setPosition(0);

        sleep(1500);

        back_left_motor.setPower(0.1);
        back_right_motor.setPower(0.1);
        front_left_motor.setPower(0.1);
        front_right_motor.setPower(0.1);
        left_slide.setPower(0.6);
        left_slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_slide.setPower(0.6);
        right_slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shoulder.setPosition(0.4);
        wrist.setPosition(0.1);
        claw.setPosition(0.005);


        sleep(1500);

        back_left_motor.setPower(0.1);
        back_right_motor.setPower(0.1);
        front_left_motor.setPower(0.1);
        front_right_motor.setPower(0.1);
        left_slide.setPower(0.2);
        right_slide.setPower(0.2);
        shoulder.setPosition(0.4);
        wrist.setPosition(0.1);
        claw.setPosition(0.05);

        sleep(3950);

        back_left_motor.setPower(0.1);
        back_right_motor.setPower(0.1);
        front_left_motor.setPower(0.1);
        front_right_motor.setPower(0.1);
        claw.setPosition(0);
        shoulder.setPosition(0.4);
        wrist.setPosition(0.1);

        sleep(760);

        left_slide.setPower(-0.3);
        right_slide.setPower(-0.3);
        claw.setPosition(0);
        shoulder.setPosition(0.4);
        back_left_motor.setPower(0);
        back_right_motor.setPower(0);
        front_left_motor.setPower(0);
        front_right_motor.setPower(0);
        sleep(1050);

        left_slide.setPower(-0.2);
        left_slide.setPower(-0.2);
        claw.setPosition(0.3);
        wrist.setPosition(0.1);
        shoulder.setPosition(0.4);
        back_left_motor.setPower(0);
        back_right_motor.setPower(0);
        front_left_motor.setPower(0);
        front_right_motor.setPower(0);

        sleep(900);

        back_left_motor.setPower(-0.4);
        back_left_motor.setPower(-0.4);
        back_left_motor.setPower(-0.4);
        back_left_motor.setPower(-0.4);
        wrist.setPosition(0);

        sleep(3500);

        back_left_motor.setPower(-0.4);
        back_right_motor.setPower(0.4);
        front_left_motor.setPower(0.4);
        front_right_motor.setPower(-0.4);
        left_slide.setPower(0);
        right_slide.setPower(0);
        wrist.setPosition(0);

        sleep(2800);

    }
}
