package com.example.thiagotorres.jamverdungeon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Thiago on 11/07/2016.
 */
public class Controller {

    private Bitmap image;
    private String direction;
    private Paint paint = new Paint();
    private int canvasWidth, canvasHeight;
    private float posX, posY;

    public Controller(Bitmap bitmap, String pointing) {
        image = bitmap;
        direction = pointing;
    }

    void drawController(Canvas canvas){
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        Matrix mat = new Matrix();
        switch (direction){
            case "up":
                mat.postRotate(180);
                posX = (canvasWidth / 2) - image.getWidth() / 2;
                posY = canvasHeight - image.getHeight() * 2;
                break;
            case "down":
                mat.postRotate(0);
                posX = (canvasWidth / 2) - image.getWidth() / 2;
                posY = canvasHeight - image.getHeight();
                break;
            case "left":
                mat.postRotate(90);
                posX = (canvasWidth / 2) - image.getWidth() * 1.5f;
                posY = canvasHeight - image.getHeight();
                break;
            case "right":
                mat.postRotate(270);
                posX = (canvasWidth / 2) + image.getWidth() / 2;
                posY = canvasHeight - image.getHeight();
                break;

        }

        Bitmap rotateImage = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), mat, true);
        canvas.drawBitmap(rotateImage, posX, posY, paint);
    }
}
