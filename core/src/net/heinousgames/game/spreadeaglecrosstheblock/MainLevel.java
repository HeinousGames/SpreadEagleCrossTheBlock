package net.heinousgames.game.spreadeaglecrosstheblock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets.CastleFlagBottomActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets.CastleFlagMiddleActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets.CastleFlagTopActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets.CastleTargetActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets.CastleTorchActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets.CastleWallActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets.CastleWallLeftActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets.CastleWallRightActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.castletargets.CastleWallTopActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.GenericActor;

import net.heinousgames.game.spreadeaglecrosstheblock.actors.BoxItemActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.CloudActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.EagleActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.FallingActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.HorizontalMovingActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.JennyDeathActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.MoonActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.NLDWActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.NOTMActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.PowersCoverActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.RideActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.StarActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.UsedToActor;
import net.heinousgames.game.spreadeaglecrosstheblock.actors.VerticalMovingActor;
import net.heinousgames.game.spreadeaglecrosstheblock.stages.EagleStage;

import java.util.Date;

/**
 * Created by Steve on 1/17/2016
 */
class MainLevel implements Screen, InputProcessor {

    private enum State {
        RUNNING, PAUSED
    }

    private enum PowerUpState {
        SOUND_BYTE, TRIGGERED, OFF;
        enum PowerUp {
            EXMILITARY, MONEY_STORE, NLDW, GOV_PLATES, POWERS_THAT_B, BOTTOMLESS_PIT
        }
    }

    private State state = State.RUNNING;
    private PowerUpState powerUpState = PowerUpState.OFF;
    private PowerUpState.PowerUp powerUp;

    private SpreadEagles game;

    private float CAMERA_SPEED = 3f;

    private float colorCounter, eagleBurn, elapsedTime, timeToSub, exmilitaryByteRem,
            exmilitaryTrigRem, moneyStoreByteRem, moneyStoreTrigRem, nldwByteRem, nldwTrigRem,
            govPlatesByteRem, govPlateTrigRem, powersByteRem, powersTrigRem, bottomlessPitByteRem,
            bottomlessPitTrigRem;

    private boolean cameraUp, notmHit, jdHit, pausingFromPowerUp, needSub, cameraRotated,
            cameraZoomed, timeSet, gameOver, feverTrigger, startBossTime, gameWon, gameOverDrawFlag;

    private long actionBeginTime, startTime, bossStageTime;
    private int powerUpInt;

    private Array<GenericActor> genericActors, frontCastleLeftWalkActors, frontCastleRightWalkActors,
            frontCastleLeftClimbActors, frontCastleRightClimbActors, behindCastleActors;
    private Array<Rectangle> secondTargetTiles, targetTiles, fallTargetTiles, genericRects,
            castleTargetRects, castleLeftWalkRects, castleRightWalkRects, castleLeftClimbRects,
            castleRightClimbRects, behindCastleRects;
    private Array<CastleTargetActor> castleTargetActors;
    private Array<StarActor> starActors;
    private Color backgroundStageColor;
    private Date bossDate;
    private EagleStage eagleStage;
    private CloudActor cloudActor;
    private MoonActor moonActor;
    private NLDWActor nldwActor;
    private OrthographicCamera levelCamera, eagleCamera, scoreCamera, rideCamera, backgroundCamera;
    private OrthogonalTiledMapRenderer renderer;
    private Stage backgroundStage, tileStage, rideStage, nldwBoxStage, variousTargetStage,
            deadTargetStage, scoreStage, behindCastleStage;
    private TiledMapTileLayer secondTargetLayer, targetLayer, fallTargetLayer;

    MainLevel(final SpreadEagles gam) {
        this.game = gam;

        game.parameter.color = Color.BLACK;
        game.fontExmilitary100 = game.generator.generateFont(game.parameter);

        game.score = 0;
        powerUpInt = 0;

        eagleBurn = 0;

        exmilitaryByteRem = 1.305f;
        exmilitaryTrigRem = 12.38302f;
        moneyStoreByteRem = 0.845f;
        moneyStoreTrigRem = 11.04645f;
        nldwByteRem = 3.22485f;
        nldwTrigRem = 13.6925f;
        govPlatesByteRem = 1.053f;
        govPlateTrigRem = 12.597575f;
        powersByteRem = 5.745f;
        powersTrigRem = 13.971f;
        bottomlessPitByteRem = 6.082f;
        bottomlessPitTrigRem = 13.03810f;

        bossStageTime = 180000;
        bossDate = new Date(bossStageTime);

        secondTargetTiles = new Array<Rectangle>();
        targetTiles = new Array<Rectangle>();
        fallTargetTiles = new Array<Rectangle>();
        genericRects = new Array<Rectangle>();
        castleLeftWalkRects = new Array<Rectangle>();
        castleRightWalkRects = new Array<Rectangle>();
        castleLeftClimbRects = new Array<Rectangle>();
        castleRightClimbRects = new Array<Rectangle>();
        castleTargetRects = new Array<Rectangle>();
        behindCastleRects = new Array<Rectangle>();
        genericActors = new Array<GenericActor>();
        starActors = new Array<StarActor>();
        frontCastleLeftWalkActors = new Array<GenericActor>();
        frontCastleRightWalkActors = new Array<GenericActor>();
        frontCastleLeftClimbActors = new Array<GenericActor>();
        frontCastleRightClimbActors = new Array<GenericActor>();
        castleTargetActors = new Array<CastleTargetActor>();
        behindCastleActors = new Array<GenericActor>();
        Array<GenericActor> nldwBoxActors = new Array<GenericActor>();

        colorCounter = 0;
        cameraUp = true;

        nldwActor = new NLDWActor(442, 6, 448, 5f, 10, true, false,
                game.nldwTexture, game.nldwTexture, game.nldwTexture);

        Image pauseImg = new Image(game.pauseTexture);
        pauseImg.setName("pause");
        pauseImg.setPosition(70, 30);

        backgroundCamera = new OrthographicCamera(20, 11);
        backgroundCamera.position.x = 10;
        backgroundCamera.position.y = 5.5f;
        backgroundStage = new Stage(new ScreenViewport());
        backgroundStage.getViewport().setCamera(backgroundCamera);

        StarActor star1 = new StarActor(3, 9.5f, game.star);
        StarActor star2 = new StarActor(4.25f, 9.25f, game.star);
        StarActor star3 = new StarActor(7.1f, 9.45f, game.star);
        StarActor star4 = new StarActor(9, 10f, game.star);
        StarActor star5 = new StarActor(11.3f, 9.25f, game.star);
        StarActor star6 = new StarActor(14.7f, 9.5f, game.star);
        StarActor star7 = new StarActor(16.4f, 10.25f, game.star);
        StarActor star8 = new StarActor(19, 10, game.star);
        backgroundStage.addActor(star1);
        backgroundStage.addActor(star2);
        backgroundStage.addActor(star3);
        backgroundStage.addActor(star4);
        backgroundStage.addActor(star5);
        backgroundStage.addActor(star6);
        backgroundStage.addActor(star7);
        backgroundStage.addActor(star8);

        starActors.addAll(star1, star2, star3, star4, star5, star6, star7, star8);

        scoreCamera = new OrthographicCamera(1334, 750);
        scoreCamera.position.x = 667;
        scoreCamera.position.y = 375;
        scoreStage = new Stage(new ScreenViewport());
        scoreStage.getViewport().setCamera(scoreCamera);

        scoreStage.addActor(pauseImg);

        Gdx.input.setInputProcessor(this);

        // create the levelCamera to show 20x11 world units
        levelCamera = new OrthographicCamera(20, 11);
        // position the levelCamera to the world units
        levelCamera.position.x = 802;
        levelCamera.position.y = 5.5f;

        tileStage = new Stage(new ScreenViewport());
        tileStage.getViewport().setCamera(levelCamera);
        behindCastleStage = new Stage(new ScreenViewport());
        behindCastleStage.getViewport().setCamera(levelCamera);
        nldwBoxStage = new Stage(new ScreenViewport());
        variousTargetStage = new Stage(new ScreenViewport());
        deadTargetStage = new Stage(new ScreenViewport());
        nldwBoxStage.getViewport().setCamera(levelCamera);
        variousTargetStage.getViewport().setCamera(levelCamera);
        deadTargetStage.getViewport().setCamera(levelCamera);

        game.shapeRenderer.setProjectionMatrix(levelCamera.combined);

        // boss aliens behind the castle wall (top of the towers)
        behindCastleActors.addAll(
                new HorizontalMovingActor(4, 7.7f, 6, 4f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(15, 7.7f, 13, 4f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump));

        castleTargetActors.addAll(new CastleFlagTopActor(2, 9, game.crosshairTexture,
                new TextureRegion(game.castleFlagTopReg), new TextureRegion(game.castleFlagTopHit),
                new TextureRegion(game.castleFlagTopDestroyed)),
                new CastleFlagTopActor(17, 9, game.crosshairTexture,
                        new TextureRegion(game.castleFlagTopReg),
                        new TextureRegion(game.castleFlagTopHit),
                        new TextureRegion(game.castleFlagTopDestroyed)),
                new CastleFlagMiddleActor(2, 8, game.crosshairTexture,
                        new TextureRegion(game.castleFlagMiddleReg),
                        new TextureRegion(game.castleFlagMiddleHit),
                        new TextureRegion(game.castleFlagMiddleDestroyed)),
                new CastleFlagMiddleActor(17, 8, game.crosshairTexture,
                        new TextureRegion(game.castleFlagMiddleReg),
                        new TextureRegion(game.castleFlagMiddleHit),
                        new TextureRegion(game.castleFlagMiddleDestroyed)),
                new CastleFlagBottomActor(2, 7, game.crosshairTexture,
                        new TextureRegion(game.castleFlagBottomReg),
                        new TextureRegion(game.castleFlagBottomHit),
                        new TextureRegion(game.castleFlagBottomDestroyed)),
                new CastleFlagBottomActor(17, 7, game.crosshairTexture,
                        new TextureRegion(game.castleFlagBottomReg),
                        new TextureRegion(game.castleFlagBottomHit),
                        new TextureRegion(game.castleFlagBottomDestroyed)),
                new CastleTorchActor(7, 5, new TextureRegion(game.castleTorchReg),
                        new TextureRegion(game.castleTorchHit),
                        new TextureRegion(game.castleTorchDestroyed)),
                new CastleTorchActor(12, 5, new TextureRegion(game.castleTorchReg),
                        new TextureRegion(game.castleTorchHit),
                        new TextureRegion(game.castleTorchDestroyed)),
                new CastleWallLeftActor(1, 8, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallLeftReg), new TextureRegion(game.castleWallLeftHit),
                        new TextureRegion(game.castleWallLeftDestroyed1), false),
                new CastleWallLeftActor(1, 7, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallLeftReg), new TextureRegion(game.castleWallLeftHit),
                        new TextureRegion(game.castleWallLeftDestroyed1), true),
                new CastleWallLeftActor(1, 6, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallLeftReg), new TextureRegion(game.castleWallLeftHit),
                        new TextureRegion(game.castleWallLeftDestroyed2), false),
                new CastleWallLeftActor(1, 5, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallLeftReg), new TextureRegion(game.castleWallLeftHit),
                        new TextureRegion(game.castleWallLeftDestroyed1), false),
                new CastleWallLeftActor(1, 4, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallLeftReg), new TextureRegion(game.castleWallLeftHit),
                        new TextureRegion(game.castleWallLeftDestroyed1), true),
                new CastleWallLeftActor(1, 3, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallLeftReg), new TextureRegion(game.castleWallLeftHit),
                        new TextureRegion(game.castleWallLeftDestroyed2), false),
                new CastleWallRightActor(18, 8, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallRightReg), new TextureRegion(game.castleWallRightHit),
                        new TextureRegion(game.castleWallRightDestroyed1), false),
                new CastleWallRightActor(18, 7, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallRightReg), new TextureRegion(game.castleWallRightHit),
                        new TextureRegion(game.castleWallRightDestroyed1), true),
                new CastleWallRightActor(18, 6, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallRightReg), new TextureRegion(game.castleWallRightHit),
                        new TextureRegion(game.castleWallRightDestroyed2), false),
                new CastleWallTopActor(4, 7, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallTopReg), new TextureRegion(game.castleWallTopHit),
                        new TextureRegion(game.castleWallTopDestroyed)),
                new CastleWallTopActor(5, 7, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallTopReg), new TextureRegion(game.castleWallTopHit),
                        new TextureRegion(game.castleWallTopDestroyed)),
                new CastleWallTopActor(6, 7, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallTopReg), new TextureRegion(game.castleWallTopHit),
                        new TextureRegion(game.castleWallTopDestroyed)),
                new CastleWallTopActor(13, 7, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallTopReg), new TextureRegion(game.castleWallTopHit),
                        new TextureRegion(game.castleWallTopDestroyed)),
                new CastleWallTopActor(14, 7, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallTopReg), new TextureRegion(game.castleWallTopHit),
                        new TextureRegion(game.castleWallTopDestroyed)),
                new CastleWallTopActor(15, 7, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallTopReg), new TextureRegion(game.castleWallTopHit),
                        new TextureRegion(game.castleWallTopDestroyed)),
                new CastleWallActor(2, 3, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallReg), new TextureRegion(game.castleWallHit),
                        new TextureRegion(game.castleWallDestroyed)),
                new CastleWallActor(4, 4, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallReg), new TextureRegion(game.castleWallHit),
                        new TextureRegion(game.castleWallDestroyed)),
                new CastleWallActor(5, 3, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallReg), new TextureRegion(game.castleWallHit),
                        new TextureRegion(game.castleWallDestroyed)),
                new CastleWallActor(6, 4, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallReg), new TextureRegion(game.castleWallHit),
                        new TextureRegion(game.castleWallDestroyed)),
                new CastleWallActor(9, 7, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallReg), new TextureRegion(game.castleWallHit),
                        new TextureRegion(game.castleWallDestroyed)),
                new CastleWallActor(10, 7, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallReg), new TextureRegion(game.castleWallHit),
                        new TextureRegion(game.castleWallDestroyed)),
                new CastleWallActor(13, 4, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallReg), new TextureRegion(game.castleWallHit),
                        new TextureRegion(game.castleWallDestroyed)),
                new CastleWallActor(14, 3, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallReg), new TextureRegion(game.castleWallHit),
                        new TextureRegion(game.castleWallDestroyed)),
                new CastleWallActor(15, 4, game.crosshairTexture, game.one, game.two, game.three, game.four,
                        new TextureRegion(game.castleWallReg), new TextureRegion(game.castleWallHit),
                        new TextureRegion(game.castleWallDestroyed)));

        // boss aliens that cover the front of the castle
        frontCastleLeftWalkActors.addAll(
                new HorizontalMovingActor(9, 3, 0, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(0, 3, 9, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(8, 3, -1, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(10, 3, 1, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(7, 3, 3, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(10, 3, 3, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(3, 3, 7, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(0, 3, 4, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(4, 3, 0, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump));

        frontCastleRightWalkActors.addAll(
                new HorizontalMovingActor(12, 3, 16, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(16, 3, 12, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(19, 3, 15, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(15, 3, 19, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(18, 3, 14, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(14, 3, 18, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(10, 3, 19, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(19, 3, 10, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(8, 3, 13, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(13, 3, 8, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(14, 3, 8, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump));

        frontCastleLeftClimbActors.addAll(new VerticalMovingActor(1, 3, 8, 3, 5, true, false,
                        game.alienPinkClimb1, game.alienPinkClimb2, game.alienPinkClimb1),
                new VerticalMovingActor(1, 8, 3, 3, 5, true, false,
                        game.alienPinkClimb1, game.alienPinkClimb2, game.alienPinkClimb1));

        frontCastleRightClimbActors.addAll(new VerticalMovingActor(18, 3, 8, 3, 5, true, false,
                        game.alienPinkClimb1, game.alienPinkClimb2, game.alienPinkClimb1),
                new VerticalMovingActor(18, 8, 3, 3, 5, true, false,
                        game.alienPinkClimb1, game.alienPinkClimb2, game.alienPinkClimb1));

        // nldw blockades
        nldwBoxActors.addAll(
                new BoxItemActor(new TextureRegion(game.boxItemTexture), 442, 6),
                new BoxItemActor(new TextureRegion(game.boxItemTexture), 444, 6),
                new BoxItemActor(new TextureRegion(game.boxItemTexture), 446, 6),
                new BoxItemActor(new TextureRegion(game.boxItemTexture), 448, 6));

        genericActors.addAll(
                new HorizontalMovingActor(766, 6, 768, 3f, 3, true, true,
                        game.beeTexture, game.beeFlyTexture, game.beeDeadTexture),
                new HorizontalMovingActor(730, 8, 733, 3f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new VerticalMovingActor(689, 9, 1, 5f, 3, true, true,
                        game.piranhaDownTexture, game.piranhaDownTexture, game.piranhaDeadTexture),
                new VerticalMovingActor(691, 1, 9, 5f, 3, true, true,
                        game.piranhaTexture, game.piranhaTexture, game.piranhaDeadTexture),
                new VerticalMovingActor(694, 1, 9, 5f, 3, true, true,
                        game.piranhaTexture, game.piranhaTexture, game.piranhaDeadTexture),
                new VerticalMovingActor(696, 9, 1, 5f, 3, true, true,
                        game.piranhaDownTexture, game.piranhaDownTexture, game.piranhaDeadTexture),
                new VerticalMovingActor(557, 3, 8, 3, 5, true, false,
                        game.alienPinkClimb1, game.alienPinkClimb2, game.alienPinkClimb1),
                new HorizontalMovingActor(532, 4, 538, 6f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(240, 6, 544, 5f, 5, true, true,
                        game.alienYellowWalk1, game.alienYellowWalk2, game.alienYellowJump),
                new HorizontalMovingActor(527, 7, 532, 4f, 5, true, true,
                        game.alienBlueWalk1, game.alienBlueWalk2, game.alienBlueJump),
                new HorizontalMovingActor(525, 9, 535, 4f, 5, true, true,
                        game.alienBlueWalk1, game.alienBlueWalk2, game.alienBlueJump),
                new HorizontalMovingActor(545, 9, 535, 4f, 5, true, true,
                        game.alienBlueWalk1, game.alienBlueWalk2, game.alienBlueJump),
                new HorizontalMovingActor(498, 6.32f, 504, 4f, 5, true, true,
                        game.alienBlueWalk1, game.alienBlueWalk2, game.alienBlueJump),
                new VerticalMovingActor(488, 7.2f, 7.5f, 3, 5, true, false,
                        game.alienPinkClimb1, game.alienPinkClimb2, game.alienPinkClimb1),
                new HorizontalMovingActor(448, 3, 442, 5f, 5, true, true,
                        game.alienPinkSwim1, game.alienPinkSwim2, game.alienPinkJump),
                new HorizontalMovingActor(384, 6, 390, 4.5f, 5, true, true,
                        game.alienBeigeWalk1, game.alienBeigeWalk2, game.alienBeigeJump),
                new HorizontalMovingActor(382, 3.32f, 385, 3, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(379, 8.75f, 379, 0, 5, true, false,
                        game.alienPinkHurt, game.alienPinkHurt, game.alienPinkJump),
                new HorizontalMovingActor(355, 9, 359, 4.5f, 5, true, true,
                        game.alienBlueWalk1, game.alienBlueWalk2, game.alienBlueJump),
                new HorizontalMovingActor(318, 8, 324, 4.5f, 5, true, true,
                        game.alienBeigeWalk1, game.alienBeigeWalk2, game.alienBeigeJump),
                new VerticalMovingActor(318, 3, 7, 3, 5, true, false,
                        game.alienBlueClimb1, game.alienBlueClimb2, game.alienBlueClimb1),
                new VerticalMovingActor(324, 7, 3, 3, 5, true, false,
                        game.alienYellowClimb1, game.alienYellowClimb2, game.alienYellowClimb1),
                new HorizontalMovingActor(307, 3, 315, 4.5f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(284, 6.32f, 295, 4f, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(285, 3, 296, 5, 5, true, true,
                        game.alienPinkSwim1, game.alienPinkSwim2, game.alienPinkJump),
                new HorizontalMovingActor(296, 3, 283, 5f, 5, true, true,
                        game.alienBlueSwim1, game.alienBlueSwim2, game.alienBlueJump),
                new HorizontalMovingActor(293, 3, 290, 2.2f, 5, true, true,
                        game.alienBeigeSwim1, game.alienBeigeSwim2, game.alienBeigeJump),
                new HorizontalMovingActor(286, 3, 288, 2f, 5, true, true,
                        game.alienYellowSwim1, game.alienYellowSwim2, game.alienYellowJump),
                new HorizontalMovingActor(265, 6.75f, 265, 0, 5, true, false,
                        game.alienGreenHurt, game.alienGreenHurt, game.alienGreenJump),
                new HorizontalMovingActor(243, 5, 252, 4f, 3, true, true,
                        game.beeTexture, game.beeFlyTexture, game.beeDeadTexture),
                new HorizontalMovingActor(243, 3, 252, 4f, 3, true, true,
                        game.alienYellowWalk1, game.alienYellowWalk2, game.alienYellowJump),
                new NOTMActor(235.5f, 3, 10, 3, 10, true, false, game.notmTexture),
                new JennyDeathActor(228, 10, 233, 3f, 10, true, false, game.jdTexture),
                new HorizontalMovingActor(217, 6, 221, 4f, 3, true, true,
                        game.beeTexture, game.beeFlyTexture, game.beeDeadTexture),
                new HorizontalMovingActor(185, 8, 190, 4f, 3, true, true,
                        game.alienBeigeWalk1, game.alienBeigeWalk2, game.alienBeigeJump),
                new HorizontalMovingActor(171, 7, 179, 4f, 3, true, true,
                        game.alienPinkWalk1, game.alienPinkWalk2, game.alienPinkJump),
                new HorizontalMovingActor(165, 8.75f, 165, 0, 5, true, false,
                        game.alienBlueHurt, game.alienBlueHurt, game.alienBlueJump),
                new HorizontalMovingActor(139, 6.32f, 145, 4, 5, true, true,
                        game.alienYellowWalk1, game.alienYellowWalk2, game.alienYellowJump),
                new VerticalMovingActor(123, 3, 8, 3, 5, true, false,
                        game.alienBlueClimb1, game.alienBlueClimb2, game.alienBlueClimb1),
                new HorizontalMovingActor(117, 6, 122, 4, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump),
                new HorizontalMovingActor(125, 5, 130, 6, 5, true, true,
                        game.alienPinkWalk1, game.alienPinkWalk2, game.alienPinkJump),
                new HorizontalMovingActor(121, 9, 126, 7, 5, true, true,
                        game.alienBlueWalk1, game.alienBlueWalk2, game.alienBlueJump),
                new HorizontalMovingActor(89, 7, 93, 6.5f, 5, true, true,
                        game.alienYellowWalk1, game.alienYellowWalk2, game.alienYellowJump),
                new HorizontalMovingActor(86, 9, 82, 6, 5, true, true,
                        game.alienBlueWalk1, game.alienBlueWalk2, game.alienBlueJump),
                new HorizontalMovingActor(76, 6, 79, 8, 5, true, true,
                        game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump)
        );

        for (Actor a : genericActors) {
            genericRects.add(((GenericActor) a).rectangle);
            tileStage.addActor(a);
        }

        for (Actor a : frontCastleLeftWalkActors) {
            castleLeftWalkRects.add(((GenericActor) a).rectangle);
            variousTargetStage.addActor(a);
        }

        for (Actor a : frontCastleRightWalkActors) {
            castleRightWalkRects.add(((GenericActor) a).rectangle);
            variousTargetStage.addActor(a);
        }

        for (Actor a : frontCastleLeftClimbActors) {
            castleLeftClimbRects.add(((GenericActor) a).rectangle);
            variousTargetStage.addActor(a);
        }

        for (Actor a : frontCastleRightClimbActors) {
            castleRightClimbRects.add(((GenericActor) a).rectangle);
            variousTargetStage.addActor(a);
        }

        variousTargetStage.addActor(nldwActor);

        for (Actor a : nldwBoxActors) {
            nldwBoxStage.addActor(a);
        }

        for (Actor a : castleTargetActors) {
            castleTargetRects.add(((CastleTargetActor) a).rectangle);
            tileStage.addActor(a);
        }

        for (Actor a : behindCastleActors) {
            behindCastleRects.add(((GenericActor) a).rectangle);
            behindCastleStage.addActor(a);
        }

        rideCamera = new OrthographicCamera(20, 11);
        rideCamera.position.x = 10;
        rideCamera.position.y = 5.5f;
        rideStage = new Stage(new ScreenViewport());
        rideStage.getViewport().setCamera(rideCamera);
        rideStage.addActor(new RideActor(game.rideTexture));

        eagleCamera = new OrthographicCamera(20, 11);
        eagleCamera.position.x = 10;
        eagleCamera.position.y = 5.5f;
        eagleStage = new EagleStage(new ScreenViewport());
        eagleStage.getViewport().setCamera(eagleCamera);

        TiledMap map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/70f);

        Pool<Rectangle> rectPool = new Pool<Rectangle>() {
            @Override
            protected Rectangle newObject () {
                return new Rectangle();
            }
        };

        secondTargetLayer = (TiledMapTileLayer)map.getLayers().get("secondTarget");
        rectPool.freeAll(secondTargetTiles);
        secondTargetTiles.clear();
        for (int y = 0; y <= secondTargetLayer.getHeight(); y++) {
            for (int x = 0; x <= secondTargetLayer.getWidth(); x++) {
                TiledMapTileLayer.Cell cell = secondTargetLayer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = rectPool.obtain();
                    rect.set(x, y, 1, 1);
                    secondTargetTiles.add(rect);
                }
            }
        }

        // get targets targetLayer and create collidable rects for each target
        targetLayer = (TiledMapTileLayer)map.getLayers().get("target");
        rectPool.freeAll(targetTiles);
        targetTiles.clear();
        for (int y = 0; y <= targetLayer.getHeight(); y++) {
            for (int x = 0; x <= targetLayer.getWidth(); x++) {
                TiledMapTileLayer.Cell cell = targetLayer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = rectPool.obtain();
                    if (cell.getTile().getProperties().containsKey("RectWidth")) {
                        String property = String.valueOf(cell.getTile().getProperties().get("RectWidth"));
                        float rectWidth = Float.valueOf(property);
                        rect.set(x, y, rectWidth, 1);
                    } else {
                        rect.set(x, y, 1, 1);
                    }
                    targetTiles.add(rect);
                }
            }
        }

        fallTargetLayer = (TiledMapTileLayer)map.getLayers().get("fallTarget");
        rectPool.freeAll(fallTargetTiles);
        fallTargetTiles.clear();
        for (int y = 0; y <= fallTargetLayer.getHeight(); y++) {
            for (int x = 0; x <= fallTargetLayer.getWidth(); x++) {
                TiledMapTileLayer.Cell cell = fallTargetLayer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = rectPool.obtain();
                    rect.set(x, y, 1, 1);
                    fallTargetTiles.add(rect);
                }
            }
        }

        cloudActor = new CloudActor(game.cloudTexture, -20f, 7.6f, 7f);
        moonActor = new MoonActor(game.moonTexture);
        backgroundStage.addActor(cloudActor);
        backgroundStage.addActor(moonActor);

        backgroundStageColor = backgroundStage.getBatch().getColor();

        game.song_full.play();
    }

    @Override
    public void render(float delta) {

        switch(state) {

            case PAUSED:

                if (feverTrigger) {
                    if (!startBossTime && !pausingFromPowerUp) {
                        pausingFromPowerUp = true;
                        game.fever.pause();
                    } else if (startBossTime && !pausingFromPowerUp) {
                        pausingFromPowerUp = true;
                        game.bitmilitary.pause();
                    }

                    if (timeSet) {
                        timeSet = false;
                        bossStageTime = bossDate.getTime();
                    }
                }

                if (powerUp == PowerUpState.PowerUp.EXMILITARY) {
                    if (powerUpState == PowerUpState.SOUND_BYTE) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = exmilitaryByteRem - elapsedTime;
                            exmilitaryByteRem = timeToSub;
                        }
                        game.imFeelingIt.pause();

                    } else if (powerUpState == PowerUpState.TRIGGERED) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = exmilitaryTrigRem - elapsedTime;
                            exmilitaryTrigRem = timeToSub;
                        }
                        game.bloodCreepin.pause();
                    }
                    Timer.instance().clear();
                } else if (powerUp == PowerUpState.PowerUp.MONEY_STORE) {
                    if (powerUpState == PowerUpState.SOUND_BYTE) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = moneyStoreByteRem - elapsedTime;
                            moneyStoreByteRem = timeToSub;
                        }
                        game.sysBlowerRing.pause();
                    } else if (powerUpState == PowerUpState.TRIGGERED) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = moneyStoreTrigRem - elapsedTime;
                            moneyStoreTrigRem = timeToSub;
                        }
                        game.getGot.pause();
                    }
                    Timer.instance().clear();

                } else if (powerUp == PowerUpState.PowerUp.NLDW) {
                    if (powerUpState == PowerUpState.SOUND_BYTE) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = nldwByteRem - elapsedTime;
                            nldwByteRem = timeToSub;
                        }
                        game.usedToGive.pause();
                    } else if (powerUpState == PowerUpState.TRIGGERED) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = nldwTrigRem - elapsedTime;
                            nldwTrigRem = timeToSub;
                        }
                        game.lockYourDoors.pause();
                    }
                    Timer.instance().clear();
                } else if (powerUp == PowerUpState.PowerUp.GOV_PLATES) {
                    if (powerUpState == PowerUpState.SOUND_BYTE) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = govPlatesByteRem - elapsedTime;
                            govPlatesByteRem = timeToSub;
                        }
                        game.fuckWhosWatching.pause();
                    } else if (powerUpState == PowerUpState.TRIGGERED) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = govPlateTrigRem - elapsedTime;
                            govPlateTrigRem = timeToSub;
                        }
                        game.casino.pause();
                    }
                    Timer.instance().clear();
                } else if (powerUp == PowerUpState.PowerUp.POWERS_THAT_B) {
                    if (powerUpState == PowerUpState.SOUND_BYTE) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = powersByteRem - elapsedTime;
                            powersByteRem = timeToSub;
                        }
                        game.powersThatB.pause();
                    } else if (powerUpState == PowerUpState.TRIGGERED) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = powersTrigRem - elapsedTime;
                            powersTrigRem = timeToSub;
                        }
                        game.sadCum.pause();
                    }
                    Timer.instance().clear();
                } else if (powerUp == PowerUpState.PowerUp.BOTTOMLESS_PIT) {
                    if (powerUpState == PowerUpState.SOUND_BYTE) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = bottomlessPitByteRem - elapsedTime;
                            bottomlessPitByteRem = timeToSub;
                        }
                        game.trash.pause();
                    } else if (powerUpState == PowerUpState.TRIGGERED) {
                        if (needSub) {
                            needSub = false;
                            timeToSub = bottomlessPitTrigRem - elapsedTime;
                            bottomlessPitTrigRem = timeToSub;
                        }
                        game.hotHead.pause();
                    }
                    Timer.instance().clear();
                } else {
                    game.song_full.pause();
                }
                break;

            case RUNNING:

                float eagleBurn = 3f;

                elapsedTime = (TimeUtils.nanoTime() - actionBeginTime) / 1000000000.0f;
                this.eagleBurn += (eagleBurn * delta);

                if (feverTrigger) {
                    for (CastleTargetActor cta : castleTargetActors) {
                        if (cta.isDestroyed) {
                            castleTargetRects.removeValue(cta.rectangle, false);
                            castleTargetActors.removeValue(cta, false);
                        }
                    }

                    if (!startBossTime && pausingFromPowerUp) {
                        pausingFromPowerUp = false;
                        game.fever.play();
                    }

                    if (startBossTime) {
                        if (!timeSet) {
                            timeSet = true;
                            startTime = TimeUtils.millis();
                        }

                        long elapsedBossTime = TimeUtils.millis() - startTime;

                        if (!gameOver) {
                            bossDate.setTime(bossStageTime - elapsedBossTime);
                            if (bossStageTime - elapsedBossTime <= 0) {
                                gameOver = true;
                                bossDate.setTime(0);
                            } else {
                                if (castleTargetActors.size == 0) {
                                    gameOver = true;
                                } else if (castleTargetActors.size == 1) {
                                    if (castleTargetActors.get(0) instanceof CastleTorchActor) {
                                        gameOver = true;
                                    }
                                } else if (castleTargetActors.size == 2) {
                                    if (castleTargetActors.get(0) instanceof CastleTorchActor &&
                                            castleTargetActors.get(1) instanceof CastleTorchActor) {
                                        gameOver = true;
                                    }
                                }
                            }
                        }
                    }
                }

                if (gameOver && !gameOverDrawFlag) {
                    gameOverDrawFlag = true;

                    if (castleTargetActors.size == 0) {
                        gameWon = true;
                    } else if (castleTargetActors.size == 1) {
                        if (castleTargetActors.get(0) instanceof CastleTorchActor) {
                            gameWon = true;
                        }
                    } else if (castleTargetActors.size == 2) {
                        if (castleTargetActors.get(0) instanceof CastleTorchActor &&
                                castleTargetActors.get(1) instanceof CastleTorchActor) {
                            gameWon = true;
                        }
                    }

                    game.bitmilitary.stop();

                    if (gameWon) {
                        game.score += 1000;
                        game.yeah.play();
                    } else {
                        game.guillotineWhine.play();
                    }

                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            dispose();
                            game.setScreen(new RecapScreen(game));
                        }
                    }, 1.7f);
                }

                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                game.shapeRenderer.rect(-10, -5.5f, 20, 11, game.vultureBlue, game.vultureBlue,
                        Color.BLACK, Color.BLACK);
                game.shapeRenderer.end();

                if (pausingFromPowerUp) {
                    if (powerUp == PowerUpState.PowerUp.EXMILITARY) {
                        if (powerUpState == PowerUpState.SOUND_BYTE) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.imFeelingIt.play();
                            ExmilitaryTaskStart(timeToSub, exmilitaryTrigRem);
                        } else if (powerUpState == PowerUpState.TRIGGERED) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.bloodCreepin.play();
                            ExmilitaryTaskEnd(timeToSub);
                        }
                    } else if (powerUp == PowerUpState.PowerUp.MONEY_STORE) {
                        if (powerUpState == PowerUpState.SOUND_BYTE) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.sysBlowerRing.play();
                            MoneyStoreTaskStart(timeToSub, moneyStoreTrigRem);
                        } else if (powerUpState == PowerUpState.TRIGGERED) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.getGot.play();
                            MoneyStoreTaskEnd(timeToSub);
                        }
                    } else if (powerUp == PowerUpState.PowerUp.NLDW) {
                        if (powerUpState == PowerUpState.SOUND_BYTE) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.usedToGive.play();
                            NLDWTaskStart(timeToSub, nldwTrigRem);
                        } else if (powerUpState == PowerUpState.TRIGGERED) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.lockYourDoors.play();
                            NLDWTaskEnd(timeToSub);
                        }
                    } else if (powerUp == PowerUpState.PowerUp.GOV_PLATES) {
                        if (powerUpState == PowerUpState.SOUND_BYTE) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.fuckWhosWatching.play();
                            GovernmentPlatesTaskStart(timeToSub, govPlateTrigRem);
                        } else if (powerUpState == PowerUpState.TRIGGERED) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.casino.play();
                            GovernmentPlatesTaskEnd(timeToSub);
                        }
                    } else if (powerUp == PowerUpState.PowerUp.POWERS_THAT_B) {
                        if (powerUpState == PowerUpState.SOUND_BYTE) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.powersThatB.play();
                            PowersTaskStart(timeToSub, powersTrigRem);
                        } else if (powerUpState == PowerUpState.TRIGGERED) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.sadCum.play();
                            PowersTaskEnd(timeToSub);
                        }
                    } else if (powerUp == PowerUpState.PowerUp.BOTTOMLESS_PIT) {
                        if (powerUpState == PowerUpState.SOUND_BYTE) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.trash.play();
                            BottomlessPitTaskStart(timeToSub, bottomlessPitTrigRem);
                        } else if (powerUpState == PowerUpState.TRIGGERED) {
                            actionBeginTime = TimeUtils.nanoTime();
                            game.hotHead.play();
                            BottomlessPitTaskEnd(timeToSub);
                        }
                    }
                    pausingFromPowerUp = false;
                } else {
                    if (powerUpState == PowerUpState.OFF && !feverTrigger) {
                        game.song_full.play();
                    } else if (startBossTime && !gameOver) {
                        game.bitmilitary.play();
                    }
                }

                if (powerUpState == PowerUpState.TRIGGERED) {

                    if (powerUpInt == 1) {
                        psychedelicColors();
                    } else if (powerUpInt == 2) {
                        flipCamera();
                        psychedelicColors();
                    } else if (powerUpInt == 3) {
                        bounceCamera(delta);
                        psychedelicColors();
                    } else if (powerUpInt == 4) {
                        zoomCamera(delta);
                        psychedelicColors();
                    } else if (powerUpInt == 5) {
                        psychedelicColors();
                        bounceCamera(delta);
                        zoomCamera(delta);
                    }
                }

                levelCamera.position.x -= (CAMERA_SPEED * delta);
                // tell the levelCamera to update its matrices.
                levelCamera.update();

                scoreCamera.update();

                backgroundStage.act();
                backgroundStage.draw();

                behindCastleStage.draw();

                // set the TiledMapRenderer view based on what the
                // levelCamera sees, and renders the map
                renderer.setView(levelCamera);
                renderer.render();

                // TODO: Move before renderer.render to draw behind the level
                // TODO: Falling actors added when behind will fall behind (people fall behind buildings
                // TODO: and behind the water like the fish's intention)

                tileStage.draw();

                variousTargetStage.draw();
                nldwBoxStage.draw();

                deadTargetStage.draw();

                rideStage.draw();

                Array<Actor> eagles = eagleStage.getActors();
                for (Actor a : eagles) {
                    if (((EagleActor)a).isDoneMoving()) {
                        Vector3 eagleVector = new Vector3();
                        eagleVector.set(((EagleActor)a).destinationX, ((EagleActor)a).destinationY, 0);
                        eagleCamera.project(eagleVector);
                        levelCamera.unproject(eagleVector);

                        Rectangle eagleRect = new Rectangle(eagleVector.x, 11-eagleVector.y, 0.75f, 0.75f);
                        boolean hit = false;

                        if ((eagleRect.y >= 7.7f && eagleRect.y <= 8.7f)
                                || (eagleRect.y >= 5.5 && eagleRect.y <= 6.5 && (
                                (eagleRect.x >= 442.5 && eagleRect.x <= 443.5) ||
                                        (eagleRect.x >= 444.5 && eagleRect.x <= 445.5) ||
                                        (eagleRect.x >= 446.5 && eagleRect.x <= 447.5)))) {
                            if (nldwActor.rectangle.overlaps(eagleRect)) {
                                if (variousTargetStage.getActors().removeValue(nldwActor, false)) {
                                    hit = true;
                                    game.score += nldwActor.points;
                                    NLDWPowerUp();
                                }
                            }
                        }

                        if (!hit) {
                            for (Rectangle alienRect : castleLeftWalkRects) {
                                if (alienRect.overlaps(eagleRect)) {
                                    int index = castleLeftWalkRects.indexOf(alienRect, false);
                                    GenericActor deadAA = frontCastleLeftWalkActors.get(index);
                                    TextureRegion textureRegion = new TextureRegion(frontCastleLeftWalkActors.get(index).deadTextureRegion);
                                    deadTargetStage.addActor(new FallingActor(alienRect.x, alienRect.y, textureRegion, true));
                                    if (castleLeftWalkRects.removeValue(alienRect, false)) {
                                        variousTargetStage.getActors().removeValue(deadAA, false);
                                        frontCastleLeftWalkActors.removeValue(deadAA, false);
                                        if (frontCastleLeftWalkActors.size <= 3) {
                                            GenericActor aa = new HorizontalMovingActor(9, 3, 2, 6, 5, true, true,
                                                    game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump);
                                            frontCastleLeftWalkActors.add(aa);
                                            variousTargetStage.addActor(aa);
                                            castleLeftWalkRects.add(aa.rectangle);
                                        }
                                        hit = true;
                                    }
                                }
                            }
                        }

                        if (!hit) {
                            for (Rectangle alienRect : castleRightWalkRects) {
                                if (alienRect.overlaps(eagleRect)) {
                                    int index = castleRightWalkRects.indexOf(alienRect, false);
                                    GenericActor deadAA = frontCastleRightWalkActors.get(index);
                                    TextureRegion textureRegion = new TextureRegion(frontCastleRightWalkActors.get(index).deadTextureRegion);
                                    deadTargetStage.addActor(new FallingActor(alienRect.x, alienRect.y, textureRegion, true));
                                    if (castleRightWalkRects.removeValue(alienRect, false)) {
                                        variousTargetStage.getActors().removeValue(deadAA, false);
                                        frontCastleRightWalkActors.removeValue(deadAA, false);
                                        if (frontCastleRightWalkActors.size <= 3) {
                                            GenericActor aa = new HorizontalMovingActor(
                                                    10, 3, 16, 6, 5, true, true,
                                                    game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump);
                                            frontCastleRightWalkActors.add(aa);
                                            variousTargetStage.addActor(aa);
                                            castleRightWalkRects.add(aa.rectangle);
                                        }
                                        hit = true;
                                    }
                                }
                            }
                        }

                        if (!hit) {
                            for (Rectangle alienRect : castleLeftClimbRects) {
                                if (alienRect.overlaps(eagleRect)) {
                                    int index = castleLeftClimbRects.indexOf(alienRect, false);
                                    GenericActor deadAA = frontCastleLeftClimbActors.get(index);
                                    TextureRegion textureRegion = new TextureRegion(frontCastleLeftClimbActors.get(index).deadTextureRegion);
                                    deadTargetStage.addActor(new FallingActor(alienRect.x, alienRect.y, textureRegion, true));
                                    if (castleLeftClimbRects.removeValue(alienRect, false)) {
                                        variousTargetStage.getActors().removeValue(deadAA, false);
                                        frontCastleLeftClimbActors.removeValue(deadAA, false);
                                        if (frontCastleLeftClimbActors.size <= 1) {
                                            GenericActor aa = new VerticalMovingActor(
                                                    deadAA.startX, 3, 8, deadAA.speed, 5, true, false,
                                                    game.alienYellowClimb1, game.alienYellowClimb2, game.alienYellowClimb1);
                                            frontCastleLeftClimbActors.add(aa);
                                            variousTargetStage.addActor(aa);
                                            castleLeftClimbRects.add(aa.rectangle);
                                        }
                                        hit = true;
                                    }
                                }
                            }
                        }

                        if (!hit) {
                            for (Rectangle alienRect : castleRightClimbRects) {
                                if (alienRect.overlaps(eagleRect)) {
                                    int index = castleRightClimbRects.indexOf(alienRect, false);
                                    GenericActor deadAA = frontCastleRightClimbActors.get(index);
                                    TextureRegion textureRegion = new TextureRegion(frontCastleRightClimbActors.get(index).deadTextureRegion);
                                    deadTargetStage.addActor(new FallingActor(alienRect.x, alienRect.y, textureRegion, true));
                                    if (castleRightClimbRects.removeValue(alienRect, false)) {
                                        variousTargetStage.getActors().removeValue(deadAA, false);
                                        frontCastleRightClimbActors.removeValue(deadAA, false);
                                        if (frontCastleRightClimbActors.size <= 1) {
                                            GenericActor aa = new VerticalMovingActor(
                                                    deadAA.startX, 3, 8, deadAA.speed, 5, true, false,
                                                    game.alienPinkClimb1, game.alienPinkClimb2, game.alienPinkClimb1);
                                            frontCastleRightClimbActors.add(aa);
                                            variousTargetStage.addActor(aa);
                                            castleRightClimbRects.add(aa.rectangle);
                                        }
                                        hit = true;
                                    }
                                }
                            }
                        }

                        if (!hit) {
                            for (Rectangle alienRect : behindCastleRects) {
                                if (alienRect.overlaps(eagleRect)) {
                                    int index = behindCastleRects.indexOf(alienRect, false);
                                    GenericActor deadAA = behindCastleActors.get(index);
                                    TextureRegion textureRegion = new TextureRegion(behindCastleActors.get(index).deadTextureRegion);
                                    deadTargetStage.addActor(new FallingActor(alienRect.x, alienRect.y, textureRegion, true));
                                    if (behindCastleRects.removeValue(alienRect, false)) {
                                        behindCastleStage.getActors().removeValue(deadAA, false);
                                        behindCastleActors.removeValue(deadAA, false);
                                        HorizontalMovingActor aa = new HorizontalMovingActor(deadAA.startX,
                                                deadAA.startY, deadAA.endX, deadAA.speed, 5, true, true,
                                                game.alienGreenWalk1, game.alienGreenWalk2, game.alienGreenJump);
                                        behindCastleActors.add(aa);
                                        behindCastleStage.addActor(aa);
                                        behindCastleRects.add(aa.rectangle);
                                        hit = true;
                                    }
                                }
                            }
                        }

                        if (!hit) {
                            for (Rectangle castleTarget : castleTargetRects) {
                                if (castleTarget.overlaps(eagleRect)) {
                                    hit = true;
                                    int index = castleTargetRects.indexOf(castleTarget, false);
                                    castleTargetActors.get(index).isHit();
                                }
                            }
                        }

                        if (!hit) {
                            for (Rectangle genericRect : genericRects) {
                                if (genericRect.overlaps(eagleRect)) {
                                    hit = true;
                                    int index = genericRects.indexOf(genericRect, false);
                                    GenericActor deadAA = genericActors.get(index);
                                    game.score += deadAA.points;
                                    if (deadAA instanceof NOTMActor) {
                                        if (genericRects.removeValue(genericRect, false)) {
                                            tileStage.getActors().removeValue(deadAA, false);
                                            genericActors.removeValue(deadAA, false);
                                            notmHit = true;
                                            PowersThatBPowerUp();
                                        }
                                    } else if (deadAA instanceof JennyDeathActor) {
                                        if (genericRects.removeValue(genericRect, false)) {
                                            tileStage.getActors().removeValue(deadAA, false);
                                            genericActors.removeValue(deadAA, false);
                                            jdHit = true;
                                            PowersThatBPowerUp();
                                        }
                                    } else {
                                        if (genericRects.removeValue(genericRect, false)) {
                                            TextureRegion textureRegion = new TextureRegion(genericActors.get(index).deadTextureRegion);
                                            deadTargetStage.addActor(new FallingActor(genericRect.x, genericRect.y, textureRegion, true));
                                            tileStage.getActors().removeValue(deadAA, false);
                                            genericActors.removeValue(deadAA, false);
                                        }
                                    }
                                }
                            }
                        }

                        if (!hit) {
                            for (Rectangle levelRect : fallTargetTiles) {
                                if (levelRect.overlaps(eagleRect)) {
                                    if (fallTargetTiles.removeValue(levelRect, false)) {
                                        hit = true;
                                        TiledMapTileLayer.Cell targetCell = fallTargetLayer.getCell((int) levelRect.x, (int) levelRect.y);
                                        String strPoints = String.valueOf(targetCell.getTile().getProperties().get("points"));
                                        int points = Integer.parseInt(strPoints);
                                        game.score += points;
                                        TextureRegion textureRegion = new TextureRegion(targetCell.getTile().getTextureRegion());
                                        targetCell.setTile(null);
                                        tileStage.addActor(new FallingActor(levelRect.x, levelRect.y, textureRegion, true));
                                    }
                                }
                            }
                        }

                        // check regular target tiles and nullify them
                        if (!hit) {
                            for (final Rectangle levelRect : targetTiles) {
                                if (levelRect.overlaps(eagleRect)) {
                                    TiledMapTileLayer.Cell targetCell = targetLayer.getCell((int) levelRect.x, (int) levelRect.y);
                                    String strPoints = String.valueOf(targetCell.getTile().getProperties().get("points"));
                                    int points = Integer.parseInt(strPoints);
                                    game.score += points;
                                    targetCell.setTile(null);
                                    if ((int)levelRect.x == 736 && (int)levelRect.y == 3) {
                                        ExmilitaryPowerUp();
                                    } else if ((int)levelRect.x == 321 && (int)levelRect.y == 8) {
                                        GovernmentPlatesPowerUp();
                                    }
                                    targetTiles.removeValue(levelRect, false);
                                    hit = true;
                                }
                            }
                        }

                        // check regular target tiles and nullify them
                        if (!hit) {
                            for (final Rectangle levelRect : secondTargetTiles) {
                                if (levelRect.overlaps(eagleRect)) {
                                    TiledMapTileLayer.Cell targetCell = secondTargetLayer.getCell((int) levelRect.x, (int) levelRect.y);
                                    String strPoints = String.valueOf(targetCell.getTile().getProperties().get("points"));
                                    int points = Integer.parseInt(strPoints);
                                    game.score += points;
                                    targetCell.setTile(null);
                                    if ((int) levelRect.x == 589 && (int) levelRect.y == 9) {
                                        MoneyStorePowerUp();
                                    } else if ((int) levelRect.x == 112 && (int) levelRect.y == 7) {
                                        BottomlessPitPowerUp();
                                    }
                                    secondTargetTiles.removeValue(levelRect, false);
                                }
                            }
                        }
                    }
                }

                eagleStage.removeDeadEagles();
                eagleStage.draw();

                game.batch.setProjectionMatrix(levelCamera.combined);
                game.batch.begin();

                // if we haven't reached the boss or if we are in the boss stage
                // do not fire eagles during the boss warning (already fired eagles stay in effect)
                if ((!feverTrigger && !startBossTime) || (feverTrigger && startBossTime)) {
                    if (Gdx.input.justTouched() && this.eagleBurn >= 0.5f && !gameOver) {
                        this.eagleBurn = 0;
                        Vector3 crosshairVector = new Vector3();
                        crosshairVector.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                        levelCamera.unproject(crosshairVector);

                        Vector3 eagleVector = new Vector3();
                        eagleVector.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                        eagleCamera.unproject(eagleVector);

                        game.batch.draw(game.crosshairTexture, crosshairVector.x - 0.5f,
                                crosshairVector.y - 0.5f, 1, 1);

                        if (powerUpState == PowerUpState.TRIGGERED) {
                            eagleStage.setNewPoweredEagle(eagleCamera.position.x - 0.5f, 0,
                                    eagleVector.x - 0.5f, eagleVector.y - 0.5f);
                        } else {
                            eagleStage.setNewEagle(eagleCamera.position.x - 0.5f, 0,
                                    eagleVector.x - 0.5f, eagleVector.y - 0.5f);
                        }
                    }
                }

                game.batch.end();

                // score stage stuff
                game.batch.setProjectionMatrix(scoreCamera.combined);
                game.batch.begin();
                game.fontExmilitary100.draw(game.batch, String.valueOf(game.score), 600, 125);

                if (feverTrigger) {
                    game.font100Gold.draw(game.batch,
                            game.dateFormatCallback.convertDate(bossDate), 584, 750);
                }
                game.batch.end();
                scoreStage.draw();

                if (!feverTrigger && levelCamera.position.x <= 28) {
                    feverTrigger = true;
                    game.song_full.pause();
                    game.fever.play();
                    game.fever.setOnCompletionListener(new Music.OnCompletionListener() {
                        @Override
                        public void onCompletion(Music music) {
                            music.setPosition(0);
                            music.stop();
                            game.bitmilitary.play();
                        }
                    });
                }

                if (!startBossTime && levelCamera.position.x <= 10) {
                    levelCamera.position.x = 10;
                    CAMERA_SPEED = 0f;
                    startBossTime = true;
                }

                break;
        }
    }

    private void psychedelicColors() {
        float frequency = .21f; // higher frequency flashes more colors
        float red = (float) (Math.sin(frequency * colorCounter + 0) * 127 + 128) / 255f;
        float green = (float) (Math.sin(frequency * colorCounter + 2) * 127 + 128) / 255f;
        float blue = (float) (Math.sin(frequency * colorCounter + 4) * 127 + 128) / 255f;

        Gdx.gl.glClearColor(red, green, blue, 1);

        if (colorCounter < 32) {
            colorCounter += 0.25f;
        } else {
            colorCounter = 0;
        }

        backgroundStage.getBatch().setColor(green, blue, red, 1);
    }

    private void flipCamera() {
        if (!cameraRotated) {
            cameraRotated = true;
            levelCamera.rotate(180);
            eagleCamera.rotate(180);
            rideCamera.rotate(180);
            scoreCamera.rotate(180);
            backgroundCamera.rotate(180);
        }
    }

    private void zoomCamera(float delta) {

        if (cameraZoomed) {
            levelCamera.zoom += (CAMERA_SPEED * delta * .3f);
            eagleCamera.zoom += (CAMERA_SPEED * delta * .3f);
            if (levelCamera.zoom >= 1.9f && eagleCamera.zoom >= 1.9f)
                cameraZoomed = false;
        } else {
            levelCamera.zoom -= (CAMERA_SPEED * delta * .3f);
            eagleCamera.zoom -= (CAMERA_SPEED * delta * .3f);
            if (levelCamera.zoom <= 1 && eagleCamera.zoom <= 1){
                cameraZoomed = true;
            }
        }
    }

    private void bounceCamera(float delta) {
        if (cameraUp) {
            levelCamera.position.y += (CAMERA_SPEED * delta * 1.25f);
            if (levelCamera.position.y >= 7.5f) {
                cameraUp = false;
            }
        } else {
            levelCamera.position.y -= (CAMERA_SPEED * delta * 1.25f);
            if (levelCamera.position.y <= 3.5f) {
                cameraUp = true;
            }
        }
    }

    private void startSoundByteWarning() {
        actionBeginTime = TimeUtils.nanoTime();
        CAMERA_SPEED = 0;
        powerUpState = PowerUpState.SOUND_BYTE;
        game.song_full.pause();
    }

    private void startPowerUpConsts() {
        actionBeginTime = TimeUtils.nanoTime();
        powerUpState = PowerUpState.TRIGGERED;
        CAMERA_SPEED = 6f;
        cloudActor.speed = 3.8f;
        moonActor.speed = 1f;
        for (StarActor s : starActors) {
            s.trippyRotation = true;
        }
    }

    private void endPowerUpConsts() {
        powerUpInt++;
        powerUpState = PowerUpState.OFF;
        powerUp = null;
        game.song_full.play();
        CAMERA_SPEED = 3f;
        cloudActor.speed = 1.9f;
        moonActor.speed = 0.5f;
        for (StarActor s : starActors) {
            s.trippyRotation = false;
        }

        if (powerUpInt == 2) {
            backgroundStage.getBatch().setColor(backgroundStageColor);
        } else if (powerUpInt == 3) {
            backgroundStage.getBatch().setColor(backgroundStageColor);
            levelCamera.rotate(180);
            eagleCamera.rotate(180);
            rideCamera.rotate(180);
            scoreCamera.rotate(180);
            backgroundCamera.rotate(180);
            cameraRotated = false;
        } else if (powerUpInt == 4) {
            levelCamera.position.y = 5.5f;
            backgroundStage.getBatch().setColor(backgroundStageColor);
        } else if (powerUpInt == 5) {
            levelCamera.zoom = 1;
            eagleCamera.zoom = 1;
            backgroundStage.getBatch().setColor(backgroundStageColor);
        } else if (powerUpInt == 6) {
            levelCamera.position.y = 5.5f;
            levelCamera.zoom = 1;
            eagleCamera.zoom = 1;
            backgroundStage.getBatch().setColor(backgroundStageColor);
            game.score += 1000;
        }
    }

    private void ExmilitaryPowerUp() {
        startSoundByteWarning();
        powerUp = PowerUpState.PowerUp.EXMILITARY;
        game.imFeelingIt.play();
        ExmilitaryTaskStart(exmilitaryByteRem, exmilitaryTrigRem);
    }

    private void ExmilitaryTaskStart(float firstTaskSecs, final float secondTaskSecs) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                startPowerUpConsts();
                game.bloodCreepin.play();
                ExmilitaryTaskEnd(secondTaskSecs);
            }
        }, firstTaskSecs);
    }

    private void ExmilitaryTaskEnd(float seconds) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.bloodCreepin.stop();
                endPowerUpConsts();
            }
        }, seconds);
    }

    private void MoneyStorePowerUp() {
        startSoundByteWarning();
        powerUp = PowerUpState.PowerUp.MONEY_STORE;
        game.sysBlowerRing.play();
        MoneyStoreTaskStart(moneyStoreByteRem, moneyStoreTrigRem);
    }

    private void MoneyStoreTaskStart(float firstTaskSecs, final float secondTaskSecs) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                startPowerUpConsts();
                game.getGot.play();
                MoneyStoreTaskEnd(secondTaskSecs);
            }
        }, firstTaskSecs);
    }

    private void MoneyStoreTaskEnd(float seconds) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.getGot.stop();
                endPowerUpConsts();
            }
        }, seconds);
    }

    private void NLDWPowerUp() {
        startSoundByteWarning();
        powerUp = PowerUpState.PowerUp.NLDW;
        game.usedToGive.play();
        scoreStage.addActor(new UsedToActor(game.usedToTexture));
        NLDWTaskStart(nldwByteRem, nldwTrigRem);
    }

    private void NLDWTaskStart(float firstTaskSecs, final float secondTaskSecs) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                startPowerUpConsts();
                scoreStage.getActors().get(1).remove();
                game.lockYourDoors.play();
                NLDWTaskEnd(secondTaskSecs);
            }
        }, firstTaskSecs);
    }

    private void NLDWTaskEnd(float seconds) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.lockYourDoors.stop();
                endPowerUpConsts();
            }
        }, seconds);
    }

    private void GovernmentPlatesPowerUp() {
        startSoundByteWarning();
        powerUp = PowerUpState.PowerUp.GOV_PLATES;
        game.fuckWhosWatching.play();
        GovernmentPlatesTaskStart(govPlatesByteRem, govPlateTrigRem);
    }

    private void GovernmentPlatesTaskStart(float firstTaskSecs, final float secondTaskSecs) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                startPowerUpConsts();
                game.casino.play();
                GovernmentPlatesTaskEnd(secondTaskSecs);
            }
        }, firstTaskSecs);
    }

    private void GovernmentPlatesTaskEnd(float seconds) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.casino.stop();
                endPowerUpConsts();
            }
        }, seconds);
    }

    private void PowersThatBPowerUp() {
        if (notmHit && jdHit) {
            startSoundByteWarning();
            powerUp = PowerUpState.PowerUp.POWERS_THAT_B;
            game.powersThatB.play();
            rideStage.addActor(new PowersCoverActor(game.powersTexture));
            PowersTaskStart(powersByteRem, powersTrigRem);
        }
    }

    private void PowersTaskStart(final float firstTaskSecs, final float secondTaskSecs) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                startPowerUpConsts();
                game.sadCum.play();
                rideStage.getActors().get(1).remove();
                PowersTaskEnd(secondTaskSecs);
            }
        }, firstTaskSecs);
    }

    private void PowersTaskEnd(float seconds) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.sadCum.stop();
                endPowerUpConsts();
            }
        }, seconds);
    }

    private void BottomlessPitPowerUp() {
        startSoundByteWarning();
        powerUp = PowerUpState.PowerUp.BOTTOMLESS_PIT;
        game.trash.play();
        BottomlessPitTaskStart(bottomlessPitByteRem, bottomlessPitTrigRem);
    }

    private void BottomlessPitTaskStart(float firstTaskSecs, final float secondTaskSecs) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                startPowerUpConsts();
                game.hotHead.play();
                BottomlessPitTaskEnd(secondTaskSecs);
            }
        }, firstTaskSecs);
    }

    private void BottomlessPitTaskEnd(float seconds) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.hotHead.stop();
                endPowerUpConsts();
            }
        }, seconds);
    }

    @Override
    public void dispose() {
        // stages
        rideStage.dispose();
        eagleStage.dispose();
        tileStage.dispose();
        variousTargetStage.dispose();
        nldwBoxStage.dispose();
        deadTargetStage.dispose();
        scoreStage.dispose();
        backgroundStage.dispose();
        behindCastleStage.dispose();
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            pauseGame();
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 coord = scoreStage.screenToStageCoordinates(new Vector2((float)screenX, (float)screenY));
        Actor hitActor = scoreStage.hit(coord.x, coord.y, false);

        if (hitActor != null) {
            if (hitActor.getName().equals("pause")) {
                pauseGame();
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

    private void pauseGame() {
        if (state == State.RUNNING) {
            if (powerUpState == PowerUpState.SOUND_BYTE || powerUpState == PowerUpState.TRIGGERED) {
                pausingFromPowerUp = true;
                needSub = true;
            }
            state = State.PAUSED;
        } else {
            state = State.RUNNING;
        }
    }
}
