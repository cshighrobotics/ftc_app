package org.firstinspires.ftc.teamcode.production.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.production.robocomponents.FourWheelDT;

/**
 * Created by asowd on 10/28/2017.
 */

public class DriveBot extends Robot {

    public FourWheelDT dt = new FourWheelDT();

    @Override
    public void init(HardwareMap hMap) {
        robotHardware = hMap;

        dt.setMIN_POWER(-1);
        dt.setMAX_POWER(1);
        dt.setSTALL_POWER(0);

        dt.init(hMap, FourWheelDT.DriveMode.TRIG);

    }

    @Override
    public void stopRobot() {
        dt.stopDriving();
    }
}
