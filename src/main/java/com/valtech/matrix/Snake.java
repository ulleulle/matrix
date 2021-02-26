package com.valtech.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake implements SteeringInterface, DisplayInterface {
    Random random = new Random();
    boolean crashed = false;
    boolean appleEaten = false;
    int applePosX = 5;
    int applePosy = 5;
    int height;
    int width;
    TheSnake theSnake = new TheSnake();
    int forBack = 10;
    int upDown = 10;

    List<List<String>> gameField = new ArrayList();

    @Override
    public List<List<String>> getFullFrame() {
        int randomApplePos = random.nextInt(height-2);
        boolean appleSet = false;
        gameField.clear();
        gameField.add(0, generateLowerBorder());

        for(int i = 0; i < height-5; ++i){
            gameField.add(1, generateMiddleBorderPiece());
        }
        gameField.add(height - 4, generateLowerBorder());
        if (!appleEaten){
            gameField.add(applePosX, generateApple());
        }else if(appleEaten){
            while ((!appleSet)){
                if(randomApplePos != 0 && randomApplePos < height -3){
                    gameField.add(randomApplePos, generateApple());
                    applePosX = randomApplePos;
                    appleSet = true;
                }else {
                    appleSet = false;
                    randomApplePos = random.nextInt(height-2);
                }
            }
        }

        return gameField;
    }



    private List<String> generateMiddleBorderPiece(){
        List<String> gameFieldPiece = new ArrayList<>();
        gameFieldPiece.add(0, generateBorderSign());

        for(int i = 0; i < width-3; ++i){
            gameFieldPiece.add(generateClearSign());
        }

        gameFieldPiece.add(width-2, generateBorderSign());
        return gameFieldPiece;
    }

    private List<String> generateLowerBorder() {
        List<String> upperGameFieldPiece = new ArrayList<>();
        for(int i = 0; i != width-1; ++i){
            upperGameFieldPiece.add(generateLowerBorderSign());
        }
        return upperGameFieldPiece;
    }

    private List<String> generateApple(){
        List<String> pieceWithApple = new ArrayList<>();
        pieceWithApple.add(0, generateBorderSign());
        int randomPos = random.nextInt(width-2);
        boolean appleSet = false;
        for(int i = 0; i != width-2; ++i){
            pieceWithApple.add(generateClearSign());
        }

        if(!appleEaten){
            pieceWithApple.add(applePosy, generateAppleSign());
        }else if(appleEaten){
            while ((!appleSet)){
                if(randomPos != 0){
                    pieceWithApple.add(randomPos, generateAppleSign());
                    applePosy = randomPos;
                    appleSet = true;
                }else {
                    appleSet = false;
                    randomPos = random.nextInt(width-2);
                }
            }
        }
        pieceWithApple.add(width-2, generateBorderSign());
        return pieceWithApple;
    }

    private String generateBorderSign() {
        return "|";
    }

    private String generateLowerBorderSign() {
        return "-";
    }

    private String generateSnakeSign() {
        return "M";
    }

    private String generateClearSign() {
        return " ";
    }

    private String generateAppleSign() {
        return Character.toString((char) 164);
    }

    @Override
    public void moveLeft() {
        theSnake.headX = theSnake.headX - 1;
    }

    @Override
    public void moveRight() {
        theSnake.headX = theSnake.headX + 1;
    }

    @Override
    public void moveUp() {
        theSnake.headY = theSnake.headY - 1;
    }

    @Override
    public void moveDown() {
        theSnake.headY = theSnake.headY + 1;
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
