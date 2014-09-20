package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameScreen;
import com.mygdx.game.GameSettings;
import com.mygdx.game.LouieGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas on 2014-09-19.
 */
public class Center {

    public static float MAXHEIGHT;
    private Sprite sprite;

    private float moveSpeed;
    private float staticCircleRadius;
    private float angle;

    private GameScreen gameScreen;

    private List<Louie> louies;

    public Center(float circleRadius, GameScreen gameScreen, GameSettings settings){
        sprite = new Sprite(new Texture(Gdx.files.internal("images/center.png")));
        sprite.scale(1);
        sprite.setPosition(LouieGame.ORIGO.x - sprite.getWidth()/2,LouieGame.ORIGO.y - sprite.getHeight()/2);
        this.gameScreen = gameScreen;
        this.staticCircleRadius = circleRadius;
        MAXHEIGHT = circleRadius/80f;
        //TODO: SET GOOD NUMBER
        this.moveSpeed = -1;
        angle = 0;

        louies = new ArrayList<Louie>();

        for (int i = 0; i < settings.getNumberOfLouies() ; i++) {
            louies.add(new Louie(this, (float) (i*2*Math.PI/settings.getNumberOfLouies()), settings.getRandomTimes().get(i)));
        }


    }
    public void update(float delta){
        angle = angle + moveSpeed * delta;

        sprite.setRotation((float) Math.toDegrees(angle));

        for(Louie l: louies){
            l.update(delta);
        }
    }

    public void render(SpriteBatch sb){

        for(Louie l: louies){
            l.render(sb);

        }
        sprite.draw(sb);

    }

    public float getStaticCircleRadius() {
        return staticCircleRadius;
    }



    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public List<Louie> getLouies() {
        return louies;
    }


    public float getMoveSpeed() {
        return moveSpeed;
    }
}
