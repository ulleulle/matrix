package com.valtech.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake implements SteeringInterface, DisplayInterface {
    Random random = new Random();
    boolean crashed = false;
    boolean crashedItself = false;
    boolean snakeAtApplePos = false;
    int applePosY = 5;
    int applePosX = 5;
    int height;
    int width;
    int tailPieces = 0;
    TheSnake theSnake = new TheSnake();
    boolean upPressed = false;
    boolean downPressed = false;
    boolean rightPressed = false;
    boolean leftPressed = false;


    List<List<String>> gameField = new ArrayList();


    @Override
    public List<List<String>> getFullFrame() {
        int randomApplePos = random.nextInt(height - 2);
        boolean appleSet = false;


        if(!crashed && !crashedItself){
            gameField.clear();
            gameField.add(0, generateLowerBorder());

            for (int i = 0; i <= height - 5; ++i) {
                gameField.add(1, generateMiddleBorderPiece());
            }
            gameField.add(height - 3, generateLowerBorder());

            if (!snakeAtApplePos) {
                generateApple();
            } else if (snakeAtApplePos) {
                while ((!appleSet)) {
                    if (randomApplePos != 0 && randomApplePos < height - 3) {
                        generateApple();
                        applePosY = randomApplePos;
                        snakeAtApplePos = false;
                        appleSet = true;
                    } else {
                        appleSet = false;
                        randomApplePos = random.nextInt(height - 2);
                    }
                }
            }
            letSnakeRun();
            generateSnakeHead();

            int z;
            if(theSnake.tailPosY.size() > 0){
                for(z = 0; z < tailPieces; ++z){
                    generateSnakeTailPiece(theSnake.tailPosX.get(z), theSnake.tailPosY.get(z));
                }
            }

            return gameField;
        }else {
            if (crashed) {
                gameField.clear();
                List<String> endState = new ArrayList<>();
                endState.add("That was the wall!");
                gameField.add(endState);
                return gameField;
            }
            if (crashedItself) {
                gameField.clear();
                List<String> endState = new ArrayList<>();
                endState.add("That was your tail!");
                gameField.add(endState);
                return gameField;
            }
            return gameField;
        }
    }



    private List<String> generateMiddleBorderPiece() {
        List<String> gameFieldPiece = new ArrayList<>();
        gameFieldPiece.add(0, generateBorderSign());

        for (int i = 0; i < width - 3; ++i) {
            gameFieldPiece.add(generateClearSign());
        }

        gameFieldPiece.add(width - 2, generateBorderSign());
        return gameFieldPiece;
    }

    private List<String> generateLowerBorder() {
        List<String> upperGameFieldPiece = new ArrayList<>();

        for (int i = 0; i != width - 1; ++i) {
            upperGameFieldPiece.add(generateLowerBorderSign());
        }
        return upperGameFieldPiece;
    }

    private void generateSnakeHead() {

        List<String> addSnake = gameField.get(theSnake.headY);

        String s = addSnake.get(theSnake.headX);
        if (s.equals(Character.toString((char) 164))) {
            tailPieces = tailPieces +1;
            snakeAtApplePos = true;

        }else if(s.equals(generateLowerBorderSign()) || s.equals(generateBorderSign()) || s.equals(generateSnakeTailSign())){
            crashed = true;
        }

        if (!snakeAtApplePos) {
            addSnake.set(theSnake.headX, generateSnakeHeadSign());
        } else {
            addSnake.set(theSnake.headX, generateSnakeHeadSign());
            snakeAtApplePos = true;
        }
    }

    private void generateSnakeTailPiece(int posX, int posY){
        List<String> addOneSnakeTailPiece = gameField.get(posY);
        addOneSnakeTailPiece.set(posX, generateSnakeTailSign());

        List<String> addSnake = gameField.get(theSnake.headY);
        String s = addSnake.get(theSnake.headX);
        if(s.equals(generateSnakeTailSign())){
            crashedItself = true;
        }
    }

    private void generateApple() {
        List<String> pieceWithApple = gameField.get(applePosY);

        if (!snakeAtApplePos) {
            pieceWithApple.set(applePosX, generateAppleSign());
        }
    }

    private String generateBorderSign() {
        return "|";
    }

    private String generateLowerBorderSign() {
        return "-";
    }

    private String generateSnakeHeadSign() {
        return "M";
    }

    private String generateSnakeTailSign(){
        return "m";
    }

    private String generateClearSign() {
        return " ";
    }

    private String generateAppleSign() {
        return Character.toString((char) 164);
    }

    private void letSnakeRun(){
        theSnake.tailPosY.add(0,theSnake.headY);
        theSnake.tailPosX.add(0,theSnake.headX);

        if(upPressed){
            theSnake.headY = theSnake.headY - 1;
        }
        if(downPressed){
            theSnake.headY = theSnake.headY + 1;
        }
        if(leftPressed){
            theSnake.headX = theSnake.headX - 1;
        }
        if(rightPressed){
            theSnake.headX = theSnake.headX + 1;
        }
    }

    @Override
    public void moveLeft() {
        upPressed = false;
        downPressed = false;
        rightPressed = false;
        leftPressed = true;
    }

    @Override
    public void moveRight() {
         upPressed = false;
         downPressed = false;
         rightPressed = true;
         leftPressed = false;
    }

    @Override
    public void moveUp() {
         upPressed = true;
         downPressed = false;
         rightPressed = false;
         leftPressed = false;
    }

    @Override
    public void moveDown() {
         upPressed = false;
         downPressed = true;
         rightPressed = false;
         leftPressed = false;
    }

    @Override
    public void enterPressed() {
    }

    @Override
    public boolean isCrashed() {
        return false;
    }

    @Override
    public void setMatrixDimensions(int hig, int wid) {
        height = hig;
        width = wid;
    }
}
