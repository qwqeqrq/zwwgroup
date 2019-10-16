package com.qsmx.zww.algorithm;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {
    private static int[] a = {12, 45, 2, 14, 125, 574, 54, 786, 45, 2, 5, 6};

    private static void maopaoSort(int[] a) {
        /**
         * 冒泡：思路 相邻元素 两两比较 主要控制小循环每次从0开始
         */
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i-1; j++) {
                if (a[j] > a[j + 1]) {
                    int c = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = c;
                }
            }
        }
    }

    private static void selectSort(int[] a) {
        /**
         * 选择：思路 每轮第一个和后面每一个比较 挑出最小
         */
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length-1; j++) {
                if (a[i] > a[j + 1]) {
                    int c = a[j + 1];
                    a[j + 1] = a[i];
                    a[i] = c;
                }
            }
        }
    }

    private static void fastSort(int[] a) {
        /**
         * 快排：思路 从左边选一个基准值，每次将基准值归位  然后递归进行
         */
       //todo 待完成
    }

    private static void printArray(List list) {
        list.stream().forEach((e) -> {
            System.out.print(" ");
            System.out.print(e);
        });


    }

    /*public static void main(String[] args) {
        System.out.println("排序之前打印顺序");
        List list = Arrays.stream(a).boxed().collect(Collectors.toList());
        printArray(list);//打印数组
        System.out.println();
        //调用排序
        selectSort(a);
        System.out.println("排序之后打印顺序");
        List list2 = Arrays.stream(a).boxed().collect(Collectors.toList());
        printArray(list2);
    }*/

}
