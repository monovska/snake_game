package com.example.bishkoti.snake_game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.example.bishkoti.snake_game.enums.TileType;

/**
 * Created by Bishkoti on 15.2.2018 г..
 */

public class SnakeView extends View{
    private Paint nPaint = new Paint();
    private TileType snakeViewMap[][]; //2D

    public SnakeView(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    public void setsnakeViewMap (TileType[][] map)  {
        this.snakeViewMap = map; }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (snakeViewMap != null){ // we only draw if there's a snakeView set
            float tileSizeX = canvas.getWidth() / snakeViewMap.length; // divided by the snakeViewmap length
            float tileSizeY = canvas.getHeight() / snakeViewMap[0].length;
            float circleSize = Math.min(tileSizeX, tileSizeY) /2;

            for ( int x=0; x < snakeViewMap.length; x++){
                for ( int y=0; y < snakeViewMap[x].length; y++){
                    switch (snakeViewMap[x][y]){

                        case Empty:
                            nPaint.setColor(Color.WHITE);
                            break;
                        case Wall:
                            nPaint.setColor(Color.BLUE);
                            break;
                        case SnakeHead:
                            nPaint.setColor(Color.MAGENTA);
                            break;
                        case SnakeTail:
                            nPaint.setColor(Color.GREEN);
                            break;
                        case Apple:
                            nPaint.setColor(Color.RED);
                            break;
                    }
                    canvas.drawCircle(x*tileSizeX + tileSizeX / 2f + circleSize / 2, y*tileSizeY + tileSizeY / 2f + circleSize / 2, circleSize, nPaint);
                }
            }

        }
    }
}
