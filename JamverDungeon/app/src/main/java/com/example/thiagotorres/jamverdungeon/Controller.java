package com.example.thiagotorres.jamverdungeon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Thiago on 11/07/2016
 */
public class Controller {

    //If there is some time remaining, adjust the size of the arrows to always have the same size in
    //every phone, would ya? Kay;

    private Bitmap image;
    private int direction;
    private Paint paint = new Paint();
    private int posX;
    private int posY;

    public Controller(Bitmap bitmap, int pointing) {
        image = bitmap;
        direction = pointing;
    }

    int getAxis(String axis){
        if (axis.equals("x")){return posX;}
        if (axis.equals("y")){return posY;}
        return 0;
    }

    int getSize(String dimension){
        if (dimension.equals("width")){return image.getWidth();}
        if (dimension.equals("height")){return image.getHeight();}
        return 0;
    }

    void drawController(Canvas canvas){
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        Matrix mat = new Matrix();
        switch (direction){
            case 0:
                mat.postRotate(0);
                posX = (canvasWidth / 2) - image.getWidth() / 2;
                posY = canvasHeight - image.getHeight();
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.RED);
            break;
            case 1:
                mat.postRotate(90);
                posX = (canvasWidth / 2) - image.getWidth() - image.getWidth() / 2;
                posY = canvasHeight - image.getHeight();
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.BLUE);
            break;
            case 2:
                mat.postRotate(180);
                posX = (canvasWidth / 2) - image.getWidth() / 2;
                posY = canvasHeight - image.getHeight() * 2;
                image = image.copy(image.getConfig(), true);
                this.changeArrowColor(Color.GREEN);
            break;
            case 3:
                mat.postRotate(270);
                posX = (canvasWidth / 2) + image.getWidth() / 2;
                posY = canvasHeight - image.getHeight();
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
}
