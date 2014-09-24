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
import com.mygdx.game.network.GameClient;
import com.mygdx.game.tween.SpriteAccessor;

import javax.xml.soap.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LouieGame extends Game {

    public static int WIDTH, HEIGHT;
    public static Vector2 ORIGO;
    public static BitmapFont FONT;

    public static Sound chickenSound;

    public static String platform;

    private HashMap<String,Texture> textureHashMap;

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

        this.textureHashMap = new HashMap<String, Texture>();

        textureHashMap.put("images/handle1.png",new Texture(Gdx.files.internal("images/handle1.png")));
       // assetManager.load("images/handle1.png", Texture.class);

        ORIGO = new Vector2(WIDTH / 2, HEIGHT / 2);


        this.setScreen(new ConfigGameScreen(new LoggedInUser("Tomas"), this));


      /*  ArrayList<Player> p = new ArrayList<Player>();
        p.add(new Player(1));
        p.add(new Player(2));
        p.add(new Player(3));
        p.add(new Player(4));
        GameSettings g = new GameSettings();
        g.setNumberOfLouies(2);
        g.setNumberOfChickens(10);
        try {
            this.setScreen(new GameScreen(this,p,g, new GameClient(new LoggedInUser("Tomas"), this)));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


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


    public HashMap<String, Texture> getTextureHashMap() {
        return textureHashMap;
    }
}
