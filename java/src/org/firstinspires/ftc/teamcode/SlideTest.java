package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
@TeleOp
public class SlideTest extends OpMode{
    double LX, LY, RX, sensitivity = 0.5;
    DcMotor BL, FL, FR, BR, S1, S2;
    Servo SV1;
    @Override
    public void init(){
        BL = hardwareMap.dcMotor.get("back_left_motor");
        FL = hardwareMap.dcMotor.get("front_left_motor");
        FR = hardwareMap.dcMotor.get("front_right_motor");
        BR = hardwareMap.dcMotor.get("back_right_motor");
        S1 = hardwareMap.dcMotor.get("slide1");
        S2 = hardwareMap.dcMotor.get("slide2");
        S1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        S2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        S1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        S2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // This matches the DcMotors to the ones configured in the Control Hub
        BL.setDirection(REVERSE);
        S1.setDirection(FORWARD);
        S2.setDirection(FORWARD);
    }
    @Override
    public void loop(){
        S1.setPower(0.2);
        S2.setPower(0.2);
        
    }
}