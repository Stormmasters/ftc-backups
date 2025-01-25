/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "CameraATest", group = "Autonomous")
public class CameraATest extends LinearOpMode {

    // Declare motors and servos
    private DcMotor leftBack, rightBack, leftFront, rightFront;
    private CRServo wrist;
    private Servo claw;

    // TensorFlow Object Detection fields
    private static final String TFOD_MODEL_ASSET = "SampleModel.tflite";
    private static final String[] LABELS = {"Red", "Blue", "Yellow"};
    private TFObjectDetector tfod;

    // Encoder and PID constants
    static final double TICKS_PER_REV = 537.7;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER_INCHES);
    static final double KP = 0.01;
    static final double KD = 0.005;

    @Override
    public void runOpMode() {
        // Initialize hardware and TensorFlow
        initializeHardware();
        initializeTensorFlow();

        telemetry.addData("Status", "Initialized. Waiting for start...");
        telemetry.update();

        waitForStart();

        String detectedLabel = detectSample();

        if (detectedLabel != null) {
            telemetry.addData("Detected Sample", detectedLabel);
            telemetry.update();

            // Move toward the detected sample
            pidDriveForward(12, 0.5);

            // Lower the wrist and close the claw
            moveWrist(-0.5, 2000);
            operateClaw(0.0); // Close the claw

            // Move backward for 24 inches
            pidDriveBackward(24, 0.5);
        } else {
            telemetry.addData("No Sample Detected", "Robot will remain stationary");
            telemetry.update();
        }
    }

    // ===================== HELPER METHODS =====================

    private void initializeHardware() {
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        wrist = hardwareMap.get(CRServo.class, "wrist");
        claw = hardwareMap.get(Servo.class, "claw");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        resetEncoders();
    }

    private void initializeTensorFlow() {
        tfod = new TFObjectDetector(hardwareMap);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }

    private String detectSample() {
        if (tfod != null) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals("Red") ||
                        recognition.getLabel().equals("Blue") ||
                        recognition.getLabel().equals("Yellow")) {
                        return recognition.getLabel();
                    }
                }
            }
        }
        return null;
    }

    private void resetEncoders() {
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void pidDriveForward(double inches, double basePower) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);
        int previousError = 0;

        leftBack.setTargetPosition(leftBack.getCurrentPosition() + targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() + targetTicks);
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + targetTicks);

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive() && allMotorsBusy()) {
            int currentPosition = (leftBack.getCurrentPosition() + rightBack.getCurrentPosition()
                    + leftFront.getCurrentPosition() + rightFront.getCurrentPosition()) / 4;
            int error = targetTicks - currentPosition;
            int derivative = error - previousError;
            double adjustment = (KP * error) + (KD * derivative);
            double adjustedPower = basePower + adjustment;

            setMotorPowers(adjustedPower);
            telemetry.addData("Target Ticks", targetTicks);
            telemetry.addData("Current Position", currentPosition);
            telemetry.addData("Error", error);
            telemetry.addData("Power Adjustment", adjustment);
            telemetry.update();
            previousError = error;
        }

        stopMotors();
    }

    private void pidDriveBackward(double inches, double basePower) {
        pidDriveForward(-inches, basePower);
    }

    private void moveWrist(double power, int duration) {
        wrist.setPower(power);
        sleep(duration);
        wrist.setPower(0);
    }

    private void operateClaw(double position) {
        claw.setPosition(position);
        telemetry.addData("Claw", "Position: %.2f", position);
        telemetry.update();
        sleep(500);
    }

    private void setMotorPowers(double power) {
        leftBack.setPower(power);
        rightBack.setPower(power);
        leftFront.setPower(power);
        rightFront.setPower(power);
    }

    private boolean allMotorsBusy() {
        return leftBack.isBusy() && rightBack.isBusy() && leftFront.isBusy() && rightFront.isBusy();
    }

    private void stopMotors() {
        setMotorPowers(0);
    }
} */
