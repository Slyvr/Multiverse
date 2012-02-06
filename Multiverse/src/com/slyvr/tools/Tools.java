package com.slyvr.tools;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Tools {

	public static int getDistance(Rectangle rect1, Rectangle rect2){
		int distance =0;
		
		int xDiff = (int) Math.abs(rect1.getX() - rect2.getX());
		int yDiff = (int) Math.abs(rect1.getY() - rect2.getY());
		
		distance = ((xDiff^2) + (yDiff^2))^(1/2);
		
		return distance;
	}
	
	public static Color[][] getColorData(Image img){
		Color[][] imgColor = new Color[img.getWidth()][img.getHeight()];
		for(int x=0; x<img.getWidth(); x++){
			for (int y=0; y<img.getHeight(); y++){
				Color currentColor = img.getColor(x, y);
				imgColor[x][y]=currentColor;
			}
		}
		return imgColor;
	}
	
	public static Boolean intersectPixels(Rectangle rectangleA, Color[][] dataA, Rectangle rectangleB, Color[][] dataB)
    {
        // Find the bounds of the rectangle intersection
        int top = (int) Math.max(rectangleA.getMinY(), rectangleB.getMinY());
        int bottom = (int) Math.min(rectangleA.getMaxY(), rectangleB.getMaxY());
        int left = (int) Math.max(rectangleA.getMinX(), rectangleB.getMinX());
        int right = (int) Math.min(rectangleA.getMaxX(), rectangleB.getMaxX());

        // Check every point within the intersection bounds
        for (int y = top; y < bottom; y++)
        {
            for (int x = left; x < right; x++)
            {
                // Get the color of both pixels at this point
            	Color colorA = dataA[(int) (x - rectangleA.getMinX())][(int) (y - rectangleA.getMinY())];
            	Color colorB = dataB[(int) (x - rectangleB.getMinX())][(int) (y - rectangleB.getMinY())];

                // If both pixels are not completely transparent,
                if (colorA.getAlpha() != 0 && colorB.getAlpha() != 0)
                {
                    // then an intersection has been found
                    return true;
                }
            }
        }
        // No intersection found
        return false;
    }
}
