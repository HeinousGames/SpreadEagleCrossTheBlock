package net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 12/5/2016
 */
public class CastleFlagMiddleActor extends CastleTargetActor {

    public CastleFlagMiddleActor(float posX, float posY) {
        super(posX, posY);
        regularRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_046.png")));
        hitRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_046_hit.png")));
        destroyedRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_118.png")));

        hitAnimation = new Animation<TextureRegion>(0.075f, regularRegion, hitRegion);
        rectangle.width = regularRegion.getRegionWidth() / 70f;
        rectangle.height = regularRegion.getRegionHeight() / 70f;

        hits = 1;
    }
}
