package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    private CakeModel model = new CakeModel();

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint balloonPaint=new Paint();
    Paint redPaint = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 100.0f;
    public static final float candleWidth = 40.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;



    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFFC755B5);  //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        balloonPaint.setColor(0xFF0000FF);
        balloonPaint.setStyle(Paint.Style.FILL);

        redPaint.setColor(Color.RED);
        redPaint.setTextSize(50);

        setBackgroundColor(Color.WHITE);  //better than black default

    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {

        if (model.hasCandles == true) {
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);


            if (model.isLit == true) {


                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);


            }
        }

    }

    public void drawBalloon(Canvas canvas){

        if (model.xPos>0 && model.yPos>0){
            float left=model.xPos-20.0f;
            float right=model.xPos+20.0f;
            float top=model.yPos+40.0f;
            float bottom=model.yPos-40.0f;
            canvas.drawOval(left,top,right,bottom,balloonPaint);
            canvas.drawLine(model.xPos,top,right,top+20.0f,balloonPaint);
        }
    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {

        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);


        canvas.drawText(Integer.toString(model.xPos), 1500.0f, 800.0f, redPaint);
        canvas.drawText(Integer.toString(model.yPos), 1500.0f, 900.0f, redPaint);


    switch(model.numCandlesLit) {
            case 0:
                break;
            case 1:
                drawCandle(canvas, cakeLeft + cakeWidth/2 - candleWidth/2, cakeTop);
                break;
            case 2:
                drawCandle(canvas, cakeLeft + cakeWidth/2 - candleWidth/2, cakeTop);
                drawCandle(canvas, cakeLeft + cakeWidth/3 - candleWidth/3, cakeTop);
                break;
            case 3:
                drawCandle(canvas, cakeLeft + cakeWidth/2 - candleWidth/2, cakeTop);
                drawCandle(canvas, cakeLeft + cakeWidth/3 - candleWidth/3, cakeTop);
                drawCandle(canvas, cakeLeft + cakeWidth/3 + cakeWidth/3, cakeTop);
                break;
            case 4:
                drawCandle(canvas, cakeLeft + cakeWidth/2 - candleWidth/2, cakeTop);
                drawCandle(canvas, cakeLeft + cakeWidth/3 - candleWidth/3, cakeTop);
                drawCandle(canvas, cakeLeft + cakeWidth/3 + cakeWidth/3, cakeTop);
                drawCandle(canvas, cakeLeft + cakeWidth/4 - 3*candleWidth, cakeTop);
                break;
            case 5:
                drawCandle(canvas, cakeLeft + cakeWidth/2 - candleWidth/2, cakeTop);
                drawCandle(canvas, cakeLeft + cakeWidth/3 - candleWidth/3, cakeTop);
                drawCandle(canvas, cakeLeft + cakeWidth/3 + cakeWidth/3, cakeTop);
                drawCandle(canvas, cakeLeft + cakeWidth/4 - 3*candleWidth, cakeTop);
                drawCandle(canvas, cakeLeft + cakeWidth - 4*candleWidth, cakeTop);
                break;
        }
        drawBalloon(canvas);
    }//onDraw

    public CakeModel getModel() {
        return model;
    }

}//class CakeView

