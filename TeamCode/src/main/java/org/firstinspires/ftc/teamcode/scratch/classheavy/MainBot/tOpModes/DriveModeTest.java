package org.firstinspires.ftc.teamcode.scratch.classheavy.MainBot.tOpModes;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.scratch.classheavy.MainBot.MainBot;
import org.firstinspires.ftc.teamcode.scratch.classheavy.MainBot.MainMode;

/**
 * Created by asowd on 10/5/2017.
 */
@Disabled
@TeleOp(name = "DriveModeTest")
public class DriveModeTest extends MainMode {

    @Override
    public void runInstructionList() {
        robot.setDriveMode(MainBot.DriveMode.SKID);
        while(opModeIsActive()){
            if (gamepad1.x)
                robot.setDriveMode(MainBot.DriveMode.SKID);
            if (gamepad1.y)
                robot.setDriveMode(MainBot.DriveMode.POV);
            if (gamepad1.b)
                robot.setDriveMode(MainBot.DriveMode.NOTRIG);
            if (gamepad1.a)
                robot.setDriveMode(MainBot.DriveMode.TRIG);

            robot.driveByDM(
                    -gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    -gamepad1.right_stick_y,
                    gamepad1.right_stick_x
            );

            telemetry.update();
        }
    }
}
