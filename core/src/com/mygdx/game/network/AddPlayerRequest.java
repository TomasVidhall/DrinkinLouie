package com.mygdx.game.network;

/**
 * Created by Administratör on 2014-09-19.
 */
public class AddPlayerRequest {
    private String userId;
    private GameClient gameClient;


    public AddPlayerRequest(){

    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GameClient getGameClient() {
        return gameClient;
    }
}
