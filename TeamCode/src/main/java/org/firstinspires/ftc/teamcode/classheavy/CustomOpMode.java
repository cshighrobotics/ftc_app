package org.firstinspires.ftc.teamcode.classheavy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by asowd on 10/1/2017.
 */

public abstract class CustomOpMode extends LinearOpMode {

    ElapsedTime runtime = new ElapsedTime();


    abstract public void initRobot();
    abstract public void runInstructionList();
    abstract public void dsPrint();


    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();
        waitForStart();
        runInstructionList();
    }
}
