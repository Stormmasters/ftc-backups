import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

public class AlexTest {

    @TeleOp
    public class AutonBasic extends OpMode {
        DcMotor frontRight, frontLeft, backLeft, backRight;
        @Override
        public void init() {
            frontLeft = hardwareMap.dcMotor.get("front_left_motor");
            frontRight = hardwareMap.dcMotor.get("front_right_motor");
            backLeft = hardwareMap.dcMotor.get("back_left_motor");
            backRight = hardwareMap.dcMotor.get("back_right_motor");

        }

        @Override
        public void loop() {
            frontLeft.setPower(0.5);
            frontRight.setPower(0.5);
            backLeft.setPower(0.5);
            backRight.setPower(0.5);
        }
    }
    
}