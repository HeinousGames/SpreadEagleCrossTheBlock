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
    BitmapFont fontExmilitary100, font100Gold, fontExmilitary80;
    Color vultureBlue;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    HTMLDateFormat dateFormatCallback;
    int score, albumsFound;
    Music imFeelingIt, bloodCreepin, getGot, sysBlowerRing, usedToGive, lockYourDoors, casino,
            fuckWhosWatching, powersThatB, trash, hotHead, sadCum, song_full, fever, bitmilitary;
    Preferences prefs;
    ShapeRenderer shapeRenderer;
    Skin buttonSkin;
    Sound yeah, guillotineWhine;
    SpriteBatch batch;
    TextButton.TextButtonStyle style;
    Texture one, two, three, four, star, crosshairTexture, pauseTexture, nldwTexture, usedToTexture,
            notmTexture, powersTexture, rideTexture, jdTexture, boxItemTexture, cloudTexture,
            moonTexture, beeTexture, beeFlyTexture, beeDeadTexture, piranhaTexture, piranhaDownTexture,
            piranhaDeadTexture, alienGreenWalk1, alienGreenWalk2, alienGreenJump, alienGreenHurt,
            alienPinkWalk1, alienPinkWalk2, alienPinkClimb1, alienPinkClimb2, alienPinkSwim1,
            alienPinkSwim2, alienPinkJump, alienPinkHurt, alienYellowWalk1, alienYellowWalk2,
            alienYellowJump, alienYellowClimb1, alienYellowClimb2, alienYellowSwim1, alienYellowSwim2,
            alienBlueWalk1, alienBlueWalk2, alienBlueJump, alienBlueClimb1, alienBlueClimb2,
            alienBlueSwim1, alienBlueSwim2, alienBlueHurt, alienBeigeWalk1, alienBeigeWalk2,
            alienBeigeSwim1, alienBeigeSwim2, alienBeigeJump, castleFlagTopReg, castleFlagTopHit,
            castleFlagTopDestroyed, castleFlagMiddleReg, castleFlagMiddleHit, castleFlagMiddleDestroyed,
            castleFlagBottomReg, castleFlagBottomHit, castleFlagBottomDestroyed, castleTorchReg,
            castleTorchHit, castleTorchDestroyed, castleWallLeftReg, castleWallLeftHit,
            castleWallLeftDestroyed1, castleWallLeftDestroyed2,castleWallRightReg, castleWallRightHit,
            castleWallRightDestroyed1, castleWallRightDestroyed2, castleWallTopReg, castleWallTopHit,
            castleWallTopDestroyed, castleWallReg, castleWallHit, castleWallDestroyed, hgLogo, libgdxLogo;

    public interface HTMLDateFormat {
        String convertDate(Date date);
    }

    public SpreadEagles(HTMLDateFormat dateFormatCallback) {
        this.dateFormatCallback = dateFormatCallback;
    }

    public void create() {
        assetManager = new AssetManager();
        assetManager.load("gfx/libgdxLogo.png", Texture.class);
        assetManager.load("gfx/hgLogo.png", Texture.class);
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
        parameter.color = Color.WHITE;
        parameter.size = 80;
        fontExmilitary80 = generator.generateFont(parameter);
        prefs = Gdx.app.getPreferences("userPrefs");
        shapeRenderer = new ShapeRenderer();
        style = new TextButton.TextButtonStyle();
        style.up = buttonSkin.getDrawable("button_04");
        style.down = buttonSkin.getDrawable("button_02");
        style.font = fontUI;
        vultureBlue = new Color(0 / 255.0f, 8 / 255.0f, 64 / 255.0f, 1);

        this.setScreen(new LibgdxScreen(this));
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

        // disposes all assets loaded to the manager
        assetManager.dispose();
    }
}
