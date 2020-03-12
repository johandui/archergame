package com.example.dudu.archer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * Created by Dudu on 12/18/17.
 */

public class Hand implements GameObject {
    Bitmap img;
    private int x;
    private float angle;
    private int y;
    private int width, height;
    public Hand(Bitmap foot, int x, int y, int width, int height){
        this.img = foot;
        this.x = x;
        this.y = y + height / 2;
    }
    @Override
    public void draw(Canvas canvas) {
        Matrix matrix = new Matrix();
        matrix.setRotate(angle, width, height);
        matrix.postTranslate(x, y);
        canvas.drawBitmap(img, matrix, null);

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
    public void setAngle(float angle){ this.angle = angle; }
}
