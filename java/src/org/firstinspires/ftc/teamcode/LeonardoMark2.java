package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class LeonardoMark2 extends OpMode {

    private DcMotor back_left_motor;
    private DcMotor front_left_motor;
    private DcMotor back_right_motor;
    private DcMotor front_right_motor;
    private DcMotor arm;
    private DcMotor linearActuator;
    private double ly = 0;
    private double lx = 0;
    private double ry = 0;
    private double l2 = 0;
    private double r2 = 0;
    private double deadzone = 0.05;
    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    @Override
    public void init() {
        back_left_motor = hardwareMap.get(DcMotor.class, "back_left_motor");
        front_left_motor = hardwareMap.get(DcMotor.class, "front_left_motor");
        back_right_motor = hardwareMap.get(DcMotor.class, "back_right_motor");
        front_right_motor = hardwareMap.get(DcMotor.class, "front_right_motor");
        arm = hardwareMap.get(DcMotor.class, "arm_motor");
        linearActuator = hardwareMap.get(DcMotor.class, "linearact");
    }

    @Override
    public void loop() {
        ly = -gamepad1.left_stick_y;
        lx = gamepad1.left_stick_x;
        ry = gamepad1.right_stick_y;
        l2 = gamepad1.left_trigger;
        r2 = gamepad1.right_trigger;
        if(Math.abs(ly) < deadzone) {
            ly = 0;
        }
        if(Math.abs(lx) < deadzone) {
            lx = 0;
        }
        if(Math.abs(ry) < deadzone) {
            ry = 0;
        }
        if(Math.abs(l2) < deadzone) {
            l2 = 0;
        }
        if(Math.abs(r2) < deadzone) {
            r2 = 0;
        }
        frontLeftPower = ly+ + lx;
        frontRightPower = -ly - lx;
        backLeftPower = ly - lx;
        backRightPower = -ly + lx;
        if(ry > 0.05) {
            arm.setPower(ry * 3);
        } else {
            arm.setPower(ry / 10);
        }
        linearActuator.setPower(l2);
        linearActuator.setPower(-r2);
        back_left_motor.setPower(backLeftPower);
        front_right_motor.setPower(frontRightPower);
        back_right_motor.setPower(backRightPower);
        front_left_motor.setPower(frontLeftPower);
        
    }
}
