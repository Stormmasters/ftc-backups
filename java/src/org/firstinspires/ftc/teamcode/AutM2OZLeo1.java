package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class AutM2OZLeo1 extends LinearOpMode {

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

        waitForStart();

        shoulder.setPosition(0);
        wrist.setPosition(0.1);
        claw.setPosition(0);
        
        sleep(1000);
        
        back_left_motor.setPower(0.2);
        back_right_motor.setPower(0.2);
        front_left_motor.setPower(0.2);
        front_right_motor.setPower(0.2);
        shoulder.setPosition(0);
        wrist.setPosition(0.1);
        claw.setPosition(0);

        sleep(2000);

        back_left_motor.setPower(0.2);
        back_right_motor.setPower(-0.2);
        front_left_motor.setPower(-0.2);
        front_right_motor.setPower(0.2);
        shoulder.setPosition(0);
        wrist.setPosition(0.1);
        claw.setPosition(0);

        sleep(3000);
        
        back_left_motor.setPower(0.2);
        back_right_motor.setPower(0.2);
        front_left_motor.setPower(0.2);
        front_right_motor.setPower(0.2);
        
        sleep(500);
        
        back_left_motor.setPower(0);
        back_right_motor.setPower(0);
        front_left_motor.setPower(0);
        front_right_motor.setPower(0);
        shoulder.setPosition(1);
        wrist.setPosition(0.1);
        claw.setPosition(0);

        sleep(2000);

        shoulder.setPosition(0.5);
        wrist.setPosition(0.1);
        claw.setPosition(0);
        
        sleep(1000);
        
        claw.setPosition(0.1);

        sleep(100);

        shoulder.setPosition(0);
        wrist.setPosition(0.1);
        claw.setPosition(0.1);

        sleep(700);

        back_left_motor.setPower(-0.2);
        back_right_motor.setPower(-0.2);
        front_left_motor.setPower(-0.2);
        front_right_motor.setPower(-0.2);
        claw.setPosition(0);
        
        sleep(1000);
    }
}
