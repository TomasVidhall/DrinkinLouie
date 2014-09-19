package com.mygdx.game.model.paddles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.GameScreen;
import com.mygdx.game.LouieGame;

/**
 * Created by Administratör on 2014-09-17.
 */
public class Paddle2 extends Paddle {
    public Paddle2(GameScreen gameScreen) {
        super(gameScreen);
        this.setHandle(new Sprite(new Texture(Gdx.files.internal("images/handle2.png"))));
        this.setHitter(new Sprite(new Texture(Gdx.files.internal("images/hitter2.png"))));
    }

    @Override
    public void setPositions() {
        getHandle().setPosition((int) (LouieGame.ORIGO.x - getHitter().getWidth()/3),
                (int) (LouieGame.ORIGO.y) + getGameScreen().getCircleRadius() + getHitter().getHeight()/2 );


        getHitter().setPosition((int) (LouieGame.ORIGO.x - getHitter().getWidth()/2),
                (int) (LouieGame.ORIGO.y) + getGameScreen().getCircleRadius() - getHitter().getHeight()/2);


    }
}
