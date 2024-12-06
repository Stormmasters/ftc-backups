package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class InitStart extends LinearOpMode {
    private DcMotor back_left_motor;
    private DcMotor front_left_motor;
    private DcMotor back_right_motor;
    private DcMotor front_right_motor;
    private Servo shoulder;
    private Servo claw;
    private Servo wrist;
    private DcMotor left_slide;
    private DcMotor right_slide;
    @Override
    public void runOpMode() {
        left_slide = hardwareMap.get(DcMotor.class, "left_slide");
        right_slide = hardwareMap.get(DcMotor.class, "right_slide");
        back_left_motor = hardwareMap.get(DcMotor.class, "back_left_motor");
        front_left_motor = hardwareMap.get(DcMotor.class, "front_left_motor");
        back_right_motor = hardwareMap.get(DcMotor.class, "back_right_motor");
        front_right_motor = hardwareMap.get(DcMotor.class, "front_right_motor");
        shoulder = hardwareMap.get(Servo.class,"shoulder");
        claw = hardwareMap.get(Servo.class,"claw");
        wrist = hardwareMap.get(Servo.class,"wrist");

        front_left_motor.setDirection(DcMotor.Direction.REVERSE);
        right_slide.setDirection(DcMotor.Direction.REVERSE);
        left_slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();

        
        shoulder.setPosition(0);
        back_left_motor.setPower(0.15);
        back_right_motor.setPower(0.15);
        front_left_motor.setPower(0.15);
        front_right_motor.setPower(0.15);
        left_slide.setPower(0.5);
        right_slide.setPower(0.5);
        shoulder.setPosition(0.3);
        
        sleep(6500);
        
        back_left_motor.setPower(0);
        back_right_motor.setPower(0.1);
        front_left_motor.setPower(0);
        front_right_motor.setPower(0.1);
        shoulder.setPosition(0.2);
        left_slide.setPower(0.3);
        right_slide.setPower(0.3);

        sleep(2000);
        
        shoulder.setPosition(0.2);
        left_slide.setPower(0.4);
        right_slide.setPower(0.4);
        wrist.setPosition(0.3);
        // back_left_motor.setPower(-0.08);
        // back_right_motor.setPower(0.08);
        // front_left
        
        sleep(5000);
        
        left_slide.setPower(0);
        right_slide.setPower(0);
        shoulder.setPosition(0);
        claw.setPosition(1);
        
        sleep(1000);
        
        shoulder.setPosition(0);
        
        sleep(500);
    }
}

