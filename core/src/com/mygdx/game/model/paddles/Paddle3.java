package com.mygdx.game.model.paddles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.GameScreen;
import com.mygdx.game.LouieGame;


/**
 * Created by Administratör on 2014-09-17.
 */
public class Paddle3 extends Paddle {
    public Paddle3(GameScreen gameScreen) {
        super(gameScreen);
        this.setHandle(new Sprite(new Texture(Gdx.files.internal("images/handle3.png"))));
        this.setHitter(new Sprite(new Texture(Gdx.files.internal("images/hitter3.png"))));
    }

    @Override
    public void setPositions() {
        getHandle().setPosition((int) (LouieGame.ORIGO.x - getGameScreen().getCircleRadius() - getHandle().getWidth()) ,
                (int) (LouieGame.ORIGO.y) + getHitter().getHeight()/3);


        getHitter().setPosition((int) (LouieGame.ORIGO.x - getGameScreen().getCircleRadius()) ,
                (int) (LouieGame.ORIGO.y));
    }
}
