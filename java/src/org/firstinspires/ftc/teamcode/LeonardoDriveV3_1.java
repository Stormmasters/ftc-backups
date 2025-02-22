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
public class LeonardoDriveV3_1 extends OpMode{
    double LX, LY, RX, sensitivity = 0.5, a, b;
    DcMotor BL, FL, FR, BR, S1, S2, M1, M2;
    Servo Intake, Arm, Wrist, HangArm;
    @Override
    public void init(){
        BL = hardwareMap.dcMotor.get("leftBack");
        FL = hardwareMap.dcMotor.get("leftFront");
        FR = hardwareMap.dcMotor.get("rightFront");
        BR = hardwareMap.dcMotor.get("rightBack");
        S1 = hardwareMap.dcMotor.get("par1");
        S2 = hardwareMap.dcMotor.get("par0");
        S1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        S2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        S1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        S2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        S1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        S2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // This matches the DcMotors to the ones configured in the Control Hub
        FL.setDirection(REVERSE);
        Intake = hardwareMap.get(Servo.class, "claw");
        Arm = hardwareMap.get(Servo.class, "shoulder");
        Wrist = hardwareMap.get(Servo.class, "wrist");
        HangArm = hardwareMap.get(Servo.class, "hangArm");
    }
    @Override
    public void loop(){
        telemetry.addLine("Controls:");
        LX = -gamepad1.left_stick_x * sensitivity;
        LY = -gamepad1.left_stick_y * sensitivity;
        RX = -gamepad1.right_stick_x * sensitivity;
        if (sensitivity < 1 && gamepad1.dpad_up){
            sensitivity += 0.01;
        }
        else if (sensitivity > 0 && gamepad1.dpad_down){
            sensitivity -= 0.01;
        }
        telemetry.addLine("Hold dpad up and dpad down to raise and lower sensitivity; Sensitivity: " + String.format("%.2g%n", sensitivity));
        BL.setPower(LY + LX - RX);
        FL.setPower(LY - LX - RX);
        FR.setPower(LY + LX + RX);
        BR.setPower(LY - LX + RX);
        telemetry.addLine("Hold left trigger and right trigger to raise and lower the slides; Slide position: " + S1.getCurrentPosition());
        S1.setPower((gamepad2.left_trigger - gamepad2.right_trigger) * -0.6);
        S2.setPower((gamepad2.left_trigger - gamepad2.right_trigger) * 0.6);
        if (gamepad2.dpad_up){
            Arm.setPosition(Arm.getPosition() - 0.005);
        }
        else if (gamepad2.dpad_down){
            Arm.setPosition(Arm.getPosition() + 0.005);
        }
        else {
            Arm.setPosition(0.5);
        }
        if (gamepad2.left_bumper){
            Intake.setPosition(Intake.getPosition() - 0.007);
        }
        else if (gamepad2.right_bumper){
            Intake.setPosition(Intake.getPosition() + 0.007);
        }
        if (gamepad2.dpad_left){
            Wrist.setPosition(Wrist.getPosition() - 0.002);
        }
        else if (gamepad2.dpad_right){
            Wrist.setPosition(Wrist.getPosition() + 0.002);
        }
        else{
            Wrist.setPosition(Wrist.getPosition());
        }
        telemetry.addLine("Intake: " + Intake.getPosition());
        telemetry.addLine("Slides: " + S1.getCurrentPosition());
        telemetry.addLine("Wrist: " + Wrist.getPosition());
        if (gamepad2.y){
            HangArm.setPosition(0.9);
        }
        else if (gamepad2.a){
            HangArm.setPosition(0.1);
        }
        else{
            HangArm.setPosition(0.5);
        }
    }
}