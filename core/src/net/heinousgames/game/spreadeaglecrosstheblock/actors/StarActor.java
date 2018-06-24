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

    private float xPos, yPos, width, height, count, max;
    private int frmCount, rotation;
    private TextureRegion texture;
    public boolean trippyRotation;

    public StarActor(float x, float y, Texture star) {
        xPos = x;
        yPos = y;
        this.width = 0.25f;
        this.height = 0.25f;

        texture = new TextureRegion(star);
        Random rand = new Random();
        max = (rand.nextInt(25) + 20) * rand.nextFloat();
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
            if (!trippyRotation) {
                batch.draw(texture, xPos, yPos, width, height);
            } else {
                //rotate from the center of the star image
//                batch.draw(texture, xPos, yPos, width / 2, height / 2, width, height, 1, 1, rotation);
                // but let's make it trippier
                batch.draw(texture, xPos, yPos, 0.5f, 0.5f, width, height, 1, 1, rotation);
                rotation-=3;
            }
        }
    }
}
