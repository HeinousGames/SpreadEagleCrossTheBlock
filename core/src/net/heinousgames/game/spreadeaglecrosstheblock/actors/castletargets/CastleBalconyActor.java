package net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.heinousgames.game.spreadeaglecrosstheblock.SpreadEagles;

/**
 * Created by Steve on 12/10/2016
 */

public class CastleBalconyActor extends CastleTargetActor {

    public CastleBalconyActor(float posX, float posY, boolean isFacingLeft) {
        super(posX, posY);

        regularRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_017.png")));
        hitRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_017_hit.png")));
        if (isFacingLeft) {
            regularRegion.flip(true, false);
            hitRegion.flip(true, false);
        }

        hitAnimation = new Animation(0.075f, regularRegion, hitRegion);
        rectangle.width = regularRegion.getRegionWidth() / 70f;
        rectangle.height = regularRegion.getRegionHeight() / 70f;

        hits = 5;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (!isDestroyed) {
            if (isHit) {
                stateTime += Gdx.graphics.getDeltaTime();
                TextureRegion currentAnimationFrame = hitAnimation.getKeyFrame(stateTime, true);

                batch.draw(currentAnimationFrame, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            } else {
                batch.draw(regularRegion, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }

    }
}
