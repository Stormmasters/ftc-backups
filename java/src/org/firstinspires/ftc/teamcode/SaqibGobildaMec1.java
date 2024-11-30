package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "vishakhaTestMecanum (Blocks to Java)")
public class SaqibGobildaMec1 extends LinearOpMode {

  private DcMotor FrontRight;
  private DcMotor BackRight;
  private DcMotor BackLeft;
  private DcMotor FrontLeft;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
    BackRight = hardwareMap.get(DcMotor.class, "BackRight");
    BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
    FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");

    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        // ForwardBackward
        BackLeft.setPower(gamepad2.left_stick_y);
        FrontLeft.setPower(gamepad2.left_stick_y);
        BackRight.setPower(gamepad2.right_stick_y);
        FrontRight.setPower(gamepad2.right_stick_y);
        // TurnRight
        if (gamepad2.right_bumper) {
          FrontRight.setPower(1);
          BackRight.setPower(1);
          FrontLeft.setPower(-1);
          BackLeft.setPower(-1);
        }
        // TurnLeft
        if (gamepad2.left_bumper) {
          FrontLeft.setPower(1);
          BackLeft.setPower(1);
          FrontRight.setPower(-1);
          BackRight.setPower(-1);
        }
        // StrafeRight
        if (gamepad2.dpad_right) {
          FrontLeft.setPower(-1);
          BackRight.setPower(1);
          BackLeft.setPower(1);
          FrontRight.setPower(-1);
        }
        // StrafeLeft
        if (gamepad2.dpad_left) {
          FrontLeft.setPower(1);
          BackRight.setPower(-1);
          BackLeft.setPower(-1);
          FrontRight.setPower(1);
        }
        // TurnRightInPlace
        if (gamepad2.dpad_up) {
          FrontLeft.setPower(1);
          BackLeft.setPower(1);
        }
        // TurnLeftInPlace
        if (gamepad2.dpad_down) {
          FrontRight.setPower(1);
          BackRight.setPower(1);
        }
        if (gamepad2.right_stick_button) {
          while (!gamepad2.left_stick_button) {
            BackLeft.setPower(1);
            FrontLeft.setPower(1);
            BackRight.setPower(1);
            FrontRight.setPower(1);
          }
        }
        // CruiseControlForward
      }
      telemetry.update();
    }
  }
}
