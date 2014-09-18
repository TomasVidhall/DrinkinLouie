package com.mygdx.game.model;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.LouieGame;
import com.mygdx.game.tween.SpriteAccessor;

/**
 * Created by Administratör on 2014-09-16.
 */
public class Chicken {
    private Sprite sprite;
    private Player owner;

    public static final int CHICKENSIZE = 20;

    public Chicken(Player owner) {
        this.owner = owner;
        this.sprite = new Sprite(new Texture(Gdx.files.internal("images/chicken" + owner.getPlayerNumber() + ".png")));

    }

    public void render(SpriteBatch sb) {
        sprite.draw(sb);
    }

    public void setPosition(int x, int y) {
        sprite.setPosition(x, y);
    }

    public Rectangle getRectangle() {
        return sprite.getBoundingRectangle();
    }

    public void destroy(final TweenManager tweenManager) {
        //LouieGame.chickenSound.play();
        Tween.to(sprite, SpriteAccessor.ROTATION, 1f).target(sprite.getRotation() - 360).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int i, BaseTween<?> baseTween) {
                removeAndMoveChickens(tweenManager);

            }
        }).start(tweenManager);
    }

    private void removeAndMoveChickens(TweenManager tweenManager) {
        owner.getChickens().remove(this);
        owner.moveChickens(tweenManager);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
