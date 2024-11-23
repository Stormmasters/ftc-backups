package org.firstinspires.ftc.teamcode;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class FraserTestAuton extends LinearOpMode{
    DcMotor BL, FL, FR, BR;
    public void allM_Mode(DcMotor.RunMode command){BL.setMode(command); FL.setMode(command); FR.setMode(command); BR.setMode(command);}
    public void allM_Power(double power){BL.setPower(power); FL.setPower(power); FR.setPower(power); BR.setPower(power);}
    private ElapsedTime     runtime = new ElapsedTime();
    @Override
    public void runOpMode(){
        System.out.println(opModeIsActive());
        BL = hardwareMap.dcMotor.get("back_left_motor");
        FL = hardwareMap.dcMotor.get("front_left_motor");
        FR = hardwareMap.dcMotor.get("front_right_motor");
        BR = hardwareMap.dcMotor.get("back_right_motor");
        BL.setDirection(REVERSE);
        FL.setDirection(REVERSE);
        FR.setDirection(FORWARD);
        BR.setDirection(FORWARD);
        waitForStart();
        allM_Mode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        allM_Mode(DcMotor.RunMode.RUN_USING_ENCODER);
        allM_Power(0.5);
        FL.setPower(-0.5);
        BR.setPower(-0.5);
        while (Math.abs(BL.getCurrentPosition()) < 2200 && !isStopRequested()){}
        runtime.reset();
        allM_Power(0);
        while (runtime.seconds() < 1){}
        allM_Mode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        allM_Mode(DcMotor.RunMode.RUN_USING_ENCODER);
        allM_Power(1);
        while (Math.abs(BL.getCurrentPosition()) < 400 && !isStopRequested()){}
        allM_Power(0);
    }
}