package net.heinousgames.game.spreadeaglecrosstheblock;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Steve on 11/26/2016
 */
class RecapScreen implements Screen {

    private final SpreadEagles game;
    private int highScore, highestAlbums;
    private OrthographicCamera camera;
    private Stage recapStage;

    RecapScreen(final SpreadEagles game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1334, 750);

        game.parameter.color = Color.WHITE;
        game.parameter.size = 100;
        game.fontExmilitary100 = game.generator.generateFont(game.parameter);

        game.shapeRenderer.setProjectionMatrix(camera.combined);
        game.batch.setProjectionMatrix(camera.combined);

        recapStage = new Stage(new ScreenViewport());
        recapStage.getViewport().setCamera(camera);

        Button btnPlayAgain = new TextButton("Restart", game.style);
        btnPlayAgain.setPosition(900, 400);
        btnPlayAgain.setSize(300, 100);
        btnPlayAgain.setSkin(game.buttonSkin);
        btnPlayAgain.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new MainLevel(game));
            }
        });
        recapStage.addActor(btnPlayAgain);

        if (Gdx.app.getType() != Application.ApplicationType.WebGL) {
            Button btnQuit = new TextButton("Quit", game.style);
            btnQuit.setPosition(900, 200);
            btnQuit.setSize(300, 100);
            btnQuit.setSkin(game.buttonSkin);
            btnQuit.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    dispose();
                    Gdx.app.exit();
                }
            });
            recapStage.addActor(btnQuit);
        }

        Gdx.input.setInputProcessor(recapStage);

        highScore = game.prefs.getInteger("highScore");
        if (game.score > highScore) {
            game.prefs.putInteger("highScore", game.score);
            game.prefs.flush();
            highScore = game.prefs.getInteger("highScore");
        }

        highestAlbums = game.prefs.getInteger("albumsFound");
        if (game.albumsFound > highestAlbums) {
            game.prefs.putInteger("albumsFound", game.albumsFound);
            game.prefs.flush();
            highestAlbums = game.prefs.getInteger("albumsFound");
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.rect(0, 0, 1334, 750, game.vultureBlue, game.vultureBlue, Color.BLACK, Color.BLACK);
        game.shapeRenderer.end();

        recapStage.draw();

        game.batch.begin();
        game.fontExmilitary80.draw(game.batch, "Score: " + game.score, 100, 600);
        game.fontExmilitary80.draw(game.batch, "High Score: " + highScore, 100, 500);
        game.fontExmilitary80.draw(game.batch, "Albums Found: " + game.albumsFound + "/6", 100, 400);
        game.fontExmilitary80.draw(game.batch, "Most Albums\n\nFound: " + highestAlbums + "/6", 100, 300);
        game.batch.end();

        camera.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        recapStage.dispose();
    }

}
