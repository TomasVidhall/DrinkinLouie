package com.mygdx.game.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by tomas on 2014-09-16.
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {
    public static final int SCALE = 1;
    public static final int POSITION_X = 2;
    public static final int POSITION_Y = 3;
    public static final int POSITION_XY = 4;
    public static final int ROTATION = 5;

    @Override
    public int getValues(Sprite sprite, int tweenType, float[] returnValues) {
        switch (tweenType){
            case SCALE: returnValues[0] = sprite.getScaleX(); return 1;
            case POSITION_X: returnValues[0] = sprite.getX(); return 1;
            case POSITION_Y: returnValues[0] = sprite.getY(); return 1;
            case POSITION_XY:
                returnValues[0] = sprite.getX();
                returnValues[1] = sprite.getY();
                return 2;
            case ROTATION:
                returnValues[0] = sprite.getRotation(); return 1;
            default: assert false; return -1;
        }
    }

    @Override
    public void setValues(Sprite sprite, int tweenType, float[] newValues) {
        switch (tweenType) {
            case SCALE:
                sprite.setScale(newValues[0]);
                break;
            case POSITION_X:
                sprite.setX(newValues[0]);
                break;
            case POSITION_Y:
                sprite.setY(newValues[0]);
                break;
            case POSITION_XY:
                sprite.setX(newValues[0]);
                sprite.setY(newValues[1]);
                break;
            case ROTATION:
                sprite.setRotation(newValues[0]);
                break;
            default:
                assert false;
                break;

        }
    }
}
