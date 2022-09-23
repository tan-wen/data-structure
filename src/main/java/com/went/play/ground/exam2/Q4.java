package com.went.play.ground.exam2;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 14:46
 */
public class Q4 {

    public static void main(String[] args) {

        int[] A = {2, 3, 1, 1, 4, 5};
        int step = jump(A);
        System.out.println("最小跳跃次数：" + step);
    }


    public static int jump(int[] a) {

        int jumpTimes = 0;
        int maxPosition = 0;
        int reached = 0;
        for (int i = 0; i < a.length; i++) {
            if (reached < i) {
                jumpTimes++;
                reached = maxPosition;
            }
            maxPosition = Math.max(maxPosition, i + a[i]);
        }
        return jumpTimes;
    }

}
