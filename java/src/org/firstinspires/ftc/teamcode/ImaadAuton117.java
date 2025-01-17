package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "ImaadAuton117", group = "Autonomous")
public class ImaadAuton117 extends LinearOpMode {

    @Override
    public void runOpMode() {
        RobotHardware robot = new RobotHardware(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (opModeIsActive()) {
            // Move robot forward for a specified time
            double forwardPower = 0.25; // Normal speed
            long forwardDuration = 1500; // Approximate duration in milliseconds to move 3.5 feet

            // Set motor powers to move forward
            robot.setDrivePower(forwardPower, forwardPower, forwardPower, forwardPower);

            // Raise the slide motors
            robot.setSlidePower(0.25);

            // Move the shoulder servo up by 35 degrees a wrist servo down by 20 degrees
            robot.moveShoulder(-75);
            robot.moveWrist(40);

            // Open the claw servo by 15 degrees
            robot.moveClaw(10);

            // Sleep for the duration of forward movement
            sleep(forwardDuration);

            // Stop all motors
            robot.stopAllMotors();

            telemetry.addData("Status", "Task Complete");
            telemetry.update();
        }
    }
}

class RobotHardware {
    private DcMotor backLeftMotor;
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    private DcMotor leftSlideMotor;
    private DcMotor rightSlideMotor;
    private Servo shoulderServo;
    private Servo wristServo;
    private Servo clawServo;

    public RobotHardware(com.qualcomm.robotcore.hardware.HardwareMap hardwareMap) {
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");
        leftSlideMotor = hardwareMap.get(DcMotor.class, "left_slide");
        rightSlideMotor = hardwareMap.get(DcMotor.class, "right_slide");
        shoulderServo = hardwareMap.get(Servo.class, "shoulder");
        wristServo = hardwareMap.get(Servo.class, "wrist");
        clawServo = hardwareMap.get(Servo.class, "claw");

        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);

    }

    public void setDrivePower(double backLeftPower, double frontLeftPower, double frontRightPower, double backRightPower) {
        backLeftMotor.setPower(backLeftPower);
        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
    }

    public void setSlidePower(double power) {
        leftSlideMotor.setPower(power);
        rightSlideMotor.setPower(power);
    }

    public void moveShoulder(double degrees) {
        double shoulderPosition = -1 + (degrees / 180.0);
        shoulderServo.setPosition(shoulderPosition);
    }

    public void moveWrist(double degrees) {
        double wristPosition = 1 + (degrees / 180.0);
        wristServo.setPosition(wristPosition);
    }

    public void moveClaw(double degrees) {
        double clawPosition = 0.5 + (degrees / 180.0);
        clawServo.setPosition(clawPosition);
    }

    public void stopAllMotors() {
        backLeftMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        leftSlideMotor.setPower(0);
        rightSlideMotor.setPower(0);
    }
}
