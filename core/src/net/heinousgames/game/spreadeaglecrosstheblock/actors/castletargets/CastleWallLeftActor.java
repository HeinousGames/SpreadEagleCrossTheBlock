package net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.heinousgames.game.spreadeaglecrosstheblock.SpreadEagles;

/**
 * Created by Steve on 12/7/2016
 */

public class CastleWallLeftActor extends CastleTargetActor {

    public CastleWallLeftActor(float posX, float posY, int style) {
        super(posX, posY);

        regularRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_066.png")));
        hitRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_066_hit.png")));
        if (style == 1 || style == 2) {
            destroyedRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_021_left1.png")));
            if (style == 2) {
                destroyedRegion.flip(false, true);
            }
        } else {
            destroyedRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/tiles/medieval/medievalTile_022_left2.png")));
        }

        hitAnimation = new Animation(0.075f, regularRegion, hitRegion);
        rectangle.width = regularRegion.getRegionWidth() / 70f;
        rectangle.height = regularRegion.getRegionHeight() / 70f;

        hits = 5;
    }
}
