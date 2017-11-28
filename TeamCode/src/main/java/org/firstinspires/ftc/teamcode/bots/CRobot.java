package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by asowd on 10/28/2017.
 * abstract class for all custom robots - a collection of multiple robot systems
 * <p>
 * establishes practice of taking hardware map as an argument
 */

abstract class CRobot {

    StringBuilder sBuild = new StringBuilder();

    public abstract void init(HardwareMap hMap);

    public abstract String debugString();

    public abstract void stopRobot();
}
