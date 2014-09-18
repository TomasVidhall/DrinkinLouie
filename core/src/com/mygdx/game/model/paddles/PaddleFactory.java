package com.mygdx.game.model.paddles;


import com.mygdx.game.GameScreen;

/**
 * Created by Administratör on 2014-09-17.
 */
public class PaddleFactory {

    public static Paddle createPaddle(int playerNumber, GameScreen gameScreen){
        switch (playerNumber){
            
            
            case 1:
                    return new Paddle1(gameScreen);
            case 2:
                    return new Paddle2(gameScreen);
            case 3:
                    return new Paddle3(gameScreen);
            case 4:
                    return new Paddle4(gameScreen);
            default:
                return  null;
        }
        

    }
}
