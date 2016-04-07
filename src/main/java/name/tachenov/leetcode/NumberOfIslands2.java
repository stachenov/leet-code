/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author alqualos
 */
public class NumberOfIslands2 {
    
    private static final int[][] STEPS = new int[][]
    {{-1, 0}, {+1, 0}, {0, -1}, {0, +1}};
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];
        int[] parents = new int[positions.length];
        int[] ranks = new int[positions.length];
        int count = 0;
        List<Integer> result = new ArrayList<>();
        for (int k = 0; k < positions.length; ++k) {
            int id = k + 1; // Islands get IDs of 1, 2, 3... k.
            int[] p = positions[k];
            ++count; // Consider it a new island... for now.
            parents[k] = k;
            grid[p[0]][p[1]] = id;
            for (int[] step : STEPS) {
                int i1 = p[0] + step[0];
                int j1 = p[1] + step[1];
                if (i1 >= 0 && i1 < m && j1 >= 0 && j1 < n && grid[i1][j1] != 0) {
                    if (unite(parents, ranks, grid[i1][j1] - 1, k)) {
                        --count; // United two islands, so one island less now.
                    }
                }
            }
            result.add(count);
        }
        return result;
    }
    
    private static boolean unite(int[] parents, int[] ranks,
            final int id1, final int id2) {
        int root1 = find(parents, id1);
        int root2 = find(parents, id2);
        if (root1 == root2) {
            return false;
        } else {
            if (ranks[root1] < ranks[root2]) {
                parents[root1] = root2;
            } else if (ranks[root2] < ranks[root1]) {
                parents[root2] = root1;
            } else {
                parents[root2] = root1;
                ++ranks[root1];
            }
            return true;
        }
    }
    
    private static int find(int[] parents, int id) {
        if (parents[id] == id) {
            return id;
        } else {
            parents[id] = find(parents, parents[id]);
            return parents[id];
        }
    }
    
    private static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 23 * hash + this.i;
            hash = 23 * hash + this.j;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Point other = (Point) obj;
            return this.i == other.i && this.j == other.j;
        }
    }
    
    public static void main(String[] args) {
        NumberOfIslands2 num2 = new NumberOfIslands2();
        Random rnd = new Random(0);
        final int m = 10, n = 10;
        long before = System.currentTimeMillis();
        for (int i = 0; i < 100000; ++i) {
            int k = rnd.nextInt(100);
            Set<Point> points = new LinkedHashSet<>();
            for (int j = 0; j < k; ++j) {
                points.add(new Point(rnd.nextInt(m), rnd.nextInt(n)));
            }
            int[][] pos = new int[points.size()][2];
            int j = 0;
            for (Point p : points) {
                pos[j][0] = p.i;
                pos[j][1] = p.j;
                ++j;
            }
            num2.numIslands2(m, n, pos);
        }
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }
}
