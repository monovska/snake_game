package com.example.bishkoti.snake_game.engine;

import com.example.bishkoti.snake_game.enums.Direction;
import com.example.bishkoti.snake_game.enums.GameState;
import com.example.bishkoti.snake_game.enums.TileType;
import com.example.bishkoti.snake_game.operations.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bishkoti on 15.2.2018 г..
 */

public class GameEngine {
    public static final int GameWidth = 28;
    public static final int GameHeight = 42;

    private List<Coordinates> wall = new ArrayList<>();
    private List<Coordinates> snake = new ArrayList<>();
    private List<Coordinates> apple = new ArrayList<>();

    private Direction currentDirection = Direction.East;
    private GameState currentGameState = GameState.Running;
    private Random random = new Random();
    private boolean growTail = false;

    private Coordinates getSnakeHead() { return snake.get(0); }


    public GameEngine() {

    }

    public void initGame(){

        AddSnake();
        AddWall();
        AddApple();
    }

    public void UpdateDirection(Direction newDirection){
        if (Math.abs(newDirection.ordinal() - currentDirection.ordinal()) %2 == 1){
            currentDirection = newDirection;
        }
    }

    public void Update() {
        // updates the Snake
        switch (currentDirection) {
            case North:
                UpdateSnake(0, -1);
                break;
            case East:
                UpdateSnake(1, 0);
                break;
            case South:
                UpdateSnake(0, 1);
                break;
            case West:
                UpdateSnake(-1, 0);
                break;
        }
        // checks for wall collisions
        for (Coordinates w : wall) {
            if (snake.get(0).equals(w)) { // if the snake head "hits" the wall
                currentGameState = GameState.Lost;
            }
        }
        // checks for snake collisions
        for (int i = 1; i < snake.size();  i++) {
                if (getSnakeHead().equals(snake.get(i))) {
                    currentGameState = GameState.Lost;
                    return;
                }
        }
        // checks for apples
        Coordinates applesForRemoval = null;
        for ( Coordinates apples : apple ){
            if( getSnakeHead().equals(apples)){
                applesForRemoval = apples;
                growTail = true;
            }
        }
        if ( applesForRemoval != null ){
            apple.remove(applesForRemoval);
            AddApple();
        }

    }

    public TileType[][] getMap(){ // 2D visualization
        TileType[][] map = new TileType[GameWidth][GameHeight];

        for (int x = 0; x < GameWidth; x++){
            for( int y = 0; y < GameHeight; y++) {
                map[x][y] = TileType.Empty;
            }
        }
        for( Coordinates a: apple){
            map[a.getX()][a.getY()] = TileType.Apple;
        }

        for( Coordinates s: snake) {
            map[s.getX()][s.getY()] = TileType.SnakeTail;
        }
        map[snake.get(0).getX()][snake.get(0).getY()] = TileType.SnakeHead;

        for (Coordinates wall: wall) {
            map[wall.getX()][wall.getY()] = TileType.Wall;
        }
        return map;
    }

    private void AddApple() {
        Coordinates coordinates = null;
        boolean added = false;
        while( !added ){ // creates the apple on the empty space
            int x = 1 + random.nextInt (GameWidth - 2);
            int y = 1 + random.nextInt (GameHeight - 2);

            coordinates = new Coordinates ( x, y );
            boolean collision = false;
            for (Coordinates s: snake){
                if (s.equals(coordinates)){
                    collision = true;
                    // break;
                }
            }

            for (Coordinates a : apple ){
                if (a.equals(coordinates)){
                    collision = true;
                   //  break;
                }
            }
            added = !collision;
        }
        apple.add(coordinates);
    }

    private void UpdateSnake(int x, int y){
        int newX = snake.get(snake.size() -1).getX();
        int newY = snake.get(snake.size() -1).getY();

        for (int i = snake.size() -1; i > 0 ; i--) {
            snake.get(i).setX( snake.get(i-1).getX() );
            snake.get(i).setY( snake.get(i-1).getY() );
        }

        if ( growTail ) {
            snake.add(new Coordinates( newX, newY ));
            growTail = false;
        }

        snake.get(0).setX( snake.get(0).getX() + x ); // update the snake head
        snake.get(0).setY( snake.get(0).getY() + y );
    }

    private void AddSnake() {
        snake.clear(); // in case we already have a snake
        snake.add(new Coordinates (7,7));
        snake.add(new Coordinates (6,7));
        snake.add(new Coordinates (5,7));
        snake.add(new Coordinates (4,7));
        snake.add(new Coordinates (3,7));
        snake.add(new Coordinates (2,7));
    }

    private void AddWall() {
        for (int x = 0; x < GameWidth; x++) {
            wall.add(new Coordinates(x, 0)); // top wall
            wall.add(new Coordinates(x,  GameHeight-1)); // bottom wall
        }
        for (int y = 1; y < GameHeight; y++) {
            wall.add(new Coordinates(0, y)); // left wall
            wall.add(new Coordinates(GameWidth -1,  y)); // right wall
        }
    }
    public GameState getCurrentGameState(){

        return currentGameState;
    }

}

