package org.firstinspires.ftc.teamcode.lib;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by asowd on 10/21/2017.
 */

public class FTCMath {

    //takes in info about a proposed motor value and filters it accordingly

    public static double filterMotorPower(double power, double min, double max, double stall) {
        power = Range.clip(power, min, max);

        if (Math.abs(power) < Math.abs(stall))
            power = 0;

        return power;
    }

    public static int getCC(double rVal, double bVal) {
        if (rVal > bVal)
            return -1;
        if (bVal > rVal)
            return 1;
        return 0;
    }

    public static void safeWait(LinearOpMode l, ElapsedTime et, double time) {
        double endTime = et.milliseconds() + time;
        while (l.opModeIsActive() && et.milliseconds() < endTime) ;
    }
//
//    public static double getOrdinalDegree(double angle){
//        return angle % 360;
//    }

}
