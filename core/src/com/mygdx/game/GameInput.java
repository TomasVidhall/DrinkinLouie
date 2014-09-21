package com.mygdx.game;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.model.paddles.Paddle;
import com.mygdx.game.network.GameClient;


/**
 * Created by tomas on 2014-09-16.
 */
public class GameInput extends InputAdapter {


    private Paddle myPaddle;
    private TweenManager tweenManager;
    private GameScreen gameScreen;

    public GameInput(Paddle myPaddle, TweenManager tweenManager, GameScreen gameScreen){

        this.tweenManager = tweenManager;
        this.myPaddle = myPaddle;
        this.gameScreen = gameScreen;
    }
    @Override
    public boolean keyDown(int keycode) {
        if(LouieGame.platform.equals("desktop")) {
            if (keycode == Input.Keys.SPACE) {

                myPaddle.hit(tweenManager, gameScreen);
                gameScreen.getGameClient().sendHitRequest();
            }
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(LouieGame.platform.equals("android")){
            myPaddle.hit(tweenManager, gameScreen);
            gameScreen.getGameClient().sendHitRequest();
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }
}
