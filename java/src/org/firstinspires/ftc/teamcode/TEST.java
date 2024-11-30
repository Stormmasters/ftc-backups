package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "trying2 (Blocks to Java)", group = "")
public class TEST extends LinearOpMode {

  private DcMotor rightmotor;
  private DcMotor leftmotor;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    double speed;

    rightmotor = hardwareMap.dcMotor.get("right motor");
    leftmotor = hardwareMap.dcMotor.get("left motor");

    // Put initialization blocks here.
    speed = 1;
    rightmotor.setDirection(DcMotorSimple.Direction.REVERSE);
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        telemetry.update();
        if (gamepad1.left_bumper) {
          speed = 0.5;
        } else {
          speed = 1;
        }
        leftmotor.setPower(gamepad1.right_stick_y * speed);
        rightmotor.setPower(gamepad1.left_stick_y);
      }
    }
  }
}