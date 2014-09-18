package com.mygdx.game.model;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.LouieGame;
import com.mygdx.game.model.paddles.Paddle;
import com.mygdx.game.model.paddles.PaddleFactory;
import com.mygdx.game.tween.SpriteAccessor;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administratör on 2014-09-16.
 */
public class Player {
    private Paddle paddle;



    private int playerNumber;
    private float angle;
    private Vector2 chickenPosition;
    private GameScreen gameScreen;
    private List<Chicken> chickens;

    public Player(GameScreen gameScreen, int playerNumber) {
        this.paddle = PaddleFactory.createPaddle(playerNumber, gameScreen);

        this.gameScreen = gameScreen;
        this.chickens = new ArrayList<Chicken>();
        this.playerNumber = playerNumber;

        chickenPosition = initChickenPosition(playerNumber);
        spawnChickens();

    }

    private Vector2 initChickenPosition(int playerNumber) {
        Vector2 chickenPosition = new Vector2();
        switch (playerNumber){
            case 1:
                chickenPosition.set(LouieGame.ORIGO.x + gameScreen.getCircleRadius(), LouieGame.ORIGO.y - Chicken.CHICKENSIZE);
                return chickenPosition;
            case 2:
                chickenPosition.set(LouieGame.ORIGO.x + 3*Chicken.CHICKENSIZE , LouieGame.ORIGO.y + Chicken.CHICKENSIZE + gameScreen.getCircleRadius());
                return chickenPosition;
            case 3:
                chickenPosition.set(LouieGame.ORIGO.x  - gameScreen.getCircleRadius(), LouieGame.ORIGO.y + 3*Chicken.CHICKENSIZE);
                return chickenPosition;
            case 4:
                chickenPosition.set(LouieGame.ORIGO.x  - Chicken.CHICKENSIZE, LouieGame.ORIGO.y  - gameScreen.getCircleRadius() + Chicken.CHICKENSIZE);
                return chickenPosition;
            default:
                return null;
        }

    }

    private void spawnChickens() {
        for (int i = 0 ; i < gameScreen.numberOfChickens; i++){
            Chicken c = new Chicken(this);
            switch (playerNumber){
                case 1:
                    c.setPosition((int) (chickenPosition.x + i * Chicken.CHICKENSIZE), (int) chickenPosition.y);
                    break;
                case 2:
                    c.setPosition((int) chickenPosition.x, (int) (chickenPosition.y + i * Chicken.CHICKENSIZE));
                    break;
                case 3:
                    c.setPosition((int) (chickenPosition.x - i * Chicken.CHICKENSIZE), (int) chickenPosition.y);
                    break;
                case 4:
                    c.setPosition((int) chickenPosition.x, (int) (chickenPosition.y - i * Chicken.CHICKENSIZE));
                    break;
                default:

            }

            chickens.add(c);
        }

    }



    public void render(SpriteBatch sb) {

        this.paddle.render(sb);
        for (Chicken c : chickens){
            c.render(sb);
        }

    }


    public Paddle getPaddle() {
        return paddle;
    }




    public void setPosition() {
       this.paddle.setPositions();
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public List<Chicken> getChickens() {
        return chickens;
    }

    public void moveChickens(TweenManager tweenManager) {
        Timeline timeline = Timeline.createParallel();
        for(int i = 0; i < chickens.size(); i++){
            Chicken c = chickens.get(i);
            switch (playerNumber){
                case 1:
                    timeline.push(Tween.to(c.getSprite(), SpriteAccessor.POSITION_X,1).target(chickenPosition.x + i* Chicken.CHICKENSIZE));

                    break;
                case 2:
                    timeline.push(Tween.to(c.getSprite(),SpriteAccessor.POSITION_Y,1).target(chickenPosition.y + i*Chicken.CHICKENSIZE));
                    break;
                case 3:
                    timeline.push(Tween.to(c.getSprite(), SpriteAccessor.POSITION_X,1).target(chickenPosition.x - i* Chicken.CHICKENSIZE));
                    break;
                case 4:
                    timeline.push(Tween.to(c.getSprite(),SpriteAccessor.POSITION_Y,1).target(chickenPosition.y - i*Chicken.CHICKENSIZE));
                    break;
                default:

            }
            timeline.push(Tween.to(c.getSprite(),SpriteAccessor.ROTATION,1).target(c.getSprite().getRotation() - 360));


        }
        timeline.start(tweenManager);
    }
}
