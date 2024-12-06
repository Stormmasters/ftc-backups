package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous
public class AutM2NZG extends LinearOpMode {
    private DcMotor back_left_motor;
    private DcMotor front_left_motor;
    private DcMotor back_right_motor;
    private DcMotor front_right_motor;
    private Servo shoulder;
    private Servo wrist;
    @Override
    public void runOpMode() {
        back_left_motor = hardwareMap.get(DcMotor.class, "back_left_motor");
        front_left_motor = hardwareMap.get(DcMotor.class, "front_left_motor");
        back_right_motor = hardwareMap.get(DcMotor.class, "back_right_motor");
        front_right_motor = hardwareMap.get(DcMotor.class, "front_right_motor");
        shoulder = hardwareMap.get(Servo.class,"shoulder");
        wrist = hardwareMap.get(Servo.class,"wrist");

        front_left_motor.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();
        
        back_left_motor.setPower(-0.3);
        back_right_motor.setPower(-0.3);
        front_left_motor.setPower(-0.3);
        front_right_motor.setPower(-0.3);
        shoulder.setPosition(0);
        wrist.setPosition(0);

        sleep(2500);
        
        back_left_motor.setPower(0.3);
        back_right_motor.setPower(0.3);
        front_left_motor.setPower(0.3);
        front_right_motor.setPower(0.3);
        shoulder.setPosition(0);
        wrist.setPosition(0);

        sleep(1000);
        
        back_left_motor.setPower(0.5);
        back_right_motor.setPower(-0.5);
        front_left_motor.setPower(-0.5);
        front_right_motor.setPower(0.5);
        shoulder.setPosition(0);
        wrist.setPosition(0);
        
        sleep(2000);
        
        back_left_motor.setPower(-0.3);
        back_right_motor.setPower(-0.3);
        front_left_motor.setPower(-0.3);
        front_right_motor.setPower(-0.3);
        shoulder.setPosition(0);
        wrist.setPosition(0);

        sleep(1000);
        
        back_left_motor.setPower(-0.5);
        back_right_motor.setPower(0.5);
        front_left_motor.setPower(0.5);
        front_right_motor.setPower(-0.5);
        shoulder.setPosition(0);
        wrist.setPosition(0);

        sleep(2000);
        
        back_left_motor.setPower(0.5);
        back_right_motor.setPower(-0.5);
        front_left_motor.setPower(-0.5);
        front_right_motor.setPower(0.5);
        shoulder.setPosition(0);
        wrist.setPosition(0);

        sleep(3000);
        
        back_left_motor.setPower(0.3);
        back_right_motor.setPower(0.3);
        front_left_motor.setPower(0.3);
        front_right_motor.setPower(0.3);
        shoulder.setPosition(0);
        wrist.setPosition(0);
        
        sleep(1500);
    }
}

