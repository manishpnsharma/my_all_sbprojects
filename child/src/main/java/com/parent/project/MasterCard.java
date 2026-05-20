package com.parent.project;

import java.util.Arrays;
import java.util.List;

public class MasterCard {

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(null, 123, 234, 546, 6756, 234, 767, 453, 234);
        Integer findNum = 234;
        System.out.println(fetchIndex(intList, findNum));
    }

    private static Integer fetchIndex(List<Integer> intList, Integer findNum) {
        int y = 0 ;
        for (int i = 0; i < intList.size(); i++) {
            if (intList.get(i).equals(findNum)) {
                y = i;
            }
        }

        return y;
    }
}

