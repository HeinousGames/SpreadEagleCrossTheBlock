package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 1/31/2016
 */
public class RideActor extends Actor {

    private Texture texture = new Texture(Gdx.files.internal("gfx/stefan_pixel.png"));

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, 0, 0, 20, 5.5f);
    }

}
