package org.firstinspires.ftc.teamcode.production.copmodes.brennbot;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.production.copmodes.COpMode;
import org.firstinspires.ftc.teamcode.production.robots.BrennBot;

import static org.firstinspires.ftc.teamcode.lib.VuforiaInfo.MAIN_KEY;

/**
 * Created by asowd on 10/28/2017.
 */

public abstract class BrennMode extends COpMode {

    protected BrennBot robot = new BrennBot();

    private VuforiaTrackable relicTemplate;

    @Override
    public void initOpmode() {
        robot.init(hardwareMap);

        //vuforia

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = MAIN_KEY;

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);


        VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();

    }

    public RelicRecoveryVuMark getVuMark(){
        return RelicRecoveryVuMark.from(relicTemplate);

    }


    @Override
    public void stopRobot() {
        robot.stopRobot();
    }


    public void knockJewelFromStart(){
    }

    public void loadStartGlyph(){

    }

    public void scoreSideFromKnock(){

    }

    public void scoreBackFromKnock(){

    }


}
