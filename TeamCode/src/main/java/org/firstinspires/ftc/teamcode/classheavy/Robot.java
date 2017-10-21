package org.firstinspires.ftc.teamcode.classheavy;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by asowd on 10/1/2017.
 */

public abstract class Robot {
    abstract public void init(HardwareMap ahMap);
    public HardwareMap hMap = null;
    ElapsedTime roboClock  = new ElapsedTime();

}
