/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * @author alqualos
 */
public class MinStack {
    private int[] stack = new int[16];
    private int[] heap = new int[16];
    private int[] heapFromStack = new int[16];
    private int[] stackFromHeap = new int[16];
    private int size = 0;
    
    public void push(int x) {
        if (size == stack.length) {
            stack = Arrays.copyOf(stack, stack.length * 2);
            heap = Arrays.copyOf(heap, heap.length * 2);
            heapFromStack = Arrays.copyOf(heapFromStack, heapFromStack.length * 2);
            stackFromHeap = Arrays.copyOf(stackFromHeap, stackFromHeap.length * 2);
        }
        stack[size++] = x;
        percolateUp();
    }

    public void pop() {
        --size;
        percolateDown();
    }

    public int top() {
        return stack[size - 1];
    }

    public int getMin() {
        return heap[0];
    }
    
    private void percolateUp() {
        int pos = size - 1;
        while (pos > 0) {
            int parent = (pos - 1) / 2;
            if (heap[parent] <= stack[size - 1]) {
                break;
            }
            heap[pos] = heap[parent];
            heapFromStack[stackFromHeap[parent]] = pos;
            stackFromHeap[pos] = stackFromHeap[parent];
            pos = parent;
        }
        heap[pos] = stack[size - 1];
        heapFromStack[size - 1] = pos;
        stackFromHeap[pos] = size - 1;
    }
    
    private void percolateDown() {
        int pos = heapFromStack[size]; // size already decreased
        if (pos == size)
            return; // removed the last element
        int last = heap[size];
        while (pos < size / 2) {
            int left = pos * 2 + 1;
            int right = pos * 2 + 2;
            int next = left; // just in case there is no right
            if (right < size && heap[right] < heap[left]) {
                next = right;
            }
            if (last <= heap[next])
                break;
            heap[pos] = heap[next];
            heapFromStack[stackFromHeap[next]] = pos;
            stackFromHeap[pos] = stackFromHeap[next];
            pos = next;
        }
        heap[pos] = last;
        heapFromStack[stackFromHeap[size]] = pos;
        stackFromHeap[pos] = stackFromHeap[size];
    }
    
    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(3);
        ms.push(2);
        ms.push(1);
        System.out.println(ms.getMin());
        ms.push(-1);
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.getMin());
        ms.pop();
    }
}
