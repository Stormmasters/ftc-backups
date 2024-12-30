package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Gyroscope;
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

public class StringRayController extends OpMode{
    private Blinker control_Hub;
    private Blinker expansion_Hub_2;
    private Servo claw;
    private Servo hangArm;
    private Gyroscope imu;
    private DcMotor leftBack;
    private DcMotor leftFront;
    private DcMotor par0;
    private DcMotor par1;
    private DcMotor perp;
    private DcMotor rightBack;
    private DcMotor rightFront;
    private Servo shoulder;
    private Servo wrist;
    
     @Override
    public void init(){
        hangArm = hardwareMap.get(Servo.class, "hangArm");
    }
    @Override 
    public void loop() {
        if (gamepad2.dpad_up){
            hangArm.setPosition(hangArm.getPosition() + 0.5);
             telemetry.addLine("up");
        } else {
            hangArm.setPosition(0.5);
             telemetry.addLine("upstop");
        }
        if (gamepad2.dpad_down){
             hangArm.setPosition(hangArm.getPosition() - 0.5);
              telemetry.addLine("down");
        } else {
            hangArm.setPosition(0.5);
             telemetry.addLine("downstop");
        }
         telemetry.update();
    }
}