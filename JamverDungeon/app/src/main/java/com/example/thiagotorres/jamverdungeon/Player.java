package com.example.thiagotorres.jamverdungeon;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Thiago.Torres on 11/07/2016.
 */
public class Player {

    private int posX, posY;

    public void setPosX(int position) {
        this.posX = position;
    }

    public void setPosY(int position) {
        this.posY = position;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Player(){

    }

    void DrawPlayer(Canvas canvas){
        Rect r = new Rect(posX, posY, posX + 50, posY + 50);
        Paint p = new Paint();
        canvas.drawRect(r, p);
    }

}
