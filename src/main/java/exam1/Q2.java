package exam1;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 10:18
 */
public class Q2 {

    public static void main(String[] args) {
        int a = 12321;
        System.out.printf("%d " + (isPalindrome(a) ? "是": "不是") + "回文整数", a);
    }


    // 回文整数判断
    public static boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0) {
            return false;
        }
        int rem = 0;
        int temp = x;
        int y = 0;
        // 采用把一个整数的数字进行逆序处理
        while (temp != 0) {
            rem = temp % 10;
            y = y * 10 + rem;
            temp = temp / 10;
        }
        return y == x;
    }

}
