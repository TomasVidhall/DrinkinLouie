package com.mygdx.game.model;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.LouieGame;
import com.mygdx.game.tween.SpriteAccessor;


import java.util.Random;

/**
 * Created by tomas on 2014-09-16.
 */
public class Louie {


    private Sprite sprite;
    private Vector2 origin;

    private PlaneArm arm;
    public static float MAXHEIGHT;




    private float moveSpeed;
    private float staticCircleRadius, currentCircleRadius;
    private double angle;
    private boolean airborne;

    public Louie(int circleRadius, GameScreen gameScreen) {

        this.staticCircleRadius = circleRadius;

        //TODO: SET GOOD NUMBER
        MAXHEIGHT = (staticCircleRadius) /80f;
        moveSpeed = -1;
        angle = 0;


        sprite = new Sprite(new Texture(Gdx.files.internal("images/louie.png")));

        sprite.setX(LouieGame.WIDTH / 2 );
        sprite.setY(LouieGame.HEIGHT / 2);
        origin = new Vector2(LouieGame.WIDTH / 2, LouieGame.HEIGHT / 2);

        arm = new PlaneArm(this);

        //RANDOMIZE START
        Random r = new Random();
        float randomTime = r.nextFloat() * (20 - 3) + 3;
        sprite.setScale(3);
        Tween.to(sprite, SpriteAccessor.SCALE, randomTime).target(1f).start(gameScreen.getTweenManager());

    }

    public void update(float deltaTime) {
        currentCircleRadius = staticCircleRadius /sprite.getScaleX();
        angle = angle + moveSpeed * deltaTime;
        sprite.setX(origin.x + (float) (currentCircleRadius * Math.cos(angle)));
        sprite.setY(origin.y + (float) (currentCircleRadius * Math.sin(angle)));

        sprite.setRotation((float) Math.toDegrees(angle));

    }

    public void render(SpriteBatch sb) {
        arm.render(sb);
        sprite.draw(sb);




    }

    public boolean hit(float maxheight, TweenManager tweenManager) {
        // TODO: FIX MATH FOR MAXHEIGHT
        if (!airborne) {
            Tween scaleSpriteTween = Tween.to(sprite, SpriteAccessor.SCALE, maxheight/2).target(maxheight).repeatYoyo(1, 0).setCallback(new TweenCallback() {
                @Override
                public void onEvent(int i, BaseTween<?> baseTween) {
                    airborne = false;
                                  }
            });
            Timeline.createParallel().push(scaleSpriteTween).start(tweenManager);
            airborne = true;
            return true;
        }


        return false;
    }

    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    public boolean isAirborne() {
        return airborne;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

}
