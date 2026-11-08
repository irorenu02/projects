package Proj;

/**
 * Breadth-First Search on a ParkingGraph to find the nearest
 * available slot from a given source node.
 * Custom queue implemented as a circular array — no java.util.Queue used.
 * Time complexity: O(V + E)
 */
public class BFS {

    // ── Custom circular-array queue ────────────────────────────────
    private static class IntQueue {
        private int[] data;
        private int   head, tail, size;

        IntQueue(int capacity) {
            data = new int[capacity];
            head = tail = size = 0;
        }

        void enqueue(int val) {
            data[tail] = val;
            tail = (tail + 1) % data.length;
            size++;
        }

        int dequeue() {
            int val = data[head];
            head = (head + 1) % data.length;
            size--;
            return val;
        }

        boolean isEmpty() { return size == 0; }
    }

    /**
     * Find the nearest available slot from source using BFS.
     * @param graph   the facility graph
     * @param table   hash table to look up ParkingSlot by node id
     * @param source  entry node
     * @return slot id of nearest available slot, or -1 if none reachable
     */
    public static int nearestAvailableSlot(ParkingGraph graph, HashTable table, int source) {
        int       n       = graph.getNumNodes();
        boolean[] visited = new boolean[n];
        IntQueue  queue   = new IntQueue(n);

        visited[source] = true;
        queue.enqueue(source);

        while (!queue.isEmpty()) {
            int u = queue.dequeue();

            ParkingSlot slot = table.get(u);
            if (slot != null && slot.isAvailable()) return u;

            var edge = graph.getAdj(u);
            while (edge != null) {
                if (!visited[edge.to]) {
                    visited[edge.to] = true;
                    queue.enqueue(edge.to);
                }
                edge = edge.next;
            }
        }
        return -1; // no available slot reachable
    }
}
