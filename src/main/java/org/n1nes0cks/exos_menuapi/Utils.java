package org.n1nes0cks.exos_menuapi;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Integer> selectInvSquare(int startIndex,int width, int height) {
        List<Integer> slotsIndex = new ArrayList<>();
        for(int row = 0; row < height; ++row) {
            for (int i = startIndex; i < startIndex + width; ++i) {
                slotsIndex.add(i+(row*9));
            }
        }
        return slotsIndex;
    }
}
