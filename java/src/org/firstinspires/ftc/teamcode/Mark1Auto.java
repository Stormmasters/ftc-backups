package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Mark1Auto extends LinearOpMode {
    
    //TODO hi
    //hi
    private DcMotor BackLeft;
    private DcMotor BackRight;
    private DcMotor FrontLeft;
    private DcMotor FrontRight;
    private Servo Arm;

    @Override
    public void runOpMode() {
        BackLeft = hardwareMap.get(DcMotor.class, "back_left_motor");
        BackRight = hardwareMap.get(DcMotor.class, "back_right_motor");
        FrontLeft = hardwareMap.get(DcMotor.class, "front_left_motor");
        FrontRight = hardwareMap.get(DcMotor.class, "front_right_motor");
        Arm = hardwareMap.get(Servo.class, "arm");

        waitForStart();
        
        drive(1, 500);
        stopRobot();
    }
    private void drive(double power, long time) {
        BackLeft.setPower(power);
        BackRight.setPower(-power);
        FrontLeft.setPower(power);
        FrontRight.setPower(-power);
        sleep(time);
    }
    private void rotate(double power, long time) {
        FrontLeft.setPower(-power);
        BackLeft.setPower(power);
        FrontRight.setPower(-power);
        BackRight.setPower(power);
        sleep(time);
    }
    private void armRotate(double power, long time) {

    }
    private void stopRobot() {
        BackLeft.setPower(0);
        BackRight.setPower(0);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
    }
}
