package net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.heinousgames.game.spreadeaglecrosstheblock.SpreadEagles;

/**
 * Created by Steve on 12/21/2016
 */

public class CastleWallActor extends CastleTargetActor {

    public CastleWallActor(float posX, float posY) {
        super(posX, posY);

        regularRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_065.png")));
        hitRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_065_hit.png")));
        destroyedRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_063.png")));

        hitAnimation = new Animation(0.075f, regularRegion, hitRegion);
        rectangle.width = regularRegion.getRegionWidth() / 70f;
        rectangle.height = regularRegion.getRegionHeight() / 70f;

        hits = 5;
    }
}
