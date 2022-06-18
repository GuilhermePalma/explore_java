package com.guilhermepalma.streamexercices.util;

import java.util.Collection;
import java.util.Map;

public class Util {

    public static <T> boolean isNull(T value){
        return value == null;
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection){
        return isNull(collection) || collection.isEmpty();
    }

    public static <T, V> boolean isNullOrEmpty(Map<T, V> mapValues){
        return isNull(mapValues) || mapValues.isEmpty();
    }
}
