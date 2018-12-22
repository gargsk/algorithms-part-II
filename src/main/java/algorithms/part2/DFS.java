package algorithms.part2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DFS {
    private boolean[] marked;
    private Integer count;

    public DFS(Graph G, Integer v) {
        this.marked = new boolean[G.getVertices()];
        this.count = 0;
        validateVertex(v);
        dfs(G, v);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DFS search = new DFS(G, s);
        for (int v = 0; v < G.getVertices(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
        if (search.getCount() != G.getVertices())
            StdOut.println("NOT connected");
        else
            StdOut.println("connected");
    }

    private void dfs(Graph G, Integer v) {
        //mark the vertex V as visited
        this.marked[v] = true;
        count++;
        //iterate over the adjecency list of v
        for (Integer adjVertex : G.getAdjList(v)) {
            if (!this.marked[adjVertex]) // this is recursion terminal condition
                dfs(G, adjVertex);
        }
    }

    public Integer getCount() {
        return this.count;
    }

    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    private void validateVertex(Integer v) {
        if (v < 0 && v >= this.marked.length)
            throw new IllegalArgumentException("Given vertex is not in the graph");
    }

}
