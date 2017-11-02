package org.firstinspires.ftc.teamcode.production.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by asowd on 10/28/2017.
 */

public abstract class Robot {
    HardwareMap robotHardware = null;

    public abstract void init(HardwareMap hMap);

    public abstract void stopRobot();
}
