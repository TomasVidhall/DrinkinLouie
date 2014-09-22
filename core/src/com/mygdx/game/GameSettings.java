package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administratör on 2014-09-19.
 */
public class GameSettings {
    private int numberOfLouies;
    private int numberOfChickens;
    private List<Float> randomTimes;
    private float circleRadius;

    public GameSettings(){
        randomTimes = new ArrayList<Float>();
        this.circleRadius = 250;
    }

    public int getNumberOfLouies() {
        return numberOfLouies;
    }

    public void setNumberOfLouies(int numberOfLouies) {
        this.numberOfLouies = numberOfLouies;
        Random r = new Random();
        for (int i = 0; i < numberOfLouies; i++) {
             randomTimes.add(r.nextFloat() * (20 - 3) + 3);
        }


    }
    public int getNumberOfChickens() {
        return numberOfChickens;
    }

    public void setNumberOfChickens(int numberOfChickens) {
        this.numberOfChickens = numberOfChickens;
    }

    public List<Float> getRandomTimes() {
        return randomTimes;
    }

    public float getCircleRadius() {
        return circleRadius;
    }
}
