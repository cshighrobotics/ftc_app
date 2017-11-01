package org.firstinspires.ftc.teamcode.production.copmodes.brunnbot.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.production.copmodes.brunnbot.BrennMode;

/**
 * Created by asowd on 10/28/2017.
 */
@TeleOp(name = "MainTele")
public class mainTele extends BrennMode {
    @Override
    public void runInstructions() {
        while (opModeIsActive()){

//            robot.dt.driveByDM(
//                    -gamepad1.left_stick_y,
//                    gamepad1.left_stick_x,
//                    -gamepad1.right_stick_y,
//                    gamepad1.right_stick_x
//            );

            if (gamepad1.a) {
                robot.leftJewel.engageArm();
                robot.rightJewel.engageArm();
            }
            else {
                robot.leftJewel.retractArm();
                robot.rightJewel.retractArm();
            }

            if (gamepad1.x)
                robot.retractGrabbers();
            else
                robot.engageGrabbers();

            robot.mWinch.setPower(gamepad1.right_trigger - gamepad1.left_trigger);

            telemetry.addData("Left color", robot.cLJ.blue() + " " + robot.cLJ.red());
            telemetry.addData("Right color", robot.cRJ.blue() + " " + robot.cRJ.red());
            telemetry.addData("Stone color", robot.cBP.blue() + " " + robot.cBP.red());
            telemetry.update();

        }
    }
}
