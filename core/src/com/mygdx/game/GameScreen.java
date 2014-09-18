package com.mygdx.game;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Chicken;
import com.mygdx.game.model.Louie;
import com.mygdx.game.model.Player;

import java.util.List;

/**
 * Created by tomas on 2014-09-16.
 */
public class GameScreen implements Screen {
    private final LouieGame game;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private Louie louie;
    private GameInput input;
    private TweenManager tweenManager;
    private int circleRadius;
    private List<Player> players;
    public int numberOfChickens;
    private float overlapRectangleArea;
    public static boolean debug = false;
    private float totalTime = 0;


    public GameScreen(LouieGame game, List<Player> players) {
        tweenManager = new TweenManager();
        this.game = game;
        circleRadius = 250;
        this.numberOfChickens = 5;
        this.players = players;
        louie = new Louie(circleRadius, this);
    }

    @Override
    public void show() {

        spriteBatch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        input = new GameInput(players.get(0).getPaddle(), tweenManager);
        Gdx.input.setInputProcessor(input);

        decidePlayerPositions();


    }

    private void decidePlayerPositions() {
        for (Player p : players) {
            p.setPosition();
        }

    }


    @Override
    public void render(float delta) {

        update(delta);
        totalTime += delta;
        if (totalTime > 1) {
            louie.setMoveSpeed(louie.getMoveSpeed() + (louie.getMoveSpeed() * 0.01f));
            totalTime = 0;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        for (Player p : players) {
            p.render(spriteBatch);
        }
        louie.render(spriteBatch);
        LouieGame.FONT.draw(spriteBatch, "" + louie.getMoveSpeed(), 100, 100);
        spriteBatch.end();

        if (debug) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(louie.getBoundingRectangle().getX(), louie.getBoundingRectangle().getY(), louie.getBoundingRectangle().getWidth(), louie.getBoundingRectangle().getHeight());
            Rectangle rectangle = players.get(0).getPaddle().getHitRectangle();
            shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            shapeRenderer.end();
        }


    }

    private void update(float delta) {
        louie.update(delta);
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

        if (paddleRectangle.overlaps(louie.getBoundingRectangle()) && !louie.isAirborne()) {
            overlapRectangleArea = ShapeFunctions.overlapRectangleArea(paddleRectangle, louie.getBoundingRectangle());
            // TODO: FIX MATH FOR MAXHEIGHT
            louie.hit(1 + Louie.MAXHEIGHT * overlapRectangleArea, tweenManager);
        }

    }

    public Chicken checkChickenHit() {
        if (!louie.isAirborne()) {
            for (Player p : players) {
                for (Chicken c : p.getChickens()) {
                    if (c.getRectangle().overlaps(louie.getBoundingRectangle())) {
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
}
