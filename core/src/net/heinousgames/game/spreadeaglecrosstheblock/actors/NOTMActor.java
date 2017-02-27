package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 9/29/2016
 */

public class NOTMActor extends VerticalMovingActor {

    public NOTMActor(float startX, float startY, float endY, float speed, int points,
                     boolean isLooping, boolean shouldFlip) {
        super(startX, startY, speed, points, isLooping, shouldFlip);
        this.endY = endY;
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/notm.png")));
        deadTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/notm.png")));
        rectangle.width = 1;
        rectangle.height = 1;
        isAnimated = false;
    }
}
