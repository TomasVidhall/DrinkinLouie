package com.mygdx.game.network;

import com.mygdx.game.model.Louie;
import com.mygdx.game.model.Player;

/**
 * Created by Administratör on 2014-09-21.
 */
public class LouieHitRequest {
    private Louie louie;
    private float target;
    private Player player;

    public LouieHitRequest(){

    }

    public void setLouie(Louie louie) {
        this.louie = louie;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public float getTarget() {
        return target;
    }

    public Louie getLouie() {
        return louie;
    }
}
