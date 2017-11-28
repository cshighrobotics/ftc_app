package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by asowd on 10/28/2017.
 * abstract class for all robot components - motifs found in multiple robots
 * <p>
 * establishes practice of taking hardware map as an argument
 */

abstract class CRoboComponent {
    StringBuilder sBuild = new StringBuilder();

    public abstract void init(HardwareMap hMap);

    public abstract String debugString();
}
