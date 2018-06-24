package net.heinousgames.game.spreadeaglecrosstheblock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Steve on 1/17/2016
 */
class LibgdxScreen implements Screen {

    private final SpreadEagles game;
    private boolean finishedLoading;
    private OrthographicCamera camera;

    LibgdxScreen(final SpreadEagles game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 256);
        camera.position.x = 256;
        camera.position.y = 128;

        game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (game.assetManager.update()) {
            game.libgdxLogo = game.assetManager.get("gfx/libgdxLogo.png", Texture.class);
            game.hgLogo = game.assetManager.get("gfx/hgLogo.png", Texture.class);

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

            game.assetManager.load("gfx/used_to_give.png", Texture.class);
            game.assetManager.load("gfx/notm.png", Texture.class);
            game.assetManager.load("gfx/powers_cover.png", Texture.class);
            game.assetManager.load("gfx/stefan_pixel.png", Texture.class);
            game.assetManager.load("gfx/jenny_death.png", Texture.class);
            game.assetManager.load("gfx/1.png", Texture.class);
            game.assetManager.load("gfx/2.png", Texture.class);
            game.assetManager.load("gfx/3.png", Texture.class);
            game.assetManager.load("gfx/4.png", Texture.class);
            game.assetManager.load("gfx/star.png", Texture.class);
            game.assetManager.load("gfx/moon.png", Texture.class);
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

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    dispose();
                    game.setScreen(new HGScreen(game));
                }
            }, 3f);
            finishedLoading = true;
        }

        if (finishedLoading) {
            game.batch.begin();
            game.batch.draw(game.libgdxLogo, 0, 0);
            game.batch.end();
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
    }
}