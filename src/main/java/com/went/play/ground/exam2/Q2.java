package com.went.play.ground.exam2;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 14:23
 */
public class Q2 {

    public static void main(String[] args) {
        ListNode n5 = new ListNode("5", null);
        ListNode n4 = new ListNode("4", n5);
        ListNode n3 = new ListNode("3", n4);
        ListNode n2 = new ListNode("2", n3);
        ListNode head = new ListNode("1", n2);


        head = removeNthFromEnd(head, 2);

        System.out.println("print begin ");
        while (head.next != null) {
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.print(head.value);
        System.out.println("\nprint done");
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        //如果fast == null, 说明fast走n步走到了最后
        //说明要删除的是头结点
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }


    private static class ListNode {
        String value;
        ListNode next;

        public ListNode(String value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }
}


