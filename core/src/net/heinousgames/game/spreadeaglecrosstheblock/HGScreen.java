package net.heinousgames.game.spreadeaglecrosstheblock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Steve on 1/17/2016
 */
class HGScreen implements Screen {

    private final SpreadEagles game;
    private OrthographicCamera camera;

    HGScreen(final SpreadEagles game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 512);
        camera.position.x = 512;
        camera.position.y = 256;

        game.batch.setProjectionMatrix(camera.combined);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }, 3f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(game.hgLogo, 0, 0);
        game.batch.end();

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