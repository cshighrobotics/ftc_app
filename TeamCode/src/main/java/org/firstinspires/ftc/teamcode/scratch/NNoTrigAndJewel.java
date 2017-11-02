package org.firstinspires.ftc.teamcode.scratch;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by asowd on 10/17/2017.
 */
@Disabled
@TeleOp(name = "armandjewel")
public class NNoTrigAndJewel extends LinearOpMode {

    //servos
    Servo jewelArm;
    Servo grabLeft;
    Servo grabRight;
    //motors
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;


    //position values
    double JEWEL_ENGAGE = 0;
    double JEWEL_RETRACT = 0;
    //Closed pos
    double LEFT_CLOSED = 0;
    double RIGHT_CLOSED = 0;
    //open pos
    double LEFT_OPEN = 0;
    double RIGHT_OPEN = 0;


    //servos position
    double jewelPosition;
    double leftPos;
    double rightPos;
    //movement values
    double moveX, moveY, moveR;
    //motor powers
    double flPower, frPower, rlPower, rrPower;


    @Override
    public void runOpMode() throws InterruptedException {

        //associate config value pairs with objects
        frontLeft = hardwareMap.get(DcMotor.class, "mFL");
        frontRight = hardwareMap.get(DcMotor.class, "mFR");
        rearLeft = hardwareMap.get(DcMotor.class, "mRL");
        rearRight = hardwareMap.get(DcMotor.class, "mRR");
        // get servos from hardware map
        jewelArm = hardwareMap.get(Servo.class, "sJA");
        grabLeft = hardwareMap.get(Servo.class, "sGL");
        grabRight = hardwareMap.get(Servo.class, "sGR");

        //Set Position to Retract
        jewelPosition = JEWEL_RETRACT;
        leftPos = LEFT_CLOSED;
        rightPos = RIGHT_CLOSED;

        //set initial position
        jewelArm.setPosition(jewelPosition);
        grabLeft.setPosition(leftPos);
        grabRight.setPosition(rightPos);

        //reverse left wheels
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();


        while (opModeIsActive()) {
            //get movement values from gamepads
            moveX = gamepad1.left_stick_x;
            moveY = -gamepad1.left_stick_y;
            moveR = gamepad1.right_stick_x;

            //determine motor powers based on movement values
            flPower = Range.clip(-moveX + moveY + moveR, -1, 1);
            frPower = Range.clip(moveX + moveY - moveR, -1, 1);
            rlPower = Range.clip(moveX + moveY + moveR, -1, 1);
            rrPower = Range.clip(-moveX + moveY - moveR, -1, 1);

            //Set Servos
            if (gamepad1.a)
                jewelPosition = JEWEL_RETRACT;
            if (gamepad1.b)
                jewelPosition = JEWEL_ENGAGE;
            //set grabber
            if (gamepad1.x) {
                leftPos = LEFT_CLOSED;
                rightPos = RIGHT_CLOSED;
            }
            if (gamepad1.y) {
                leftPos = LEFT_OPEN;
                rightPos = RIGHT_OPEN;
            }

            //push motor powers to robot
            frontLeft.setPower(flPower);
            frontRight.setPower(frPower);
            rearLeft.setPower(rlPower);
            rearRight.setPower(rrPower);

            jewelArm.setPosition(jewelPosition);
            grabLeft.setPosition(leftPos);
            grabRight.setPosition(rightPos);
        }
    }
}
