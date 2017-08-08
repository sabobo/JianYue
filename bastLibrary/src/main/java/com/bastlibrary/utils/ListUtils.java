package com.bastlibrary.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class ListUtils {

    public static boolean isEmpty(List list){
        if (list == null){
            return true;
        }
        return list.size() == 0;
    }
}
