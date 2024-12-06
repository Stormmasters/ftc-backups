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
public class FraserDriveV3_1_Solo extends OpMode{
    double LX, LY, RX, sensitivity = 0.5;
    DcMotor BL, FL, FR, BR, S1, S2;
    Servo Intake, Arm, Wrist;
    @Override
    public void init(){
        BL = hardwareMap.dcMotor.get("back_left_motor");
        FL = hardwareMap.dcMotor.get("front_left_motor");
        FR = hardwareMap.dcMotor.get("front_right_motor");
        BR = hardwareMap.dcMotor.get("back_right_motor");
        S1 = hardwareMap.dcMotor.get("left_slide");
        S2 = hardwareMap.dcMotor.get("right_slide");
        S1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        S2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        S1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        S2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        S1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // This matches the DcMotors to the ones configured in the Control Hub
        S2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setDirection(REVERSE);
        Intake = hardwareMap.get(Servo.class, "claw");
        Arm = hardwareMap.get(Servo.class, "shoulder");
        Wrist = hardwareMap.get(Servo.class, "wrist");
    }
    @Override
    public void loop(){
        telemetry.addLine("Controls:");
        LX = -gamepad1.left_stick_x * sensitivity;
        LY = -gamepad1.left_stick_y * sensitivity;
        RX = -gamepad1.right_stick_x * sensitivity;
        if (sensitivity < 1 && gamepad1.dpad_left){
            sensitivity += 0.01;
        }
        else if (sensitivity > 0 && gamepad1.dpad_right){
            sensitivity -= 0.01;
        }
        telemetry.addLine("Hold dpad up and dpad down to raise and lower sensitivity; Sensitivity: " + String.format("%.2g%n", sensitivity));
        BL.setPower(LY + LX - RX);
        FL.setPower(LY - LX - RX);
        FR.setPower(LY + LX + RX);
        BR.setPower(LY - LX + RX);
        telemetry.addLine("Hold left trigger and right trigger to raise and lower the slides; Slide position: " + S1.getCurrentPosition());
        S1.setPower((gamepad1.left_trigger - gamepad1.right_trigger) * -0.7);
        S2.setPower((gamepad1.left_trigger - gamepad1.right_trigger) * 0.7);
        if (gamepad1.dpad_up){
            Arm.setPosition(Arm.getPosition() - 0.005);
        }
        else if (gamepad1.dpad_down){
            Arm.setPosition(Arm.getPosition() + 0.005);
        }
        else {
            Arm.setPosition(0.5);
        }
        if (gamepad1.left_bumper){
            Intake.setPosition(Intake.getPosition() - 0.005);
        }
        else if (gamepad1.right_bumper){
            Intake.setPosition(Intake.getPosition() + 0.005);
        }
        if (gamepad1.b){
            Wrist.setPosition(Wrist.getPosition() - 0.0005);
        }
        else if (gamepad1.a){
            Wrist.setPosition(Wrist.getPosition() + 0.0005);
        }
        telemetry.addLine("Intake: " + Intake.getPosition());
        telemetry.addLine("Slides: " + S1.getPower());
        telemetry.addLine("Arm: " + Arm.getPosition());
        telemetry.addLine("Wrist: " + Wrist.getPosition());
    }
}