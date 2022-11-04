package exam2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 13:16
 */
public class Q1 {

    public static void main(String[] args) {
        int[] arr = {1, 3, 100, 3, 4, 6, 5};
        System.out.println(longestConsecutive(arr));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return nums.length;
        Set<Integer> set = new HashSet();
        for (int num : nums) {
            set.add(num);
        }

        int maxLen = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int curNum = num;
                int curLen = 1;
                while (set.contains(curNum + 1)) {
                    curNum += 1;
                    curLen += 1;
                }
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;
    }
}
