package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.LouieGame;

/**
 * Created by Administratör on 2014-09-17.
 */
public class PlaneArm {
    private static final float HEIGHT = 10;
    private Louie louie;
    private Sprite sprite;

    public PlaneArm(Louie louie) {
        this.louie = louie;
        sprite = new Sprite(new Texture(Gdx.files.internal("images/armpixel.png")));
        sprite.setSize(louie.getCenter().getStaticCircleRadius(), PlaneArm.HEIGHT);
        sprite.setPosition(LouieGame.ORIGO.x,LouieGame.ORIGO.y-sprite.getHeight()/2);
    }


    public void update(float delta){
            sprite.setRotation((float) Math.toDegrees(louie.getAngle()));
            sprite.setSize(louie.getCurrentCircleRadius(), HEIGHT);
    }

    public void render(SpriteBatch sb) {
           sprite.draw(sb);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
