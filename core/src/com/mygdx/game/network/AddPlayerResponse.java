package com.mygdx.game.network;

import com.mygdx.game.model.Player;

/**
 * Created by Administratör on 2014-09-19.
 */
public class AddPlayerResponse {
    private String text;
    private Player player;

    public  AddPlayerResponse(){

    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
