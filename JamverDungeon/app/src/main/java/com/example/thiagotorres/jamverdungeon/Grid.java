package com.example.thiagotorres.jamverdungeon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Thiago.Torres on 11/07/2016
 */
public class Grid {

    private int posX, posY;
    private Bitmap image;
    private int direction;
    private boolean posSet;
    private Matrix mat;
    public Rect body;

    public Grid(Bitmap bitmap, int pointing){
        image = bitmap;
        direction = pointing;
    }

    void setPos(Canvas canvas){
        posSet = true;
        mat = new Matrix();
        posX = canvas.getWidth() / 20;
        switch (direction){
            case 0:
                posY = (canvas.getHeight() / 2) + image.getHeight();
                mat.postRotate(0);
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.RED);
                break;
            case 1:
                posY = (canvas.getHeight() / 2);
                mat.postRotate(90);
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.BLUE);
                break;
            case 2:
                posY = (canvas.getHeight() / 2) - image.getHeight() * 2;
                mat.postRotate(180);
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.GREEN);
                break;
            case 3:
                posY = (canvas.getHeight() / 2) - image.getHeight();
                mat.postRotate(270);
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.YELLOW);
                break;
        }

        Bitmap rotateImage = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), mat, true);
        image = rotateImage;
    }

    void drawGrid(Canvas canvas) {
        if (!posSet){ this.setPos(canvas); }
        canvas.drawBitmap(image, posX, posY, null);
    }

    void changeArrowColor(int color){
        int [] imagePixels = new int [image.getHeight() * image.getWidth()];
        image.getPixels(imagePixels, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
        for(int i = 0; i < imagePixels.length; i++) {
            if(imagePixels[i] == Color.BLACK) {
                imagePixels[i] = color;
            }
        }

        image.setPixels(imagePixels, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

    }

    void updateGrid(){
        body = new Rect();
        body.set(posX, posY, posX + image.getWidth(), posY + image.getHeight());
    }

}
