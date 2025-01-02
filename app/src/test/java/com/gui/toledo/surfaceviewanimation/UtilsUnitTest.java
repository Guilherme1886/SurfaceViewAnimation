package com.gui.toledo.surfaceviewanimation;

import static com.gui.toledo.surfaceviewanimation.Utils.getRandomIndex;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import android.graphics.Color;

import java.util.ArrayList;

public class UtilsUnitTest {
    @Test
    public void getRandomIndex_listWithItems_returnsIndexInRange() {
        // GIVEN
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Color.BLUE);
        list.add(Color.DKGRAY);
        //WHEN
        int result = getRandomIndex(list);
        boolean condition = result >= 0 && result < list.size();
        // THEN
        assertTrue(condition);
    }

    @Test
    public void getRandomIndex_emptyList_returnsNegativeOne() {
        //GIVEN
        ArrayList<Integer> list = new ArrayList<>();
        //WHEN
        int result = getRandomIndex(list);
        boolean condition = result == -1;
        //THEN
        assertTrue(condition);
    }
}