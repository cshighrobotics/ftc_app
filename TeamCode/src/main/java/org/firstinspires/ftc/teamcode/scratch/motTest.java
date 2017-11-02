package org.firstinspires.ftc.teamcode.scratch;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by asowd on 10/5/2017.
 */
@Disabled
@TeleOp(name = "SkidSteer")
public class motTest extends LinearOpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;

    @Override
    public void runOpMode() throws InterruptedException {

        //named ports associating with config file
        frontLeft = hardwareMap.get(DcMotor.class, "mFL");
        frontRight = hardwareMap.get(DcMotor.class, "mFR");
        rearLeft = hardwareMap.get(DcMotor.class, "mRL");
        rearRight = hardwareMap.get(DcMotor.class, "mRR");

        //change the direction of offset motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            //set motors based on the joystick position
            frontLeft.setPower(-gamepad1.left_stick_y);
            frontRight.setPower(-gamepad1.right_stick_y);
            rearLeft.setPower(-gamepad1.left_stick_y);
            rearRight.setPower(-gamepad1.right_stick_y);
        }
    }
}
