package com.mygdx.game;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.tween.SpriteAccessor;

import java.util.ArrayList;

public class LouieGame extends Game {

    public static int WIDTH, HEIGHT;
    public static Vector2 ORIGO;
    public static BitmapFont FONT;

    public static Sound chickenSound;

    public static String platform;

    public static AssetManager assetManager;

    public LouieGame(String platform) {
        this.platform = platform;
    }


    @Override
    public void create() {


        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        //chickenSound = Gdx.audio.newSound(Gdx.files.internal("sounds/chicken.wav"));
        this.FONT = new BitmapFont();
        this.WIDTH = Gdx.graphics.getWidth();
        this.HEIGHT = Gdx.graphics.getHeight();

        this.assetManager = new AssetManager();
        assetManager.load("images/handle1.png", Texture.class);

        ORIGO = new Vector2(WIDTH / 2, HEIGHT / 2);


        //this.setScreen(new ConfigGameScreen(new LoggedInUser("Tomas"), this));


        ArrayList<Player> p = new ArrayList<Player>();
        p.add(new Player(1));
        p.add(new Player(2));
        p.add(new Player(3));
        p.add(new Player(4));
        GameSettings g = new GameSettings();
        g.setNumberOfLouies(2);
        g.setNumberOfChickens(10);
        this.setScreen(new GameScreen(this,p,g));


    }

    @Override
    public void render() {

        this.getScreen().render(Gdx.graphics.getDeltaTime());

    }

    @Override
    public void resize(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        // super.resize(width,height);
    }


}
