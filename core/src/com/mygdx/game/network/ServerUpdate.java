package com.mygdx.game.network;

import com.mygdx.game.CountDown;
import com.mygdx.game.model.Center;
import com.mygdx.game.model.Player;

import java.util.List;

/**
 * Created by Administratör on 2014-09-22.
 */
public class ServerUpdate {


    private Center center;
    private List<Player> players;
    private CountDown countDown;

    public ServerUpdate(){

    }

    public Center getCenter() {
        return center;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public CountDown getCountDown() {
        return countDown;
    }
    public void setCenter(Center center) {
        this.center = center;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCountDown(CountDown countDown) {
        this.countDown = countDown;
    }
}
