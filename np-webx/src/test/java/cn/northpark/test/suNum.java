package cn.northpark.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangyang
 * @date 2021年04月21日 18:59:46
 * 100以内的素数题目
 */
public class suNum {


    public static void main(String[] args) {

        List<Integer> a = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        List<Integer> b= new ArrayList<>();
        List<Integer> c= new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            b.add(a.get(i) + 13);
            c.add(a.get(i) + 15);
        }
        System.err.println("b--"+b);
        System.err.println("c--"+c);

        for (int i = 0; i < c.size(); i++) {
            if(a.contains(c.get(i))){
                System.err.println("c13匹配=====》"+c.get(i));
            }
        }

        for (int i = 0; i < b.size(); i++) {
            if(a.contains(b.get(i))){
                System.err.println("b13匹配=====》"+b.get(i));
            }
        }
    }
}
