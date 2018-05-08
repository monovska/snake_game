package com.example.bishkoti.snake_game;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.bishkoti.snake_game.engine.GameEngine;
import com.example.bishkoti.snake_game.enums.Direction;
import com.example.bishkoti.snake_game.enums.GameState;
import com.example.bishkoti.snake_game.view.SnakeView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private GameEngine gameEngine;
    private SnakeView snakeView;
    private final Handler handler = new Handler();
    private final long updateDelay = 300;
    private float prevX, prevY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameEngine = new GameEngine();
        gameEngine.initGame();

        snakeView = (SnakeView)findViewById(R.id.snakeView);
        snakeView.setOnTouchListener(this);

        startUpdateHandler(); // start of the application update
    }
    private void startUpdateHandler(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameEngine.Update();

                if( gameEngine.getCurrentGameState() == GameState.Running){
                    handler.postDelayed(this, updateDelay);
                }
                if ( gameEngine.getCurrentGameState() == GameState.Lost){
                   GameFailed();
                }
                snakeView.setsnakeViewMap(gameEngine.getMap()); // update the snake
                snakeView.invalidate(); // refreshes the view
            }
        }, updateDelay);
    }
    private void GameFailed() {

        Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 2500);
    }



    @Override
    public boolean onTouch(View v, MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                prevX = event.getX();
                prevY = event.getY();
            break;

            case MotionEvent.ACTION_UP:
                float newX = event.getX();
                float newY = event.getY();

                //calculates where we have swiped the screen
                if ( Math.abs ( newX - prevX) > Math.abs ( newY - prevY) ) {
                    if (newX > prevX) {
                    // right direction
                        gameEngine.UpdateDirection(Direction.East);
                    } else {
                    // left direction
                        gameEngine.UpdateDirection(Direction.West);
                    }
                }else {
                    // UP AND DOWN
                    if (newY > prevY) {
                        // up
                        gameEngine.UpdateDirection(Direction.South);
                    } else {
                        // down
                        gameEngine.UpdateDirection(Direction.North);


                    }
                }
                break;
        }
        return true;
    }
}
