package com.went.play.ground.exam1;

import com.clickhouse.client.internal.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 10:36
 */
public class Q3 {

    public static void main(String[] args) {

        int[][] intervals = {
                 {1,3}
                ,{2,6}
                ,{8,10}
                ,{15,18}
        };
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    public static int[][] merge(int[][] intervals) {
        //如果数组行数为0，则直接返回一个空的二维数组（一行2列）
        if (intervals.length == 0) {
            return new int[0][2];
        }
        //将目标数组中所有的区间安装左端点升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        //按照顺序依次考虑剩下的每个区间
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                //如果当前区间的左端点在数组merged中最后一个区间的右端点之后，那么这两个区间便不会重合，可以将这个区间加入merged的末
                merged.add(new int[]{L, R});
            } else {
                //否则，重合，便需要用当前区间的右端点更新数组merged中最后一个区间的右端点，更新的值为二者的较大者
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
