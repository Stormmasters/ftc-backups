package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class AlexServo extends OpMode {
    private DcMotor Shoulder;
    private Servo Elbow;
    private Servo Wrist;
    private Servo Intake;
    double w = 0;
    double e = w; //what does this do?

    @Override
    public void init() {
        Shoulder = hardwareMap.get(DcMotor.class, "Shoulder");
        Shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Elbow = hardwareMap.get(Servo.class, "Elbow");
        Wrist = hardwareMap.get(Servo.class, "Wrist");
        Intake = hardwareMap.get(Servo.class, "Intake");
    }



    @Override
    public void loop() {
        //Control Shoulder

        Shoulder.setPower(gamepad2.left_stick_y);


        if(gamepad2.a) {
            Wrist.setPosition(1);
        } else {
            Wrist.setPosition(0);
        }

        if(gamepad2.x) {
            Elbow.setPosition(0);
        } else {
            Elbow.setPosition(0.7);
        }

        if(-gamepad2.right_stick_y != 0) {
            Intake.setPosition(-gamepad2.right_stick_y);
        } else {
            Intake.setPosition(0.5);
        }

        telemetry.addData("Shoulder Power", Shoulder.getPower());
        telemetry.addData("Elbow Position", Elbow.getPosition());
        telemetry.addData("Wrist Position", Wrist.getPosition());
        telemetry.addData("Intake Position", Intake.getPosition());
        telemetry.addLine("HI GUYS from Leo :)");
        telemetry.addLine("grgrgrfrfdrdrssrarar");
        telemetry.addLine("I don't think that I know wat I'm doing! AHHHHH");
        telemetry.addLine("bro just delete this Evan!");
        telemetry.update();
    }
}
