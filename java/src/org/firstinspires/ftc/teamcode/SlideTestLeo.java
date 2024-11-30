package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class SlideTestLeo extends LinearOpMode {
    private DcMotor left_slide;
    private DcMotor right_slide;
    @Override
    public void runOpMode() {
        left_slide = hardwareMap.get(DcMotor.class, "left_slide");
        right_slide = hardwareMap.get(DcMotor.class, "right_slide");
        
        right_slide.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        
        left_slide.setPower(0.5);
        right_slide.setPower(0.5);
        
        sleep(1000);
    }
}