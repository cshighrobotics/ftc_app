package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.bots.MixBot;
import org.firstinspires.ftc.teamcode.components.FourWheelDT;

/**
 * Created by asowd on 11/27/2017.
 * <p>
 * teleop for mix bot
 */

public class MixTeleop extends OpMode {

    private MixBot robot;

    @Override
    public void init() {
        robot = new MixBot();
        robot.init(hardwareMap);
        robot.dt.setDriveMode(FourWheelDT.DriveMode.TRIG);
        robot.stopRobot();
    }

    @Override
    public void start() {
        robot.stopRobot();
    }

    @Override
    public void loop() {

        if (gamepad1.a)
            robot.jewelArm.stepUp();
        if (gamepad1.b)
            robot.jewelArm.stepDown();

        robot.dt.driveByDM(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                -gamepad1.right_stick_y,
                gamepad1.right_stick_x
        );

        telemetry.clear();
        telemetry.addLine(robot.debugString());
        telemetry.update();
    }

    @Override
    public void stop() {
        robot.stopRobot();
    }
}
