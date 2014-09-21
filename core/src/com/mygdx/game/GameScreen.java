package com.mygdx.game;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Center;
import com.mygdx.game.model.Chicken;
import com.mygdx.game.model.Louie;
import com.mygdx.game.model.Player;
import com.mygdx.game.network.GameClient;

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
        center = new Center(circleRadius, this, gameSettings);
        this.gameClient = gameClient;
        this.clientPlayer = players.get(gameClient.getPlayer().getPlayerNumber() - 1);
        this.countDown = new CountDown(tweenManager);

    }

    @Override
    public void show() {

        spriteBatch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        decidePlayerPositions();
        spawnChickens();

        input = new GameInput(clientPlayer.getPaddle(), tweenManager, this);
        Gdx.input.setInputProcessor(input);


    }

    private void spawnChickens() {
        for (Player p : players) {
            p.spawnChickens(settings.getNumberOfChickens());
        }
    }

    private void decidePlayerPositions() {
        for (Player p : players) {
            p.initPositions(p.getPlayerNumber(), this);
        }

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
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

    public void hitPlayer(int playerNumber) {
        Player p = players.get(playerNumber - 1);
        p.getPaddle().hit(tweenManager, this);
    }

    public GameClient getGameClient() {
        return gameClient;
    }
}
