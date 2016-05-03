/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

/**
 *
 * @author alqualos
 */
public class KMerge {
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length);
    }

    private static ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (start >= end) {
            return null;
        } else if (start == end - 1) {
            return lists[start];
        } else {
            int mid = start + (end - start) / 2;
            System.out.println("Merge " + start + " to " + mid);
            ListNode left = mergeKLists(lists, start, mid);
            System.out.println("Merge " + mid + " to " + end);
            ListNode right = mergeKLists(lists, mid, end);
            return merge2Lists(left, right);
        }
    }
    
    private static ListNode merge2Lists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        else if (list2 == null)
            return list1;
        ListNode head = null, tail = null;
        ListNode left = list1, right = list2;
        while (left != null || right != null) {
            ListNode next;
            if (left == null) {
                next = right;
                right = right.next;
            } else if (right == null || right.val > left.val) {
                next = left;
                left = left.next;
            } else {
                next = right;
                right = right.next;
            }
            if (tail == null) {
                head = next;
                tail = next;
            } else {
                tail.next = next;
                tail = next;
            }
        }
        if (tail != null) {
            tail.next = null;
        }
        return head;
    }
    
    public static void main(String[] args) {
        
    }
    
    public static class ListNode {
        public int val;
        public ListNode next;
    }
}
