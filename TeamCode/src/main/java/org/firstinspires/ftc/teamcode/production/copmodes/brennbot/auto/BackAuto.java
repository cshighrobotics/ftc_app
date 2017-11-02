package org.firstinspires.ftc.teamcode.production.copmodes.brennbot.auto;

import org.firstinspires.ftc.teamcode.production.copmodes.brennbot.BrennMode;

/**
 * Created by asowd on 11/1/2017.
 */

public class BackAuto extends BrennMode {
    @Override
    public void runInstructions() {
        loadStartGlyph();
        if(opModeIsActive())
            knockJewelFromStart();
        if(opModeIsActive())
            scoreBackFromKnock();
    }
}
