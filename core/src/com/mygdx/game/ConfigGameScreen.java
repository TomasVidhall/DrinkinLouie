package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.network.GameClient;
import com.mygdx.game.network.StartGameRequest;

import java.io.IOException;

/**
 * Created by Administratör on 2014-09-19.
 */
public class ConfigGameScreen implements Screen {

    private GameClient gameClient;
    private LoggedInUser user;
    private GameSettings settings;
    private LouieGame game;

    public ConfigGameScreen(LoggedInUser user, LouieGame game){
        this.user = user;
        this.game = game;
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        try {
            gameClient = new GameClient(user, game);
        } catch (IOException e) {
            e.printStackTrace();
        }
        settings = new GameSettings();
        settings.setNumberOfLouies(2);

        sendStartGameRequest(settings);

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

    public void sendStartGameRequest(GameSettings settings){
        if(gameClient.getClient().isConnected()){
            StartGameRequest startGameRequest = new StartGameRequest();
            startGameRequest.setGameSettings(settings);
        }
    }
}
