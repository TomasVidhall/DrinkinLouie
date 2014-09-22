package com.mygdx.game;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tween.SpriteAccessor;

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
        this.sprite = new Sprite(new Texture(Gdx.files.internal("images/countdown/3.png")));
        this.manager = manager;
        setUpSprite();
        sprite.setPosition(LouieGame.WIDTH/2 - sprite.getWidth()/2, LouieGame.HEIGHT/2 - sprite.getHeight()/2);



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
        Tween.to(sprite, SpriteAccessor.SCALE,0.5f).target(2).repeatYoyo(1,0).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                number -= 1;
                if( number < 1){
                    done = true;
                    return;
                }
                setUpSprite();
            }
        }).start(manager);
    }


    public boolean isDone() {
        return done;
    }
}
