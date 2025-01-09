package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class AutonMeet3NZA extends LinearOpMode {

    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftSlide;
    private DcMotor rightSlide;
    private Servo shoulder;
    private Servo wrist;
    private Servo claw;
    private Servo hangArm;

    @Override
    public void runOpMode(){

        leftBack = hardwareMap.get(DcMotor.class,"leftBack");
        rightBack = hardwareMap.get(DcMotor.class,"rightBack");
        leftFront = hardwareMap.get(DcMotor.class,"leftFront");
        rightFront = hardwareMap.get(DcMotor.class,"rightFront");
        leftSlide = hardwareMap.get(DcMotor.class,"par0");
        rightSlide = hardwareMap.get(DcMotor.class,"par1");
        shoulder = hardwareMap.get(Servo.class,"shoulder");
        wrist = hardwareMap.get(Servo.class,"wrist");
        claw = hardwareMap.get(Servo.class,"claw");
        hangArm = hardwareMap.get(Servo.class,"hangArm");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        shoulder.setPosition(0);
        wrist.setPosition(1);
        leftBack.setPower(0.15);
        leftFront.setPower(0.15);
        rightBack.setPower(0.15);
        rightFront.setPower(0.15);
        leftSlide.setPower(0.3);
        rightSlide.setPower(0.3);
        
        sleep(3000);
        
        leftSlide.setPower(0.3);
        rightSlide.setPower(0.3);
        shoulder.setPosition(0);
        wrist.setPosition(0);
        
        sleep(5000);
        
        
    }

}
