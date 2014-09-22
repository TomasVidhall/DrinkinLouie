package com.mygdx.game.model.paddles;


import com.mygdx.game.GameScreen;

/**
 * Created by Administratör on 2014-09-17.
 */
public class PaddleFactory {

    public static Paddle createPaddle(int playerNumber){
        switch (playerNumber){
            
            
            case 1:
                    return new Paddle1();
            case 2:
                    return new Paddle2();
            case 3:
                    return new Paddle3();
            case 4:
                    return new Paddle4();
            default:
                return  null;
        }
        

    }
}
