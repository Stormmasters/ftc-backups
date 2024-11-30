package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MecanumDriveReal (Blocks to Java)")
public class MecanumDriveReal extends LinearOpMode {

  private DcMotor BackLeft;
  private DcMotor FrontLeft;
  private DcMotor FrontRight;
  private DcMotor BackRight;
  private Servo claw;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    BackLeft = hardwareMap.get(DcMotor.class, "back_left_motor");
    FrontLeft = hardwareMap.get(DcMotor.class, "front_left_motor");
    FrontRight = hardwareMap.get(DcMotor.class, "front_right_motor");
    BackRight = hardwareMap.get(DcMotor.class, "back_right_motor");
    claw = hardwareMap.get(Servo.class, "claw");

    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // drive controls
        FrontLeft.setPower(gamepad1.left_stick_y);
        BackLeft.setPower(gamepad1.left_stick_y);
        FrontRight.setPower(gamepad1.right_stick_y);
        BackRight.setPower(gamepad1.right_stick_y);
        if (gamepad1.triangle) {
          BackLeft.setPower(0.75);
          BackRight.setPower(-0.75);
          FrontLeft.setPower(0.75);
          FrontRight.setPower(-0.75);
        }
        if (gamepad1.square) {
          BackLeft.setPower(-0.75);
          BackRight.setPower(0.75);
          FrontLeft.setPower(-0.75);
          FrontRight.setPower(0.75);
        }
        if (gamepad1.left_bumper) {
          FrontRight.setPower(-0.5);
          BackRight.setPower(0.5);
          FrontLeft.setPower(0.5);
          BackLeft.setPower(-0.5);
        }
        if (gamepad1.right_bumper) {
          FrontRight.setPower(0.5);
          BackRight.setPower(-0.5);
          FrontLeft.setPower(-0.5);
          BackLeft.setPower(0.5);
        }
        // Elevator
        BackLeft.setPower(gamepad2.left_stick_y);
        BackLeft.setPower(gamepad2.left_stick_y);
        if (gamepad2.square) {
          claw.setPosition(-0.5);
        }
        if (gamepad2.triangle) {
          claw.setPosition(1);
        }
        telemetry.update();
      }
    }
  }
}
