package com.mygdx.game.network;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameSettings;
import com.mygdx.game.model.Player;

import java.util.HashMap;

/**
 * Created by Administratör on 2014-09-19.
 */
public class StartGameRequest {
    private GameSettings gameSettings;
    private Player player;







    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }



}
