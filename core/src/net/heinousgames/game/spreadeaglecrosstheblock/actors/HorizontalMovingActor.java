package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 2/21/2016
 */
public class HorizontalMovingActor extends GenericActor {

    private boolean goingLeftAtStart, movingLeft;

    HorizontalMovingActor(float startX, float startY, float speed, int points, boolean isLooping, boolean shouldFlip) {
        super(startX, startY, speed, points, isLooping, shouldFlip);
    }

    public HorizontalMovingActor(float startX, float startY, float endX, float speed, int points,
                                 boolean isLooping, boolean shouldFlip, Texture animFrame1,
                                 Texture animFrame2, Texture deadFrame) {
        super(startX, startY, speed, points, isLooping, shouldFlip);
        this.endX = endX;
        if (startX < endX) {
            movingLeft = false;
            goingLeftAtStart = false;
        } else {
            movingLeft = true;
            goingLeftAtStart = true;
        }

        deadTextureRegion = new TextureRegion(deadFrame);
        TextureRegion frame1 = new TextureRegion(animFrame1);
        TextureRegion frame2 = new TextureRegion(animFrame2);
        if (movingLeft) {
            frame1.flip(true, false);
            frame2.flip(true, false);
        }
        textureRegions = new TextureRegion[] {frame1, frame2};
        rectangle.width = frame1.getRegionWidth() / 70f;
        rectangle.height = frame1.getRegionHeight() / 70f;
        animation = new Animation<TextureRegion>(0.075f, textureRegions);
        isAnimated = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (isLooping) {

            if (movingLeft) {
                rectangle.x -= (speed * Gdx.graphics.getDeltaTime());
            } else {
                rectangle.x += (speed * Gdx.graphics.getDeltaTime());
            }

            if (!goingLeftAtStart) {
                if (rectangle.x <= startX) {
                    rectangle.x = startX;
                    if (shouldFlip)
                        flipTexture(true, false);
                    movingLeft = false;
                } else if (rectangle.x >= endX) {
                    rectangle.x = endX;
                    if (shouldFlip)
                        flipTexture(true, false);
                    movingLeft = true;
                }
            } else {
                if (rectangle.x <= endX) {
                    rectangle.x = endX;
                    if (shouldFlip)
                        flipTexture(true, false);
                    movingLeft = false;
                } else if (rectangle.x >= startX) {
                    rectangle.x = startX;
                    if (shouldFlip)
                        flipTexture(true, false);
                    movingLeft = true;
                }
            }
        } else {
            if (goingLeftAtStart) {
                if (movingLeft) {
                    rectangle.x -= (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.x <= endX) {
                        rectangle.x = endX;
                        isAnimated = false;
                    }
                } else {
                    rectangle.x += (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.x >= endX) {
                        rectangle.x = endX;
                        isAnimated = false;
                    }
                }
            } else {
                if (movingLeft) {
                    rectangle.x -= (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.x <= startX) {
                        rectangle.x = startX;
                        isAnimated = false;
                    }
                } else {
                    rectangle.x += (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.x >= startX) {
                        rectangle.x = startX;
                        isAnimated = false;
                    }
                }
            }
        }
    }
}
