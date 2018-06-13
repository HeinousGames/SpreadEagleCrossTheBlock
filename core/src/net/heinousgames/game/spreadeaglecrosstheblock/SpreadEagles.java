package net.heinousgames.game.spreadeaglecrosstheblock;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

    // Local variable to hold the callback implementation
    private SendFeedback feedbackCallback;
    private BitmapFont font;
    int score;
    AssetManager assetManager;
    BitmapFont fontExmilitary100, font100Gold, fontUI;
    Color VULTURE_BLUE;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    Music imFeelingIt, bloodCreepin, getGot, sysBlowerRing, usedToGive, lockYourDoors, casino,
            fuckWhosWatching, powersThatB, trash, hotHead, sadCum, song_full, fever, bitmilitary;
    ShapeRenderer shapeRenderer;
    Sound yeah, guillotineWhine;
    SpriteBatch batch;
    Texture buttonTexture;

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
        assetManager.load("sfx/theme_bitmilitary.ogg", Music.class);
        assetManager.load("sfx/yeah.ogg", Sound.class);
        assetManager.load("sfx/guillotine_whine.ogg", Sound.class);
        assetManager.finishLoading();

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
        bitmilitary.dispose();
        yeah.dispose();
        guillotineWhine.dispose();
        fever.dispose();

        // music bytes
        song_full.dispose();
    }
}
