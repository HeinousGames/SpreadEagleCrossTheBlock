package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 1/31/2016
 */
public class RideActor extends Actor {

    private Texture texture;

    public RideActor(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, 0, 0, 20, 5.5f);
    }

}
