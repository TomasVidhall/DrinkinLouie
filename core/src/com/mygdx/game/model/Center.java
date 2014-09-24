package com.mygdx.game.model;





import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.game.GameScreen;
import com.mygdx.game.GameSettings;
import com.mygdx.game.LouieGame;
import com.mygdx.game.network.ServerGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tomas on 2014-09-19.
 */
public class Center {

    public static float MAXHEIGHT;
    private Sprite sprite;

    private float moveSpeed;
    private float staticCircleRadius;
    private float angle;



    private List<Louie> louies;

    public Center(GameSettings settings){

        sprite = new Sprite();
        sprite.scale(1);
       // sprite.setPosition(ServerGame.ORIGO.x - sprite.getWidth()/2,ServerGame.ORIGO.y - sprite.getHeight()/2);

        this.staticCircleRadius = settings.getCircleRadius();
        MAXHEIGHT = settings.getCircleRadius()/80f;
        //TODO: SET GOOD NUMBER
        this.moveSpeed = -1;
        angle = 0;

        louies = new ArrayList<Louie>();

        for (int i = 0; i < settings.getNumberOfLouies() ; i++) {
            louies.add(new Louie(this, (float) (i*2*Math.PI/settings.getNumberOfLouies()), settings.getRandomTimes().get(i)));
        }



    }
    public void update(float delta){
        angle = angle + moveSpeed * delta;

        sprite.setRotation((float) Math.toDegrees(angle));

        for(Louie l: louies){
            l.update(delta);
        }
    }

    public void render(SpriteBatch sb){

        for(Louie l: louies){
            l.render(sb);

        }
        sprite.draw(sb);

    }

    public float getStaticCircleRadius() {
        return staticCircleRadius;
    }




    public List<Louie> getLouies() {
        return louies;
    }


    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSpriteTexture(){
        sprite.setTexture(new Texture(Gdx.files.internal("images/center.png")));
        for (Louie louie : louies){
            louie.setSpriteTexture();
        }

    }
}
