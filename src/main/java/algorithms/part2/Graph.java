package algorithms.part2;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Graph {
    private final Integer vertices;
    private final List<Integer>[] adjacencyList;
    private Integer edges;

    public Graph(Integer vertices) {
        if (vertices < 0)
            throw new IllegalArgumentException("Number of vertices in a graph must be non negative");
        this.vertices = vertices;
        this.edges = 0;
        this.adjacencyList = (List<Integer>[]) new ArrayList[this.vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public Graph(In in) {

        this.vertices = in.readInt();
        if (this.vertices < 0)
            throw new IllegalArgumentException("Number of vertices in a graph must be non negative");
        this.adjacencyList = (List<Integer>[]) new ArrayList[this.vertices];
        for (int i = 0; i < this.vertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        Integer edgesGiven = in.readInt();
        this.edges = 0;
        if (edgesGiven < 0)
            throw new IllegalArgumentException("Number of edges in a graph cannot be negative");
        for (int i = 0; i < edgesGiven; i++) {
            Integer v = in.readInt();
            Integer w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            addEdge(v, w);
        }

    }

    private void validateVertex(Integer v) {
        if (v < 0 && v >= this.vertices)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (this.vertices-1));
    }

    private void addEdge(Integer v, Integer w) {
        this.edges++;
        this.adjacencyList[v].add(w);
        this.adjacencyList[w].add(v);
    }

    public Iterable<Integer> getAdjList(Integer v){
        validateVertex(v);
        return this.adjacencyList[v];
    }

    public Integer getVertices() {
        return this.vertices;
    }

    public Integer getEdges() {
        return this.edges;
    }

    public Integer getDegree(Integer v) {
        validateVertex(v);
        return this.adjacencyList[v].size();
    }

    public Graph(Graph G) {
        this(G.getVertices());
        this.edges = G.getEdges();
        for (int v = 0; v < G.getVertices(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.getAdjList(v)) {
                reverse.push(w);
            }
            for (int w : reverse) {
                this.adjacencyList[v].add(w);
            }
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.vertices + " vertices, " + this.edges + " edges \n");
        for (int v = 0; v < this.vertices; v++) {
            s.append(v + " : ");
            for (int w : this.adjacencyList[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
