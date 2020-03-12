package com.example.dudu.archer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Dudu on 12/18/17.
 */

public class Background implements GameObject {
    private Bitmap image;
    private int x = 0, y = 0;
    public Background(Bitmap res){
        this.image = res;
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x , y, null);
    }

    @Override
    public void update() {

    }
    public int getWidth(){
        return image.getWidth();
    }
    public int getHeight(){
        return image.getHeight();
    }
}
