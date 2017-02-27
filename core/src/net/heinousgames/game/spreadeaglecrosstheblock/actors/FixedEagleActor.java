package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 11/28/2016
 */

public class FixedEagleActor extends Actor {

    private final float EAGLE_SPEED;

    public float destinationX, destinationY, startX, startY;
    private Texture texture;

    public FixedEagleActor(float startX, float startY, float destX, float destY) {
        this.startX = startX;
        this.startY = startY;
        destinationX = destX;
        destinationY = destY;

        setX(startX);
        setY(startY);

        texture = new Texture(Gdx.files.internal("gfx/eagle.png"));

        EAGLE_SPEED = 7;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (destinationX < startX) {
            batch.draw(texture, getX(), getY(), 0.75f, 0.75f, 0, 0, 128, 64, true, false);
        } else {
            batch.draw(texture, getX(), getY(), 0.75f, 0.75f);
        }

        float xDistance = Math.max(startX, destinationX) - Math.min(startX, destinationX);
        float yDistance = Math.max(startY, destinationY) - Math.min(startY, destinationY);
        float hypotenuse = (float)Math.sqrt(xDistance * xDistance + yDistance * yDistance);

        if (startX > destinationX){
            setX(getX() - (EAGLE_SPEED * (xDistance/hypotenuse)* Gdx.graphics.getDeltaTime()));
        } else if (startX < destinationX){
            setX(getX() + (EAGLE_SPEED * (xDistance/hypotenuse) * Gdx.graphics.getDeltaTime()));
        }

        setY(getY() + (EAGLE_SPEED * (yDistance/hypotenuse) * Gdx.graphics.getDeltaTime()));
    }

    public boolean isDoneMoving() {
        return !needsToMoveX() && !needsToMoveY();
    }

    private boolean needsToMoveX() {
        if (wasGoingLeft()) {
            return !(getX() <= destinationX);
        } else if (wasGoingRight()) {
            return !(getX() >= destinationX);
        } else {
            return false;
        }
    }

    private boolean needsToMoveY() {
        return getY() <= destinationY;
    }

    private boolean wasGoingLeft() {
        return startX > destinationX;
    }

    private boolean wasGoingRight() {
        return startX < destinationX;
    }
}
