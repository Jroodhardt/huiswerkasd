package toets1;

import graphs.Node;
import graphs.Path;

public class Pathfinding implements Comparable<Pathfinding> {
    Kamer dest;
    private double cost;
    public Pathfinding(Kamer d, double c){
        dest =d;
        cost =c;
    }

    public String print(){
        return " -> " + dest.name + " costs: " + cost;
    }
    public int compareTo(Pathfinding rhs) {
        double otherCost=rhs.cost;
        return Double.compare(cost, otherCost);    }
}
