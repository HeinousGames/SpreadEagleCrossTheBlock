package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by Steve on 3/3/2016
 */
public class CloudActor extends Image {

    public CloudActor(Texture texture, float xPos, float yPos, float width) {
        super(texture);
        setPosition(xPos, yPos);
        setSize(width, 3.5f);

        MoveToAction action = new MoveToAction();
        action.setDuration(5);
        action.setPosition(20, yPos);

        RepeatAction infiniteLoop = new RepeatAction();
        infiniteLoop.setCount(RepeatAction.FOREVER);
        infiniteLoop.setAction(action);

        addAction(Actions.sequence(action, new Action() {
            public boolean act (float delta) {
                // This runs when someAction is done.
                setX(-20);
                return true;
            }
        }, infiniteLoop));
    }
}
