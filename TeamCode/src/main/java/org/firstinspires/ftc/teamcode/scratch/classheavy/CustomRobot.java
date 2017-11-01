package org.firstinspires.ftc.teamcode.scratch.classheavy;


import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by asowd on 10/1/2017.
 */
//abstract class for all custom robots
public abstract class CustomRobot {
    public HardwareMap hMap = null;

    public CustomRobot(){}

    //all robots require a hardwaremap to be passed from opmode
    abstract public void init(HardwareMap ahwMap);
}
