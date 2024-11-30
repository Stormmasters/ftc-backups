package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous
public class Meet2NZLong extends LinearOpMode {

    private DcMotor back_left_motor;
    private DcMotor back_right_motor;
    private DcMotor front_left_motor;
    private DcMotor front_right_motor;
    private Servo shoulder;

    @Override
    public void runOpMode(){

        back_left_motor = hardwareMap.get(DcMotor.class,("back_left_motor"));
        back_right_motor = hardwareMap.get(DcMotor.class,("back_right_motor"));
        front_left_motor = hardwareMap.get(DcMotor.class,("front_left_motor"));
        front_right_motor = hardwareMap.get(DcMotor.class,("front_right_motor"));
        shoulder = hardwareMap.get(Servo.class,("shoulder"));
        
        front_left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        
        waitForStart();

        back_left_motor.setPower(-0.5);
        back_right_motor.setPower(-0.5);
        front_left_motor.setPower(-0.5);
        front_right_motor.setPower(-0.5);

        sleep(1500);

    }
}
