package com.mygdx.game.model.paddles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.GameSettings;
import com.mygdx.game.LouieGame;

/**
 * Created by Administratör on 2014-09-17.
 */
public class Paddle4 extends Paddle {
    public Paddle4(Color color) {
        super();
        this.setHandle(new Sprite(new Texture(Gdx.files.internal("images/handle4.png"))));
        this.getHandle().setColor(color);
        this.setHitter(new Sprite(new Texture(Gdx.files.internal("images/hitter24.png"))));
        this.getHitter().setColor(color);
    }


    @Override
    public void setPositions(GameSettings gameScreen) {
        getHandle().setPosition((int) (LouieGame.ORIGO.x - getHitter().getWidth()/3),
                (int) (LouieGame.ORIGO.y) - gameScreen.getCircleRadius() - getHitter().getHeight()/2 - getHandle().getHeight()  );


        getHitter().setPosition((int) (LouieGame.ORIGO.x - getHitter().getWidth()/2),
                (int) (LouieGame.ORIGO.y) - gameScreen.getCircleRadius() - getHitter().getHeight()/2 );
    }
}
