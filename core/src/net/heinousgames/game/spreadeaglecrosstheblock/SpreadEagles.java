package net.heinousgames.game.spreadeaglecrosstheblock;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.Date;

public class SpreadEagles extends Game {

    private BitmapFont fontUI;
    private Texture buttonTexture;
    private TextureAtlas atlas;

    AssetManager assetManager;
    BitmapFont fontExmilitary100, font100Gold;
    Color vultureBlue;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    HTMLDateFormat dateFormatCallback;
    int score;
    Music imFeelingIt, bloodCreepin, getGot, sysBlowerRing, usedToGive, lockYourDoors, casino,
            fuckWhosWatching, powersThatB, trash, hotHead, sadCum, song_full, fever, bitmilitary;
    Preferences prefs;
    ShapeRenderer shapeRenderer;
    Skin buttonSkin;
    Sound yeah, guillotineWhine;
    SpriteBatch batch;
    TextButton.TextButtonStyle style;
    Texture crosshairTexture, pauseTexture, nldwTexture, boxItemTexture, cloudTexture,
            beeTexture, beeFlyTexture, beeDeadTexture, piranhaTexture, piranhaDownTexture,
            piranhaDeadTexture, alienGreenWalk1, alienGreenWalk2, alienGreenJump, alienGreenHurt,
            alienPinkWalk1, alienPinkWalk2, alienPinkClimb1, alienPinkClimb2, alienPinkSwim1,
            alienPinkSwim2, alienPinkJump, alienPinkHurt, alienYellowWalk1, alienYellowWalk2,
            alienYellowJump, alienYellowClimb1, alienYellowClimb2, alienYellowSwim1, alienYellowSwim2,
            alienBlueWalk1, alienBlueWalk2, alienBlueJump, alienBlueClimb1, alienBlueClimb2,
            alienBlueSwim1, alienBlueSwim2, alienBlueHurt, alienBeigeWalk1, alienBeigeWalk2,
            alienBeigeSwim1, alienBeigeSwim2, alienBeigeJump;

    public interface HTMLDateFormat {
        String convertDate(Date date);
    }

    public SpreadEagles(HTMLDateFormat dateFormatCallback) {
        this.dateFormatCallback = dateFormatCallback;
    }

    public void create() {
        assetManager = new AssetManager();
        atlas = new TextureAtlas(Gdx.files.internal("ui-blue.atlas"));
        batch = new SpriteBatch();
        buttonSkin = new Skin();
        buttonSkin.addRegions(atlas);
        buttonTexture = new Texture(Gdx.files.internal("gfx/ui_button4.png"));
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OldLondon.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BLACK;
        parameter.size = 100;
        fontUI = generator.generateFont(parameter);
        parameter.color = Color.GOLD;
        font100Gold = generator.generateFont(parameter);
        prefs = Gdx.app.getPreferences("userPrefs");
        shapeRenderer = new ShapeRenderer();
        style = new TextButton.TextButtonStyle();
        style.up = buttonSkin.getDrawable("button_04");
        style.down = buttonSkin.getDrawable("button_02");
        style.font = fontUI;
        vultureBlue = new Color(0 / 255.0f, 8 / 255.0f, 64 / 255.0f, 1);

        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        atlas.dispose();
        batch.dispose();
        buttonSkin.dispose();
        buttonTexture.dispose();
        fontExmilitary100.dispose();
        font100Gold.dispose();
        fontUI.dispose();
        generator.dispose();
        shapeRenderer.dispose();

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
        song_full.dispose();

        // textures
        crosshairTexture.dispose();
        pauseTexture.dispose();
        boxItemTexture.dispose();
        nldwTexture.dispose();
        cloudTexture.dispose();
        beeTexture.dispose();
        beeFlyTexture.dispose();
        beeDeadTexture.dispose();
        piranhaTexture.dispose();
        piranhaDownTexture.dispose();
        piranhaDeadTexture.dispose();
        alienGreenWalk1.dispose();
        alienGreenWalk2.dispose();
        alienGreenJump.dispose();
        alienGreenHurt.dispose();
        alienPinkWalk1.dispose();
        alienPinkWalk2.dispose();
        alienPinkClimb1.dispose();
        alienPinkClimb2.dispose();
        alienPinkSwim1.dispose();
        alienPinkSwim2.dispose();
        alienPinkJump.dispose();
        alienPinkHurt.dispose();
        alienYellowWalk1.dispose();
        alienYellowWalk2.dispose();
        alienYellowJump.dispose();
        alienYellowSwim1.dispose();
        alienYellowSwim2.dispose();
        alienBlueWalk1.dispose();
        alienBlueWalk2.dispose();
        alienBlueJump.dispose();
        alienBlueClimb1.dispose();
        alienBlueClimb2.dispose();
        alienBlueSwim1.dispose();
        alienBlueSwim2.dispose();
        alienBlueHurt.dispose();
        alienBeigeWalk1.dispose();
        alienBeigeWalk2.dispose();
        alienBeigeSwim1.dispose();
        alienBeigeSwim2.dispose();
        alienBeigeJump.dispose();
    }
}
