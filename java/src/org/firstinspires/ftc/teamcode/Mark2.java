package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous

public class Mark2 extends OpMode {
    
    private DcMotor BackLeft;
    private DcMotor BackRight;
    private DcMotor FrontLeft;
    private DcMotor FrontRight;
    private Servo Arm;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        BackLeft = hardwareMap.get(DcMotor.class, "back_left_motor");
        BackRight = hardwareMap.get(DcMotor.class, "back_right_motor");
        FrontLeft = hardwareMap.get(DcMotor.class, "front_left_motor");
        FrontRight = hardwareMap.get(DcMotor.class, "front_right_motor");
        Arm = hardwareMap.get(Servo.class, "arm");
    }
    public void loop() {
        drive(1, 500);
    }
    private void drive(double power, long time) {
        runtime.reset();
        while (runtime.milliseconds() < time) {
            BackLeft.setPower(power);
            BackRight.setPower(-power);
            FrontLeft.setPower(power);
            FrontRight.setPower(-power);
        }
        stopRobot();
    }
    private void rotate(double power, long time) {
        runtime.reset();
        while (runtime.milliseconds() < time) {
            BackLeft.setPower(-power);
            BackRight.setPower(power);
            FrontLeft.setPower(-power);
            FrontRight.setPower(power);
        }
        stopRobot();
    }
    private void stopRobot() {
        BackLeft.setPower(0);
        BackRight.setPower(0);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
    }
}
