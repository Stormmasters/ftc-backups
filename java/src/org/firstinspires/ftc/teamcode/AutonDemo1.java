package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


@Autonomous
public class AutonDemo1 extends LinearOpMode{

    @Override
    public void runOpMode() {
        DcMotorSimple front_left_motor = hardwareMap.get(DcMotorSimple.class,"front_left_motor");
        DcMotorSimple front_right_motor = hardwareMap.get(DcMotorSimple.class,"front_right_motor");
        DcMotorSimple back_left_motor = hardwareMap.get(DcMotorSimple.class,"back_left_motor");
        DcMotorSimple back_right_motor = hardwareMap.get(DcMotorSimple.class,"back_right_motor");

        front_left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        //back_left_motor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        front_right_motor.setPower(0.5);
        front_left_motor.setPower(0.5);
        back_left_motor.setPower(0.5);
        back_right_motor.setPower(0.5);

        sleep(1000);

        requestOpModeStop();

    }

}
