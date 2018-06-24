package net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 12/5/2016
 */
public class CastleFlagTopActor extends CastleTargetActor {

    public CastleFlagTopActor(float posX, float posY, Texture crossHair, TextureRegion regularRegion,
                              TextureRegion hitRegion, TextureRegion destroyedRegion) {
        super(posX, posY, crossHair, regularRegion, destroyedRegion);
        hitAnimation = new Animation<TextureRegion>(0.075f, regularRegion, hitRegion);
        hits = 1;
    }
}
