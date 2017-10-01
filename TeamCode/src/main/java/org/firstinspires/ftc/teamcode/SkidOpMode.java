package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by asowd on 10/1/2017.
 */

public abstract class SkidOpMode extends CustomOpMode {
    SkidBot robot;

    @Override
    void dsPrint() {

    }

    @Override
    void initRobot() {
        robot = new SkidBot(
                hardwareMap.get(DcMotor.class, ""),
                hardwareMap.get(DcMotor.class, ""),
                hardwareMap.get(DcMotor.class, ""),
                hardwareMap.get(Servo.class, ""),
                hardwareMap.get(Servo.class, ""),
                hardwareMap.get(Servo.class, "")
        );
        robot.init();
    }
}
