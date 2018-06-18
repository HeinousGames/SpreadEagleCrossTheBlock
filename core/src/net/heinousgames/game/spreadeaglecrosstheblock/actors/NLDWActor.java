package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Steve on 11/19/2016
 */
public class NLDWActor extends HorizontalMovingActor {

    public NLDWActor(float startX, float startY, float endX, float speed, int points, boolean isLooping,
                     boolean shouldFlip, Texture animFrame1, Texture animFrame2, Texture deadFrame) {
        super(startX, startY, endX, speed, points, isLooping, shouldFlip, animFrame1, animFrame2, deadFrame);
        this.endX = endX;
        rectangle.width = 1;
        rectangle.height = 1;
        isAnimated = true;
    }
}
