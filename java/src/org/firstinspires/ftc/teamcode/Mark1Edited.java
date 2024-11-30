package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

@TeleOp
public class Mark1Edited extends OpMode{
    private DcMotor backLeft;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private Servo Arm;
    double arm = 0;
    @Override
    public void init(){
    }
    @Override
    public void loop(){
        if (gamepad1.dpad_up){
            Arm.setPosition(arm + 0.0002);
            arm += 0.0002;
        }
        if (gamepad1.dpad_down){
            Arm.setPosition(arm - 0.0002);
            arm += 0.0002;
        }
    }
}
