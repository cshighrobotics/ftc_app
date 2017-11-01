package org.firstinspires.ftc.teamcode.scratch.classheavy.MainBot;

import org.firstinspires.ftc.teamcode.scratch.classheavy.CustomOpMode;

/**
 * Created by asowd on 10/5/2017.
 */

public abstract class MainMode extends CustomOpMode {
    //creat robot and other important opmode objects
    public MainBot robot = new MainBot();

//
//    RelicRecoveryVuMark vuMark = null;
//    VuforiaTrackables relicTrackables = null;
//    VuforiaTrackable relicTemplate = null;
//

    @Override
    public void initRobot(){
        robot.init(hardwareMap);
        //initialize robot
        robot.stopDriving();
//
//        //initialize vuforia trackables
//        relicTrackables =  robot.vuforia.loadTrackablesFromAsset("RelicVuMark");
//        relicTemplate = relicTrackables.get(0);
        dsPrint();

        telemetry.update();

    }

    @Override
    public void startComponents() {
//
//        //activating and setting listener for trackables
//        relicTrackables.activate();
//        vuMark = RelicRecoveryVuMark.from(relicTemplate);

    }

//    //return collumn identifier for vuforia
//    public String getPictoCol(){
//        return vuMark.toString();
//    }

    @Override
    public void dsPrint() {
        //drive values
        telemetry.addLine("Drive Values");
        telemetry.addData("Drive Mode", robot.getDriveMode());
        telemetry.addData("fl", robot.flPower);
        telemetry.addData("fr", robot.frPower);
        telemetry.addData("rl", robot.rlPower);
        telemetry.addData("rr", robot.rrPower);

        //servo values
        telemetry.addLine("Servo Values");
//
//        //Vuforia data
//        telemetry.addLine("vuforia");
//        telemetry.addData("VuMark", "%s visible", vuMark);
    }

//
//    public void turnToAngle(double ang, double tol){
//        while (opModeIsActive() && robot.getHeading() < ang - tol && robot.getHeading() > ang + tol){
//            robot.driveByPOV(0, 1);
//        }
//        robot.stopDriving();
//    }
//
//    public void driveStraightForTime(double angle, double power, double time){
//        float startAng = robot.getHeading();
//        double desTime = getRuntime() + time;
//        double rFactor = 0;
//        while (getRuntime() < desTime && opModeIsActive()){
//            robot.driveByTrig(power,angle,rFactor);
//            if (robot.getHeading() < startAng){
//                rFactor = 1;
//            }else if (robot.getHeading() > startAng){
//                rFactor = -1;
//            }else{
//                rFactor = 0;
//            }
//        }
//    }
//
//
//    public void driveStraightForDistance(){}
//

}
