package com.mygdx.game;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.model.paddles.Paddle;
import com.mygdx.game.network.GameClient;
import com.mygdx.game.network.ServerGame;


/**
 * Created by tomas on 2014-09-16.
 */
public class GameInput extends InputAdapter {



    private GameClient gameClient;

    public GameInput(GameClient gameClient){

        this.gameClient = gameClient;
    }
    @Override
    public boolean keyDown(int keycode) {
        if(LouieGame.platform.equals("desktop")) {
            if (keycode == Input.Keys.SPACE) {


                gameClient.sendHitRequest();
            }
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(LouieGame.platform.equals("android")){

            gameClient.sendHitRequest();
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }
}
