package toets1;

import java.util.LinkedList;
import java.util.List;

public class Kamer implements Comparable<Kamer>{
    public String name;
    public List<Deur> adj;
    public Kamer prev;
    public double dist;
    public boolean isVisited=false;
    public int scratch;
    private Kamer pos;
    public Kamer(String nm){
        name=nm;
        adj=new LinkedList<Deur>();
        reset();
    }
    public void reset(){
        dist=Kasteel.INFINITY;
        prev=null;
        pos = null;
        isVisited=false;
        scratch=0;

    }
    public void visit(){
        this.isVisited=true;
    }


    public int compareTo(Kamer o) {
        if(name.equals(o.name)){
            return 1;
        }
        return 0;
    }
}
