package com.ufsite.utils;

//
public class MathUtils {


    public static void main(String[] args) {
        printNumbers2();
    }

    public static void printNumbers2() {
        for (int i = 100; i <= 999; i++) {
            if (isUnique(i)) {
                for (int j = i + 1; j <= 999; j++) {
                    if (isUnique(j)) {
                        int sum = i + j;
                        if (sum >= 1000 && sum <= 9999 && isUnique(sum)) {
                            if (isUniqueWithZero(i, j, sum)) {
                                System.out.printf("%d + %d = %d%n", i, j, sum);
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean isUnique(int num) {
        int[] digits = new int[10];
        while (num > 0) {
            int digit = num % 10;
            if (digits[digit] > 0) {
                return false;
            }
            digits[digit]++;
            num /= 10;
        }
        return true;
    }

    public static boolean isUniqueWithZero(int num1, int num2, int num3) {
        int[] digits = new int[10];
        while (num1 > 0) {
            int digit = num1 % 10;
            if (digits[digit] > 0) {
                return false;
            }
            digits[digit]++;
            num1 /= 10;
        }
        while (num2 > 0) {
            int digit = num2 % 10;
            if (digits[digit] > 0) {
                return false;
            }
            digits[digit]++;
            num2 /= 10;
        }
        while (num3 > 0) {
            int digit = num3 % 10;
            if (digits[digit] > 0) {
                return false;
            }
            digits[digit]++;
            num3 /= 10;
        }
        return true;
    }

    public static void printNumbers1() {
        for (int i = 100; i <= 999; i++) {
            for (int j = i + 1; j <= 999; j++) {
                int sum = i + j;
                if (sum >= 1000 && sum <= 9999 && isUnique(sum) && isUnique(i) && isUnique(j)) {
                    System.out.printf("%d + %d = %d%n", i, j, sum);
                }
            }
        }
    }

    public static void printNumbers() {
        int result = 0;
        for (int i = 123; i <= 987; i++) {
            for (int j = 123; j <= 987; j++) {
                result = i + j;
                if ((result > 1000) && isTrue(result, i, j)) {

                    if (result == i + j && i > j) {

                        System.out.println(i + "+" + j + "=" + result);
                    }
                }
            }
        }
    }

    public static boolean isTrue(int result, int i, int j) {
        String str = "0123456789";
        String s = result + String.valueOf(i) + j;
        int index;
        String str1;
        for (int k = 0; k < str.length(); k++) {
            index = s.indexOf(str.charAt(k));
            if (index >= 0) {
                str1 = s.replaceFirst(str.charAt(k) + "", "a");
                s = str1;
            }
        }
        String s1 = s.replaceAll("a", "");
        if (s1.length() == 0) {
            return true;
        }
        return false;
    }

}
