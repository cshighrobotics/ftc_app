package org.firstinspires.ftc.teamcode.scratch.classheavy.MainBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.lib.FTCMath;
import org.firstinspires.ftc.teamcode.scratch.classheavy.CustomRobot;

/**
 * Created by asowd on 10/5/2017.
 */


//custom bot that describes the main competition bot added more comments
public class MainBot extends CustomRobot {

    public MainBot() {}

    //motors
    //drive motors for a 4 powered wheel drive train
    DcMotor frontLeft   = null;
    DcMotor frontRight  = null;
    DcMotor rearLeft    = null;
    DcMotor rearRight   = null;

//    BNO055IMU imu = null;
//

    public enum DriveMode{
        SKID,
        POV,
        NOTRIG,
        TRIG
    }

    DriveMode dMode = DriveMode.SKID;


//
//    //Vuforia
//    VuforiaLocalizer vuforia;
//
//    //Servo
//    Servo sJewel = null;
//    Servo sClaw = null;
//    Servo sGlyph = null;
//
//    //servo constants
//    double JEWEL_UP = -1, JEWEL_DOWN = 0;
//    double CLAW_CLOSE, CLAW_OPEN;

    //driving constants
    double MIN_POWER = -1, MAX_POWER = 1, STALL_POWER = 1;
    double CRAWL_POWER = .2, CRUISE_POWER = 1;



    //motor powers for tracking and pass powers method
    //these will be public but the motors will not
    public double
            flPower = 0,
            frPower = 0,
            rlPower = 0,
            rrPower = 0;

    //initializing the hardware components based on config file
    @Override
    public void init(HardwareMap ahwMap) {
        hMap = ahwMap;

        frontLeft   =       hMap.get(DcMotor.class, "mFL");
        frontRight  =       hMap.get(DcMotor.class, "mFR");
        rearLeft    =       hMap.get(DcMotor.class, "mRL");
        rearRight   =       hMap.get(DcMotor.class, "mRR");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);

//
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
//        parameters.loggingEnabled = true;
//        parameters.loggingTag = "IMU";
//        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
//
//        imu = hMap.get(BNO055IMU.class, "imu");
//        imu.initialize(parameters);


//
//        //Vuforia
//        int cameraMonitorViewId = hMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hMap.appContext.getPackageName());
//        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//
//        parameters.vuforiaLicenseKey = "ATsODcD/////AAAAAVw2lR...d45oGpdljdOh5LuFB9nDNfckoxb8COxKSFX";
//
//        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
//        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
//

    }

    //DRIVING FUNCTIONS


    //standardization for driving by power
    public void passPowers(){


        flPower = FTCMath.filterMotorPower(flPower, MIN_POWER, MAX_POWER, STALL_POWER);
        frPower = FTCMath.filterMotorPower(frPower, MIN_POWER, MAX_POWER, STALL_POWER);
        rlPower = FTCMath.filterMotorPower(rlPower, MIN_POWER, MAX_POWER, STALL_POWER);
        rrPower = FTCMath.filterMotorPower(rrPower, MIN_POWER, MAX_POWER, STALL_POWER);

        frontLeft.setPower(flPower);
        frontRight.setPower(frPower);
        rearLeft.setPower(rlPower);
        rearRight.setPower(rrPower);
    }

    //stopping function
    public void stopDriving(){

        //assign appropriate wheel powers
        flPower = 0;
        rlPower = 0;
        frPower = 0;
        rrPower = 0;

        passPowers();
    }

    //skid steer tank controls
    public void driveByTank(double left, double right){

        //assign appropriate wheel powers
        flPower = left;
        rlPower = left;
        frPower = right;
        rrPower = left;

        passPowers();
    }

    //drive by pov controls
    public void driveByPOV(double drive, double turn){
        double left, right, max;
        // Combine drive and turn for blended motion.
        left  = drive + turn;
        right = drive - turn;

        // Normalize the values so neither exceed +/- 1.0
        max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0)
        {
            left /= max;
            right /= max;
        }

        driveByTank(left, right);
    }

    //mecanum controlled no trig example
    public void driveByNoTrig(double moveX, double moveY, double moveR){

        //determine motor powers based on movement values

        flPower     =   Range.clip( -moveX + moveY + moveR,  -1, 1);
        frPower     =   Range.clip( moveX + moveY - moveR,   -1, 1);
        rlPower     =   Range.clip( moveX + moveY + moveR,   -1, 1);
        rrPower     =   Range.clip( -moveX + moveY - moveR,  -1, 1);

        passPowers();

    }

    public void driveByTrig(double power, double angle, double rotation){

        flPower = (power * Math.cos(angle) + rotation);
        frPower = (power * Math.sin(angle) - rotation);
        rlPower = (power * Math.sin(angle) + rotation);
        rrPower = (power * Math.cos(angle) - rotation);

        passPowers();
    }

    public void setDriveMode(DriveMode dm){
        dMode = dm;
    }

    public DriveMode getDriveMode(){
        return dMode;
    }

    public void driveByDM(double ly,double lx,double ry,double rx){
        switch (dMode){
            case SKID:
                driveByTank(ly,ry);
            case POV:
                driveByPOV(ly, rx);
            case NOTRIG:
                driveByNoTrig(ly, lx, rx);
            case TRIG:
                driveByTrig(
                        Math.hypot(lx, ly),
                        Math.atan2(ly, lx) - Math.PI / 4,
                        rx
                );
        }
    }


//
//    //jewel arm controls
//    public void jewelEngage(){
//        sJewel.setPosition(JEWEL_UP);
//    }
//
//    public void jewelRetract(){
//        sJewel.setPosition(JEWEL_DOWN);
//    }
//
//    //claw controls
//    public void openClaw(){
//        sClaw.setPosition(CLAW_OPEN);
//    }
//
//    public void closeClaw(){
//        sClaw.setPosition(CLAW_CLOSE);
//    }
//
//
//
//    public float getHeading(){
//        return imu.getAngularOrientation().firstAngle;
//
//    }


}
