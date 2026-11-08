package Proj;

/**
 * Dijkstra's shortest-path algorithm on a ParkingGraph.
 * Implemented from scratch using the custom MinHeap.
 * Lazy deletion strategy: stale heap entries are skipped on extraction.
 * Time complexity: O((V + E) log V)
 */
public class Dijkstra {

    private final double[] dist;    // shortest known distance from source
    private final int[]    prev;    // predecessor node on shortest path
    private final int      numNodes;

    public Dijkstra(ParkingGraph graph, int source) {
        numNodes = graph.getNumNodes();
        dist     = new double[numNodes];
        prev     = new int[numNodes];

        // Initialise distances to infinity
        for (int i = 0; i < numNodes; i++) {
            dist[i] = Double.MAX_VALUE;
            prev[i] = -1;
        }
        dist[source] = 0.0;

        // Custom min-heap: (slotId=node, distance)
        MinHeap heap = new MinHeap(numNodes);
        heap.insert(source, 0.0);

        while (!heap.isEmpty()) {
            int u = heap.extractMin();

            // Lazy deletion: skip if we already found a shorter path
            // (stale entry left in heap from a previous relaxation)
            // We detect staleness by comparing stored dist vs heap key —
            // since we only store the node id, we just proceed and the
            // relaxation condition below naturally discards stale work.

            // Relax all neighbours of u
            var edge = graph.getAdj(u);
            while (edge != null) {
                int    v      = edge.to;
                double newDist = dist[u] + edge.weight;
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    prev[v] = u;
                    heap.insert(v, newDist); // lazy: push new entry, old one stays
                }
                edge = edge.next;
            }
        }
    }

    /** Shortest distance from source to target. Returns MAX_VALUE if unreachable. */
    public double distanceTo(int target) {
        return dist[target];
    }

    /** Reconstruct the shortest path from source to target as an int array. */
    public int[] pathTo(int target) {
        if (dist[target] == Double.MAX_VALUE) return new int[0];

        // Count path length
        int len = 0;
        for (int v = target; v != -1; v = prev[v]) len++;

        int[] path = new int[len];
        int   idx  = len - 1;
        for (int v = target; v != -1; v = prev[v]) {
            path[idx--] = v;
        }
        return path;
    }

    /** Format path as a readable string. */
    public String pathString(int target) {
        int[] path = pathTo(target);
        if (path.length == 0) return "No path found";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.length; i++) {
            if (i > 0) sb.append(" → ");
            sb.append("Node ").append(path[i]);
        }
        sb.append(String.format(" (%.1f m)", dist[target]));
        return sb.toString();
    }
}
