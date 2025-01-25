package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.util.Range;

@TeleOp
public class AteleOp extends OpMode {
    private double LX, LY, RX, sensitivity = 0.5, wristPower = 0;
    private DcMotor BL, FL, FR, BR, S1, S2;
    private Servo Intake, HangArm;
    private CRServo Wrist, Arm;

    private PIDController blPID, flPID, frPID, brPID;

    @Override
    public void init() {
        // Initialize hardware
        BL = hardwareMap.dcMotor.get("leftBack");
        FL = hardwareMap.dcMotor.get("leftFront");
        FR = hardwareMap.dcMotor.get("rightFront");
        BR = hardwareMap.dcMotor.get("rightBack");
        S1 = hardwareMap.dcMotor.get("par1");
        S2 = hardwareMap.dcMotor.get("par0");

        Intake = hardwareMap.get(Servo.class, "claw");
        Arm = hardwareMap.get(CRServo.class, "shoulder");
        Wrist = hardwareMap.get(CRServo.class, "wrist");
        HangArm = hardwareMap.get(Servo.class, "hangArm");

        // Reset and configure motors
        configureMotors();

        // Initialize PID controllers
        blPID = new PIDController(0.1, 0.01, 0.05);
        flPID = new PIDController(0.1, 0.01, 0.05);
        frPID = new PIDController(0.1, 0.01, 0.05);
        brPID = new PIDController(0.1, 0.01, 0.05);

        // Initialize servo positions
        HangArm.setPosition(0.5);
    }

    private void configureMotors() {
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        S1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        S2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FL.setDirection(REVERSE);

        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        // Drive controls
        driveControl();

        // Slide controls
        slideControl();

        // Arm and wrist controls
        armControl();
        wristControl();

        // Intake controls
        intakeControl();

        // Hang arm controls
        hangArmControl();

        // Update telemetry
        updateTelemetry();
    }

    private void driveControl() {
        LX = -gamepad1.left_stick_x * sensitivity;
        LY = -gamepad1.left_stick_y * sensitivity;
        RX = -gamepad1.right_stick_x * sensitivity;

        double blTarget = LY + LX - RX;
        double flTarget = LY - LX - RX;
        double frTarget = LY + LX + RX;
        double brTarget = LY - LX + RX;

        double blPower = blPID.calculate(BL.getCurrentPosition(), blTarget);
        double flPower = flPID.calculate(FL.getCurrentPosition(), flTarget);
        double frPower = frPID.calculate(FR.getCurrentPosition(), frTarget);
        double brPower = brPID.calculate(BR.getCurrentPosition(), brTarget);

        BL.setPower(blPower);
        FL.setPower(flPower);
        FR.setPower(frPower);
        BR.setPower(brPower);

        // Adjust sensitivity dynamically
        if (gamepad1.dpad_up) sensitivity = Range.clip(sensitivity + 0.01, 0, 1);
        if (gamepad1.dpad_down) sensitivity = Range.clip(sensitivity - 0.01, 0, 1);
    }

    private void slideControl() {
        double slidePower = (gamepad2.left_trigger - gamepad2.right_trigger) * 0.8;
        S1.setPower(slidePower);
        S2.setPower(slidePower);
    }

    private void armControl() {
        if (gamepad2.dpad_up) {
            Arm.setPower(1.0);
        } else if (gamepad2.dpad_down) {
            Arm.setPower(-1.0);
        } else {
            Arm.setPower(0);
        }
    }

    private void wristControl() {
        if (gamepad2.dpad_left) {
            Wrist.setPower(-0.5);
        } else if (gamepad2.dpad_right) {
            Wrist.setPower(0.5);
        } else {
            Wrist.setPower(0);
        }
    }

    private void intakeControl() {
        if (gamepad2.left_bumper) {
            Intake.setPosition(Range.clip(Intake.getPosition() - 0.005, 0.15, 0.8));
        } else if (gamepad2.right_bumper) {
            Intake.setPosition(Range.clip(Intake.getPosition() + 0.005, 0.15, 0.8));
        }
    }

    private void hangArmControl() {
        if (gamepad2.y) {
            HangArm.setPosition(0.9);
        } else if (gamepad2.a) {
            HangArm.setPosition(0.1);
        } else {
            HangArm.setPosition(0.5);
        }
    }

    private void updateTelemetry() {
        telemetry.addData("Sensitivity", String.format("%.2f", sensitivity));
        telemetry.addData("Wrist Power", wristPower);
        telemetry.addData("Intake Position", Intake.getPosition());
        telemetry.addData("BL Encoder", BL.getCurrentPosition());
        telemetry.addData("FL Encoder", FL.getCurrentPosition());
        telemetry.addData("FR Encoder", FR.getCurrentPosition());
        telemetry.addData("BR Encoder", BR.getCurrentPosition());
        telemetry.update();
    }

    private static class PIDController {
        private final double kP, kI, kD;
        private double prevError = 0, integral = 0;

        public PIDController(double kP, double kI, double kD) {
            this.kP = kP;
            this.kI = kI;
            this.kD = kD;
        }

        public double calculate(double currentPosition, double targetPosition) {
            double error = targetPosition - currentPosition;
            integral += error;
            double derivative = error - prevError;
            prevError = error;

            return (kP * error) + (kI * integral) + (kD * derivative);
        }
    }
}
