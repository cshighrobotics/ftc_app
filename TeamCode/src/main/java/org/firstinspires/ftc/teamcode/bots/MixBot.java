package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.components.FourWheelDT;
import org.firstinspires.ftc.teamcode.components.ServoArm;

/**
 * Created by asowd on 11/27/2017.
 * <p>
 * robot designed by matt hudson with rotating upper decka dn four wheel mecanum drivetrain
 * <p>
 * this robot should be instantiated at the beginning of all opmodes using this robot
 */


public class MixBot extends CRobot {

    private final double
            JEWEL_MIN = 0,
            JEWEL_MAX = 1,
            JEWEL_INC = .0005;

    private final double
            DRIVE_MAX = 1,
            DRIVE_MIN = -1,
            DRIVE_STALL = 0,
            DRIVE_SCALE = .8;


    //drivetrain that makes robot move
    public FourWheelDT dt = new FourWheelDT(DRIVE_MIN, DRIVE_MAX, DRIVE_STALL, DRIVE_SCALE);
    public ServoArm jewelArm = new ServoArm("sja", JEWEL_MIN, JEWEL_MAX, JEWEL_INC);


    @Override
    public void init(HardwareMap hMap) {
        dt.init(hMap);
        dt.stopDriving();
        jewelArm.retractArm();
    }

    @Override
    public String debugString() {
        sBuild.setLength(0);
        sBuild.append(dt.debugString()).append("\n");
        sBuild.append(jewelArm.debugString());
        return sBuild.toString();
    }

    @Override
    public void stopRobot() {
        dt.stopDriving();
        jewelArm.retractArm();
    }
}
