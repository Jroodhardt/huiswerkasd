package graphs;

public class Edge {
    public Node dest; // Second vertex in Edge
    public double cost; // Edge cost

    public Edge(Node d, double c) {
        dest = d;
        cost = c;
    }
    public String print(){
        return " -> " + dest.name + " costs: " + cost;
    }
}