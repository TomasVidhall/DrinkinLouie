package com.mygdx.game.network;

import com.mygdx.game.GameSettings;
import com.mygdx.game.model.Player;

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
