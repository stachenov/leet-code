package name.tachenov.leetcode;

import org.jetbrains.annotations.*;

import java.util.*;

public class MinimumWeightedSubgraphWithRequiredPaths {

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        final var minPathFromSrc1 = findMinPath(n, edges, src1, false);
        final var minPathFromSrc2 = findMinPath(n, edges, src2, false);
        final var minPathToDest = findMinPath(n, edges, dest, true);
        var minimumWeight = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            final var fromSrc1 = minPathFromSrc1[i];
            final var fromSrc2 = minPathFromSrc2[i];
            final var toDest = minPathToDest[i];
            if (fromSrc1 != Long.MAX_VALUE && fromSrc2 != Long.MAX_VALUE && toDest != Long.MAX_VALUE) {
                final var total = fromSrc1 + fromSrc2 + toDest;
                minimumWeight = Math.min(minimumWeight, total);
            }
        }
        return minimumWeight == Long.MAX_VALUE ? -1L : minimumWeight;
    }

    // Dijkstra’s algorithm
    private long[] findMinPath(int n, int[][] edges, int node, boolean reverse) {
        final var minPath = new long[n];
        Arrays.fill(minPath, Long.MAX_VALUE);
        minPath[node] = 0;
        final var unvisitedNodes = new PriorityQueue<NodeMinPath>();
        unvisitedNodes.add(new NodeMinPath(node, minPath[node]));
        final boolean[] visited = new boolean[n];
        int currentNode = node;
        while (true) {
            while (!unvisitedNodes.isEmpty() && visited[currentNode]) {
                currentNode = unvisitedNodes.remove().node;
            }
            if (visited[currentNode])
                break;
            for (int[] edge : edges) {
                final var weight = edge[2];
                final var from = reverse ? edge[1] : edge[0];
                final var to = reverse ? edge[0] : edge[1];
                if (from != currentNode || visited[to])
                    continue;
                final var alternativePath = minPath[from] + weight;
                if (alternativePath < minPath[to]) {
                    minPath[to] = alternativePath;
                    unvisitedNodes.add(new NodeMinPath(to, minPath[to]));
                }
            }
            visited[currentNode] = true;
        }
        return minPath;
    }

    private record NodeMinPath(int node, long minPath) implements Comparable<NodeMinPath> {
        @Override
        public int compareTo(@NotNull NodeMinPath o) {
            return Long.compare(minPath, o.minPath);
        }
    }

}
