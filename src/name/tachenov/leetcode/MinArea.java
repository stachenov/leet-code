/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.zip.CRC32;

/**
 *
 * @author alqualos
 */
public class MinArea {
    public int minArea(char[][] image, int x, int y) {
        int height = image.length, width = image[0].length;
        boolean[][] visited = new boolean[height][width];
        int minX = x, minY = y, maxX = x, maxY = y;
        Queue<int[]> nextStep = new ArrayDeque<>();
        nextStep.add(new int[] {x, y});
        do {
            int[] step = nextStep.remove();
            if (step[0] < minX) {
                minX = step[0];
            }
            if (step[0] > maxX) {
                maxX = step[0];
            }
            if (step[1] < minY) {
                minY = step[1];
            }
            if (step[1] > maxY) {
                maxY = step[1];
            }
            if (step[0] > 0 && image[step[0] - 1][step[1]] == '1' && !visited[step[0] - 1][step[1]]) {
                nextStep.add(new int[] {step[0] - 1, step[1]}); // up
                visited[step[0] - 1][step[1]] = true;
            }
            if (step[1] > 0 && image[step[0]][step[1] - 1] == '1' && !visited[step[0]][step[1] - 1]) {
                nextStep.add(new int[] {step[0], step[1] - 1}); // left
                visited[step[0]][step[1] - 1] = true;
            }
            if (step[0] + 1 < height && image[step[0] + 1][step[1]] == '1' && !visited[step[0] + 1][step[1]]) {
                nextStep.add(new int[] {step[0] + 1, step[1]}); // down
                visited[step[0] + 1][step[1]] = true;
            }
            if (step[1] + 1 < width && image[step[0]][step[1] + 1] == '1' && !visited[step[0]][step[1] + 1]) {
                nextStep.add(new int[] {step[0], step[1] + 1}); // right
                visited[step[0]][step[1] + 1] = true;
            }
        } while (!nextStep.isEmpty());
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
    
    public static void main(String[] args) {
        System.out.println((1 << 31) + (1 << 31) + (1 << 31));
        System.out.println((1 << 31) * 3);
        MinArea ma = new MinArea();
        String[] strings = new String[] {"000000000000000000000010000000000000000000000000000000000000000000000000000","000000000000000000000110000000000000000000000000000000000000000000000000000","000000000000000000011100000000000000000000000000000000000000000000000000000","000000000000000000111100000000000000000000000000000000000000000000000000000","000000000000000100111000000000000010001000000000100000000000000000000000000","000000000000011111011000000000000011111100000011110000101000000000000000000","000000000000011011111100100000000011111110001111100111111100000000000000000","000000000000000001111111110000001111111111011110100111111110000000000000000","000000000000000000110011111110000111111100011111110101111100000000000000000","000000000000000000000111111111110111111111111111111111111111000000000000000","000000000000000000000111111111111111111111111111111111111111000000000000000","000000000000000000000111111111111000111000001111111111111011000000000000000","000000000000000000011111101111111000111111111110010111111111100000000000000","000000000000000000010111111111111111111111100000000111111111011000000000000","000000000000000000000111101100100111110000100011100111111111111000000000000","000000000000000000000111111111111111110001111111101111111111111100000000000","000000000000000000001111111111111111111111101111111111111111111100000000000","000000000000000000001000011111111000001000001111111111111111111100000000000"};
        char[][] image = new char[strings.length][strings[0].length()];
        for (int i = 0; i < image.length; ++i) {
            image[i] = strings[i].toCharArray();
        }
        ma.minArea(image, 17, 63);
    }
    
}
