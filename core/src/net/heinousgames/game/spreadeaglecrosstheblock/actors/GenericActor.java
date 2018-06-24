package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 2/22/2016
 */
public class GenericActor extends Actor {

    private float stateTime;
    public float startX, startY, endX, speed;
    public int points;
    public Rectangle rectangle;
    public TextureRegion deadTextureRegion;
    boolean isAnimated, isLooping, shouldFlip;
    Animation<TextureRegion> animation;
    TextureRegion textureRegion;
    TextureRegion[] textureRegions;

    GenericActor(TextureRegion deadTextureRegion, float startX, float startY) {
        this(startX, startY);
        this.textureRegion = deadTextureRegion;
        rectangle.width = deadTextureRegion.getRegionWidth() / 70f;
        rectangle.height = deadTextureRegion.getRegionHeight() / 70f;
        isAnimated = false;
    }

    GenericActor(float startX, float startY, float speed, int points, boolean isLooping, boolean shouldFlip) {
        this(startX, startY);
        this.speed = speed;
        this.isLooping = isLooping;
        this.shouldFlip = shouldFlip;
        this.points = points;
    }

    private GenericActor(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;

        rectangle = new Rectangle();
        rectangle.x = startX;
        rectangle.y = startY;
    }

    void flipTexture(boolean horizontal, boolean vertical) {
        if (isAnimated) {
            for (TextureRegion tr : textureRegions) {
                tr.flip(horizontal, vertical);
            }
        } else {
            textureRegion.flip(horizontal, vertical);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isAnimated) {
            stateTime += Gdx.graphics.getDeltaTime();
            TextureRegion currentAnimationFrame = animation.getKeyFrame(stateTime, true);

            batch.draw(currentAnimationFrame, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        } else {
            batch.draw(textureRegion, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
}
