package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 2/21/2016
 */
public class VerticalMovingActor extends GenericActor {

    public float endY;
    private boolean movingDown;
    private boolean goingDownAtStart;

    public VerticalMovingActor(TextureRegion[] textureRegions, float startX, float startY,
                               float endY, boolean isLooping) {
        super(textureRegions, startX, startY);
        this.endY = endY;
        this.isLooping = isLooping;
        if (startY > endY) {
            movingDown = true;
            goingDownAtStart = true;
        } else {
            movingDown = false;
            goingDownAtStart = false;
        }
    }

    public VerticalMovingActor(TextureRegion textureRegion, float startX, float startY,
                               float endY, boolean isLooping) {
        super(textureRegion, startX, startY);
        this.endY = endY;
        this.isLooping = isLooping;

        if (startY > endY) {
            movingDown = true;
            goingDownAtStart = true;
        } else {
            movingDown = false;
            goingDownAtStart = false;
        }
    }

    VerticalMovingActor(float startX, float startY, float speed, int points, boolean isLooping, boolean shouldFlip) {
        super(startX, startY, speed, points, isLooping, shouldFlip);
        if (movingDown) {
            textureRegion.flip(false, true);
        }
    }

    public VerticalMovingActor(float startX, float startY, float endY, float speed, int points,
                               boolean isLooping, boolean shouldFlip, Texture animFrame1,
                               Texture animFrame2, Texture deadFrame) {
        super(startX, startY, speed, points, isLooping, shouldFlip);
        this.endY = endY;
        if (startY > endY) {
            movingDown = true;
            goingDownAtStart = true;
        } else {
            movingDown = false;
            goingDownAtStart = false;
        }

        deadTextureRegion = new TextureRegion(deadFrame);
        TextureRegion frame1 = new TextureRegion(animFrame1);
        TextureRegion frame2 = new TextureRegion(animFrame2);
//        if (movingDown) {
//            frame1.flip(false, false);
//            frame2.flip(false, false);
//        }
        textureRegions = new TextureRegion[] {frame1, frame2};
        rectangle.width = frame1.getRegionWidth() / 70f;
        rectangle.height = frame1.getRegionHeight() / 70f;
        animation = new Animation<TextureRegion >(0.075f, textureRegions);
        isAnimated = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);

        if (isLooping) {

            if (movingDown) {
                rectangle.y -= (speed * Gdx.graphics.getDeltaTime());
            } else {
                rectangle.y += (speed * Gdx.graphics.getDeltaTime());
            }

            if (!goingDownAtStart) {
                if (rectangle.y <= startY) {
                    rectangle.y = startY;
                    if (shouldFlip)
                        flipTexture(false, true);
                    movingDown = false;
                } else if (rectangle.y >= endY) {
                    rectangle.y = endY;
                    if (shouldFlip)
                        flipTexture(false, true);
                    movingDown = true;
                }
            } else {
                if (rectangle.y <= endY) {
                    rectangle.y = endY;
                    if (shouldFlip)
                        flipTexture(false, true);
                    movingDown = false;
                } else if (rectangle.y >= startY) {
                    rectangle.y = startY;
                    if (shouldFlip)
                        flipTexture(false, true);
                    movingDown = true;
                }
            }
        } else {
            if (goingDownAtStart) {
                if (movingDown) {
                    rectangle.y -= (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.y <= endY) {
                        rectangle.y = endY;
                        isAnimated = false;
                    }
                } else {
                    rectangle.y += (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.y >= endY) {
                        rectangle.y = endY;
                        isAnimated = false;
                    }
                }
            } else {
                if (movingDown) {
                    rectangle.y -= (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.y <= startY) {
                        rectangle.y = startY;
                        isAnimated = false;
                    }
                } else {
                    rectangle.y += (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.y >= startY) {
                        rectangle.y = startY;
                        isAnimated = false;
                    }
                }
            }
        }
    }
}
