package com.mygdx.game.network;

import com.mygdx.game.GameSettings;
import com.mygdx.game.model.Player;

import java.util.List;

/**
 * Created by Administratör on 2014-09-19.
 */
public class StartGameResponse {
    private List<Player> players;
    private GameSettings settings;

    public StartGameResponse(){

    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameSettings getSettings() {
        return settings;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setSettings(GameSettings settings) {
        this.settings = settings;
    }
}
