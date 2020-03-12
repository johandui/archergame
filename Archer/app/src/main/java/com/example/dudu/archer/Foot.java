package com.example.dudu.archer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Dudu on 12/18/17.
 */

public class Foot implements GameObject {
    Bitmap img;
    private int x;



    private int y;
    private int width, height;
    public Foot(Bitmap foot, int x, int y, int width, int height){
        this.img = foot;
        this.x = x + width / 5;
        this.y = y + height - 20;
    }
    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(img, x, y, null);
    }

    @Override
    public void update() {

    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
