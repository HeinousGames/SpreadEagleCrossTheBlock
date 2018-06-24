package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 3/3/2016
 */
public class CloudActor extends Actor {

    private Texture texture;
    public float speed;

    public CloudActor(Texture texture, float xPos, float yPos, float width) {
        this.speed = 1.9f;
        this.texture = texture;
        setPosition(xPos, yPos);
        setSize(width, 3.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), 3.5f);

        // 0.05 places behind building
        setX(getX() + (speed * Gdx.graphics.getDeltaTime()));

        if (getX() >= 20) {
            setX(-6);
        }
    }
}
