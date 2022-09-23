package com.went.play.ground.exam1;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 10:46
 */
public class Q4 {

    public static void main(String[] args) {
        int[] nums = {1,3,5,8,10};
        System.out.println("插入位置下标：" + searchInsert(nums, 6));
    }

    public static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}
