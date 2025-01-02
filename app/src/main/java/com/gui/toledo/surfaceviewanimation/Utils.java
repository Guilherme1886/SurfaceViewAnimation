package com.gui.toledo.surfaceviewanimation;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Random;

public class Utils {

    static Random random = new Random();

    public static int getRandomIndex(@NonNull List<Integer> list) {
        if (list.isEmpty()) {
            return -1;
        }
        int min = 0;
        int max = list.size();
        return random.nextInt(max - min) + min;
    }
}
