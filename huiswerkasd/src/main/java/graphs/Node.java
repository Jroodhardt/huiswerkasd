package graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Node implements Comparable<Node> {
    public String name;
    public List<Edge> adj;
    public double dist;
    public Node prev;
    public boolean isVisited = false;
    public int scratch;
    private Node pos;

    public Node(String nm) {
        name = nm;
        adj = new LinkedList<Edge>();
        reset();
    }

    public void reset() {
        dist = Graph.INFINITY;
        prev = null;
        pos = null;
        isVisited = false;
        scratch = 0;
    }

    public void visit() {
        this.isVisited = true;
    }


    public int compareTo(Node o) {
        if (name.equals(o.name)) {
            return 1;
        }
        return 0;
    }
}
