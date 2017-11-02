package org.firstinspires.ftc.teamcode.scratch.classheavy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by asowd on 10/1/2017.
 */
//abstract class for all custom opmodes
public abstract class CustomOpMode extends LinearOpMode {

    //time tracking is standard for all opmodes
    ElapsedTime runtime = new ElapsedTime();

    //abstract methods required in instantiated opmodes
    //runs before wait for start - is meant for hardware instantiation and initial robot setup
    abstract public void initRobot();

    //runs right after wait for start - good for start of sensor and vuforia tracking
    abstract public void startComponents();

    //all opmodes have an instruction list to follow that occurs after start
    abstract public void runInstructionList();

    //all opmodes must have a telemetry handling function  - should not include telemetry.update()
    abstract public void dsPrint();

    //runopmode occurs after the init button is pressed on the drivers station
    @Override
    public void runOpMode() throws InterruptedException {
        //abstract functions must be declared for runopmode to function
        initRobot();
        waitForStart();
        startComponents();
        runInstructionList();
    }


}
