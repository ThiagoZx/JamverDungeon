package com.example.thiagotorres.jamverdungeon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

/**
 * Created by Thiago on 15/07/2016
 */
public class Background {

    public Paint paint = new Paint();
    Bitmap album;

    public Background(Bitmap bitmap){
        album = bitmap;
        paint.setColor(Color.BLACK);
    }

    void DrawBackground(Canvas canvas) {
        Shader shader = new LinearGradient(0, 0, 0, canvas.getHeight() / 2, Color.WHITE, Color.BLACK, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        canvas.drawRect(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), paint);
        canvas.drawBitmap(album, canvas.getWidth() / 20, 30, null);
    }

    void DrawButton(Canvas canvas) {
        Rect r = new Rect();
        r.set(canvas.getWidth() / 2, 50, canvas.getWidth() / 2 + 200, 150);
        canvas.drawRect(r, paint);
    }
}
