package com.example.dudu.archer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

/**
 * Created by Dudu on 12/18/17.
 */

public class Arrow implements GameObject {
    Bitmap img;

    public Matrix getMatrix1() {
        return matrix1;
    }

    Matrix matrix1;
    public Bitmap getArrow() {
        return img2;
    }

    Bitmap img2;

    public void setAngle(float angle) {
        this.angle = angle;
    }

    private float angle;
    private int x;
    private int y;
    private int width, height;
    public Arrow(Bitmap arrow1, Bitmap arrow2, int x, int y, int width, int height){
        this.img = arrow1;
        this.img2 = arrow2;
        this.x = x + width / 3;
        this.y = y + height / 2 - 10;
        matrix1 = new Matrix();

    }
    @Override
    public void draw(Canvas canvas) {
        Matrix matrix = new Matrix();
        matrix.setRotate(angle, width, height);
        matrix.postTranslate(x, y);
        canvas.drawBitmap(img, matrix, null);

        matrix1.setRotate(angle, width - img.getWidth(), height);
        matrix1.postTranslate(x + img.getWidth(), y);

        canvas.drawBitmap(img2, matrix1, null);
    }

    @Override
    public void update() {

    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() { return y;}

    public int getX() {
        return x;
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


    public Bitmap getImg() {
        return img;
    }

    public Bitmap getImg2() {
        return img2;
    }

    public float getAngle() {
        return angle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public Rect getBounds(){
/*        float[] values = new float[9];
        matrix1.getValues(values);
        int x = (int)values[2];
        int y = (int)values[5];
        int width = (int)values[0]*img2.getWidth();
        int height = (int)values[4]*img2.getHeight();*/
        return new Rect(x - img.getWidth(), y - img.getHeight(), x, y);
    }
}
