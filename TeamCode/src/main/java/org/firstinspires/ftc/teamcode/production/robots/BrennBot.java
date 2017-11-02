package org.firstinspires.ftc.teamcode.production.robots;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.production.robocomponents.FourWheelDT;
import org.firstinspires.ftc.teamcode.production.robocomponents.ServoArm;

/**
 * Created by asowd on 10/28/2017.
 */

public class BrennBot extends Robot {
    public FourWheelDT dt = new FourWheelDT();


    private double je = 1, jr = .2;
    public ServoArm leftJewel = new ServoArm("sLJ", je, jr);
    public ServoArm rightJewel = new ServoArm("sRJ", 1 - je, 1 - jr);

    double gEn = 1, gRe = .2;
    public ServoArm leftGrab = new ServoArm("sLG", gEn, gRe);
    public ServoArm rightGrab = new ServoArm("sRG", 1 - gEn, 1 - gRe);

    public ColorSensor cLJ;
    public ColorSensor cRJ;
    public ColorSensor cBP;

    public DcMotor mWinch;


    public void engageGrabbers() {
        leftGrab.retractArm();
        rightGrab.retractArm();
    }

    public void retractGrabbers() {
        leftGrab.engageArm();
        rightGrab.engageArm();
    }

    public void retractJewels() {
        leftJewel.retractArm();
        rightJewel.retractArm();
    }

    public void engageJewels() {
        leftJewel.engageArm();
        rightJewel.engageArm();
    }

    @Override
    public void init(HardwareMap hMap) {
        robotHardware = hMap;

        mWinch = robotHardware.get(DcMotor.class, "mWI");

        cLJ = robotHardware.get(ColorSensor.class, "cLJ");
        cRJ = robotHardware.get(ColorSensor.class, "cRJ");
        cBP = robotHardware.get(ColorSensor.class, "cBP");

        mWinch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        dt.setMIN_POWER(-1);
        dt.setMAX_POWER(1);
        dt.setSTALL_POWER(.05);
        dt.init(hMap, FourWheelDT.DriveMode.TRIG);

        leftJewel.init(hMap);
        rightJewel.init(hMap);
        leftGrab.init(hMap);
        rightGrab.init(hMap);

        engageGrabbers();


    }

    @Override
    public void stopRobot() {
        dt.stopDriving();
        retractGrabbers();
        leftJewel.retractArm();
        rightJewel.retractArm();
        mWinch.setPower(0);
    }
}
