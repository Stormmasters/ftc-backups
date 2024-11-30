package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp
public class FraserDrive18514RCV1 extends OpMode{
    DcMotor BL, FL, FR, BR;
    double LX, LY, RX, LXa, LYa, Angle, AngleA, r, sensitivity = 0.5;
    boolean fieldOriented = true, invControls = false;
    @Override
    public void init(){
        IMU imu = hardwareMap.get(IMU.class, "imu");
        Orientation orientation = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        // Configures IMU
        imu.resetYaw();
        // "Calibrates" IMU by making the current heading 0
        BL = hardwareMap.dcMotor.get("BL");
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BR = hardwareMap.dcMotor.get("BR");
        // This matches the DcMotors to the ones configured in the Control Hub
        BL.setDirection(REVERSE);
        FL.setDirection(REVERSE);
        FR.setDirection(FORWARD);
        BR.setDirection(FORWARD);
        // Sets motor directions
    }
    @Override
    public void loop(){
        telemetry.addLine("Controls: ");
        IMU imu = hardwareMap.get(IMU.class, "imu");
        Orientation orientation = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        // Gets the robot's current orientation, reconfigures
        if (gamepad1.left_bumper){
            if (fieldOriented){
                fieldOriented = false;
            }
            else{
                fieldOriented = true;
            }
        }
        if (fieldOriented){
            telemetry.addLine("Press the left bumper to disable field oriented control");
        }
        else{
            telemetry.addLine("Press the left bumper to enable field oriented control");
        }
        // This code allows the left bumper to switch whether fieldOriented is true
        if (gamepad1.right_bumper){
            if (invControls){
                invControls = false;
            }
            else{
                invControls = true;
            }
        }
        if (invControls){
            telemetry.addLine("Press the right bumper to disable inverted controls");
        }
        else{
            telemetry.addLine("Press the right bumper to enable inverted controls");
        }
        // This code allows the right bumper to switch whether invControls is true
        if (sensitivity < 0.95 && gamepad1.right_trigger > 0){
            sensitivity += 0.0004 * gamepad1.right_trigger;
        }
        else if (sensitivity > 0.05){
            sensitivity -= 0.0004 * gamepad1.left_trigger;
        }
        telemetry.addLine("Press the left trigger to lower the sensitivity and the right trigger to raise it");
        // Right trigger makes sensitivity go up and vice versa
        if (gamepad1.b){
            imu.resetYaw();
        }
        telemetry.addLine("Press b to reset the IMU");
        // Resets/recalibrates IMU is b is pressed
        LX = sensitivity * -gamepad1.left_stick_x;
        LY = sensitivity * -gamepad1.left_stick_y;
        RX = sensitivity * -gamepad1.right_stick_x;
        if (invControls){
            LX *= -1;
            LY *= -1;
            RX *= -1;
        }
        // I like to turn controller inputs into smaller variables because "gamepad1.ect." is such a mouthful
        // It also multiplies them by "sensitivity", which allows the driver to change the sensitivity
        if (fieldOriented){
            r = Math.hypot(LY, LX);
            // Getting the "r", or distance of the controller input from the origin
            Angle = Math.atan2(LY, LX);
            // "atan2" converts a point into an angle from the origin
            AngleA = -orientation.firstAngle - Angle;
            // Difference of -yaw and Angle with the 0.5 * pi added because it starts out offset by 90 degrees
            LYa = -Math.sin(AngleA) * r;
            LXa = Math.cos(AngleA) * r;
            // The adjusted inputs are multiplied by r in order to get the magnitude of a movement, allowing for speed control
        }
        else{
            LYa = LY;
            LXa = LX;
            // If fieldOriented is turned off, the adjusted inputs are just set to the regular ones
        }
        BL.setPower(LYa + LXa - RX);
        FL.setPower(LYa - LXa - RX);
        FR.setPower(LYa + LXa + RX);
        BR.setPower(LYa - LXa + RX);
        // Sets motor powers
        telemetry.addData("Field oriented: ", fieldOriented);
        telemetry.addData("Controls inverted: ", invControls);
        telemetry.addData("Sensitivity: ", sensitivity);
    }
}