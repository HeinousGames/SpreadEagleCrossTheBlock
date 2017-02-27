package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 2/5/2016
 */
public class MoonActor extends Actor {

    private Texture texture;

    public void setMOON_SPEED(float MOON_SPEED) {
        this.MOON_SPEED = MOON_SPEED;
    }

    private float MOON_SPEED = 0.5f;

    public MoonActor() {
        texture = new Texture(Gdx.files.internal("gfx/moon.png"));
        setX(-3);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), 8.2f, 2.5f, 2.5f);

        // 0.05 places behind building
        setX(getX() + (MOON_SPEED * Gdx.graphics.getDeltaTime()));

        if (getX() >= 20) {
            setX(-3);
        }
    }
}
