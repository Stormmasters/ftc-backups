package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "mecanumencoder (Blocks to Java)")
public class mecanumencoder extends LinearOpMode {

  private DcMotor mtrFr;
  private DcMotor mtrBr;
  private DcMotor mtrBl;
  private DcMotor mtrFl;

  /**
   * This function is executed when this OpMode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    mtrFr = hardwareMap.get(DcMotor.class, "mtrFr");
    mtrBr = hardwareMap.get(DcMotor.class, "mtrBr");
    mtrBl = hardwareMap.get(DcMotor.class, "mtrBl");
    mtrFl = hardwareMap.get(DcMotor.class, "mtrFl");

    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        mtrPosTelem();
        telemetry.update();
      }
    }
  }

  /**
   * Describe this function...
   */
  private void mtrPosTelem() {
    telemetry.addData("mtrFr", mtrFr.getCurrentPosition());
    telemetry.addData("mtrBr", mtrBr.getCurrentPosition());
    telemetry.addData("mtrBl", mtrBl.getCurrentPosition());
    telemetry.addData("mtrFl", mtrFl.getCurrentPosition());
  }
}
