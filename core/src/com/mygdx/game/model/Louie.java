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
    private Center center;
    private PlaneArm arm;


private float currentCircleRadius;


    private boolean airborne;
    private double angle;
    private float randomTime;


    public Louie(Center center, float startAngle, Float randomTime) {
        this.center = center;

        angle = startAngle;
        this.randomTime = randomTime;
        sprite = new Sprite(new Texture(Gdx.files.internal("images/louie.png")));

        sprite.setX(LouieGame.WIDTH / 2 );
        sprite.setY(LouieGame.HEIGHT / 2);
        arm = new PlaneArm(this);

        //RANDOMIZE START

        sprite.setScale(3);
        getPlaneArm().getSprite().setScale(getPlaneArm().getSprite().getScaleX(),2);


    }

    public void update(float deltaTime) {
        /** TODO : MAYBE INCLUDE MOVESPEED ACCELERATION
        totalTime += delta;
        if (totalTime > 1) {
            louie.setMoveSpeed(louie.getMoveSpeed() + (louie.getMoveSpeed() * 0.01f));
            totalTime = 0;
        }
        */
        currentCircleRadius = (center.getStaticCircleRadius() /sprite.getScaleX());
        angle = angle + center.getMoveSpeed() * deltaTime;
        sprite.setX(LouieGame.ORIGO.x + (float) (currentCircleRadius * Math.cos(angle)) - sprite.getWidth()/2);
        sprite.setY(LouieGame.ORIGO.y + (float) (currentCircleRadius * Math.sin(angle)) - sprite.getHeight()/2);

        sprite.setRotation((float) Math.toDegrees(angle));

        arm.update(deltaTime);

    }

    public void render(SpriteBatch sb) {
        arm.render(sb);
        sprite.draw(sb);




    }

    public boolean hit(float maxheight, TweenManager tweenManager) {
        // TODO: FIX MATH FOR MAXHEIGHT
        if (!airborne) {
            Tween scaleSpriteTween = Tween.to(sprite, SpriteAccessor.SCALE, maxheight / 2).target(maxheight).repeatYoyo(1, 0).setCallback(new TweenCallback() {
                @Override
                public void onEvent(int i, BaseTween<?> baseTween) {
                    airborne = false;
                }
            });
            Tween scaleArmTween = Tween.to(getPlaneArm().getSprite(),SpriteAccessor.SCALEY, maxheight/2).target(maxheight).repeatYoyo(1,0);
            Timeline.createParallel().push(scaleSpriteTween).push(scaleArmTween).start(tweenManager);
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


    public double getAngle() {
        return angle;
    }

    public Vector2 getPosition() {
        return new Vector2(sprite.getX(),sprite.getY());
    }


    public PlaneArm getPlaneArm() {
        return arm;
    }

    public Center getCenter() {
        return center;
    }

    public float getCurrentCircleRadius() {
        return currentCircleRadius;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getRandomTime() {
        return randomTime;
    }
}
