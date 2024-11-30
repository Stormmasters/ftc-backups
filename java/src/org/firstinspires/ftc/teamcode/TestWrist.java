package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class TestWrist extends LinearOpMode {
    
    private Servo wrist;
    
    @Override
    public void runOpMode(){
    
        wrist = hardwareMap.get(Servo.class,("wrist"));
        
        waitForStart();
        
        wrist.setPosition(0);
        
        sleep(5000);
        
        wrist.setPosition(0.5);
        
        sleep(5000);
        
        wrist.setPosition(1);
        
        sleep(000);
        
    }
}
