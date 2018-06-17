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
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Date;

public class SpreadEagles extends Game {

    public interface HTMLDateFormat {
        String convertDate(Date date);
    }

    // Local variable to hold the callback implementation
    private HTMLDateFormat dateFormatCallback;

    HTMLDateFormat getFeedbackCallback() {
        return dateFormatCallback;
    }

    BitmapFont font;
    int score;
    AssetManager assetManager;
//    BitmapFont fontExmilitary100, font100Gold, fontUI;
    Color VULTURE_BLUE;
//    FreeTypeFontGenerator generator;
//    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    Music imFeelingIt, bloodCreepin, getGot, sysBlowerRing, usedToGive, lockYourDoors, casino,
            fuckWhosWatching, powersThatB, trash, hotHead, sadCum, song_full, fever, bitmilitary;
    ShapeRenderer shapeRenderer;
    Sound yeah, guillotineWhine;
    SpriteBatch batch;
    Texture buttonTexture;

    public SpreadEagles(HTMLDateFormat dateFormatCallback) {
        this.dateFormatCallback = dateFormatCallback;
    }

    public void create() {
        assetManager = new AssetManager();
//        assetManager.finishLoading();

        buttonTexture = new Texture(Gdx.files.internal("gfx/ui_button4.png"));
        batch = new SpriteBatch();
//        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OldLondon.ttf"));
//        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.color = Color.BLACK;
//        parameter.size = 40;
//        fontUI = generator.generateFont(parameter);
//        parameter.size = 100;
//        fontExmilitary100 = generator.generateFont(parameter);
//        parameter.color = Color.GOLD;
//        font100Gold = generator.generateFont(parameter);
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
//        fontExmilitary100.dispose();
        font.dispose();
        shapeRenderer.dispose();
//        generator.dispose();
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
