package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class IntakeFraserEdition extends OpMode {
    private DcMotor Shoulder;
    private Servo Elbow;
    private Servo Wrist;
    private Servo Intake;
    boolean xDown = false;
    @Override
    public void init() {
        Shoulder = hardwareMap.get(DcMotor.class, "Shoulder");
        Elbow = hardwareMap.get(Servo.class, "Elbow");
        Wrist = hardwareMap.get(Servo.class, "Wrist");
        Intake = hardwareMap.get(Servo.class, "Intake");
        Wrist.setPosition(0.5);
        Elbow.setPosition(0);
    }

    @Override
    public void loop() {
        //Sets power for the shoulder
        Shoulder.setPower(gamepad2.left_stick_y/1.5);

        //Makes the elbow turn 180 degrees
        Wrist.setPosition(0.5);
        
        if(gamepad2.x) {
            if (!xDown) {
                xDown = true;
            }
        }
        else if (xDown) {
            if (Elbow.getPosition() == 0) {
                Elbow.setPosition(1);
            } 
            else if (Elbow.getPosition() == 1) {
                Elbow.setPosition(0);
            }
            xDown = false;
        }

        Intake.setPosition(-gamepad2.right_stick_y);

        telemetry.addData("Shoulder Power", Shoulder.getPower());
        telemetry.addData("Elbow Position", Elbow.getPosition());
        telemetry.addData("Wrist Position", Wrist.getPosition());
        telemetry.addData("Intake Position", Intake.getPosition());
        telemetry.addLine("HI GUYS from Leo");
        telemetry.update();
    }
}
