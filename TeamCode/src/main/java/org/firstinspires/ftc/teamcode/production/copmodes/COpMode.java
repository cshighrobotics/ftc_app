package org.firstinspires.ftc.teamcode.production.copmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by asowd on 10/28/2017.
 */

public abstract class COpMode extends LinearOpMode {

    protected ElapsedTime et = new ElapsedTime();

    public abstract void initOpmode();
    public abstract void runInstructions();
    public abstract void stopRobot();

    @Override
    public void runOpMode() throws InterruptedException {
        initOpmode();
        waitForStart();
        runInstructions();
        stopRobot();
    }
}
