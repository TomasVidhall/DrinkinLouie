package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.Player;
import com.mygdx.game.network.GameClient;
import com.mygdx.game.network.StartGameRequest;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administratör on 2014-09-19.
 */
public class ConfigGameScreen implements Screen {

    private GameClient gameClient;
    private LoggedInUser user;
    private GameSettings settings;
    private LouieGame game;
    private TextButton startGameButton;
    private Stage stage;
    private Skin skin;
    private TextureAtlas atlas;



    private boolean startGame;
    private List<Player> players;
    private Player clientPlayer;


    public ConfigGameScreen(LoggedInUser user, LouieGame game){
        this.user = user;
        this.game = game;
        this.startGame = false;
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();

        if(startGame){
            game.setScreen(new GameScreen(game, players, settings, gameClient));
        }
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


        stage = new Stage();


        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/atlas.pack");
        skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);
        startGameButton = new TextButton("12", skin);

        startGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameClient.sendStartGameRequest(settings);
            }
        });


        stage.addActor(startGameButton);

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



    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame, List<Player> players, GameSettings settings, Player clientPlayer) {
        this.startGame = startGame;
        this.players = players;
        this.settings =settings;
        this.clientPlayer = clientPlayer;

    }
}
