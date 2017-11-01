package org.firstinspires.ftc.teamcode.production.robocomponents;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by asowd on 10/28/2017.
 */

public class ServoArm extends RoboComponent {

    private Servo arm = null;

    private double ENGAGE_POS, RETRACT_POS;

    private String SERVO_NAME = null;

    public ServoArm(String sName, double ePos, double rPos){
        SERVO_NAME = sName;
        ENGAGE_POS = ePos;
        RETRACT_POS = rPos;
    }

    @Override
    public void init(HardwareMap hMap) {
        componentMap = hMap;
        arm = componentMap.get(Servo.class, SERVO_NAME);
        retractArm();
    }

    public void engageArm(){
        arm.setPosition(ENGAGE_POS);
    }

    public void retractArm(){
        arm.setPosition(RETRACT_POS);
    }

    public void freeSet(double p){
        arm.setPosition(p);
    }


}
