package org.n1nes0cks.exos_menuapi;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class  Utils {
    public static <T> T[] toArray(ArrayList<T> list, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(clazz, list.size());
        return list.toArray(array);
    }

    public static <T> ArrayList<T> toArrayList(@NotNull T[] objects) {
        return new ArrayList<>(Arrays.asList(objects));
    }
}
