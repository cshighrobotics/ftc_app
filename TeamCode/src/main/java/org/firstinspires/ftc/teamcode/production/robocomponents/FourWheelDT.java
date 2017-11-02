package org.firstinspires.ftc.teamcode.production.robocomponents;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.lib.FTCMath;

/**
 * Created by asowd on 10/28/2017.
 */

public class FourWheelDT extends RoboComponent {
    //motors
    //drive motors for a 4 powered wheel drive train
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor rearLeft = null;
    private DcMotor rearRight = null;

    private DriveMode dMode;

    public enum DriveMode {
        SKID,
        POV,
        NOTRIG,
        TRIG
    }


    public double getMIN_POWER() {
        return MIN_POWER;
    }

    public void setMIN_POWER(double MIN_POWER) {
        this.MIN_POWER = MIN_POWER;
    }

    public double getMAX_POWER() {
        return MAX_POWER;
    }

    public void setMAX_POWER(double MAX_POWER) {
        this.MAX_POWER = MAX_POWER;
    }

    public double getSTALL_POWER() {
        return STALL_POWER;
    }

    public void setSTALL_POWER(double STALL_POWER) {
        this.STALL_POWER = STALL_POWER;
    }

    public double getCRAWL_POWER() {
        return CRAWL_POWER;
    }

    public void setCRAWL_POWER(double CRAWL_POWER) {
        this.CRAWL_POWER = CRAWL_POWER;
    }

    public double getCRUISE_POWER() {
        return CRUISE_POWER;
    }

    public void setCRUISE_POWER(double CRUISE_POWER) {
        this.CRUISE_POWER = CRUISE_POWER;
    }

    //driving constants
    double MIN_POWER = -1, MAX_POWER = 1, STALL_POWER = 1;
    double CRAWL_POWER = .2, CRUISE_POWER = 1;


    //motor powers for tracking and pass powers method
    //these will be public but the motors will not
    private double
            flPower = 0,
            frPower = 0,
            rlPower = 0,
            rrPower = 0;

    public void init(HardwareMap hMap, DriveMode dm) {
        setDriveMode(dm);
        init(hMap);
    }

    @Override
    public void init(HardwareMap hMap) {
        componentMap = hMap;

        frontLeft = hMap.get(DcMotor.class, "mFL");
        frontRight = hMap.get(DcMotor.class, "mFR");
        rearLeft = hMap.get(DcMotor.class, "mRL");
        rearRight = hMap.get(DcMotor.class, "mRR");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        stopDriving();
    }


    //standardization for driving by power
    public void passPowers() {


        flPower = FTCMath.filterMotorPower(flPower, MIN_POWER, MAX_POWER, STALL_POWER);
        frPower = FTCMath.filterMotorPower(frPower, MIN_POWER, MAX_POWER, STALL_POWER);
        rlPower = FTCMath.filterMotorPower(rlPower, MIN_POWER, MAX_POWER, STALL_POWER);
        rrPower = FTCMath.filterMotorPower(rrPower, MIN_POWER, MAX_POWER, STALL_POWER);

        frontLeft.setPower(flPower);
        frontRight.setPower(frPower);
        rearLeft.setPower(rlPower);
        rearRight.setPower(rrPower);
    }

    //stopping function
    public void stopDriving() {

        //assign appropriate wheel powers
        flPower = 0;
        rlPower = 0;
        frPower = 0;
        rrPower = 0;

        passPowers();
    }

    //skid steer tank controls
    public void driveByTank(double left, double right) {

        //assign appropriate wheel powers
        flPower = left;
        rlPower = left;
        frPower = right;
        rrPower = left;

        passPowers();
    }

    //drive by pov controls
    public void driveByPOV(double drive, double turn) {
        double left, right, max;
        // Combine drive and turn for blended motion.
        left = drive + turn;
        right = drive - turn;

        // Normalize the values so neither exceed +/- 1.0
        max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0) {
            left /= max;
            right /= max;
        }

        driveByTank(left, right);
    }

    //mecanum controlled no trig example
    public void driveByNoTrig(double moveX, double moveY, double moveR) {

        //determine motor powers based on movement values

        flPower = Range.clip(-moveX + moveY + moveR, -1, 1);
        frPower = Range.clip(moveX + moveY - moveR, -1, 1);
        rlPower = Range.clip(moveX + moveY + moveR, -1, 1);
        rrPower = Range.clip(-moveX + moveY - moveR, -1, 1);

        passPowers();

    }

    public void driveByTrig(double power, double angle, double rotation) {

        flPower = (power * Math.cos(angle) + rotation);
        frPower = (power * Math.sin(angle) - rotation);
        rlPower = (power * Math.sin(angle) + rotation);
        rrPower = (power * Math.cos(angle) - rotation);

        passPowers();
    }

    public void setDriveMode(DriveMode dm) {
        dMode = dm;
    }

    public DriveMode getDriveMode() {
        return dMode;
    }

    public void driveByDM(double ly, double lx, double ry, double rx) {
        switch (dMode) {
            case SKID:
                driveByTank(ly, ry);
            case POV:
                driveByPOV(ly, rx);
            case NOTRIG:
                driveByNoTrig(ly, lx, rx);
            case TRIG:
                driveByTrig(
                        Math.hypot(lx, ly),
                        Math.atan2(ly, lx) - Math.PI / 4,
                        rx
                );
        }
    }


}
