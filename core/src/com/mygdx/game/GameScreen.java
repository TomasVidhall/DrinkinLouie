package com.mygdx.game;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.*;
import com.mygdx.game.network.GameClient;
import com.mygdx.game.network.ServerUpdate;

import java.util.List;

/**
 * Created by tomas on 2014-09-16.
 */
public class GameScreen implements Screen {
    private final LouieGame game;
    private final GameClient gameClient;
    private GameSettings settings;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private GameInput input;
    private TweenManager tweenManager;
    private int circleRadius;
    private List<Player> players;
    private float overlapRectangleArea;
    public static boolean debug = false;
    private float totalTime = 0;
    private Center center;
    private Player clientPlayer;
    private CountDown countDown;


    public GameScreen(LouieGame game, List<Player> players, GameSettings gameSettings, GameClient gameClient) {
        tweenManager = new TweenManager();
        this.game = game;
        circleRadius = 250;
        this.players = players;
        this.settings = gameSettings;
        this.center = new Center(gameSettings);
        this.gameClient = gameClient;
        this.clientPlayer = players.get(gameClient.getPlayer().getPlayerNumber() - 1);
        this.countDown = new CountDown(tweenManager);

        this.center.setSpriteTexture();
        this.countDown.setUpSprite();

        for(Player player : players){
            player.setUpSpriteTextures();
        }

    }

    @Override
    public void show() {

        spriteBatch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        decidePlayerPositions();
        spawnChickens();

        input = new GameInput(gameClient);
        Gdx.input.setInputProcessor(input);


    }

    private void spawnChickens() {
        for (Player p : players) {
            p.spawnChickens(settings.getNumberOfChickens());
        }
    }

    private void decidePlayerPositions() {
        for (Player p : players) {
            p.initPositions(p.getPlayerNumber(), settings);
        }

    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(255,255,255,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!countDown.isDone()) {
            countDown.update(delta);
        }

     //   update(delta);
        spriteBatch.begin();
        for (Player p : players) {
            p.render(spriteBatch);
        }
        center.render(spriteBatch);
        if (!countDown.isDone()) {
            countDown.render(spriteBatch);
        }
        spriteBatch.end();

        if (debug) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            Rectangle rectangle = players.get(0).getPaddle().getHitRectangle();
            shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            shapeRenderer.end();
        }


    }

    private void update(float delta) {
        center.update(delta);
        tweenManager.update(delta);
        Chicken hitChicken = checkChickenHit();
        if (hitChicken != null) {
            hitChicken.destroy(tweenManager);
        }
    }

    @Override
    public void resize(int width, int height) {

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

    public void checkPaddleHit(Rectangle paddleRectangle) {

        for (Louie louie : center.getLouies()) {
            if (paddleRectangle.overlaps(louie.getBoundingRectangle()) && !louie.isAirborne()) {
                overlapRectangleArea = ShapeFunctions.overlapRectangleArea(paddleRectangle, louie.getBoundingRectangle());
                // TODO: FIX MATH FOR MAXHEIGHT
                louie.hit(1 + Center.MAXHEIGHT * overlapRectangleArea, tweenManager);
            }
        }

    }

    public Chicken checkChickenHit() {
        for (Louie louie : center.getLouies()) {
            if (!louie.isAirborne()) {
                for (Player p : players) {
                    for (Chicken c : p.getChickens()) {
                        if (c.getRectangle().overlaps(louie.getBoundingRectangle()))
                            return c;

                    }
                }
            }
        }
        return null;
    }


    public int getCircleRadius() {
        return circleRadius;
    }

    public TweenManager getTweenManager() {
        return tweenManager;
    }



    public GameClient getGameClient() {
        return gameClient;
    }

    public void hitPlayer(int playerNumber) {
        System.out.println("FAIL");
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public void setCountDown(CountDown countDown) {
        this.countDown = countDown;
    }

    public void updateFromServer(ServerUpdate update) {


        center.getSprite().setRotation(update.getCenter().getSprite().getRotation());


        List<Louie> updateLouies = update.getCenter().getLouies();
        List<Louie> louies = this.center.getLouies();
        Sprite updateSprite;
        Sprite louieSprite;
        for (int i = 0; i < updateLouies.size(); i++) {
            Louie louie = louies.get(i);
            Louie updateLouie = updateLouies.get(i);

            louie.setCurrentCircleRadius(updateLouie.getCurrentCircleRadius());
            louie.setAirborne(updateLouie.isAirborne());

            updateSprite = updateLouie.getSprite();
            louieSprite = louie.getSprite();

            louieSprite.setScale(updateSprite.getScaleX());
            louieSprite.setPosition(updateSprite.getX(),updateSprite.getY());
            louieSprite.setRotation(updateSprite.getRotation());

            PlaneArm arm = louie.getPlaneArm();
            PlaneArm updateArm = updateLouie.getPlaneArm();

            arm.getSprite().setRotation(updateArm.getSprite().getRotation());
            arm.getSprite().setSize(updateArm.getSprite().getWidth(),updateArm.getSprite().getHeight());
        }

        this.countDown = update.getCountDown();
        this.players = update.getPlayers();


    }
}
