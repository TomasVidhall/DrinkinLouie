package com.mygdx.game.model.paddles;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameScreen;
import com.mygdx.game.GameSettings;
import com.mygdx.game.network.ServerGame;
import com.mygdx.game.tween.SpriteAccessor;


/**
 * Created by tomas on 2014-09-16.
 */
public abstract class Paddle {

    private Sprite handle, hitter;
    private boolean airborne;




    private float cooldown = 0.5f, riseTime = 0.25f;

    public Paddle() {


        this.airborne = false;



    }

    public boolean hit(final TweenManager tweenManager, final ServerGame serverGame) {
        if (!airborne) {
            Tween handleTween = Tween.to(handle, SpriteAccessor.SCALE, riseTime).target(0.5f).setCallback(new TweenCallback() {
                @Override
                public void onEvent(int i, BaseTween<?> baseTween) {
                    checkHit(serverGame);
                    Tween handleTween = Tween.to(handle, SpriteAccessor.SCALE, cooldown).target(1f).setCallback(new TweenCallback() {
                        @Override
                        public void onEvent(int i, BaseTween<?> baseTween) {
                            airborne = false;
                        }
                    });
                   // Tween hitterTween = Tween.to(hitter, SpriteAccessor.SCALE, cooldown).target(1f);
                    Timeline.createParallel().push(handleTween).start(tweenManager);


                }
            });
            //Tween hitterTween = Tween.to(hitter, SpriteAccessor.SCALE, riseTime).target(2f);
            Timeline.createParallel().push(handleTween).start(tweenManager);

            airborne = true;
            return true;
        }


        return false;

    }

    private void checkHit(ServerGame serverGame) {
        serverGame.checkPaddleHit(this.hitter.getBoundingRectangle());
    }


    public void render(SpriteBatch sb) {


        handle.draw(sb);
        hitter.draw(sb);
    }

    public Rectangle getHitRectangle() {
        return hitter.getBoundingRectangle();
    }

    public abstract void setPositions(GameSettings gameScreen);


    public Sprite getHandle() {
        return handle;
    }

    public Sprite getHitter() {
        return hitter;
    }

    public void setHitter(Sprite hitter) {
        this.hitter = hitter;
    }

    public void setHandle(Sprite handle) {
        this.handle = handle;
    }

    public abstract void setUpSpriteTextures();
}
