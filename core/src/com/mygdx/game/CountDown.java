package com.mygdx.game;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Louie;

/**
 * Created by Administratör on 2014-09-21.
 */
public class CountDown {

    private Sprite sprite;
    private int number;
    private boolean done;
    private float totalTime;
    private TweenManager manager;


    public CountDown(TweenManager manager){
        this.done = false;
        this.number = 3;
        this.sprite = new Sprite();

        sprite.setPosition(LouieGame.WIDTH/2 - sprite.getWidth()/2, LouieGame.HEIGHT/2 - sprite.getHeight()/2);
        this.manager = manager;


    }

    public void render(SpriteBatch sb){

        sprite.draw(sb);

    }

    public void update(float delta){
        totalTime += delta;
        if(totalTime > 1){
            number -=1;
            if(number < 1){
                done = true;
                return;
            }
            setUpSprite();
            totalTime = 0;
        }


    }

    public void setUpSprite(){
        sprite.setTexture(new Texture(Gdx.files.internal("images/countdown/" + number + ".png")));
    }


    public boolean isDone() {
        return done;
    }
}
