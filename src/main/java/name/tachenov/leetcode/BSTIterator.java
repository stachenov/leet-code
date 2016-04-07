/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.Random;

/**
 *
 * @author alqualos
 */
public class BSTIterator {

    private TreeNode next;
    private final TreeNode root;

    public BSTIterator(TreeNode root) {
        this.root = new TreeNode(Integer.MIN_VALUE);
        this.root.right = root;
        this.next = this.root;
    }

    public boolean hasNext() {
        if (next == null) {
            return false;
        }
        if (next.right != null) {
            next = next.right;
            while (next.left != null) {
                next = next.left;
            }
            return true;
        } else {
            TreeNode temp = null;
            TreeNode cur = root;
            while (cur != next) {
                if (cur.val < next.val) {
                    cur = cur.right;
                } else {
                    temp = cur;
                    cur = cur.left;
                }
            }
            next = temp;
            if (next != null) {
                return true;
            } else {
                return false;
            }
        }
    }

    public int next() {
        return next.val;
    }
    
    public static void main(String[] args) {
        TreeNode bst = LeetCode.buildBBST(new Random(0), 3);
        BSTIterator it = new BSTIterator(bst);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
