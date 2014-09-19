package com.mygdx.game;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.tween.SpriteAccessor;

import java.util.ArrayList;
import java.util.List;

public class LouieGame extends Game {
    public static int WIDTH,HEIGHT;
    public static Vector2 ORIGO;
    public static BitmapFont FONT;
    private List<Player> players;
    public static  Sound chickenSound;

    public static String platform;
    public static AssetManager assetManager;

    public LouieGame(String platform) {
        this.platform = platform;
    }


    @Override
	public void create () {

        players = new ArrayList<Player>();
        Tween.registerAccessor(Sprite.class,new SpriteAccessor());

        //chickenSound = Gdx.audio.newSound(Gdx.files.internal("sounds/chicken.wav"));
        this.FONT = new BitmapFont();
        this.WIDTH = Gdx.graphics.getWidth();
        this.HEIGHT = Gdx.graphics.getHeight();

        ORIGO = new Vector2(WIDTH/2,HEIGHT/2);
        GameScreen gameScreen= new GameScreen(this, players, 1);
        players.add(new Player( gameScreen,1));
        players.add(new Player( gameScreen,2));
        players.add(new Player( gameScreen,3));
        players.add(new Player( gameScreen,4));

        this.setScreen(gameScreen);


	}

	@Override
	public void render () {

        this.getScreen().render(Gdx.graphics.getDeltaTime());

		}

    @Override
    public void resize(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

       // super.resize(width,height);
    }



}
