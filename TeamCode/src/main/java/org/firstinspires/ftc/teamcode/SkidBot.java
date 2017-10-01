package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by asowd on 10/1/2017.
 */

public class SkidBot extends Robot {

    //hardware objects
    DcMotor mLeft, mRight, mWinch;
    Servo sJewel, sClaw, sGlyph;

    //servo constants
    double JEWEL_UP = -1, JEWEL_DOWN = 0;
    double CLAW_CLOSE, CLAW_OPEN;

    //driving constants
    double MIN_POWER = 0, MAX_POWER = 1;
    double CRAWL_POWER = .2, CRUISE_POWER = 1;

    //constructor
    public SkidBot(DcMotor left, DcMotor right, DcMotor winch, Servo jewel, Servo claw, Servo glyph){
        mLeft = left;
        mRight = right;
        mWinch = winch;
        sJewel = jewel;
        sClaw = claw;
        sGlyph = glyph;
    }

    //stopping
    public void stopMotors(){
        driveByPower(0);
        mWinch.setPower(0);
    }


    //driving modes
    public void driveByPower(double power){
        power = Range.clip(power,-1,1);
        mLeft.setPower(power);
        mRight.setPower(power);
    }

    public void driveByTank(double leftPower, double rightPower){
        leftPower = Range.clip(leftPower, -1,1);
        rightPower = Range.clip(rightPower, -1,1);

        mLeft.setPower(leftPower);
        mRight.setPower(rightPower);
    }

    public void driveByPOV(double drive, double turn){
        double left, right, max;
        // Combine drive and turn for blended motion.
        left  = drive + turn;
        right = drive - turn;

        // Normalize the values so neither exceed +/- 1.0
        max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0)
        {
            left /= max;
            right /= max;
        }

    }

    //jewel arm controls
    public void jewelEngage(){
        sJewel.setPosition(JEWEL_UP);
    }

    public void jewelRetract(){
        sJewel.setPosition(JEWEL_DOWN);
    }

    //claw controls
    public void openClaw(){
        sClaw.setPosition(CLAW_OPEN);
    }

    public void closeClaw(){
        sClaw.setPosition(CLAW_CLOSE);
    }


    //winchControls
    public void setWinchPower(double power){
        mWinch.setPower(power);
    }

    @Override
    public void init() {
        stopMotors();
        closeClaw();
        jewelRetract();
    }

}
