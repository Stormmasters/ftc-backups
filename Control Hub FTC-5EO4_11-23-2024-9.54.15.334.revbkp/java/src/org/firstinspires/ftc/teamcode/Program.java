package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Program (Blocks to Java)")
public class Program extends LinearOpMode {

  private DcMotor LiftMotor;
  private DcMotor LeftMotor;
  private DcMotor RightMotor;
  private Servo Smacker;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    LiftMotor = hardwareMap.get(DcMotor.class, "LiftMotor");
    LeftMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
    RightMotor = hardwareMap.get(DcMotor.class, "RightMotor");
    Smacker = hardwareMap.get(Servo.class, "Smacker");

    LiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    waitForStart();
    while (opModeIsActive()) {
      LeftMotor.setPower(-gamepad1.right_stick_y);
      RightMotor.setPower(-gamepad1.left_stick_y);
      LiftMotor.setPower(-gamepad1.right_trigger);
      Smacker.setPosition(gamepad1.left_trigger);
    }
  }
}
