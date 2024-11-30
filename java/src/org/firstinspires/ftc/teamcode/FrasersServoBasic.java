package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class FrasersServoBasic extends OpMode{
    Servo arm;
    double servoPosition = 0;
    public void init(){
        arm = hardwareMap.servo.get("arm");
    }
    public void loop(){
        if(gamepad1.dpad_up){
            System.out.print("moving ...");
            arm.setPosition(servoPosition + 0.0005);
            servoPosition += 0.002;
        }
        if(gamepad1.dpad_down){
            arm.setPosition(servoPosition - 0.0005);
            servoPosition -= 0.002;
        }
    }
}
