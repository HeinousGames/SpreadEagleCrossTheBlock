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
    private boolean isHit, showCrossHair;
    private Texture one, two, three, four;
    private TextureRegion crossHairRegion, regularRegion;
    public boolean isDestroyed;
    public Rectangle rectangle;
    float stateTime;
    int hits;
    Animation<TextureRegion> hitAnimation;
    TextureRegion destroyedRegion;

    CastleTargetActor(float posX, float posY, Texture crossHair, Texture one, Texture two,
                      Texture three, Texture four, TextureRegion regularRegion,
                      TextureRegion destroyedRegion) {
        this(posX, posY, crossHair, regularRegion, destroyedRegion);
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
    }

    CastleTargetActor(float posX, float posY, Texture crossHair, TextureRegion regularRegion,
                      TextureRegion destroyedRegion) {
        this(posX, posY, regularRegion, destroyedRegion);
        showCrossHair = true;
        crossHairRegion = new TextureRegion(crossHair);
    }

    CastleTargetActor(float posX, float posY, TextureRegion regularRegion,
                      TextureRegion destroyedRegion) {
        rectangle = new Rectangle();
        rectangle.x = posX;
        rectangle.y = posY;
        rectangle.width = regularRegion.getRegionWidth() / 70f;
        rectangle.height = regularRegion.getRegionHeight() / 70f;
        this.regularRegion = regularRegion;
        this.destroyedRegion = destroyedRegion;
        showCrossHair = false;
    }

    public void isHit() {
        if (!isHit && !isDestroyed) {
            showCrossHair = false;
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
                if (showCrossHair) {
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
