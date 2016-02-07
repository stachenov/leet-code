/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

/**
 *
 * @author alqualos
 */
public class TreeLink {
    public void connect(TreeLinkNode root) {
        for (TreeLinkNode head = root; head != null; ) {
            TreeLinkNode nextHead = null, nextTail = null;
            for (TreeLinkNode node = head; node != null; node = node.next) {
                TreeLinkNode child = node.left;
                if (nextTail == null) {
                    nextHead = nextTail = child;
                } else {
                    nextTail.next = child;
                    nextTail = child;
                }
                child = node.right;
                if (nextTail == null) {
                    nextHead = nextTail = child;
                } else {
                    nextTail.next = child;
                    nextTail = child;
                }
            }
            head = nextHead;
        }
    }
    
    public static void main(String[] args) {
        
    }
}
