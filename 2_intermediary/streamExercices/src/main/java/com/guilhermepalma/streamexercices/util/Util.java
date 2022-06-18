package com.guilhermepalma.streamexercices.util;

import java.util.Collection;

public class Util {

    public static <T> boolean isNull(T value){
        return value == null;
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection){
        return isNull(collection) || collection.isEmpty();
    }
}
