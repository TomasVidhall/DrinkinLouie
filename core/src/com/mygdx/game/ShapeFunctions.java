package com.mygdx.game;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Administratör on 2014-09-17.
 */
public class ShapeFunctions {


    /**
     *
     * @param r1
     * @param r2
     * @return How many percent of r1 is in r2.
     */
    public static float overlapRectangleArea(Rectangle r1, Rectangle r2){
        if (r1.overlaps(r2)) {

            Rectangle intersection = new Rectangle();
            intersection.x = Math.max(r1.x, r2.x);
            intersection.width = Math.min(r1.x + r1.width, r2.x + r2.width) - intersection.x;
            intersection.y = Math.max(r1.y, r2.y);
            intersection.height = Math.min(r1.y + r1.height, r2.y + r2.height) - intersection.y;
            float area = r1.area()+r2.area() - intersection.area();
            return intersection.area()/area;
        }
        return 0;
    }
}
