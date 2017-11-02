package org.firstinspires.ftc.teamcode.production.copmodes.brennbot.tele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.production.copmodes.brennbot.BrennMode;

/**
 * Created by asowd on 10/28/2017.
 */
@TeleOp(name = "MainTele")
@Disabled
public class MainTele extends BrennMode {
    @Override
    public void runInstructions() {
        while (opModeIsActive()) {

            robot.dt.driveByDM(
                    -gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    -gamepad1.right_stick_y,
                    gamepad1.right_stick_x
            );

            if (gamepad1.a) {
                robot.leftJewel.engageArm();
                robot.rightJewel.engageArm();
            } else {
                robot.leftJewel.retractArm();
                robot.rightJewel.retractArm();
            }

            if (gamepad1.x)
                robot.retractGrabbers();
            else
                robot.engageGrabbers();

            telemetry.addData("vumark", getVuMark());
            robot.mWinch.setPower(gamepad1.right_trigger - gamepad1.left_trigger);

            telemetry.update();
        }
    }
}
