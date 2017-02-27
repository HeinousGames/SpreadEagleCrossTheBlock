package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Steve on 3/3/2016
 */
public class CloudActor extends Image {

    private Texture texture;
    private float CLOUD_SPEED, xPos, yPos, width;

    public CloudActor(Texture texture, float speed, float xPos, float yPos, float width) {
        super(texture);
        CLOUD_SPEED = speed;
//        this.xPos = xPos;
//        this.yPos = yPos;
//        this.width = width;
//        texture = new Texture(Gdx.files.internal(path));
        setPosition(xPos, yPos);
        setSize(width, 3.5f);

        MoveToAction action = new MoveToAction();
        action.setDuration(5);
        action.setPosition(20, yPos);
//        action.setInterpolation(Interpolation.linear);

        RepeatAction infiniteLoop = new RepeatAction();
        infiniteLoop.setCount(RepeatAction.FOREVER);
        infiniteLoop.setAction(action);

        addAction(sequence(action, new Action() {
            public boolean act (float delta) {
                // This runs when someAction is done.
                setX(-20);
                return true;
            }
        }, infiniteLoop));
    }

//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        batch.draw(texture, xPos, yPos, width, 3.5f);

//        // 0.05 places behind building
//        setX(getX() + (CLOUD_SPEED * Gdx.graphics.getDeltaTime()));
//
//        if (getX() >= 20) {
//            setX(-6);
//        }
//    }
}
