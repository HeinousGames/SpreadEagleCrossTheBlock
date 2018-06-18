package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 2/3/2016
 */
public class EagleActor extends Actor {

    private float EAGLE_SPEED;

    public float destinationX, destinationY, startX, startY;
    private boolean isPowerful;
    private Texture texture;

    public EagleActor(float startX, float startY, float destX, float destY, boolean powerful) {
        this.startX = startX;
        this.startY = startY;
        destinationX = destX;
        destinationY = destY;

        isPowerful = powerful;

        setX(startX);
        setY(startY);

        texture = new Texture(Gdx.files.internal("gfx/eagle.png"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (destinationX < startX) {
            batch.draw(texture, getX(), getY(), 0.75f, 0.75f, 0, 0, 128, 64, true, false);
        } else {
            batch.draw(texture, getX(), getY(), 0.75f, 0.75f);
        }

        if (isPowerful) {
            EAGLE_SPEED = 1.2f;
            float distanceX = Math.max(startX, destinationX) - Math.min(startX, destinationX);
            float distanceY = Math.max(startY, destinationY) - Math.min(startY, destinationY);
            float speedX = distanceX/EAGLE_SPEED;
            float speedY = distanceY/EAGLE_SPEED;

            if (needsToMoveX()) {
                if (wasGoingRight()) {
                    setX(getX() + (speedX * Gdx.graphics.getDeltaTime()));
                } else if (wasGoingLeft()) {
                    setX(getX() - (speedX * Gdx.graphics.getDeltaTime()));
                }
            }

            if (needsToMoveY()) {
                setY(getY() + (speedY * Gdx.graphics.getDeltaTime()));
            }
        } else {
            EAGLE_SPEED = 9;
            float xDistance = Math.max(startX, destinationX) - Math.min(startX, destinationX);
            float yDistance = Math.max(startY, destinationY) - Math.min(startY, destinationY);
            float hypotenuse = (float) Math.sqrt(xDistance * xDistance + yDistance * yDistance);

            if (startX > destinationX) {
                setX(getX() - (EAGLE_SPEED * (xDistance / hypotenuse) * Gdx.graphics.getDeltaTime()));
            } else if (startX < destinationX) {
                setX(getX() + (EAGLE_SPEED * (xDistance / hypotenuse) * Gdx.graphics.getDeltaTime()));
            }

            setY(getY() + (EAGLE_SPEED * (yDistance / hypotenuse) * Gdx.graphics.getDeltaTime()));
        }
    }

    public boolean isDoneMoving() {
        return !needsToMoveX() && !needsToMoveY();
    }

    private boolean needsToMoveX() {
        if (wasGoingLeft()) {
            return !(getX() <= destinationX);
        } else {
            return wasGoingRight() && !(getX() >= destinationX);
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
