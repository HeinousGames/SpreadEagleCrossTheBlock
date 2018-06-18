package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 10/2/2016
 */
public class JennyDeathActor extends HorizontalMovingActor {

    public JennyDeathActor(float startX, float startY, float endX, float speed, int points,
                           boolean isLooping, boolean shouldFlip) {
        super(startX, startY, speed, points, isLooping, shouldFlip);
        this.endX = endX;
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/jenny_death.png")));
        deadTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/jenny_death.png")));
        rectangle.width = 1;
        rectangle.height = 1;
        isAnimated = false;
    }
}
