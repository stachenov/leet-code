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
public class MorrisPostorder {
    
    private static void printReverse(Node node, Node end) {
        //Deque<Integer> deque = new LinkedList<>();
        Node prev = null, next;
        for (Node n = node; n != end; n = next) {
            next = n.right;
            n.right = prev;
            prev = n;
        }
        assert prev != null;
        next = end;
        for (Node n = prev; n != null; n = prev) {
            prev = n.right;
            n.right = next;
            next = n;
            System.out.println(next.val);
        }
    }
    
    private static void morrisPostorder(Node root) {
        Node current = root;
        while (current != null) {
            if (current.left != null) {
                Node chain = current.left;
                while (chain.right != current && chain.right != null) {
                    chain = chain.right;
                }
                if (chain.right != current) {
                    chain.right = current;
                    current = current.left; // left
                } else {
                    printReverse(current.left, current);
                    chain.right = null;
                    current = current.right; // up
                }
            } else {
                current = current.right; // right
            }
        }
    }
    
    private static void postorder(Node root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.val);
    }
    
    public static void main(String[] args) {
        Node root = new Node();
        root.val = 0;
        root.left = new Node();
        root.left.val = 10;
        root.left.left = new Node();
        root.left.left.val = 12;
        root.left.right = new Node();
        root.left.right.val = 17;
        root.right = new Node();
        root.right.val = 20;
        Node dummy = new Node();
        dummy.left = root;
        morrisPostorder(dummy);
        postorder(root);
    }

    private static class Node {
        private int val;
        private Node left;
        private Node right;
    }
    
}
