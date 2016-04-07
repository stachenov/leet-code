/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alqualos
 */
public class ClosestKBST {
    private static final int ADDR_ENTRY = 0,
                             ADDR_CURRENT = 1,
                             ADDR_SECOND = 2,
                             ADDR_EXIT = 3;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (true) {
            TreeNode next;
            if (target == current.val) {
                break;
            } else if (target > current.val) {
                next = current.right;
            } else {
                next = current.left;
            }
            if (next == null)
                break;
            stack.push(current);
            current = next;
        }
        // Now we are at approximately right point.
        // We need to perform in-order traversal from this point
        // in both directions.
        // Emulating recursive calls of the following functions:
        // if (current.right/left != null)   <-- ADDR_ENTRY
        //     traverse(current.right/left);
        // array[index++] = current.val;     <-- ADDR_CURRENT
        // if (current.left/right != null)   <-- ADDR_SECOND
        //     traverse(current.left/right);
        // return;                           <-- ADDR_EXIT
        // Will traverse in both directions, so need two sets of everything:
        // index 0 - traversing left (right-current-left), index 1 - right (left-current-right)
        Deque<TreeNode>[] st = new Deque[2];
        st[0] = stack;
        st[1] = new ArrayDeque<>(stack);
        int[] addr = new int[2];
        TreeNode[] par = new TreeNode[] {current, current};
        if (current.val <= target) {
            addr[0] = ADDR_CURRENT; // need to include current
            addr[1] = ADDR_SECOND; // skip current
        } else { // ditto
            addr[0] = ADDR_SECOND;
            addr[1] = ADDR_CURRENT;
        }
        List<Integer> closest = new ArrayList<>();
        TreeNode nextLE = null, nextGT = null;
        while (closest.size() < k) {
            if (nextLE == null) {
                nextLE = findNext(st, addr, par, 0);
            }
            if (nextGT == null) {
                nextGT = findNext(st, addr, par, 1);
            }
            if (nextLE == null) {
                closest.add(nextGT.val);
                nextGT = null;
            } else if (nextGT == null || Math.abs(nextGT.val - target) > Math.abs(nextLE.val - target)) {
                closest.add(nextLE.val);
                nextLE = null;
            } else {
                closest.add(nextGT.val);
                nextGT = null;
            }
        }
        return closest;
    }
    
    private static TreeNode findNext(Deque<TreeNode>[] st, int[] addr, TreeNode[] par, int i) {
        while (addr[i] != ADDR_EXIT || !st[i].isEmpty()) {
            switch (addr[i]) {
                case ADDR_ENTRY:
                case ADDR_SECOND: {
                    TreeNode next = i == (addr[i] == ADDR_ENTRY ? 0 : 1) ?
                                    par[i].right : par[i].left;
                    if (next == null) {
                        addr[i] = addr[i] == ADDR_ENTRY ? ADDR_CURRENT : ADDR_EXIT;
                    } else {
                        st[i].push(par[i]);
                        par[i] = next;
                        addr[i] = ADDR_ENTRY;
                    }
                    break;
                }
                case ADDR_CURRENT: {
                    addr[i] = ADDR_SECOND;
                    return par[i];
                }
                case ADDR_EXIT:
                    TreeNode up = st[i].poll();
                    if (up != null) {
                        // If we came down through the right path and we're going
                        // right-current-left, then we are between recursive calls,
                        // the same for came-down-left and traversing left-current-right.
                        // Otherwise, we exit another recursion level.
                        addr[i] = par[i] == (i == 0 ? up.right : up.left) ?
                                       ADDR_CURRENT : ADDR_EXIT;
                    } // else the address doesn't matter (top level exit)
                    par[i] = up;
                    break;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        Random rnd = new Random(0);
        ClosestKBST kbst = new ClosestKBST();
        TreeNode[] trees = new TreeNode[100];
        for (int i = 0; i < trees.length; ++i) {
            trees[i] = LeetCode.buildBBST(rnd, 10000);
        }
        long before = System.currentTimeMillis();
        for (int i = 0; i < 1; ++i) {
            kbst.closestKValues(trees[i % trees.length], 9000.5, 100);
        }
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }
}
