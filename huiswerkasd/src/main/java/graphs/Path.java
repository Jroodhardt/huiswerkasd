package graphs;

public class Path implements Comparable<Path> {
      Node dest;
      private double cost;
    public Path(Node d, double c){
        dest =d;
        cost =c;
    }

    public int compareTo(Path rhs) {
        double otherCost=rhs.cost;
        return Double.compare(cost, otherCost);    }
}
