package exam1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 10:49
 */
public class Q5 {

    static List<String> res;
    static int[] segment = new int[4];

    public static void main(String[] args) {
        List<String> list = restoreIpAddresses("101023");
        for (String tmp : list) {
            System.out.println(tmp);
        }
    }


    public static List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        dfs(s, 0, 0);
        return res;
    }

    /**
     * @param s
     * @param id    ip段索引
     * @param index 字符串索引
     */
    private static void dfs(String s, int id, int index) {
        if (id == 4) {
            if (index == s.length()) {
                StringBuilder ss = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    if (i != 3) {
                        ss.append(segment[i] + ".");
                    } else {
                        ss.append(segment[i]);
                    }
                }
                res.add(ss.toString());
            }
            // 这里必须返回。
            return;
        }
        //  特殊判断  搜索到了最后
        if (index == s.length()) return;
        // 不能有0前导，所以这个数单独就能作为一个IP段
        if (s.charAt(index) == '0') {
            segment[id] = 0;
            dfs(s, id + 1, index + 1);
        }
        // 枚举可能符合要求的ip段进行dfs
        int ip = 0;
        for (int end = index; end < s.length(); end++) {
            ip = 10 * ip + s.charAt(end) - '0';
            if (ip > 0 && ip <= 255) {
                segment[id] = ip;
                dfs(s, id + 1, end + 1);
            } else {
                // 提前返回 剪枝
                break;
            }
        }
        return;
    }

}
