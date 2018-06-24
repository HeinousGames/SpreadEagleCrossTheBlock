package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 10/2/2016
 */
public class JennyDeathActor extends HorizontalMovingActor {

    public JennyDeathActor(float startX, float startY, float endX, float speed, int points,
                           boolean isLooping, boolean shouldFlip, Texture texture) {
        super(startX, startY, speed, points, isLooping, shouldFlip);
        this.endX = endX;
        textureRegion = new TextureRegion(texture);
        deadTextureRegion = new TextureRegion(texture);
        rectangle.width = 1;
        rectangle.height = 1;
        isAnimated = false;
    }
}
