package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by asowd on 10/21/2017.
 */

public class FTCMath {

    //takes in info about a proposed motor value and filters it accordingly

    public static double filterMotorPower(double power, double min, double max, double stall){
        power = Range.clip(power, min, max);

        if (Math.abs(power) < Math.abs(stall))
            power = 0;

        return power;
    }
//
//    public static double getOrdinalDegree(double angle){
//        return angle % 360;
//    }

}
