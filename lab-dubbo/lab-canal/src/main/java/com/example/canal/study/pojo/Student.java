package com.example.canal.study.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 普通的实体domain对象
 *
 * @Data 用户生产getter、setter方法
 */
@Data
public class Student implements Serializable {
    private String id;
    private String name;
    private int age;
    private String sex;
    private String city;

    public boolean isPalindrome(String s) {
        int len = s.length();
        if (len == 0 || len == 1) {
            return true;
        }
        int left = 0;
        int right = len - 1;
        while (left < right) {
            while (left < right
                    && !(s.charAt(left) <= 'z' && s.charAt(left) >= 'a')
                    && !(s.charAt(left) <= 'Z' && s.charAt(left) >= 'A')) {
                ++left;
            }
            while (left < right
                    && !(s.charAt(right) <= 'z' && s.charAt(right) >= 'a')
                    && !(s.charAt(right) <= 'Z' && s.charAt(right) >= 'A')) {
                --right;
            }
            if (left < right) {
                if (s.charAt(left) == s.charAt(right)
                        || s.charAt(left) - s.charAt(right) == 32
                        || s.charAt(right) - s.charAt(left) == 32) {
                    ++left;
                    --right;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.isPalindrome("race a car"));
    }
}
