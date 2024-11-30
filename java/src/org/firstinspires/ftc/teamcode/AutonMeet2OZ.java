package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutonMeet2OZ extends LinearOpMode {

    public DcMotor back_left_motor;
    public DcMotor back_right_motor;
    public DcMotor front_left_motor;
    public DcMotor front_right_motor;
    public Servo shoulder;

    @Override
    public void runOpMode(){

        back_left_motor = hardwareMap.get(DcMotor.class,("back_left_motor"));
        back_right_motor = hardwareMap.get(DcMotor.class,("back_left_motor"));
        front_left_motor = hardwareMap.get(DcMotor.class,("back_left_motor"));
        front_right_motor = hardwareMap.get(DcMotor.class,("back_left_motor"));
        shoulder = hardwareMap.get(Servo.class,("shoulder"));

        back_left_motor.setDirection(DcMotor.Direction.REVERSE);
        front_right_motor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        back_left_motor.setPower(-0.4);
        back_right_motor.setPower(0.4);
        front_left_motor.setPower(0.4);
        front_right_motor.setPower(-0.4);

        sleep(1000);

        back_left_motor.setPower(-0.5);
        back_right_motor.setPower(-0.5);
        front_left_motor.setPower(-0.5);
        front_right_motor.setPower(-0.5);

        sleep(3000);

        back_left_motor.setPower(-0.4);
        back_right_motor.setPower(0.4);
        front_left_motor.setPower(0.4);
        shoulder.setPosition(0.1);
        front_right_motor.setPower(-0.4);
        sleep(800);



        back_left_motor.setPower(0.3);
        back_right_motor.setPower(0.3);
        front_left_motor.setPower(0.3);
        front_right_motor.setPower(0.3);

        sleep(500);

    }


}
