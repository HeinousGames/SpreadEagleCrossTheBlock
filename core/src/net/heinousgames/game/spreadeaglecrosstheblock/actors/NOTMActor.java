package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 9/29/2016
 */
public class NOTMActor extends VerticalMovingActor {

    public NOTMActor(float startX, float startY, float endY, float speed, int points,
                     boolean isLooping, boolean shouldFlip, Texture texture) {
        super(startX, startY, speed, points, isLooping, shouldFlip);
        this.endY = endY;
        textureRegion = new TextureRegion(texture);
        deadTextureRegion = new TextureRegion(texture);
        rectangle.width = 1;
        rectangle.height = 1;
        isAnimated = false;
    }
}
