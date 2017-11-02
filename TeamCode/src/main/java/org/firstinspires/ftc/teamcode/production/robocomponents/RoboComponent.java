package org.firstinspires.ftc.teamcode.production.robocomponents;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by asowd on 10/28/2017.
 */

public abstract class RoboComponent {
    HardwareMap componentMap = null;

    public abstract void init(HardwareMap hMap);
}
