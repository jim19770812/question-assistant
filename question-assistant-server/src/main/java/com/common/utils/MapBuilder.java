package com.common.utils;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {
    public static <K, V> MapBuilder<K, V> map(Map<K, V> mapInstance){
        var result=new MapBuilder<K, V>(mapInstance);
        return result;
    }
    public static <K, V> MapBuilder<K, V> map(){
        var result=new MapBuilder<K, V>(new HashMap<>());
        return result;
    }
    private Map<K, V> mapInstance;

    public MapBuilder(Map<K, V> mapInstance){
        this.mapInstance=mapInstance;
    }

    public MapBuilder<K, V> put(K k, V v){
        this.mapInstance.put(k, v);
        return this;
    }
    public Map<K, V> build(){
        return this.mapInstance;
    }
}
