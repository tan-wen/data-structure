package com.went.play.ground.exam3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 14:58
 */
public class Q5 {

    static List<List<Integer>> ans = new ArrayList<>();
    static List<Integer> temp = new ArrayList<>();
    static int[] visited;

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        System.out.println(permuteUnique(nums));
    }

    private static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        visited = new int[nums.length];
        backtrack(nums, 0);
        return ans;
    }

    private static void backtrack(int[] nums, int begin) {
        if (begin == nums.length) {
            ans.add(new ArrayList(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 0) {
                // 这行代码用来防止重复
                if (i != 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0) continue;//剪枝
                temp.add(nums[i]);
                visited[i] = 1;
                backtrack(nums, begin + 1);
                temp.remove(temp.size() - 1);
                visited[i] = 0;
            }
        }
    }

}
