package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 2/12/2016
 */
public class FallingActor extends Actor {

    private final float FALLING_SPEED = 4.3f;

    private TextureRegion textureRegion;

    public FallingActor(float startX, float startY, TextureRegion textureRegion, boolean flip) {
        setX(startX);
        setY(startY);
        this.textureRegion = textureRegion;
        if (flip)
            textureRegion.flip(false, true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), textureRegion.getRegionWidth() / 70f,
                textureRegion.getRegionHeight() / 70f);
        if (getY() > 2.25f) {
            setY(getY() - (FALLING_SPEED * Gdx.graphics.getDeltaTime()));
        }
    }
}
