package graphs;

import sun.awt.image.ImageWatched;

import java.util.*;

public class Graph {
    private Map<String, Node> nodeMap = new HashMap<String, Node>();
    static final double INFINITY = Double.MAX_VALUE;

    public void addEdge(String sourceName, String destName, double cost) {
        Node v = getNode(sourceName);
        Node w = getNode(destName);
        v.adj.add(new Edge(w, cost));
    }

    public boolean isConnected(String startName, String destName) {
        clearAll();
        Node start = nodeMap.get(startName);
        start.visit();
        Queue<Node> Q = new LinkedList<Node>();
        Q.add(start);
        while (!Q.isEmpty()) {
            Node n = Q.remove();
            for (Edge e : n.adj) {
                Node current = e.dest;
                current.visit();
                if (current.isVisited) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printPath(String destName) {
        Node n = nodeMap.get(destName);
        if (n == null)
            throw new NoSuchElementException();
        else if (n.dist == INFINITY)
            System.out.println(destName + " is unreachable");
        else {
            System.out.print("(Cost is: " + n.dist + ") ");
            printPath(n);
            System.out.println();
        }
    }

    public void unWeighted(String startName) {
        clearAll();
        Node start = nodeMap.get(startName);
        LinkedList<Node> q = new LinkedList<Node>();
        q.add(start);
        start.dist = 0;

        while (!q.isEmpty()) {
            System.out.println(q.getFirst().name + "start");
            System.out.println(q.getLast().name + "last");

            Node n = q.remove();
            System.out.println(n.name);

            for (Edge b : n.adj) {
                Node a = b.dest;
                System.out.println("sub" + a.name);
                if (a.dist == INFINITY) {
                    a.dist = n.dist + 1;
                    a.prev = n;
                    q.add(a);
                }
            }
        }
    }

    public void dijkstra(String startName) {
        clearAll();
        Node start = nodeMap.get(startName);
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.add(new Edge(start, 0));
        start.dist = 0;
        int nodeSeen = 0;
        while (!pq.isEmpty() && nodeSeen < nodeMap.size()) {
            Edge pt = pq.remove();
            Node n = pt.dest;
            if (n.scratch != 0) {
                continue;
            }
            n.scratch = 1;
            nodeSeen++;
            for (Edge e : n.adj) {
                System.out.print(e.print());
                Node w = e.dest;
                double cvw = e.cost;
                if (w.dist > n.dist + cvw) {
                    w.dist = n.dist + cvw;
                    w.prev = n;
                    pq.add(new Edge(w, w.dist));
                }
            }
        }
    }

    public Node getNode(String nodeName) {
        Node n = nodeMap.get(nodeName);
        if (n == null) {
            n = new Node(nodeName);
            nodeMap.put(nodeName, n);
        }
        return n;
    }

    void printPath(Node dest) {
        if (dest.prev != null) {
            printPath(dest.prev);
            System.out.print(" to ");
        }
        System.out.print(dest.name);
    }

    void clearAll() {
        for (Node v : nodeMap.values())
            v.reset();
    }

//    public double[][] toMatrixMap() {
//        GraphMatrix graphMatrix = new GraphMatrix();
//        double[][] array = new double[nodes.size()][nodes.size()];
//        for (int i=0;i<nodes.size();i++) {
//            Node n=nodeMap.get(nodes.get(i));
//            for(int j=0;j<nodes.length;j++) {
//                Edge e=n.adj.get(j);
//                for(int k=0;k<nodes.length;k++) {
//                    if(e.dest==nodes[k]) {
//                        array[j][k] = e.cost;
//                    } else {
//                        array[j][k] = 0;
//                    }
//                }
//            }
//        }
//        return array;
//    }

    void getAllAxis() {
        Collection<Node> list = nodeMap.values();
        for (Node n : list) {
            System.out.print(n.name);
        }
    }
    boolean depthFirst(String source, String dest){
        Node s= getNode(source);
        Node d= getNode(dest);
        HashMap<String, Double> v=new HashMap<String, Double>();

        return depthfirst(s,d,v);
    }
    boolean depthfirst(Node source, Node dest,HashMap<String, Double> visited) {
        if (visited.containsKey(source.name)) {
            return false;
        }
        visited.put(source.name, source.dist);
        if (source.name.equals(dest.name)) {
            System.out.println(" found");
            return true;
        }
        for (Edge n : source.adj) {
            System.out.print(n.print());
            if (depthfirst(n.dest, dest, visited))

                return true;
        }
        return true;
    }
    boolean bredthfirst(String source, String destin){
        LinkedList<Node> visited= new LinkedList<Node>();
        LinkedList<Node> next = new LinkedList<Node>();
        next.add(getNode(source));
        while (!next.isEmpty()){
            Node node= next.remove();
            if(node==getNode(destin)){
                System.out.println(" found");
                return true;
            }
            if(visited.contains(node)){
                continue;
            }
            visited.add(node);
            for (Edge c: node.adj) {
                System.out.print(c.print());
                next.add(c.dest);
            }
        }
        return false;
    }

}
