package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by asowd on 10/17/2017.
 */

@TeleOp(name = "withTrig")
public class MecanumWithTrig extends LinearOpMode {

    //motors
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;

    //movement values
    double power, angle, rotation;

    //motor powers
    double flPower, frPower, rlPower, rrPower;

    public void initRobot(){

        //associate config value pairs with objects
        frontLeft   =       hardwareMap.get(DcMotor.class, "mFL");
        frontRight  =       hardwareMap.get(DcMotor.class, "mFR");
        rearLeft    =       hardwareMap.get(DcMotor.class, "mRL");
        rearRight   =       hardwareMap.get(DcMotor.class, "mRR");

        frontLeft   .setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft    .setDirection(DcMotorSimple.Direction.REVERSE);

    }


    @Override
    public void runOpMode() throws InterruptedException {

        initRobot();

        waitForStart();


        while(opModeIsActive()){

            //get movement values from gamepads

            power       =   Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            angle       =   Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            rotation    =   gamepad1.right_stick_x;

            //determine motor powers based on movement values

            flPower = Range.clip(power * Math.cos(angle) + rotation, -1, 1);
            frPower = Range.clip(power * Math.sin(angle) - rotation, -1, 1);
            rlPower = Range.clip(power * Math.sin(angle) + rotation, -1, 1);
            rrPower = Range.clip(power * Math.cos(angle) - rotation, -1, 1);


            //push motor powers to robot

            frontLeft   .setPower(flPower);
            frontRight  .setPower(frPower);
            rearLeft    .setPower(rlPower);
            rearRight   .setPower(rrPower);
        }
    }
}
