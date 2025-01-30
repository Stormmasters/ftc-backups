package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutoSpecimenPID extends LinearOpMode {

    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor rightSlide;
    private DcMotor leftSlide;
    private CRServo wrist;
    private CRServo shoulder;
    private Servo claw;

    // PID coefficients
    private static final double KP = 0.1; // Proportional
    private static final double KI = 0.0; // Integral
    private static final double KD = 0.01; // Derivative

    @Override
    public void runOpMode() {

        // Hardware mapping
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftSlide = hardwareMap.get(DcMotor.class, "par0");
        rightSlide = hardwareMap.get(DcMotor.class, "par1");
        shoulder = hardwareMap.get(CRServo.class, "shoulder");
        wrist = hardwareMap.get(CRServo.class, "wrist");
        claw = hardwareMap.get(Servo.class, "claw");

        // Set motor directions and behaviors
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reset encoders
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        // Example usage of PID for movement
        moveToPosition(1000, 1000, 1000, 1000, 0.5); // Move forward with target encoder values

        leftSlide.setPower(0.45);
        rightSlide.setPower(0.45);
        shoulder.setPower(-0.35);
        wrist.setPower(0.05);
        claw.setPosition(0.16);
        sleep(2800);

        claw.setPosition(0.15);
        leftSlide.setPower(-0.4);
        rightSlide.setPower(-0.4);
        sleep(1000);

        moveToPosition(-500, -500, -500, -500, 0.35); // Move backward

        claw.setPosition(0.4);
        sleep(1000);

        moveToPosition(-285, 285, 285, -285, 0.25); // Turn left

        leftSlide.setPower(-0.1);
        rightSlide.setPower(-0.1);
        shoulder.setPower(0.25);
        wrist.setPower(0.3);
        sleep(5000);

        moveToPosition(250, 250, 250, 250, 0.25); // Move forward

        claw.setPosition(0.15);
        sleep(1000);

        shoulder.setPower(-0.5);
        wrist.setPower(-0.1);
        sleep(1500);

        claw.setPosition(0.4);
        sleep(5000);
    }

    private void moveToPosition(int leftFrontTarget, int rightFrontTarget, int leftBackTarget, int rightBackTarget, double maxPower) {
        int startLF = leftFront.getCurrentPosition();
        int startRF = rightFront.getCurrentPosition();
        int startLB = leftBack.getCurrentPosition();
        int startRB = rightBack.getCurrentPosition();

        double integralLF = 0, integralRF = 0, integralLB = 0, integralRB = 0;
        double prevErrorLF = 0, prevErrorRF = 0, prevErrorLB = 0, prevErrorRB = 0;

        while (opModeIsActive()) {
            int currentLF = leftFront.getCurrentPosition();
            int currentRF = rightFront.getCurrentPosition();
            int currentLB = leftBack.getCurrentPosition();
            int currentRB = rightBack.getCurrentPosition();

            int errorLF = leftFrontTarget - (currentLF - startLF);
            int errorRF = rightFrontTarget - (currentRF - startRF);
            int errorLB = leftBackTarget - (currentLB - startLB);
            int errorRB = rightBackTarget - (currentRB - startRB);

            integralLF += errorLF;
            integralRF += errorRF;
            integralLB += errorLB;
            integralRB += errorRB;

            double derivativeLF = errorLF - prevErrorLF;
            double derivativeRF = errorRF - prevErrorRF;
            double derivativeLB = errorLB - prevErrorLB;
            double derivativeRB = errorRB - prevErrorRB;

            double powerLF = KP * errorLF + KI * integralLF + KD * derivativeLF;
            double powerRF = KP * errorRF + KI * integralRF + KD * derivativeRF;
            double powerLB = KP * errorLB + KI * integralLB + KD * derivativeLB;
            double powerRB = KP * errorRB + KI * integralRB + KD * derivativeRB;

            powerLF = Math.max(-maxPower, Math.min(maxPower, powerLF));
            powerRF = Math.max(-maxPower, Math.min(maxPower, powerRF));
            powerLB = Math.max(-maxPower, Math.min(maxPower, powerLB));
            powerRB = Math.max(-maxPower, Math.min(maxPower, powerRB));

            leftFront.setPower(powerLF);
            rightFront.setPower(powerRF);
            leftBack.setPower(powerLB);
            rightBack.setPower(powerRB);

            if (Math.abs(errorLF) < 10 && Math.abs(errorRF) < 10 && Math.abs(errorLB) < 10 && Math.abs(errorRB) < 10) {
                break; // Target position reached
            }

            prevErrorLF = errorLF;
            prevErrorRF = errorRF;
            prevErrorLB = errorLB;
            prevErrorRB = errorRB;

            sleep(10); // Allow some time for motors to adjust
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }
}
