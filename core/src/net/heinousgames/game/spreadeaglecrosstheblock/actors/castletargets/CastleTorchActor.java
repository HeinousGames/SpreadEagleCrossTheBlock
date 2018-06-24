package net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 12/6/2016
 */
public class CastleTorchActor extends CastleTargetActor {

    public CastleTorchActor(float posX, float posY, TextureRegion regularRegion,
                            TextureRegion hitRegion, TextureRegion destroyedRegion) {
        super(posX, posY, regularRegion, destroyedRegion);
        hitAnimation = new Animation<TextureRegion>(0.15f, regularRegion, hitRegion);
        hits = 1;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!isDestroyed) {
            stateTime += Gdx.graphics.getDeltaTime();
            TextureRegion currentAnimationFrame = hitAnimation.getKeyFrame(stateTime, true);
            batch.draw(currentAnimationFrame, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        } else {
            batch.draw(destroyedRegion, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
}
