package org.firstinspires.ftc.teamcode.scratch;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by asowd on 10/17/2017.
 */
@Disabled
@TeleOp(name = "NoTrig")
public class MecanumNoTrig extends LinearOpMode {

    //motors
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;

    //movement values
    double moveX, moveY, moveR;

    //motor powers
    double flPower, frPower, rlPower, rrPower;


    @Override
    public void runOpMode() throws InterruptedException {

        //associate config value pairs with objects
        frontLeft   =   hardwareMap.get(DcMotor.class, "mFL");
        frontRight  =   hardwareMap.get(DcMotor.class, "mFR");
        rearLeft    =   hardwareMap.get(DcMotor.class, "mRL");
        rearRight   =   hardwareMap.get(DcMotor.class, "mRR");

        //reverse left wheels
        frontLeft   .setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft    .setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();


        while(opModeIsActive()){

            //get movement values from gamepads

            moveX   =     gamepad1.left_stick_x;
            moveY   =     -gamepad1.left_stick_y;
            moveR   =     gamepad1.right_stick_x;

            //determine motor powers based on movement values

            flPower     =   Range.clip( -moveX + moveY + moveR,  -1, 1);
            frPower     =   Range.clip( moveX + moveY - moveR,   -1, 1);
            rlPower     =   Range.clip( moveX + moveY + moveR,   -1, 1);
            rrPower     =   Range.clip( -moveX + moveY - moveR,  -1, 1);

            //push motor powers to robot

            frontLeft   .setPower(flPower);
            frontRight  .setPower(frPower);
            rearLeft    .setPower(rlPower);
            rearRight   .setPower(rrPower);
        }
    }
}
