package com.mygdx.game.network;

import com.mygdx.game.model.Player;

/**
 * Created by Administratör on 2014-09-19.
 */
public class HitResponse {

    private Player player;

    public HitResponse(){

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
