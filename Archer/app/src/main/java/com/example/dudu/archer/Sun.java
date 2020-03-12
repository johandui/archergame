package com.example.dudu.archer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Dudu on 12/18/17.
 */

public class Sun implements GameObject {
    Bitmap img;
    Bitmap scale;
    private int x;
    private int y;
    private int width = 100;
    private int height = 100;
    public void setRadius(int width) {
        this.width = width;
    }



    private int maxWidth;
    private int temp = 10;

    public Sun(Bitmap foot, int x, int y, int width, int k){
        this.img = foot;
        this.x = x;
        this.y = y;
        this.maxWidth = width;
        this.width = k;
        this.height = k;
    }
    @Override
    public void draw(Canvas canvas) {

        scale = Bitmap.createScaledBitmap(img, this.width, this.height, false);
        canvas.drawBitmap(scale, x, y, null);
    }

    @Override
    public void update() {

        x += temp;
        if(x > maxWidth)
            temp = -10;
        if(x < maxWidth / 2)
            temp = 10;
    }

    public void update(int width) {
        this.width = width;
    }
    public boolean shootingTarget(Arrow arrow){
        Rect rect = new Rect(x, y, x + width,  y + width);


        Log.i("rect", rect.toString());

        Log.i("rect1", arrow.getBounds().toString());
        return  (Rect.intersects(arrow.getBounds(), rect) || Rect.intersects(rect, arrow.getBounds())
                || rect.intersect(arrow.getBounds()) || arrow.getBounds().intersect(rect));

    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return width;
    }
}
