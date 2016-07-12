package com.example.thiagotorres.jamverdungeon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Thiago on 11/07/2016.
 */
public class Controller {

    //If there is some time remaining, adjust the size of the arrows to always have the same size in
    //every phone, would ya? Kay;

    private Bitmap image;
    private String direction;
    private Paint paint = new Paint();
    private int canvasWidth, canvasHeight, posX, posY;

    public Controller(Bitmap bitmap, String pointing) {
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
                posX = (canvasWidth / 2) - image.getWidth() - image.getWidth() / 2;
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

    void movePlayerTo(Player character){
        switch (direction){
            case "up":
                character.setPosY(character.getPosY() - 50);
                break;
            case "down":
                character.setPosY(character.getPosY() + 50);
                break;
            case "left":
                character.setPosX(character.getPosX() - 50);
                break;
            case "right":
                character.setPosX(character.getPosX() + 50);
                break;
        }
    }
}
