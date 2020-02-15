package com.common.utils;

public class ArrayUtil {
    /**
     * 获取数组中某个元素的索引下标
     * @param array
     * @param obj
     * @return
     */
    public static int indexOf(Comparable[] array, Comparable obj){
        int cnt=array.length;
        for(int result=0; result<=cnt-1; result++){
            Comparable t=array[result];
            if (t.compareTo(obj)==0){
                return result;
            }
        }
        return -1;
    }
}
