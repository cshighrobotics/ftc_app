package org.firstinspires.ftc.teamcode.classheavy;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by asowd on 10/1/2017.
 */
//abstract class for all custom robots
public abstract class CustomRobot {
    //all robots require a hardwaremap to be passed from opmode
    abstract public void init(HardwareMap ahMap);
    public HardwareMap hMap = null;
}
