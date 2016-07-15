package com.example.thiagotorres.jamverdungeon;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Thiago on 15/07/2016.
 */
public class Button {

    public Paint paint = new Paint();

    public Button(){

    }

    void DrawButton(Canvas canvas) {
        Rect r = new Rect();
        r.set(canvas.getWidth() / 2 - 50, 50, canvas.getWidth() / 2 + 50, 100);
        canvas.drawRect(r, paint);
    }
}
