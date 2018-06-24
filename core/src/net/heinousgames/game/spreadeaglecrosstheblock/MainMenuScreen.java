package net.heinousgames.game.spreadeaglecrosstheblock;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Steve on 1/17/2016
 */
class MainMenuScreen implements Screen {

    private final SpreadEagles game;
    private boolean finishedLoading;
    private OrthographicCamera camera;

    private Stage stageMenu;

    MainMenuScreen(final SpreadEagles game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1334, 750);
        camera.position.x = 667;
        camera.position.y = 375;

        game.parameter.color = Color.WHITE;
        game.parameter.size = 100;
        game.fontExmilitary100 = game.generator.generateFont(game.parameter);

        stageMenu = new Stage(new ScreenViewport());
        stageMenu.getViewport().setCamera(camera);

        Button btnStart = new TextButton("Start", game.style);
        btnStart.setPosition(557, 250);
        btnStart.setSize(220, 100);
        btnStart.setSkin(game.buttonSkin);
        btnStart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainLevel(game));
            }
        });
        stageMenu.addActor(btnStart);

        if (Gdx.app.getType() != Application.ApplicationType.WebGL) {
            Button btnQuit = new TextButton("Quit", game.style);
            btnQuit.setPosition(557, 100);
            btnQuit.setSize(220, 100);
            btnQuit.setSkin(game.buttonSkin);
            btnQuit.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    dispose();
                    Gdx.app.exit();
                }
            });
            stageMenu.addActor(btnQuit);
        }

        game.shapeRenderer.setProjectionMatrix(camera.combined);
        game.batch.setProjectionMatrix(camera.combined);

        Gdx.input.setInputProcessor(stageMenu);

        game.assetManager.load("sfx/sad_cum.ogg", Music.class);
        game.assetManager.load("sfx/hot_head.ogg", Music.class);
        game.assetManager.load("sfx/im_feeling_it.ogg", Music.class);
        game.assetManager.load("sfx/blood_creepin.ogg", Music.class);
        game.assetManager.load("sfx/sys_blower_ring.ogg", Music.class);
        game.assetManager.load("sfx/get_got.ogg", Music.class);
        game.assetManager.load("sfx/used_to_give.ogg", Music.class);
        game.assetManager.load("sfx/lock_your_doors.ogg", Music.class);
        game.assetManager.load("sfx/fuck_whos_watching.ogg", Music.class);
        game.assetManager.load("sfx/whose_watching_casino.ogg", Music.class);
        game.assetManager.load("sfx/powers_that_b.ogg", Music.class);
        game.assetManager.load("sfx/trash.ogg", Music.class);
        game.assetManager.load("sfx/song_instrumental.ogg", Music.class);
        game.assetManager.load("sfx/fever_fave_part.ogg", Music.class);
        game.assetManager.load("sfx/theme_bitmilitary.ogg", Music.class);
        game.assetManager.load("sfx/yeah.ogg", Sound.class);
        game.assetManager.load("sfx/guillotine_whine.ogg", Sound.class);

        game.assetManager.load("gfx/1.png", Texture.class);
        game.assetManager.load("gfx/2.png", Texture.class);
        game.assetManager.load("gfx/3.png", Texture.class);
        game.assetManager.load("gfx/4.png", Texture.class);
        game.assetManager.load("gfx/star.png", Texture.class);
        game.assetManager.load("gfx/tiles/base/boxItemAlt.png", Texture.class);
        game.assetManager.load("gfx/nldw.png", Texture.class);
        game.assetManager.load("gfx/crosshair.png", Texture.class);
        game.assetManager.load("gfx/pause_black.png", Texture.class);
        game.assetManager.load("gfx/cloud5.png", Texture.class);
        game.assetManager.load("gfx/tiles/enemies/bee.png", Texture.class);
        game.assetManager.load("gfx/tiles/enemies/bee_fly.png", Texture.class);
        game.assetManager.load("gfx/tiles/enemies/bee_dead.png", Texture.class);
        game.assetManager.load("gfx/tiles/enemies/piranha.png", Texture.class);
        game.assetManager.load("gfx/tiles/enemies/piranha_down.png", Texture.class);
        game.assetManager.load("gfx/tiles/enemies/piranha_dead.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienGreen_walk1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienGreen_walk2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienGreen_jump.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienGreen_hurt.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienPink_walk1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienPink_walk2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienPink_climb1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienPink_climb2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienPink_swim1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienPink_swim2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienPink_jump.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienPink_hurt.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienYellow_walk1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienYellow_walk2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienYellow_jump.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienYellow_climb1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienYellow_climb2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienYellow_swim1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienYellow_swim2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBlue_walk1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBlue_walk2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBlue_jump.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBlue_climb1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBlue_climb2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBlue_swim1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBlue_swim2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBlue_hurt_correct.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBeige_walk1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBeige_walk2.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBeige_jump.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBeige_swim1.png", Texture.class);
        game.assetManager.load("gfx/tiles/aliens/alienBeige_swim2.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_024.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_024_hit.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_094.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_046.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_046_hit.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_118.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_070.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_070_hit.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_142.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_187.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_188.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_165.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_066.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_066_hit.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_021_left1.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_022_left2.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_068.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_068_hit.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_021_right1.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_022_right2.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_019.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_019_hit.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_041.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_065.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_065_hit.png", Texture.class);
        game.assetManager.load("gfx/tiles/medieval/medievalTile_063.png", Texture.class);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.rect(0, 0, 1334, 750, game.vultureBlue, game.vultureBlue, Color.BLACK, Color.BLACK);
        game.shapeRenderer.end();

        game.batch.begin();
        game.fontExmilitary100.draw(game.batch, "Spread Eagle Cross the Block!", 100, 650);

        int highScore = game.prefs.getInteger("highScore");
        if (finishedLoading && highScore > 0) {
            game.fontExmilitary100.draw(game.batch, "High Score: " + highScore, 400, 500);
        }

        if (!finishedLoading) {
            game.fontExmilitary100.draw(game.batch, "Loading...", 500, 350);
        }

        game.batch.end();

        if (game.assetManager.update()) {
            game.one = game.assetManager.get("gfx/1.png", Texture.class);
            game.two = game.assetManager.get("gfx/2.png", Texture.class);
            game.three = game.assetManager.get("gfx/3.png", Texture.class);
            game.four = game.assetManager.get("gfx/4.png", Texture.class);
            game.star = game.assetManager.get("gfx/star.png", Texture.class);
            game.boxItemTexture = game.assetManager.get("gfx/tiles/base/boxItemAlt.png", Texture.class);
            game.nldwTexture = game.assetManager.get("gfx/nldw.png", Texture.class);
            game.crosshairTexture = game.assetManager.get("gfx/crosshair.png", Texture.class);
            game.pauseTexture = game.assetManager.get("gfx/pause_black.png", Texture.class);
            game.cloudTexture = game.assetManager.get("gfx/cloud5.png", Texture.class);
            game.beeTexture = game.assetManager.get("gfx/tiles/enemies/bee.png", Texture.class);
            game.beeFlyTexture = game.assetManager.get("gfx/tiles/enemies/bee_fly.png", Texture.class);
            game.beeDeadTexture = game.assetManager.get("gfx/tiles/enemies/bee_dead.png", Texture.class);
            game.piranhaTexture = game.assetManager.get("gfx/tiles/enemies/piranha.png", Texture.class);
            game.piranhaDownTexture = game.assetManager.get("gfx/tiles/enemies/piranha_down.png", Texture.class);
            game.piranhaDeadTexture = game.assetManager.get("gfx/tiles/enemies/piranha_dead.png", Texture.class);
            game.alienGreenWalk1 = game.assetManager.get("gfx/tiles/aliens/alienGreen_walk1.png", Texture.class);
            game.alienGreenWalk2 = game.assetManager.get("gfx/tiles/aliens/alienGreen_walk2.png", Texture.class);
            game.alienGreenJump = game.assetManager.get("gfx/tiles/aliens/alienGreen_jump.png", Texture.class);
            game.alienGreenHurt = game.assetManager.get("gfx/tiles/aliens/alienGreen_hurt.png", Texture.class);
            game.alienPinkWalk1 = game.assetManager.get("gfx/tiles/aliens/alienPink_walk1.png", Texture.class);
            game.alienPinkWalk2 = game.assetManager.get("gfx/tiles/aliens/alienPink_walk2.png", Texture.class);
            game.alienPinkClimb1 = game.assetManager.get("gfx/tiles/aliens/alienPink_climb1.png", Texture.class);
            game.alienPinkClimb2 = game.assetManager.get("gfx/tiles/aliens/alienPink_climb2.png", Texture.class);
            game.alienPinkSwim1 = game.assetManager.get("gfx/tiles/aliens/alienPink_swim1.png", Texture.class);
            game.alienPinkSwim2 = game.assetManager.get("gfx/tiles/aliens/alienPink_swim2.png", Texture.class);
            game.alienPinkJump = game.assetManager.get("gfx/tiles/aliens/alienPink_jump.png", Texture.class);
            game.alienPinkHurt = game.assetManager.get("gfx/tiles/aliens/alienPink_hurt.png", Texture.class);
            game.alienYellowWalk1 = game.assetManager.get("gfx/tiles/aliens/alienYellow_walk1.png", Texture.class);
            game.alienYellowWalk2 = game.assetManager.get("gfx/tiles/aliens/alienYellow_walk2.png", Texture.class);
            game.alienYellowJump = game.assetManager.get("gfx/tiles/aliens/alienYellow_jump.png", Texture.class);
            game.alienYellowClimb1 = game.assetManager.get("gfx/tiles/aliens/alienYellow_climb1.png", Texture.class);
            game.alienYellowClimb2 = game.assetManager.get("gfx/tiles/aliens/alienYellow_climb2.png", Texture.class);
            game.alienYellowSwim1 = game.assetManager.get("gfx/tiles/aliens/alienYellow_swim1.png", Texture.class);
            game.alienYellowSwim2 = game.assetManager.get("gfx/tiles/aliens/alienYellow_swim2.png", Texture.class);
            game.alienBlueWalk1 = game.assetManager.get("gfx/tiles/aliens/alienBlue_walk1.png", Texture.class);
            game.alienBlueWalk2 =  game.assetManager.get("gfx/tiles/aliens/alienBlue_walk2.png", Texture.class);
            game.alienBlueJump = game.assetManager.get("gfx/tiles/aliens/alienBlue_jump.png", Texture.class);
            game.alienBlueClimb1 = game.assetManager.get("gfx/tiles/aliens/alienBlue_climb1.png", Texture.class);
            game.alienBlueClimb2 = game.assetManager.get("gfx/tiles/aliens/alienBlue_climb2.png", Texture.class);
            game.alienBlueSwim1 = game.assetManager.get("gfx/tiles/aliens/alienBlue_swim1.png", Texture.class);
            game.alienBlueSwim2 = game.assetManager.get("gfx/tiles/aliens/alienBlue_swim2.png", Texture.class);
            game.alienBlueHurt =  game.assetManager.get("gfx/tiles/aliens/alienBlue_hurt_correct.png", Texture.class);
            game.alienBeigeWalk1 = game.assetManager.get("gfx/tiles/aliens/alienBeige_walk1.png", Texture.class);
            game.alienBeigeWalk2 = game.assetManager.get("gfx/tiles/aliens/alienBeige_walk2.png", Texture.class);
            game.alienBeigeJump = game.assetManager.get("gfx/tiles/aliens/alienBeige_jump.png", Texture.class);
            game.alienBeigeSwim1 = game.assetManager.get("gfx/tiles/aliens/alienBeige_swim1.png", Texture.class);
            game.alienBeigeSwim2 = game.assetManager.get("gfx/tiles/aliens/alienBeige_swim2.png", Texture.class);
            game.castleFlagTopReg = game.assetManager.get("gfx/tiles/medieval/medievalTile_024.png", Texture.class);
            game.castleFlagTopHit = game.assetManager.get("gfx/tiles/medieval/medievalTile_024_hit.png", Texture.class);
            game.castleFlagTopDestroyed = game.assetManager.get("gfx/tiles/medieval/medievalTile_094.png", Texture.class);
            game.castleFlagMiddleReg = game.assetManager.get("gfx/tiles/medieval/medievalTile_046.png", Texture.class);
            game.castleFlagMiddleHit = game.assetManager.get("gfx/tiles/medieval/medievalTile_046_hit.png", Texture.class);
            game.castleFlagMiddleDestroyed = game.assetManager.get("gfx/tiles/medieval/medievalTile_118.png", Texture.class);
            game.castleFlagBottomReg = game.assetManager.get("gfx/tiles/medieval/medievalTile_070.png", Texture.class);
            game.castleFlagBottomHit = game.assetManager.get("gfx/tiles/medieval/medievalTile_070_hit.png", Texture.class);
            game.castleFlagBottomDestroyed = game.assetManager.get("gfx/tiles/medieval/medievalTile_142.png", Texture.class);
            game.castleTorchReg = game.assetManager.get("gfx/tiles/medieval/medievalTile_187.png", Texture.class);
            game.castleTorchHit = game.assetManager.get("gfx/tiles/medieval/medievalTile_188.png", Texture.class);
            game.castleTorchDestroyed = game.assetManager.get("gfx/tiles/medieval/medievalTile_165.png", Texture.class);
            game.castleWallLeftReg = game.assetManager.get("gfx/tiles/medieval/medievalTile_066.png", Texture.class);
            game.castleWallLeftHit = game.assetManager.get("gfx/tiles/medieval/medievalTile_066_hit.png", Texture.class);
            game.castleWallLeftDestroyed1 = game.assetManager.get("gfx/tiles/medieval/medievalTile_021_left1.png", Texture.class);
            game.castleWallLeftDestroyed2 = game.assetManager.get("gfx/tiles/medieval/medievalTile_022_left2.png", Texture.class);
            game.castleWallRightReg = game.assetManager.get("gfx/tiles/medieval/medievalTile_068.png", Texture.class);
            game.castleWallRightHit = game.assetManager.get("gfx/tiles/medieval/medievalTile_068_hit.png", Texture.class);
            game.castleWallRightDestroyed1 = game.assetManager.get("gfx/tiles/medieval/medievalTile_021_right1.png", Texture.class);
            game.castleWallRightDestroyed2 = game.assetManager.get("gfx/tiles/medieval/medievalTile_022_right2.png", Texture.class);
            game.castleWallTopReg = game.assetManager.get("gfx/tiles/medieval/medievalTile_019.png", Texture.class);
            game.castleWallTopHit = game.assetManager.get("gfx/tiles/medieval/medievalTile_019_hit.png", Texture.class);
            game.castleWallTopDestroyed = game.assetManager.get("gfx/tiles/medieval/medievalTile_041.png", Texture.class);
            game.castleWallReg = game.assetManager.get("gfx/tiles/medieval/medievalTile_065.png", Texture.class);
            game.castleWallHit = game.assetManager.get("gfx/tiles/medieval/medievalTile_065_hit.png", Texture.class);
            game.castleWallDestroyed = game.assetManager.get("gfx/tiles/medieval/medievalTile_063.png", Texture.class);

            game.sadCum = game.assetManager.get("sfx/sad_cum.ogg", Music.class);
            game.hotHead = game.assetManager.get("sfx/hot_head.ogg", Music.class);
            game.imFeelingIt = game.assetManager.get("sfx/im_feeling_it.ogg", Music.class);
            game.bloodCreepin = game.assetManager.get("sfx/blood_creepin.ogg", Music.class);
            game.sysBlowerRing = game.assetManager.get("sfx/sys_blower_ring.ogg", Music.class);
            game.getGot = game.assetManager.get("sfx/get_got.ogg", Music.class);
            game.usedToGive = game.assetManager.get("sfx/used_to_give.ogg", Music.class);
            game.lockYourDoors = game.assetManager.get("sfx/lock_your_doors.ogg", Music.class);
            game.fuckWhosWatching = game.assetManager.get("sfx/fuck_whos_watching.ogg", Music.class);
            game.casino = game.assetManager.get("sfx/whose_watching_casino.ogg", Music.class);
            game.powersThatB = game.assetManager.get("sfx/powers_that_b.ogg", Music.class);
            game.trash = game.assetManager.get("sfx/trash.ogg", Music.class);
            game.song_full = game.assetManager.get("sfx/song_instrumental.ogg", Music.class);
            game.fever = game.assetManager.get("sfx/fever_fave_part.ogg", Music.class);
            game.bitmilitary = game.assetManager.get("sfx/theme_bitmilitary.ogg", Music.class);
            game.yeah = game.assetManager.get("sfx/yeah.ogg", Sound.class);
            game.guillotineWhine = game.assetManager.get("sfx/guillotine_whine.ogg", Sound.class);
            game.song_full.setLooping(true);
            game.song_full.setVolume(0.25f);
            game.fever.setVolume(0.25f);
            game.bitmilitary.setLooping(true);
            game.bitmilitary.setVolume(0.25f);
            game.sadCum.setVolume(0.5f);
            game.hotHead.setVolume(0.5f);
            game.imFeelingIt.setVolume(0.5f);
            game.bloodCreepin.setVolume(0.5f);
            game.sysBlowerRing.setVolume(0.5f);
            game.getGot.setVolume(0.5f);
            game.usedToGive.setVolume(0.5f);
            game.lockYourDoors.setVolume(0.5f);
            game.fuckWhosWatching.setVolume(0.5f);
            game.casino.setVolume(0.5f);
            game.powersThatB.setVolume(0.5f);
            game.trash.setVolume(0.5f);
            finishedLoading = true;
        }

        if (finishedLoading) {
            stageMenu.draw();
        }

        camera.update();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stageMenu.dispose();
    }

}