package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.lib.FTCMath;

/**
 * Created by asowd on 10/28/2017.
 * <p>
 * code for a typical four wheel drive robot with multiple drive modes
 * <p>
 * the different drive modes allow for control in various different wheel setups
 */

public class FourWheelDT extends CRoboComponent {
    //motors
    //drive motors for a 4 powered wheel drive train
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor rearLeft = null;
    private DcMotor rearRight = null;

    private DriveMode dMode;
    //driving constants
    private double MIN_POWER, MAX_POWER, STALL_POWER, SCALE_POWER;
    //motor powers for tracking and pass powers method
    //these will be public but the motors will not
    private double
            flPower = 0,
            frPower = 0,
            rlPower = 0,
            rrPower = 0;


    public FourWheelDT(double minPow, double maxPow, double stallPow, double scalePow) {
        MIN_POWER = minPow;
        MAX_POWER = maxPow;
        STALL_POWER = stallPow;
        SCALE_POWER = scalePow;
    }

    public void init(HardwareMap hMap, DriveMode dm) {
        setDriveMode(dm);
        init(hMap);
    }

    @Override
    public void init(HardwareMap hMap) {

        frontLeft = hMap.get(DcMotor.class, "mFL");
        frontRight = hMap.get(DcMotor.class, "mFR");
        rearLeft = hMap.get(DcMotor.class, "mRL");
        rearRight = hMap.get(DcMotor.class, "mRR");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRight.setDirection(DcMotorSimple.Direction.REVERSE);

        sBuild.append("DriveTrain:\n");

        stopDriving();
    }

    @Override
    public String debugString() {
        sBuild.setLength(1);
        sBuild.append("flPow: \t").append(frontLeft.getPower()).append("\n");
        sBuild.append("frPow: \t").append(frontRight.getPower()).append("\n");
        sBuild.append("rlPow: \t").append(rearLeft.getPower()).append("\n");
        sBuild.append("rrPow: \t").append(rearRight.getPower());

        return sBuild.toString();
    }

    //standardization for driving by power
    private void passPowers() {


        flPower = FTCMath.filterMotorPower(flPower, MIN_POWER, MAX_POWER, STALL_POWER, SCALE_POWER);
        frPower = FTCMath.filterMotorPower(frPower, MIN_POWER, MAX_POWER, STALL_POWER, SCALE_POWER);
        rlPower = FTCMath.filterMotorPower(rlPower, MIN_POWER, MAX_POWER, STALL_POWER, SCALE_POWER);
        rrPower = FTCMath.filterMotorPower(rrPower, MIN_POWER, MAX_POWER, STALL_POWER, SCALE_POWER);

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

    //uses trig to get strafing
    public void driveByTrig(double power, double angle, double rotation) {

        flPower = (power * Math.cos(angle) + rotation);
        frPower = (power * Math.sin(angle) - rotation);
        rlPower = (power * Math.sin(angle) + rotation);
        rrPower = (power * Math.cos(angle) - rotation);

        passPowers();
    }

    public DriveMode getDriveMode() {
        return dMode;
    }

    public void setDriveMode(DriveMode dm) {
        dMode = dm;
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

    public enum DriveMode {
        SKID,
        POV,
        NOTRIG,
        TRIG
    }


}
