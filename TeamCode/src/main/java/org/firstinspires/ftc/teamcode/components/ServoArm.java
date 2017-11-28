package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by asowd on 10/28/2017.
 */

public class ServoArm extends CRoboComponent {

    private Servo arm = null;

    private double ENGAGE_POS, RETRACT_POS, POS_INC;

    private String SERVO_NAME = null;

    public ServoArm(String sName, double ePos, double rPos, double inc) {
        SERVO_NAME = sName;
        ENGAGE_POS = ePos;
        RETRACT_POS = rPos;
        POS_INC = inc;
    }

    @Override
    public void init(HardwareMap hMap) {
        arm = hMap.get(Servo.class, SERVO_NAME);
        retractArm();

        sBuild.append(SERVO_NAME).append("\n");
    }

    @Override
    public String debugString() {
        sBuild.setLength(2);
        sBuild.append("ArmPos: \t").append(arm.getPosition());
        return sBuild.toString();
    }

    private void boundArm() {
        if (arm.getPosition() < RETRACT_POS)
            arm.setPosition(RETRACT_POS);

        if (arm.getPosition() > ENGAGE_POS)
            arm.setPosition(ENGAGE_POS);
    }

    public void stepUp() {
        arm.setPosition(arm.getPosition() + POS_INC);
        boundArm();
    }


    public void stepDown() {
        arm.setPosition(arm.getPosition() - POS_INC);
        boundArm();
    }

    public void engageArm() {
        arm.setPosition(ENGAGE_POS);
        boundArm();
    }

    public void retractArm() {
        arm.setPosition(RETRACT_POS);
        boundArm();
    }

    public void freeSet(double p) {
        arm.setPosition(p);
    }


}
