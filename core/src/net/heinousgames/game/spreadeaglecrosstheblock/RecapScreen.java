package net.heinousgames.game.spreadeaglecrosstheblock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Steve on 11/26/2016
 */

class RecapScreen implements Screen, InputProcessor {

    private final SpreadEagles game;
    private OrthographicCamera camera;
    private Stage recapStage;
    private int record;

    RecapScreen(SpreadEagles game) {
        this.game = game;

        Preferences prefs = Gdx.app.getPreferences("userPrefs");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1334, 750);

        Image feedbackButton = new Image(game.buttonTexture);
        feedbackButton.setName("feedback");
        feedbackButton.setPosition(80, 100);
        feedbackButton.setHeight(120);
        feedbackButton.setWidth(585);

//        Image startOverButton = new Image(game.buttonTexture);
//        startOverButton.setName("startOver");
//        startOverButton.setPosition(780, 350.5f);
//        startOverButton.setHeight(120);
//        startOverButton.setWidth(420);

        Image quitButton = new Image(game.buttonTexture);
        quitButton.setName("quit");
        quitButton.setPosition(1020, 100);
        quitButton.setHeight(120);
        quitButton.setWidth(180);

        game.parameter.color = Color.WHITE;
        game.parameter.size = 100;
        game.fontExmilitary100 = game.generator.generateFont(game.parameter);

        game.shapeRenderer.setProjectionMatrix(camera.combined);
        game.batch.setProjectionMatrix(camera.combined);

        recapStage = new Stage(new ScreenViewport());
        recapStage.getViewport().setCamera(camera);
        recapStage.addActor(feedbackButton);
//        recapStage.addActor(startOverButton);
        recapStage.addActor(quitButton);

        Gdx.input.setInputProcessor(this);

        record = prefs.getInteger("highScore");
        if (game.score > record) {
            prefs.putInteger("highScore", game.score);
            prefs.flush();
            record = prefs.getInteger("highScore");
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.rect(0, 0, 1334, 750, game.VULTURE_BLUE, game.VULTURE_BLUE, Color.BLACK, Color.BLACK);
        game.shapeRenderer.end();

        recapStage.draw();

        game.batch.begin();
        game.fontExmilitary100.draw(game.batch, "Quit", 1030, 210);
        game.fontExmilitary100.draw(game.batch, "Leave Feedback", 90, 210);
//        game.fontExmilitary100.draw(game.batch, "Start Over", 790, 465);
        game.fontExmilitary100.draw(game.batch, "Score: " + game.score, 100, 700);
        game.fontExmilitary100.draw(game.batch, "High Score: " + record, 100, 600);
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 coord = recapStage.screenToStageCoordinates(new Vector2((float)screenX,(float)screenY));
        Actor hitActor = recapStage.hit(coord.x,coord.y,false);

        if (hitActor != null) {
            if (hitActor.getName().equals("feedback")) {
                System.out.println("desktop button");

                //todo only works on android
                //     must implement method in other project folders to work
                game.getFeedbackCallback().sendEmail();
            }
//            else if (hitActor.getName().equals("startOver")) {
//                dispose();
//                game.setScreen(new MainLevel(game));
//            }
            else if (hitActor.getName().equals("quit")) {
                dispose();
                Gdx.app.exit();
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
