package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class Meet1AutonLong extends LinearOpMode {
    private DcMotor back_left_motor;
    private DcMotor front_left_motor;
    private DcMotor back_right_motor;
    private DcMotor front_right_motor;
    @Override
    public void runOpMode() {
        back_left_motor = hardwareMap.get(DcMotor.class, "back_left_motor");
        front_left_motor = hardwareMap.get(DcMotor.class, "front_left_motor");
        back_right_motor = hardwareMap.get(DcMotor.class, "back_right_motor");
        front_right_motor = hardwareMap.get(DcMotor.class, "front_right_motor");

        front_right_motor.setDirection(DcMotor.Direction.REVERSE);
        back_right_motor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        back_left_motor.setPower(0.5);
        back_right_motor.setPower(0.5);
        front_left_motor.setPower(0.5);
        front_right_motor.setPower(0.5);

        sleep(1000);

        back_left_motor.setPower(0.75);
        back_right_motor.setPower(-0.75);
        front_left_motor.setPower(0.75);
        front_right_motor.setPower(-0.75);

        sleep(2250);

        back_left_motor.setPower(-0.7);
        back_right_motor.setPower(0.7);
        front_left_motor.setPower(0.7);
        front_right_motor.setPower(-0.7);

        sleep(3700);


        back_left_motor.setPower(0.3);
        back_right_motor.setPower(0.3);
        front_left_motor.setPower(0.3);
        front_right_motor.setPower(0.3);

        sleep(300);
    }
}
