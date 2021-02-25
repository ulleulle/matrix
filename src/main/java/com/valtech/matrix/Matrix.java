package com.valtech.matrix;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Matrix implements MatrixInterface {
    Random randomNumber = new Random();
    private int height;
    private int width;
    List<List<String>> matrix = new ArrayList<>();

    @Override
    public List<List<String>> getFullFrame() {
       matrix.add(0, generateRow());
       if(matrix.size() > height){
           matrix.remove(height);
       }
       return matrix;
    }


    private String generateRandomSign() {
        String alphabet = "ABCDEFGHIHAGFEDBJW/&%$%876543";

        int index = randomNumber.nextInt(alphabet.length());
        char randomChar = alphabet.charAt(index);
        return String.valueOf(randomChar);

    }


    private List<String> generateRow() {
    List<String> stringList = new ArrayList<>();
        for (int i = 0; i <= width; ++i) {
            stringList.add(generateRandomSign());
        }

        return stringList;
    }

    @Override
    public void setMatrixDimensions(int heigh, int widt) {
        height = heigh;
        width = widt;
    }


}
