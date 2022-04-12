package name.tachenov.leetcode;

import org.jetbrains.annotations.*;

import java.util.*;

public class MinimumWeightedSubgraphWithRequiredPaths {

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        final var graph = new Graph(n, edges);
        final var minPathFromSrc1 = findMinPath(graph, src1);
        final var minPathFromSrc2 = findMinPath(graph, src2);
        final var minPathToDest = findMinPath(graph.reverse(), dest);
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
    private long[] findMinPath(Graph graph, int node) {
        final var minPath = new long[graph.size()];
        Arrays.fill(minPath, Long.MAX_VALUE);
        minPath[node] = 0;
        final var unvisitedNodes = new PriorityQueue<NodeMinPath>();
        unvisitedNodes.add(new NodeMinPath(node, minPath[node]));
        final boolean[] visited = new boolean[graph.size()];
        int from = node;
        while (true) {
            while (!unvisitedNodes.isEmpty() && visited[from]) {
                from = unvisitedNodes.remove().node;
            }
            if (visited[from])
                break;
            for (Edge edge : graph.getEdgesFrom(from)) {
                final var weight = edge.weight();
                final var to = edge.to();
                if (visited[to])
                    continue;
                final var alternativePath = minPath[from] + weight;
                if (alternativePath < minPath[to]) {
                    minPath[to] = alternativePath;
                    unvisitedNodes.add(new NodeMinPath(to, minPath[to]));
                }
            }
            visited[from] = true;
        }
        return minPath;
    }

    private static class Graph {

        private final int size;
        private final int[][] edges;
        private final List<List<Edge>> fromTo = new ArrayList<>();

        Graph(int n, int[][] edges) {
            this.size = n;
            this.edges = edges;
            for (int i = 0; i < size(); i++) {
                fromTo.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                fromTo.get(edge[0]).add(new Edge(edge[1], edge[2]));
            }
        }

        Graph reverse() {
            int[][] reverseEdges = new int[edges.length][3];
            for (int i = 0; i < edges.length; i++) {
                reverseEdges[i][0] = edges[i][1];
                reverseEdges[i][1] = edges[i][0];
                reverseEdges[i][2] = edges[i][2];
            }
            return new Graph(size, reverseEdges);
        }

        public int size() {
            return size;
        }

        public List<Edge> getEdgesFrom(int from) {
            return fromTo.get(from);
        }
    }

    private record Edge(int to, int weight) { }

    private record NodeMinPath(int node, long minPath) implements Comparable<NodeMinPath> {
        @Override
        public int compareTo(NodeMinPath o) {
            return Long.compare(minPath, o.minPath);
        }
    }

}
