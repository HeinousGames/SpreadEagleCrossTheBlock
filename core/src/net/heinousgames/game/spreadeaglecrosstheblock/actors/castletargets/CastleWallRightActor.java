package net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 12/7/2016
 */
public class CastleWallRightActor extends CastleTargetActor {

    public CastleWallRightActor(float posX, float posY, Texture crossHair, Texture one,
                                Texture two, Texture three, Texture four, TextureRegion regularRegion,
                                TextureRegion hitRegion, TextureRegion destroyedRegion, boolean isFlipped) {
        super(posX, posY, crossHair, one, two, three, four, regularRegion, destroyedRegion);
        hitAnimation = new Animation<TextureRegion>(0.075f, regularRegion, hitRegion);
        hits = 5;
        if (isFlipped)
            this.destroyedRegion.flip(false, true);
    }
}
