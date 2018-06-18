package net.heinousgames.game.spreadeaglecrosstheblock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    private Skin buttonSkin; //** images are used as skins of the button **//
    private Stage stageMenu;
    private TextureAtlas atlas;

    MainMenuScreen(final SpreadEagles game) {
        this.game = game;

        atlas = new TextureAtlas(Gdx.files.internal("ui-blue.atlas"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1334, 750);
        camera.position.x = 667;
        camera.position.y = 375;

        game.parameter.color = Color.WHITE;
        game.parameter.size = 100;
        game.fontExmilitary100 = game.generator.generateFont(game.parameter);

        stageMenu = new Stage(new ScreenViewport());
        stageMenu.getViewport().setCamera(camera);

        buttonSkin = new Skin();
        buttonSkin.addRegions(atlas);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("button_04");
        style.down = buttonSkin.getDrawable("button_02");
//        style.font = game.font;
        style.font = game.fontUI;

//        t.font = mySkin.getFont("default");
//        t.fontColor = new Color(0, 0, 0, 1f);
//        t.disabledFontColor = new Color(0, 0, 0, 0.4f);
//        t.checkboxOff = mySkin.getDrawable("checkbox_off");
//        t.checkboxOn = mySkin.getDrawable("checkbox_on");
//        mySkin.add("default", t);

        Button btnStart = new TextButton("START", style); //** Button text and style **//
        Button btnQuit = new TextButton("Fuck You!!!", style);
        btnStart.setPosition(550, 350); //** Button location **//
        btnStart.setSize(200, 100);
        btnStart.setSkin(buttonSkin);
        btnStart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                dispose();
                game.setScreen(new MainLevel(game));
            }
        });

        btnQuit.setPosition(550, 50);
        btnQuit.setSize(200, 100);
        btnQuit.setSkin(buttonSkin);
        btnQuit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //todo
            }
        });

        stageMenu.addActor(btnStart);

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
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.rect(0, 0, 1334, 750, game.VULTURE_BLUE, game.VULTURE_BLUE, Color.BLACK, Color.BLACK);
        game.shapeRenderer.end();

        game.batch.begin();
        game.fontExmilitary100.draw(game.batch, "Spread Eagle Cross the Block!", 100, 650);

//        if (!finishedLoading) {
//            game.fontExmilitary100.draw(game.batch, "Loading...", 475, 350);
//        }

        game.batch.end();

//        Gdx.gl.glViewport(Gdx.graphics.getWidth()/2, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());

        if (game.assetManager.update()) {
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
        buttonSkin.dispose();
        atlas.dispose();
    }

}