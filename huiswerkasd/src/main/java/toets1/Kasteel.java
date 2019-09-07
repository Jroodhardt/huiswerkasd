package toets1;

import java.util.*;

public class Kasteel {
    private Map<String, Kamer> kamerMap = new HashMap<String, Kamer>();
    static final int INFINITY = Integer.MAX_VALUE;

    public void addDoor(String sourceName, String destName, double cost, String typeDoor) {
        Kamer v = getKamer(sourceName);
        Kamer w = getKamer(destName);
        v.adj.add(new Deur(w, cost));
    }

    Kamer getKamer(String kamerName) {
        Kamer n = kamerMap.get(kamerName);
        if (n == null) {
            n = new Kamer(kamerName);
            kamerMap.put(kamerName, n);
        }
        return n;
    }

    public Kamer hint(String sourceName, String destName) {
        dijkstra(sourceName);
        Kamer dest = kamerMap.get(destName);
        Kamer source = kamerMap.get(sourceName);
        if (dest == null)
            throw new NoSuchElementException();
        else if (dest.dist == INFINITY)
            System.out.println(destName + " is onbereikbaar");
        else {
            do{
                dest = dest.prev;
            } while(dest != null && dest.prev != source);
            System.out.println("De volgende kamer is: " + dest.name);
            return dest;
        }
        return null;
    }

    void clearAll() {
        for (Kamer v : kamerMap.values())
            v.reset();
    }

    public void dijkstra(String startName) {
        clearAll();
        Kamer start = kamerMap.get(startName);
        PriorityQueue<Pathfinding> pq = new PriorityQueue<Pathfinding>();
        pq.add(new Pathfinding(start, 0));
        start.dist = 0;
        int nodeSeen = 0;
        while (!pq.isEmpty() && nodeSeen < kamerMap.size()) {
            Pathfinding pt = pq.remove();
            Kamer n = pt.dest;
            if (n.scratch != 0) {
                continue;
            }
            n.scratch = 1;
            nodeSeen++;
            for (Deur e : n.adj) {
                Kamer w = e.dest;
                double cvw = e.aantalSlagen;
                if (w.dist > n.dist + cvw) {
                    w.dist = n.dist + cvw;
                    w.prev = n;
                    pq.add(new Pathfinding(w, w.dist));
                }
            }
            if (pq.peek() != null) {
                System.out.println(pq.peek().print());
            }
        }
    }




}

