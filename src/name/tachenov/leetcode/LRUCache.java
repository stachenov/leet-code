/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alqualos
 */
public class LRUCache {
    
    private final int capacity;
    private final Map<Integer, Node> valueFromKey;
    private Node tail, head;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        valueFromKey = new HashMap<>(capacity);
    }
    
    public int get(int key) {
        Node value = valueFromKey.get(key);
        if (value == null)
            return -1;
        if (value == head)
            return value.value; // MRU
        moveToHead(value);
        for (Node n = tail; n != null; n = n.next) {
            assert n.previous == null || n.previous.next == n;
            assert n.next == null || n.next.previous == n;
            System.out.println(n.key + "," + n.value);
        }
        System.out.println();
        return value.value;
    }
    
    public void set(int key, int value) {
        Node newNode = new Node(key, value);
        Node oldNode = valueFromKey.put(key, newNode);
        if (oldNode == null) {
            if (head == null) {
                head = tail = newNode;
            } else {
                head.next = newNode;
                newNode.previous = head;
                head = newNode;
            }
            if (valueFromKey.size() > capacity) {
                valueFromKey.remove(tail.key);
                tail = tail.next;
                tail.previous = null;
            }
        } else {
            moveToHead(oldNode, newNode);
        }
        for (Node n = tail; n != null; n = n.next) {
            assert n.previous == null || n.previous.next == n;
            assert n.next == null || n.next.previous == n;
            System.out.println(n.key + "," + n.value);
        }
        System.out.println();
    }
    
    private void moveToHead(Node node) {
        if (node == tail) {
            tail = tail.next;
            tail.previous = null;
        } else {
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }
        head.next = node;
        node.previous = head;
        node.next = null;
        head = node;
    }
    
    private void moveToHead(Node oldNode, Node newNode) {
        if (head == tail) {
            head = tail = newNode;
        } else {
            if (oldNode.previous == null) {
                tail = tail.next;
                tail.previous = null;
            } else {
                oldNode.previous.next = oldNode.next;
            }
            if (oldNode.next == null) {
                head = head.previous;
                // head.next will be newNode anyway
            } else {
                oldNode.next.previous = oldNode.previous;
            }
            head.next = newNode;
            newNode.previous = head;
            head = newNode;
        }
    }
    
    private static class Node {
        int key;
        int value;
        Node previous, next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(10);
        cache.set(10,13);
        cache.set(3,17);
        cache.set(6,11);
        cache.set(10,5);
        cache.set(9,10);
        System.out.println(cache.get(13));
        cache.set(2,19);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.set(5,25);
        System.out.println(cache.get(8));
        cache.set(9,22);
        cache.set(5,5);
        cache.set(1,30);
        System.out.println(cache.get(11));
        cache.set(9,12);
        System.out.println(cache.get(7));
        System.out.println(cache.get(5));
        System.out.println(cache.get(8));
        System.out.println(cache.get(9));
        cache.set(4,30);
        cache.set(9,3);
        System.out.println(cache.get(9));
        System.out.println(cache.get(10));
        System.out.println(cache.get(10));
        cache.set(6,14);
        cache.set(3,1);
        System.out.println(cache.get(3));
        cache.set(10,11);
        System.out.println(cache.get(8));
        cache.set(2,14);
        System.out.println(cache.get(1));
        System.out.println(cache.get(5));
        System.out.println(cache.get(4));
        cache.set(11,4);
        cache.set(12,24);
        cache.set(5,18);
        System.out.println(cache.get(13));
        cache.set(7,23);
        System.out.println(cache.get(8));
        System.out.println(cache.get(12));
        cache.set(3,27);
        cache.set(2,12);
        System.out.println(cache.get(5));
        cache.set(2,9);
        cache.set(13,4);
        cache.set(8,18);
        cache.set(1,7);
        System.out.println(cache.get(6));
        cache.set(9,29);
        cache.set(8,21);
        System.out.println(cache.get(5));
        cache.set(6,30);
        cache.set(1,12);
        System.out.println(cache.get(10));
        cache.set(4,15);
        cache.set(7,22);
        cache.set(11,26);
        cache.set(8,17);
        cache.set(9,29);
        System.out.println(cache.get(5));
        cache.set(3,4);
        cache.set(11,30);
        System.out.println(cache.get(12));
        cache.set(4,29);
        System.out.println(cache.get(3));
        System.out.println(cache.get(9));
        System.out.println(cache.get(6));
        cache.set(3,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(10));
        cache.set(3,29);
        cache.set(10,28);
        cache.set(1,20);
        cache.set(11,13);
        System.out.println(cache.get(3));
        cache.set(3,12);
        cache.set(3,8);
        cache.set(10,9);
        cache.set(3,26);
        System.out.println(cache.get(8));
        System.out.println(cache.get(7));
        System.out.println(cache.get(5));
        cache.set(13,17);
        cache.set(2,27);
        cache.set(11,15);
        System.out.println(cache.get(12));
        cache.set(9,19);
        cache.set(2,15);
        cache.set(3,16);
        System.out.println(cache.get(1));
        cache.set(12,17);
        cache.set(9,1);
        cache.set(6,19);
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
        System.out.println(cache.get(5));
        cache.set(8,1);
        cache.set(11,7);
        cache.set(5,2);
        cache.set(9,28);
        System.out.println(cache.get(1));
        cache.set(2,2);
        cache.set(7,4);
        cache.set(4,22);
        cache.set(7,24);
        cache.set(9,26);
        cache.set(13,28);
        cache.set(11,26);
    }
}