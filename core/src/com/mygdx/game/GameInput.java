package com.mygdx.game;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.model.Louie;
import com.mygdx.game.model.paddles.Paddle;


/**
 * Created by tomas on 2014-09-16.
 */
public class GameInput extends InputAdapter {


    private Paddle myPaddle;
    private TweenManager tweenManager;

    public GameInput(Paddle myPaddle, TweenManager tweenManager){

        this.tweenManager = tweenManager;
        this.myPaddle = myPaddle;
    }
    @Override
    public boolean keyDown(int keycode) {
        if(LouieGame.platform.equals("desktop")) {
            if (keycode == Input.Keys.SPACE) {
                myPaddle.hit(tweenManager);
            }
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(LouieGame.platform.equals("android")){
            myPaddle.hit(tweenManager);
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }
}
