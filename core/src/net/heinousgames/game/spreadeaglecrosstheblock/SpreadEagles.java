package net.heinousgames.game.spreadeaglecrosstheblock;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SpreadEagles extends Game {

    interface SendFeedback {
        void sendEmail();
    }

    SpriteBatch batch;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont font;
    public BitmapFont fontExmilitary100, font100Gold, fontUI;
    ShapeRenderer shapeRenderer;
    Color VULTURE_BLUE;
    int score;
    Texture buttonTexture;
    AssetManager assetManager;
    Music imFeelingIt, bloodCreepin, getGot, sysBlowerRing, usedToGive, lockYourDoors,
            fuckWhosWatching, casino, powersThatB, trash, hotHead, sadCum, song_full, fever;
    // yeah, whatUp, guillotineWhine, spreadEagle;

    // Local variable to hold the callback implementation
    private SendFeedback feedbackCallback;

    // ** Additional **
    // Setter for the callback
    void setFeedbackCallback(SendFeedback callback) {
        feedbackCallback = callback;
    }

    SendFeedback getFeedbackCallback() {
        return feedbackCallback;
    }

    public void create() {
        assetManager = new AssetManager();
        assetManager.load("sfx/sad_cum.ogg", Music.class);
        assetManager.load("sfx/hot_head.ogg", Music.class);
        assetManager.load("sfx/im_feeling_it.ogg", Music.class);
        assetManager.load("sfx/blood_creepin.ogg", Music.class);
        assetManager.load("sfx/sys_blower_ring.ogg", Music.class);
        assetManager.load("sfx/get_got.ogg", Music.class);
        assetManager.load("sfx/used_to_give.ogg", Music.class);
        assetManager.load("sfx/lock_your_doors.ogg", Music.class);
        assetManager.load("sfx/fuck_whos_watching.ogg", Music.class);
        assetManager.load("sfx/whose_watching_casino.ogg", Music.class);
        assetManager.load("sfx/powers_that_b.ogg", Music.class);
        assetManager.load("sfx/trash.ogg", Music.class);
        assetManager.load("sfx/song_instrumental.ogg", Music.class);
        assetManager.load("sfx/fever_fave_part.ogg", Music.class);
        assetManager.finishLoading();

        // whatUp = Gdx.audio.newSound(Gdx.files.internal("sfx/what_up.ogg"));
        // yeah = Gdx.audio.newSound(Gdx.files.internal("sfx/yeah.ogg"));
        // guillotineWhine = Gdx.audio.newSound(Gdx.files.internal("sfx/guillotine_whine.ogg"));
        // fever = Gdx.audio.newSound(Gdx.files.internal("sfx/fever_fave_part.ogg"));
//        imFeelingIt = Gdx.audio.newMusic(Gdx.files.internal("sfx/im_feeling_it.ogg"));
//        bloodCreepin = Gdx.audio.newMusic(Gdx.files.internal("sfx/blood_creepin.ogg"));
//        sysBlowerRing = Gdx.audio.newMusic(Gdx.files.internal("sfx/sys_blower_ring.ogg"));
//        getGot = Gdx.audio.newMusic(Gdx.files.internal("sfx/get_got.ogg"));
//        usedToGive = Gdx.audio.newMusic(Gdx.files.internal("sfx/used_to_give.ogg"));
//        lockYourDoors = Gdx.audio.newMusic(Gdx.files.internal("sfx/lock_your_doors.ogg"));
//        fuckWhosWatching = Gdx.audio.newMusic(Gdx.files.internal("sfx/fuck_whos_watching.ogg"));
//        casino = Gdx.audio.newMusic(Gdx.files.internal("sfx/whose_watching_casino.ogg"));
//        powersThatB = Gdx.audio.newMusic(Gdx.files.internal("sfx/powers_that_b.ogg"));
//        sadCum = Gdx.audio.newSound(Gdx.files.internal("sfx/sad_cum.ogg"));
//        trash = Gdx.audio.newMusic(Gdx.files.internal("sfx/trash.ogg"));
//        hotHead = Gdx.audio.newMusic(Gdx.files.internal("sfx/hot_head.ogg"));
//        song_full = Gdx.audio.newMusic(Gdx.files.internal("sfx/song_instrumental.ogg"));
//        spreadEagle = Gdx.audio.newMusic(Gdx.files.internal("sfx/spread_eagle.ogg"));

        buttonTexture = new Texture(Gdx.files.internal("gfx/ui_button4.png"));
        batch = new SpriteBatch();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OldLondon.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BLACK;
        parameter.size = 40;
        fontUI = generator.generateFont(parameter);
        parameter.size = 100;
        fontExmilitary100 = generator.generateFont(parameter);
        parameter.color = Color.GOLD;
        font100Gold = generator.generateFont(parameter);
        font = new BitmapFont();
        VULTURE_BLUE = new Color(0 / 255.0f, 8 / 255.0f, 64 / 255.0f, 1);
        shapeRenderer = new ShapeRenderer();

        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        fontExmilitary100.dispose();
        font.dispose();
        shapeRenderer.dispose();
        generator.dispose();
        buttonTexture.dispose();

        // audio bytes
        imFeelingIt.dispose();
        bloodCreepin.dispose();
        sysBlowerRing.dispose();
        getGot.dispose();
        usedToGive.dispose();
        lockYourDoors.dispose();
        fuckWhosWatching.dispose();
        casino.dispose();
        powersThatB.dispose();
        sadCum.dispose();
        trash.dispose();
        hotHead.dispose();

        // music bytes
        song_full.dispose();
    }
}
