package org.firstinspires.ftc.teamcode.lib;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by asowd on 10/21/2017.
 * <p>
 * for common math functions needed in FTC programming
 */

public class FTCMath {

    //takes in info about a proposed motor value and filters it accordingly
    public static double filterMotorPower(double power, double min, double max, double stall, double scale) {
        //scale power
        power *= scale;

        //clip based on min and max
        power = Range.clip(power, min, max);

        //we don't want to push a power that doesn't move the motor
        if (Math.abs(power) < Math.abs(stall))
            power = 0;

        return power;
    }

    //red and blue comparison code that returns -1 for red 1 for blue and 0 for undefined
    public static int getCC(double rVal, double bVal) {
        if (rVal > bVal)
            return -1;
        if (bVal > rVal)
            return 1;
        return 0;
    }

    //makes a running opmode wait for a certain amount of time
    public static void safeWait(LinearOpMode l, ElapsedTime et, double time) {
        double endTime = et.milliseconds() + time;
        while (l.opModeIsActive() && et.milliseconds() < endTime) ;
    }


    public static double getOrdinalDegree(double angle) {
        return angle % 360;
    }

}
