package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by asowd on 10/1/2017.
 */

public abstract class CustomOpMode extends LinearOpMode {

    ElapsedTime runtime = new ElapsedTime();


    abstract void initRobot();
    abstract void runInstructionList();
    abstract void dsPrint();

    protected void pauseProgram(double pauseTime){
        double  startTime = runtime.milliseconds(),
                endTime = startTime + pauseTime;
        while (runtime.milliseconds() < endTime){
            dsPrint();
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();
        waitForStart();
        runInstructionList();
    }
}
