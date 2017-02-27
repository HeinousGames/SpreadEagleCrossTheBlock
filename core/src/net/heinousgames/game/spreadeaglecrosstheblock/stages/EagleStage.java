package net.heinousgames.game.spreadeaglecrosstheblock.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.EagleActor;

/**
 * Created by Steve on 2/3/2016
 */
public class EagleStage extends Stage {

    private EagleActor[] eagleArr;
    private int eagleAmt;

    public EagleStage(ScreenViewport viewport) {
        super(viewport);

        eagleArr = new EagleActor[20];
        eagleAmt = 0;
    }

    public void setNewEagle(float startX, float startY, float destX, float destY) {
        if (eagleAmt < 5) {
            eagleArr[eagleAmt] = new EagleActor(startX, startY, destX, destY, false);
            addActor(eagleArr[eagleAmt]);
            eagleAmt++;
        }
    }

    public void setNewPoweredEagle(float startX, float startY, float destX, float destY) {
        if (eagleAmt < 20-5) {
            eagleArr[eagleAmt] = new EagleActor(startX, startY, destX, destY, true);
            addActor(eagleArr[eagleAmt]);
            eagleAmt++;
            eagleArr[eagleAmt] = new EagleActor(startX, startY, destX-2, destY, true);
            addActor(eagleArr[eagleAmt]);
            eagleAmt++;
            eagleArr[eagleAmt] = new EagleActor(startX, startY, destX+2, destY, true);
            addActor(eagleArr[eagleAmt]);
            eagleAmt++;
            eagleArr[eagleAmt] = new EagleActor(startX, startY, destX, destY+2, true);
            addActor(eagleArr[eagleAmt]);
            eagleAmt++;
            eagleArr[eagleAmt] = new EagleActor(startX, startY, destX, destY-2, true);
            addActor(eagleArr[eagleAmt]);
            eagleAmt++;
        }

    }

//    public void setPoweredEagles() {
//        if (eagleAmt == 0) {
//            int x = -1;
//            for (int i = eagleAmt; i < 10; i++) {
//                eagleArr[eagleAmt] = new EagleActor(9.5f, 0, x, 12, 35);
//                addActor(eagleArr[eagleAmt]);
//                eagleAmt++;
//                x += 2.5f;
//            }
//        }
//    }

//    public void removeAllEagles() {
//        for (int i = 0; i < eagleAmt; i++) {
//            eagleArr[i].remove();
//            eagleArr[i] = eagleArr[eagleAmt-1];
//            eagleAmt--;
//        }
//    }

//    public void removeEagle(EagleActor ea) {
//        for (int i = 0; i < eagleAmt; i++) {
//            if (eagleArr[i].equals(ea)) {
//                eagleArr[i].remove();
//                eagleArr[i] = eagleArr[eagleAmt-1];
//                eagleAmt--;
//            }
//        }
//    }

    public void removeDeadEagles() {
        for (int i = 0; i < eagleAmt; i++) {
            if (/*eagleArr[i].isDeadByCollision() ||*/ eagleArr[i].isDoneMoving()) {
                //                eagleArr[i].setVisible(false);
                eagleArr[i].remove();
                eagleArr[i] = eagleArr[eagleAmt-1];
                eagleAmt--;
            }
        }
    }
}
