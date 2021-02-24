package com.valtech.matrix;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MatrixInterface matrix = new Matrix();
        matrix.setMatrixDimensions(15, 40);

        while(true) {
            List<List<Character>> fullFrame = matrix.getFullFrame();
            Main.clear();
            Main.print(fullFrame);
            Thread.sleep(200);
        }
    }

    private static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void print(List<List<Character>> fullFrame) {
        for (List<Character> characters:fullFrame) {
            StringBuilder builder = new StringBuilder(characters.size());
            for(Character ch: characters)
            {
                builder.append(ch);
            }
            System.out.println(builder.toString());
        }
    }
}
