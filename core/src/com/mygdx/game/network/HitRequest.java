package com.mygdx.game.network;

import com.mygdx.game.model.Player;

/**
 * Created by Administratör on 2014-09-19.
 */
public class HitRequest {


    private Player player;

    public HitRequest(){

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
