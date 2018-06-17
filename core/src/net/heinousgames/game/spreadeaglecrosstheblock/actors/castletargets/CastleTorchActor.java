package net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 12/6/2016
 */
public class CastleTorchActor extends CastleTargetActor {

    public CastleTorchActor(float posX, float posY) {
        super(posX, posY);

        regularRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_187.png")));
        hitRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_188.png")));
        destroyedRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_165.png")));

        hitAnimation = new Animation<TextureRegion>(0.15f, regularRegion, hitRegion);
        rectangle.width = regularRegion.getRegionWidth() / 70f;
        rectangle.height = regularRegion.getRegionHeight() / 70f;

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
