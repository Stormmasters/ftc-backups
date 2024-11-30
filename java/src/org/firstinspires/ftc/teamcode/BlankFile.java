package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class BlankFile extends LinearOpMode{
    private DcMotor BackLeft;
    private DcMotor FrontLeft;
    private DcMotor BackRight;
    private DcMotor FrontRight;

    @Override
    public void runOpMode() {
        BackLeft = hardwareMap.get(DcMotor.class,"back_left_motor");
        FrontLeft = hardwareMap.get(DcMotor.class, "front_left_motor");
        BackRight = hardwareMap.get(DcMotor.class, "back_right_motor");
        FrontRight = hardwareMap.get(DcMotor.class, "front_right_motor");
        waitForStart();
        if (opModeIsActive()){
            while (opModeIsActive()) {
                FrontRight.setPower(-gamepad1.left_stick_y/1.5);
                BackRight.setPower(-gamepad1.left_stick_y/1.5);
                FrontLeft.setPower(-gamepad1.left_stick_y/1.5);
                BackLeft.setPower(-gamepad1.left_stick_y/1.5);
            }
        }
    }
}