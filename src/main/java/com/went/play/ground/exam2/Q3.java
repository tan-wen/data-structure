package com.went.play.ground.exam2;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 14:41
 */
public class Q3 {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        System.out.println(convert(s, numRows));
    }

    public static String convert(String s, int numRows) {
        StringBuilder s1 = new StringBuilder();
        if (numRows == 1) return s;//特殊情况的处理
        int m = (numRows - 1) * 2;//每个周期的数
        int n = m;//同行相邻两点的间隔距离
        int i = 0;//用来寻找每个周期第一个数
        int mark = 0;//用来判断是否是每个周期的第一个数
        int location = 0;//每次循环的位置
        while (s1.length() < s.length()) {
            location += n * mark;//每次都要进行位置的更新
            mark = 1;//用来判断是否为周期的第一个数
            if (location >= s.length()) {
                i++;//周期第一个数的改变
                n = m - i * 2 > 0 ? i * 2 : m;
                location = i;
                mark = 0;
                continue;
            }
            s1.append(s.charAt(location));
            n = m - n > 0 ? m - n : n;
        }
        return s1.toString();
    }

}
