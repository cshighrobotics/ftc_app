package org.firstinspires.ftc.teamcode.production.robocomponents;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by asowd on 11/1/2017.
 */

public class VFRelic extends RoboComponent {

    private VuforiaLocalizer vuforia;
    private VuforiaLocalizer.Parameters parameters;
    private VuforiaTrackables relicTrackables;
    private VuforiaTrackable relicTemplate;
    private int cameraMonitorViewId;

    @Override
    public void init(HardwareMap hMap) {
        //look to hardware for camera settings
        componentMap = hMap;
        cameraMonitorViewId = componentMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", componentMap.appContext.getPackageName());


        //set up parameters for running vuforia tracker
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "ATsODcD/////AAAAAVw2lR...d45oGpdljdOh5LuFB9nDNfckoxb8COxKSFX";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;


        //set tracker to look for relic tracks(0) or the collumns signs
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTrackables.activate();
    }

    //return the collumn the camera is seeing
    public RelicRecoveryVuMark getRelic(){
        return RelicRecoveryVuMark.from(relicTemplate);
    }
}
