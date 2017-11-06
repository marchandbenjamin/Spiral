package com.marchben.spiral;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by March on 06/11/2017.
 */

public class CanvasView extends View {

    protected Bitmap spiralBitmap;
    protected Paint spiralPaint;
    protected Canvas spiralCanvas;
    protected Path spiralPath;

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        spiralBitmap = Bitmap.createBitmap(128,128, Bitmap.Config.RGB_565);
        spiralPaint = new Paint();

        spiralPaint.setStyle(Paint.Style.STROKE);
        spiralPaint.setColor(Color.BLACK);
        spiralPaint.setStrokeWidth(25);

        spiralCanvas = new Canvas(spiralBitmap);

        spiralPath = new Path();
    }

    /**
     * When the Canvas in drawn
     * @param canvas
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        float viewWidth = this.getWidth();
        float viewHeight = this.getHeight();

        float viewCenterX = viewWidth/2;
        float viewCenterY = viewHeight/2;

        float scale = Math.max(viewHeight,viewWidth)/100; // A scale is used for the spiral to be wide enough
        float angle = 0;
        float x = viewCenterX;
        float y = viewCenterY;

        spiralPath.moveTo(x, y);

        for(int i = 0; x < viewWidth || y < viewHeight; i++ ){

            /**
             * Angle calculus in radian
             * In order to keep a nice drawing we decrease the angle's size for each @scale/2 steps
             */
            angle = (float) (angle + (3.14 / (10 + Math.round(i/(scale/2)))));
            /**
             * Carthesian coordinates calculi using archimedean spiral.
             */
            x = (float) (scale * angle * Math.cos(angle)) + viewCenterX;
            y = (float) (scale * angle * Math.sin(angle)) + viewCenterY;

            spiralPath.lineTo(x,y);

        }
        canvas.drawPath(spiralPath,spiralPaint);
    }

    /**
     * Erase the Canvas
     */
    public void clearCanvas(){
        invalidate();
    }
}
