package com.mygdx.game.model.paddles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.GameScreen;
import com.mygdx.game.LouieGame;


/**
 * Created by Administratör on 2014-09-17.
 */
public class Paddle1 extends Paddle {
    public Paddle1() {
        super();

        this.setHandle(new Sprite(new Texture(Gdx.files.internal("images/handle1.png"))));
        this.setHitter(new Sprite(new Texture(Gdx.files.internal("images/hitter1.png"))));
    }

    @Override
    public void setPositions(GameScreen gameScreen) {
        getHandle().setPosition((int) (LouieGame.ORIGO.x + gameScreen.getCircleRadius() + getHitter().getWidth()/2) ,
                (int) (LouieGame.ORIGO.y - getHitter().getHeight()/3));


        getHitter().setPosition((int) (LouieGame.ORIGO.x + gameScreen.getCircleRadius() - getHitter().getWidth()/2) ,
                (int) (LouieGame.ORIGO.y -getHitter().getHeight()/2));

    }


}
