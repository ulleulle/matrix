package com.valtech.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake implements MatrixInterface{

    Random randomNumber = new Random();
    private int height;
    private int width;
    private int borderPosition = 5;
    private int playerPosition = 10;
    private int oldPlayerPosition = 10;

    List<List<String>> raceTrack = new ArrayList<>();

    @Override
    public List<List<String>> getFullFrame() {
        raceTrack.add(0, generateRow());
        if(raceTrack.size() > height){
            raceTrack.remove(height);
        }


        return raceTrack;
    }


    private void holdOldCar(int currentPos) {
        oldPlayerPosition = currentPos;
    }


    private String generateSnakeSign() {
        String alphabet = "  |";

        int snake = 2;
        char snakeSpace = alphabet.charAt(snake);
        return String.valueOf(snakeSpace);
    }

    private String generateClearSpace(){
        String alphabet = "  |";
        int clearField = 1;
        char clearSpace = alphabet.charAt(clearField);
        return String.valueOf(clearSpace);
    }

    private String generatePlayerSign(){
        String alphabet = "  |O";
        int playerField = 3;
        char playerSpace = alphabet.charAt(playerField);
        return String.valueOf(playerSpace);
    }




    private List<String> generateRow() {
        List<String> stringList = new ArrayList<>();
        int borderSteps = randomNumber.nextInt(3);
        int borderPosition2 = 20;

        for (int i = 0; i <= width - 3 ; ++i) {
            stringList.add(generateClearSpace());
        }

        if(raceTrack.size() > 1){
            List<String> toRemove = raceTrack.get(raceTrack.size() - 2);
            if(!toRemove.get(oldPlayerPosition).equals(generatePlayerSign())){
                toRemove.add(playerPosition, generatePlayerSign());
                holdOldCar(playerPosition);
            }

        }

        if(borderSteps == 0){
            borderPosition = borderPosition + 0;
            borderPosition2 = borderPosition2 + 0;
        }else if(borderSteps == 1){
            borderPosition = borderPosition + 1;
            borderPosition2 = borderPosition2 -1;
        }else if(borderSteps == 2){
            borderPosition = borderPosition - 1;
            borderPosition2 = borderPosition2 + 1;
        }
        borderPosition = borderPosition + 0;


        if(borderPosition2 > 40 ){
            borderPosition2 = 39;
            borderPosition = 24;
        }
        if(borderPosition < 1){
            borderPosition = 1;
            borderPosition2 = 16;
        }
        int difference = borderPosition2 - borderPosition;

        if(difference < 6){
            borderPosition = borderPosition -1;
            borderPosition2 = borderPosition2  + 1;
        }

        if(difference > 17){
            borderPosition = borderPosition +1;
            borderPosition2 = borderPosition2 -1;
        }
        playerPosition = borderPosition2 - 5;
        stringList.add(borderPosition, generateSnakeSign());
        stringList.add(borderPosition2, generateSnakeSign());

        return stringList;
    }

    @Override
    public void setMatrixDimensions(int heigh, int widt) {
        height = heigh;
        width = widt;
    }
}
