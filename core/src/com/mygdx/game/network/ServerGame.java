package com.mygdx.game.network;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.CountDown;
import com.mygdx.game.GameSettings;
import com.mygdx.game.ShapeFunctions;
import com.mygdx.game.model.Center;
import com.mygdx.game.model.Chicken;
import com.mygdx.game.model.Louie;
import com.mygdx.game.model.Player;
import com.mygdx.game.tween.SpriteAccessor;

import java.util.List;

/**
 * Created by Administratör on 2014-09-22.
 */
public class ServerGame {
    private final List<Player> players;
    private final GameSettings settings;
    private final Center center;
    private CountDown countDown;
    private boolean running;
    private long currentFrameTime,lastFrameTime;
    private float overlapRectangleArea;
    private TweenManager tweenManager;
    private GameServer server;

    public ServerGame(List<Player> players, GameSettings gameSettings, GameServer server) {
        this.players = players;
        this.settings = gameSettings;
        center = new Center(gameSettings);
        this.tweenManager = new TweenManager();
        this.countDown = new CountDown(tweenManager);
        this.server = server;

    }


    public void start() {

        Thread t = new Thread(new Runnable() {


            @Override
            public void run() {
                running = true;
                currentFrameTime = System.nanoTime();
                lastFrameTime = currentFrameTime;

                decidePlayerPositions();
                spawnChickens();
                initLouies();
                gameLoop();
            }
        });

        t.start();
    }

    private void initLouies() {
        for(Louie l: center.getLouies()){
            Tween armTween = Tween.to(l.getPlaneArm().getSprite(), SpriteAccessor.SCALEY, l.getRandomTime()).target(1f);
            Tween spriteTween = Tween.to(l.getSprite(), SpriteAccessor.SCALE, l.getRandomTime()).target(1f);
            Timeline.createParallel().push(armTween).push(spriteTween).start(center.getGameScreen().getTweenManager());
        }

    }

    private void spawnChickens() {
        for (Player p : players) {
            p.spawnChickens(settings.getNumberOfChickens());
        }
    }

    private void decidePlayerPositions() {
        for (Player p : players) {
            p.initPositions(p.getPlayerNumber(),settings);
        }

    }

    private void gameLoop() {


        while(running){
            long delta = currentFrameTime - lastFrameTime;

            update(delta);
        }
    }

    private void update(long delta) {
        tweenManager.update(delta);
        center.update(delta);

        if (!countDown.isDone()) {
            countDown.update(delta);
        }



        Chicken hitChicken = checkChickenHit();
        if (hitChicken != null) {
           hitChicken.destroy(tweenManager);
        }

        server.sendFrameUpdate(center, players, countDown);



        //TODO: SEND DATA TO CLIENTS
    }

    public void hitPaddle(Player p){
        players.get(p.getPlayerNumber()-1).getPaddle().hit(tweenManager,this);

    }

    public void checkPaddleHit(Rectangle paddleRectangle) {

        for (Louie louie : center.getLouies()) {
            if (paddleRectangle.overlaps(louie.getBoundingRectangle()) && !louie.isAirborne()) {
                overlapRectangleArea = ShapeFunctions.overlapRectangleArea(paddleRectangle, louie.getBoundingRectangle());
                // TODO: FIX MATH FOR MAXHEIGHT

                louie.hit(1 + Center.MAXHEIGHT * overlapRectangleArea,tweenManager);
            }
        }

    }

    public Chicken checkChickenHit() {
        for (Louie louie : center.getLouies()) {
            if (!louie.isAirborne()) {
                for (Player p : players) {
                    for (Chicken c : p.getChickens()) {
                        if (c.getRectangle().overlaps(louie.getBoundingRectangle()))
                            return c;

                    }
                }
            }
        }
        return null;
    }
}

