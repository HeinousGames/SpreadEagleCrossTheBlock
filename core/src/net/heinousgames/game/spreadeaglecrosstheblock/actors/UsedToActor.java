package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 3/1/2016
 */
public class UsedToActor extends Actor {

    private Texture texture;

    public UsedToActor(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, 0, 0, 1334, 750);
    }
}
