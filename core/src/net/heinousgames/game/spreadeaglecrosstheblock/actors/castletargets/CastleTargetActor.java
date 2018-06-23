package net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Steve on 12/5/2016
 */
public class CastleTargetActor extends Actor {

    private int rotation;
    private boolean isHit, showCrosshair;
    private Texture one, two, three, four;
    private TextureRegion crossHairRegion;
    public boolean isDestroyed;
    public Rectangle rectangle;
    float stateTime;
    int hits;
    Animation<TextureRegion> hitAnimation;
    TextureRegion regularRegion, hitRegion, destroyedRegion;

    CastleTargetActor(float posX, float posY) {
        rectangle = new Rectangle();
        rectangle.x = posX;
        rectangle.y = posY;
        showCrosshair = true;
        crossHairRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/crosshair.png")));
        one = new Texture(Gdx.files.internal("gfx/1.png"));
        two = new Texture(Gdx.files.internal("gfx/2.png"));
        three = new Texture(Gdx.files.internal("gfx/3.png"));
        four = new Texture(Gdx.files.internal("gfx/4.png"));
    }

    public void isHit() {
        if (!isHit && !isDestroyed) {
            showCrosshair = false;
            isHit = true;
            hits--;
            if (hits <= 0) {
                isDestroyed = true;
            }
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    isHit = false;
                }
            }, 0.6f);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isHit) {
            stateTime += Gdx.graphics.getDeltaTime();
            TextureRegion currentAnimationFrame = hitAnimation.getKeyFrame(stateTime, true);

            batch.draw(currentAnimationFrame, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        } else {
            if (!isDestroyed) {
                batch.draw(regularRegion, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                if (showCrosshair) {
                    batch.draw(crossHairRegion, rectangle.x, rectangle.y, 0.5f, 0.5f, 1, 1, 1, 1, rotation);
                    rotation--;
                } else {
                    if (hits == 4) {
                        if (Gdx.app.getType() == Application.ApplicationType.WebGL) {
                            batch.draw(four, rectangle.x + 0.4f, rectangle.y + 0.4f, 0.6f, 0.6f);
                        } else {
                            batch.draw(four, rectangle.x + 0.5f, rectangle.y + 0.5f, 0.4f, 0.4f);
                        }
                    } else if (hits == 3) {
                        if (Gdx.app.getType() == Application.ApplicationType.WebGL) {
                            batch.draw(three, rectangle.x + 0.4f, rectangle.y + 0.4f, 0.6f, 0.6f);
                        } else {
                            batch.draw(three, rectangle.x + 0.5f, rectangle.y + 0.5f, 0.4f, 0.4f);
                        }
                    } else if (hits == 2) {
                        if (Gdx.app.getType() == Application.ApplicationType.WebGL) {
                            batch.draw(two, rectangle.x + 0.4f, rectangle.y + 0.4f, 0.6f, 0.6f);
                        } else {
                            batch.draw(two, rectangle.x + 0.5f, rectangle.y + 0.5f, 0.4f, 0.4f);
                        }
                    } else if (hits == 1) {
                        if (Gdx.app.getType() == Application.ApplicationType.WebGL) {
                            batch.draw(one, rectangle.x + 0.4f, rectangle.y + 0.4f, 0.6f, 0.6f);
                        } else {
                            batch.draw(one, rectangle.x + 0.5f, rectangle.y + 0.5f, 0.4f, 0.4f);
                        }
                    }
                }
            } else {
                batch.draw(destroyedRegion, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
    }
}
