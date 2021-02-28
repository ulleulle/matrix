package com.valtech.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Racing implements DisplayInterface, SteeringInterface {

    List<List<String>> raceTrack = new ArrayList<>();
    Random randomNumber = new Random();
    private int height;
    private int width;
    private int borderPosition = 5;
    private int borderPosition2 = 20;
    private int playerPosition = 10;
    private int oldPlayerPosition = 10;
    private int playerHigh = raceTrack.size();
    private boolean crashed = false;
    private String obstacles = "*^";


    @Override
    public List<List<String>> getFullFrame() {

        if (crashed) {
            List<String> endState = new ArrayList<>();
            endState.add("CRASHED!");
            endState.add(" Try again");
            if (!raceTrack.get(raceTrack.size() - 1).equals(endState)) {
                raceTrack.add(endState);
                raceTrack.remove(0);
            }
        } else {
            raceTrack.add(0, generateRow());
            if (raceTrack.size() > height) {
                raceTrack.remove(height);
            }
        }
        return raceTrack;
    }



    private String generateLeftBorderSign() {
        String alphabet = "  |";
        int snake = 2;
        char snakeSpace = alphabet.charAt(snake);
        return "|";
    }

    private String generateRightBorderSign() {
        String alphabet = "  !";
        int snake = 2;
        char snakeSpace = alphabet.charAt(snake);
        return "!";
    }

    private String generateClearSpace() {
        String alphabet = "  |";
        int clearField = 1;
        char clearSpace = alphabet.charAt(clearField);
        return " ";
    }

    private String generatePlayerSign() {
        String alphabet = "  |O";
        int playerField = 3;
        char playerSpace = alphabet.charAt(playerField);
        return "O";
    }

    private String generateObstacleSign() {
        obstacles = "*^";
        int obstacleField = randomNumber.nextInt(2);
        char obstacleSpace = obstacles.charAt(obstacleField);
        return String.valueOf(obstacleSpace);
    }


    private List<String> generateRow() {
        List<String> stringList = new ArrayList<>();
        int borderSteps = randomNumber.nextInt(5);
        boolean setObstacle = randomNumber.nextBoolean();
        boolean reallySetObstacle = false;
        int obstaclePosition = 0;

        if (setObstacle) {
            reallySetObstacle = randomNumber.nextBoolean();
        }

        for (int i = 0; i <= width - 3; ++i) {
            stringList.add(generateClearSpace());
        }

        if (raceTrack.size() > 1) {
            List<String> lastRow = raceTrack.get(raceTrack.size() - 2);
            String s = lastRow.get(playerPosition);
            if (s.equals("*") || s.equals("^") || s.equals("|") || s.equals("!") || playerPosition <= lastRow.indexOf(generateLeftBorderSign())|| playerPosition >= lastRow.indexOf(generateRightBorderSign())) {
                crashed = true;
                lastRow.remove(playerPosition);
                lastRow.set(playerPosition, Character.toString((char) 164));
            }else {
                lastRow.set(playerPosition, generatePlayerSign());
            }
        }


        if (borderSteps == 0) {
            borderPosition = borderPosition + 0;
            borderPosition2 = borderPosition2 + 0;
        } else if (borderSteps == 1) {
            borderPosition = borderPosition + 1;
        } else if (borderSteps == 2) {
            borderPosition = borderPosition - 1;
        } else if (borderSteps == 3) {
            borderPosition2 = borderPosition2 - 1;
        } else if (borderSteps == 4) {
            borderPosition2 = borderPosition2 + 1;
        }

        if (borderPosition2 > 40) {
            borderPosition2 = 39;
            borderPosition = 24;
            playerPosition = 30;
        }
        if (borderPosition < 1) {
            borderPosition = 1;
            borderPosition2 = 16;
            playerPosition = 10;
        }
        int difference = borderPosition2 - borderPosition;

        if (difference < 6) {
            borderPosition = borderPosition - 1;
            borderPosition2 = borderPosition2 + 1;
        }

        if (difference > 17) {
            borderPosition = borderPosition + 1;
            borderPosition2 = borderPosition2 - 1;
        }

        if (reallySetObstacle) {
            boolean obstaclePosCorrect = false;
            while (!obstaclePosCorrect) {
                obstaclePosition = randomNumber.nextInt(width);
                if (obstaclePosition > borderPosition && obstaclePosition < borderPosition2) {
                    obstaclePosCorrect = true;
                }
            }
            stringList.add(obstaclePosition, generateObstacleSign());
            stringList.remove(obstaclePosition - 1);
        }

        stringList.add(borderPosition, generateLeftBorderSign());
        stringList.add(borderPosition2, generateRightBorderSign());

        return stringList;
    }

    @Override
    public void setMatrixDimensions(int heigh, int widt) {
        height = heigh;
        width = widt;
    }

    @Override
    public void moveLeft() {
        playerPosition = playerPosition - 1;


    }


    @Override
    public void moveRight() {
        playerPosition = playerPosition + 1;

    }

    @Override
    public void moveUp() {
        if (playerHigh != 0) {
            playerHigh = playerHigh + 1;
        }
    }

    @Override
    public void moveDown() {
        if (playerHigh != raceTrack.size()) {
            playerHigh = playerHigh - 1;
        }
    }

    @Override
    public void enterPressed() {

    }

    @Override
    public boolean isCrashed() {
        return crashed;
    }

}