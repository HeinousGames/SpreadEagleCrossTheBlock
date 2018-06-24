package net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 12/21/2016
 */
public class CastleWallActor extends CastleTargetActor {

    public CastleWallActor(float posX, float posY, Texture crossHair, Texture one, Texture two,
                           Texture three, Texture four,TextureRegion regularRegion,
                           TextureRegion hitRegion, TextureRegion destroyedRegion) {
        super(posX, posY, crossHair, one, two, three, four,regularRegion, destroyedRegion);
        hitAnimation = new Animation<TextureRegion>(0.075f, regularRegion, hitRegion);
        hits = 5;
    }
}
