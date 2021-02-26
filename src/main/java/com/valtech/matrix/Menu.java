package com.valtech.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu implements SteeringInterface, DisplayInterface {

    int chosenList = 0;
    int maxGames = 3;

    private MainInterface mainInterface;
    List<List<String>> menu = new ArrayList<>();

    public Menu(MainInterface mainInterface) {
        this.mainInterface = mainInterface;
    }

    @Override
    public List<List<String>> getFullFrame() {
        String raceFull = Racing.class.toString();
        String race = raceFull.replaceAll("class com.valtech.matrix.", "");
        race = "[ ] " + race;
        String matrixFull = Matrix.class.toString();
        String matrix = matrixFull.replaceAll("class com.valtech.matrix.", "");
        matrix = "[ ] " + matrix;
        String snakeFull = Snake.class.toString();
        String snake = snakeFull.replaceAll("class com.valtech.matrix.", "");
        snake = "[ ] " + snake;

        menu.clear();
        menu.add(addRow(race));
        menu.add(addRow(matrix));
        menu.add(addRow(snake));
        List<String> strings = menu.get(chosenList);
        strings.set(1, "X");
        return menu;
    }

    private List<String> addRow(String game) {

        return Arrays.asList(game.split(""));
    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveUp() {
        if (chosenList > 0) {
            chosenList--;
        }
    }

    @Override
    public void moveDown() {
        if (chosenList < maxGames - 1) {
            chosenList++;
        }
    }

    @Override
    public void enterPressed() {
        if (chosenList == 0) {
            mainInterface.selectedApplication(Racing.class.toString());
        }
        if (chosenList == 1) {
            mainInterface.selectedApplication(Matrix.class.toString());
        }
        if (chosenList == 2) {
            mainInterface.selectedApplication(Snake.class.toString());
        }
    }

    @Override
    public boolean isCrashed() {
        return false;
    }

    @Override
    public void setMatrixDimensions(int height, int width) {

    }
}