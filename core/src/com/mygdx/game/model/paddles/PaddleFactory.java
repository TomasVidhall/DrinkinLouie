package com.mygdx.game.model.paddles;


import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GameScreen;

/**
 * Created by Administratör on 2014-09-17.
 */
public class PaddleFactory {

    public static Paddle createPaddle(int playerNumber, Color color){
        switch (playerNumber){
            
            
            case 1:
                    return new Paddle1(color);
            case 2:
                    return new Paddle2(color);
            case 3:
                    return new Paddle3(color);
            case 4:
                    return new Paddle4(color);
            default:
                return  null;
        }
        

    }
}
