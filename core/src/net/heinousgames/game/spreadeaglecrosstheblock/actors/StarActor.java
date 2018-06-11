package net.heinousgames.game.spreadeaglecrosstheblock.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

/**
 * Created by Steve on 3/16/2016
 */
public class StarActor extends Actor {

    private float xPos, yPos, width, height, rotation, count, max;
    private int frmCount;
    private TextureRegion texture;

    public StarActor(float x, float y, float width, float height, float rotation) {
        xPos = x;
        yPos = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;

        texture = new TextureRegion(new Texture(Gdx.files.internal("gfx/star2.png")));
        Random rand = new Random();
        max = (rand.nextInt(15) + 10) * rand.nextFloat();
        count = 0;
        frmCount = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        float delta = Gdx.graphics.getDeltaTime();
        count += delta;

        if (count >= max) {
            frmCount++;
            if (frmCount >= 12) {
                frmCount = 0;
                count = 0;
            } else {
                count = max;
            }
        } else {
            if (rotation == 0) {
                batch.draw(texture, xPos, yPos, width, height);
            } else {
                batch.draw(texture, xPos, yPos, 0, 0, width, height, 1, 1, rotation);
            }
        }
    }
}
