package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class TestOdometer extends LinearOpMode {

    private DcMotorEx leftBack;
    private DcMotorEx rightBack;
    private DcMotorEx leftFront;
    private DcMotorEx rightFront;
    private DcMotor left_slide;
    private DcMotor right_slide;
    private Servo shoulder;
    private Servo claw;
    private Servo wrist;

    // Odometry and movement constants
    private double xPos = 0.0;
    private double yPos = 0.0;
    private double turn = 0.0;

    private static final double TICKS_PER_REV = 2000;
    private static final double WHEEL_DIAMETER_IN_MM = 96;
    private static final double WHEEL_DIAMETER = WHEEL_DIAMETER_IN_MM / 25.4;
    private static final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER);

    @Override
    public void runOpMode() {
        // Hardware mapping
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        left_slide = hardwareMap.get(DcMotor.class, "par1");
        right_slide = hardwareMap.get(DcMotor.class, "par0");
        shoulder = hardwareMap.get(Servo.class, "shoulder");
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        right_slide.setDirection(DcMotorSimple.Direction.REVERSE);

        // Reset and set motor modes
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            wrist.setPosition(0);
            moveForward(24);
            sleep(500);
            moveBackward(24);
            sleep(500);
            turnRight(90);
            sleep(500);
            turnLeft(90);
            sleep(500);
            strafeRight(15);
            sleep(500);
            strafeLeft(15);



            telemetry.addData("xPos", xPos);
            telemetry.addData("yPos", yPos);
            telemetry.addData("turn", turn);
            telemetry.update();

        }
    }


    // Method to move the robot forward a specified distance in inches
    public void moveForward(double inches) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);

        // Set target position for all drive motors
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + targetTicks);
        leftBack.setTargetPosition(leftBack.getCurrentPosition() + targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() + targetTicks);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set power to the motors
        leftFront.setPower(0.5);
        rightFront.setPower(0.5);
        leftBack.setPower(0.5);
        rightBack.setPower(0.5);

        // Wait until motors reach the target position
        while (opModeIsActive() &&
                (leftFront.isBusy() && rightFront.isBusy() &&
                        leftBack.isBusy() && rightBack.isBusy())) {
            telemetry.addData("TargetPosition", targetTicks);
            telemetry.addData("LeftFrontPos", leftFront.getCurrentPosition());
            telemetry.addData("RightFrontPos", rightFront.getCurrentPosition());
            telemetry.addData("LeftBackpos", leftBack.getCurrentPosition());
            telemetry.addData("RightBackPos", rightBack.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motors
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    // Method to move the robot forward a specified distance in inches
    public void moveBackward(double inches) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);

        // Set target position for all drive motors
        leftFront.setTargetPosition(leftFront.getCurrentPosition() - targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - targetTicks);
        leftBack.setTargetPosition(leftBack.getCurrentPosition() - targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() - targetTicks);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set power to the motors
        leftFront.setPower(0.5);
        rightFront.setPower(0.5);
        leftBack.setPower(0.5);
        rightBack.setPower(0.5);

        // Wait until motors reach the target position
        while (opModeIsActive() &&
                (leftFront.isBusy() && rightFront.isBusy() &&
                        leftBack.isBusy() && rightBack.isBusy())) {
            telemetry.addData("TargetPosition", targetTicks);
            telemetry.addData("LeftFrontPos", leftFront.getCurrentPosition());
            telemetry.addData("RightFrontPos", rightFront.getCurrentPosition());
            telemetry.addData("LeftBackpos", leftBack.getCurrentPosition());
            telemetry.addData("RightBackPos", rightBack.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motors
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    private void turnRight(double degrees){

        double robotCircumference = Math.PI *WHEEL_DIAMETER;
        double turnDistance = (degrees / 360)*robotCircumference;
        int targetTicks = (int) (turnDistance * TICKS_PER_INCH);

        leftFront.setTargetPosition(leftFront.getCurrentPosition() + targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - targetTicks);
        leftBack.setTargetPosition(leftBack.getCurrentPosition() + targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() -  targetTicks);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(0.5);
        leftBack.setPower(0.5);
        rightFront.setPower(0.5);
        rightBack.setPower(0.5);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && (leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy())) {
            telemetry.addLine("Turning");
            telemetry.update();
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    private void turnLeft(double degrees){

        double robotCircumference = Math.PI *WHEEL_DIAMETER;
        double turnDistance = (degrees / 360)*robotCircumference;
        int targetTicks = (int) (turnDistance * TICKS_PER_INCH);

        leftFront.setTargetPosition(leftFront.getCurrentPosition() - targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + targetTicks);
        leftBack.setTargetPosition(leftBack.getCurrentPosition() - targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() +  targetTicks);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(0.5);
        leftBack.setPower(0.5);
        rightFront.setPower(0.5);
        rightBack.setPower(0.5);

        while (opModeIsActive() && (leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy())) {
            telemetry.addLine("Turning");
            telemetry.update();
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }


    public void strafeRight(double inches) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);

        // Set target position for all drive motors
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - targetTicks);
        leftBack.setTargetPosition(leftBack.getCurrentPosition() - targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() + targetTicks);

        // Set power to the motors
        leftFront.setPower(0.5);
        rightFront.setPower(0.5);
        leftBack.setPower(0.5);
        rightBack.setPower(0.5);

        // Wait until motors reach the target position
        while (opModeIsActive() &&
                (leftFront.isBusy() && rightFront.isBusy() &&
                        leftBack.isBusy() && rightBack.isBusy())) {
            telemetry.addData("TargetPosition", targetTicks);
            telemetry.addData("LeftFrontPos", leftFront.getCurrentPosition());
            telemetry.addData("RightFrontPos", rightFront.getCurrentPosition());
            telemetry.addData("LeftBackPos", leftBack.getCurrentPosition());
            telemetry.addData("RightBackPos", rightBack.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motors
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    public void strafeLeft(double inches) {
        int targetTicks = (int) (inches * TICKS_PER_INCH);

        // Set target position for all drive motors
        leftFront.setTargetPosition(leftFront.getCurrentPosition() - targetTicks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + targetTicks);
        leftBack.setTargetPosition(leftBack.getCurrentPosition() + targetTicks);
        rightBack.setTargetPosition(rightBack.getCurrentPosition() - targetTicks);

        // Set power to the motors
        leftFront.setPower(0.5);
        rightFront.setPower(0.5);
        leftBack.setPower(0.5);
        rightBack.setPower(0.5);

        // Wait until motors reach the target position
        while (opModeIsActive() &&
                (leftFront.isBusy() && rightFront.isBusy() &&
                        leftBack.isBusy() && rightBack.isBusy())) {
            telemetry.addData("TargetPosition", targetTicks);
            telemetry.addData("LeftFrontPos", leftFront.getCurrentPosition());
            telemetry.addData("RightFrontPos", rightFront.getCurrentPosition());
            telemetry.addData("LeftBacPos", leftBack.getCurrentPosition());
            telemetry.addData("RightBackPos", rightBack.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motors
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
}
