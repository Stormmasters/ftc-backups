package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "mecanumquestionmark (Blocks to Java)")
public class Mecanumtest1 extends LinearOpMode {

  private DcMotor FL;
  private DcMotor BR;
  private DcMotor FR;
  private DcMotor BL;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    FR = hardwareMap.get(DcMotor.class, "FR");
    BR = hardwareMap.get(DcMotor.class, "BR");
    FL = hardwareMap.get(DcMotor.class, "FL");
    BL = hardwareMap.get(DcMotor.class, "BL");

    FR.setDirection(DcMotorSimple.Direction.REVERSE);
    BR.setDirection(DcMotorSimple.Direction.REVERSE);
    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        FR.setPower(gamepad1.right_stick_y);
        FL.setPower(gamepad1.right_stick_y);
        BR.setPower(gamepad1.left_stick_y);
        BL.setPower(gamepad1.left_stick_y);
      }
      if (gamepad1.dpad_left) {
        FR.setPower(-1);
        BR.setPower(1);
        FL.setPower(1);
        BL.setPower(-1);
      }
      if (gamepad1.dpad_right) {
        FR.setPower(1);
        BR.setPower(-1);
        FL.setPower(-1);
        BL.setPower(1);
      }
    }
    telemetry.update();
  }
}