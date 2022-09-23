package com.went.play.ground.exam3;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 16:21
 */
public class Q4 {

    static String SQL = "DELETE p2\n" +
            "from Person p1\n" +
            "left join Person p2\n" +
            "on p1.email = p2.email and p1.id < p2.id";
}
