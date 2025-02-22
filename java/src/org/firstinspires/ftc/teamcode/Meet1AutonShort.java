package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Meet1AutonShort extends LinearOpMode {
    private DcMotor back_left_motor;
    private DcMotor front_left_motor;
    private DcMotor back_right_motor;
    private DcMotor front_right_motor;
    @Override
    public void runOpMode() {
        back_left_motor = hardwareMap.get(DcMotor.class, "back_left_motor");
        front_left_motor = hardwareMap.get(DcMotor.class, "front_left_motor");
        back_right_motor = hardwareMap.get(DcMotor.class, "back_right_motor");
        front_right_motor = hardwareMap.get(DcMotor.class, "front_right_motor");

        
        //back_left_motor.setDirection(DcMotor.Direction.REVERSE);
         //front_left_motor.setDirection(DcMotor.Direction.REVERSE);
         front_right_motor.setDirection(DcMotor.Direction.REVERSE);
         back_right_motor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

//        back_left_motor.setPower(0.5);
//        back_right_motor.setPower(0.5);
//        front_left_motor.setPower(0.5);
//        front_right_motor.setPower(0.5);
//
//        sleep(250);
//
//        back_left_motor.setPower(0.5);
//        back_right_motor.setPower(-0.5);
//        front_left_motor.setPower(0.5);
//        front_right_motor.setPower(-0.5);
//
//        sleep(1690);
//
//        back_left_motor.setPower(0.5);
//        back_right_motor.setPower(-0.5);
//        front_left_motor.setPower(-0.5);
//        front_right_motor.setPower(0.5);
//
//        sleep(150);
//
//        back_left_motor.setPower(0.5);
//        back_right_motor.setPower(0.5);
//        front_left_motor.setPower(0.5);
//        front_right_motor.setPower(0.5);
//
//        sleep(2750);

        back_left_motor.setPower(0.5);
        back_right_motor.setPower(0.5);
        front_left_motor.setPower(0.5);
        front_right_motor.setPower(0.5);

        sleep(300);

        back_left_motor.setPower(0.5);
        back_right_motor.setPower(-0.5);
        front_left_motor.setPower(-0.5);
        front_right_motor.setPower(0.5);

        sleep(1690);

        back_left_motor.setPower(0.5);
        back_right_motor.setPower(0.5);
        front_left_motor.setPower(0.5);
        front_right_motor.setPower(0.5);

        sleep(4500);

        back_left_motor.setPower(-0.5);
        back_right_motor.setPower(0.5);
        front_left_motor.setPower(0.5);
        front_right_motor.setPower(-0.5);

        sleep(900);




    }
}
