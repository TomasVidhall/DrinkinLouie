package com.mygdx.game.model;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.GameSettings;
import com.mygdx.game.LouieGame;
import com.mygdx.game.model.paddles.Paddle;
import com.mygdx.game.model.paddles.PaddleFactory;
import com.mygdx.game.network.GameClient;
import com.mygdx.game.tween.SpriteAccessor;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administratör on 2014-09-16.
 */
public class Player{
    private Paddle paddle;


    private Color color;
    private int playerNumber;
    private float angle;
    private Vector2 chickenPosition;
       private List<Chicken> chickens;

    public Player(){

    }

    public Player(int playerNumber){

        this(playerNumber,Color.RED);
    }

    public Player(int playerNumber, Color color) {


        this.chickens = new ArrayList<Chicken>();
        this.playerNumber = playerNumber;
        this.chickenPosition = new Vector2();
        this.color = color;

    }

    public void initPositions(int playerNumber, GameSettings gameSettings) {
        this.paddle = PaddleFactory.createPaddle(playerNumber, color);
        paddle.setPositions(gameSettings);
        initChickenPosition(playerNumber,gameSettings);

    }

    private void initChickenPosition(int playerNumber, GameSettings gameSettings) {

        switch (playerNumber){
            case 1:
                chickenPosition.set(LouieGame.ORIGO.x + gameSettings.getCircleRadius() - this.getPaddle().getHitter().getWidth()/2,
                        LouieGame.ORIGO.y - this.getPaddle().getHitter().getHeight()/2 - Chicken.CHICKENSIZE);
                break;

            case 2:
                chickenPosition.set(LouieGame.ORIGO.x + this.getPaddle().getHitter().getWidth()/2 ,
                        LouieGame.ORIGO.y + gameSettings.getCircleRadius() - this.getPaddle().getHitter().getHeight()/2);
                break;

            case 3:
                chickenPosition.set(LouieGame.ORIGO.x  - gameSettings.getCircleRadius() - this.getPaddle().getHitter().getWidth()/2,
                        LouieGame.ORIGO.y + this.getPaddle().getHitter().getHeight()/2);
                break;

            case 4:
                chickenPosition.set(LouieGame.ORIGO.x  - this.getPaddle().getHitter().getWidth()/2 - Chicken.CHICKENSIZE
                        , LouieGame.ORIGO.y  - gameSettings.getCircleRadius() - this.getPaddle().getHitter().getHeight()/2);

                break;

            default:

        }

    }

    public void spawnChickens(int numberOfChickens) {
        for (int i = 0 ; i < numberOfChickens; i++){
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
