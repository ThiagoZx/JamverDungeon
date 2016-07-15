package com.example.thiagotorres.jamverdungeon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Thiago on 14/07/2016.
 */
public class Command {

    private int posX, posY;
    private Bitmap image;
    private int direction;
    private Paint paint = new Paint();
    private boolean posSet;
    public Rect body;

    public Command(Bitmap bitmap, int pointing){
        image = bitmap;
        direction = pointing;
        posSet = false;
    }

    public int getDirection() {
        return direction;
    }

    void setPos(Canvas canvas){
        posSet = true;
        posX = canvas.getWidth();
        switch (direction){
            case 0:
                posY = (canvas.getHeight() / 2) + image.getHeight();
                break;
            case 1:
                posY = (canvas.getHeight() / 2);
                break;
            case 2:
                posY = (canvas.getHeight() / 2) - image.getHeight() * 2;
                break;
            case 3:
                posY = (canvas.getHeight() / 2) - image.getHeight();
                break;

        }
    }

    void drawCommand(Canvas canvas) {
        if (!posSet){ this.setPos(canvas); }
        Matrix mat = new Matrix();
        switch (direction){
            case 0:
                mat.postRotate(0);
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.RED);
                break;
            case 1:
                mat.postRotate(90);
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.BLUE);
                break;
            case 2:
                mat.postRotate(180);
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.GREEN);
                break;
            case 3:
                mat.postRotate(270);
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.YELLOW);
                break;

        }
        Bitmap rotateImage = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), mat, true);
        canvas.drawBitmap(rotateImage, posX, posY, null);
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

    void updateCommand(){
        this.posX = posX - 10;
        body = new Rect();
        body.set(posX, posY, posX + image.getWidth(), posY + image.getHeight());
    }

    boolean deleteCommand() {
        if (posX + image.getWidth() < 0) { return true; }
        return false;
    }
}
