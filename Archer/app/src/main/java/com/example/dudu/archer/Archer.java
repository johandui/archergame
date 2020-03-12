package com.example.dudu.archer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dudu on 12/18/17.
 */

public class Archer implements GameObject {
    private Bitmap img;
    private Foot foot;
    private Hand hand;
    private int vectorX = 0;
    private float angle = 0;
    private int vectorY = 0;
    private int interval = 0;
    private double g = 4.9;
    public boolean isshoot;

    public void setShoot(boolean shoot) {
        isShoot = shoot;
    }

    private boolean isShoot = false;

    public List<Arrow> getArrows() {
        return arrows;
    }

    private List<Arrow> arrows;
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return img.getWidth();
    }

    public int getHeight() {
        return img.getHeight();
    }

    private int x, y;
    private int width, height;
    public Archer(Bitmap bitmap, int x, int y){
        this.img = bitmap;
        this.x = x;
        this.y = y;
        arrows = new ArrayList<Arrow>();
    }
    @Override
    public void draw(Canvas canvas) {
            Matrix matrix = new Matrix();
            matrix.setRotate(angle, getWidth() / 4, img.getHeight());
            matrix.postTranslate(x, y);
            canvas.drawBitmap(img, matrix, null);
            foot.draw(canvas);
            hand.setAngle(angle);
            hand.setWidth(getWidth() / 4);
            hand.setHeight(getHeight());
            hand.draw(canvas);

            arrows.get(arrows.size() - 1).draw(canvas);
    }

    @Override
    public void update( ) {

        double gif = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
        if(vectorY != 0 && vectorX != 0) {
            angle = (float) Math.toDegrees(Math.asin(vectorY / gif));
            if(angle < -28)
                angle = -28;
            arrows.get(arrows.size() - 1).setAngle(angle);
            arrows.get(arrows.size() - 1).setWidth(getWidth() / 4);
            arrows.get(arrows.size() - 1).setHeight(getHeight());
            Log.i("Angle", angle + "");
            if (isShoot) {

                int vec = (int)(Math.abs(gif) * 0.2);
                int x = (int)((Math.cos(Math.toRadians(angle)) * vec * interval) / 30 + arrows.get(arrows.size() - 1).getX());
                int y = (int)(((Math.sin(Math.toRadians(angle)) * vec * interval - (g * interval * interval))) / 30 + arrows.get(arrows.size() - 1).getY());
                Log.i("COOR", x + " : " + y);
                arrows.get(arrows.size() - 1).setX(x);
                arrows.get(arrows.size() - 1).setY(y);
                arrows.get(arrows.size() - 1).setAngle(angle + angle / 10);
                interval++;
                if(x > 3000 || y < -3000){
                    arrows.remove(arrows.size() - 1);
                    isShoot = false;
                    interval = 0;
                }

            }
            else interval = 0;
        }
    }
    public Arrow getArrow(){
        return arrows.get(arrows.size() - 1);
    }
    public void setFoot(Foot foot) {
        this.foot = foot;
    }
    public void setHand(Hand hand) {
        this.hand = hand;
    }
    public void setVector(int x, int y){
        this.vectorX = x;
        this.vectorY = y;
    }
    public void setArrows(List<Arrow>arrows){ this.arrows = arrows;}
}
