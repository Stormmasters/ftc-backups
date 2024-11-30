package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class AutonTestIDK extends OpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;


    @Override
    public void init() {
        //configuring the motors
        frontLeft = hardwareMap.dcMotor.get("front_left_motor");
        frontRight = hardwareMap.dcMotor.get("front_right_motor");
        backLeft = hardwareMap.dcMotor.get("back_left_motor");
        backRight = hardwareMap.dcMotor.get("back_right_motor");
        //reverse the motors
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        //start the encoders
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //reset the encoders for precision
        frontLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        frontRight.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backRight.setMode(DcMotor.RunMode.RESET_ENCODERS);

    }

    @Override
    public void loop() {
        //motors run forward for one meter
        time += 1;
        if (time < 1000) {
            frontRight.setPower(0.5);
            frontLeft.setPower(0.5);
            backLeft.setPower(0.5);
            backRight.setPower(0.5);
        }
        else {
            frontRight.setPower(0.0);
            frontLeft.setPower(0.0);
            backLeft.setPower(0.0);
            backRight.setPower(0.0);
        }


    }
}
