package name.tachenov.leetcode;

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

    private long[] findMinPath(int n, int[][] edges, int node, boolean reverse) {
        var minPathOfMaxLength = new long[n];
        Arrays.fill(minPathOfMaxLength, Long.MAX_VALUE);
        minPathOfMaxLength[node] = 0;
        for (int len = 1; len <= edges.length; ++len) {
            final var minPathOfNextLength = Arrays.copyOf(minPathOfMaxLength, minPathOfMaxLength.length);
            for (int[] edge : edges) {
                final var from = reverse ? edge[1] : edge[0];
                final var to = reverse ? edge[0] : edge[1];
                final var weight = edge[2];
                if (minPathOfMaxLength[from] != Long.MAX_VALUE) {
                    minPathOfNextLength[to] = Math.min(minPathOfNextLength[to], minPathOfNextLength[from] + weight);
                }
            }
            minPathOfMaxLength = minPathOfNextLength;
        }
        return minPathOfMaxLength;
    }

}
