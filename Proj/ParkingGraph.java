package Proj;

/**
 * Custom adjacency-list weighted directed graph.
 * No Java Collections Framework graph utilities used.
 * Nodes 0..n-1 represent parking slots / intersections.
 */
public class ParkingGraph {

    // ---------- inner edge node ----------
    static class EdgeNode {
        int to;
        double weight;
        EdgeNode next;

        EdgeNode(int to, double weight, EdgeNode next) {
            this.to     = to;
            this.weight = weight;
            this.next   = next;
        }
    }

    private final int       numNodes;
    private final EdgeNode[] adj;   // head of each adjacency linked list
    private int              edgeCount;

    public ParkingGraph(int numNodes) {
        this.numNodes  = numNodes;
        this.adj       = new EdgeNode[numNodes];
        this.edgeCount = 0;
    }

    /** Add a directed edge from → to with given weight (distance in metres). */
    public void addEdge(int from, int to, double weight) {
        adj[from] = new EdgeNode(to, weight, adj[from]);
        edgeCount++;
    }

    /** Add an undirected edge (both directions). */
    public void addUndirectedEdge(int a, int b, double weight) {
        addEdge(a, b, weight);
        addEdge(b, a, weight);
    }

    public int getNumNodes()  { return numNodes; }
    public int getEdgeCount() { return edgeCount; }

    /** Iterate neighbours of node; returns head of linked list (package-private). */
    EdgeNode getAdj(int node) { return adj[node]; }
}
