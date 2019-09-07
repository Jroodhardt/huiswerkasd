package graphs;

public class Main {
    String inputString="hallo";
    public static void main(String [ ] args)
    {
        Graph graph=new Graph();
        Node node=new Node("a");
        Node node1=new Node("b");
        Node node2=new Node("c");
        Node node3=new Node("d");
        graph.addEdge("a","b",3);
        graph.addEdge("b","c",5);
        graph.addEdge("c","d",6);
        graph.addEdge("d","a",7);

        graph.bredthfirst("a","b");
        graph.bredthfirst("a","c");
        graph.depthFirst("a","c");
        graph.dijkstra("c");
//        Gra kasteel=new Kasteel();
//        Kamer hal=new Kamer("hal");
//        Kamer eetzaal=new Kamer("eetzaal");
//        Kamer keuken=new Kamer("keuken");
//        Kamer balzaal=new Kamer("balzaal");
//        Kamer werkkamer=new Kamer("werkkamer");
//        Kamer slaapkamer=new Kamer("slaapkamer");
//        Kamer torenkamer=new Kamer("torenkamer");
//
//        kasteel.addDoor("hal","keuken",3,"hout");
//        kasteel.addDoor("hal","eetzaal",10,"staal");
//        kasteel.addDoor("keuken","eetzaal",3,"hout");
//        kasteel.addDoor("eetzaal","balzaal",1,"kristal");
//        kasteel.addDoor("balzaal","werkkamer",10,"staal");
//        kasteel.addDoor("balzaal","slaapkamer",1,"kristal");
//        kasteel.addDoor("werkkamer","torenkamer",1,"kristal");
//        kasteel.addDoor("slaapkamer","torenkamer",3,"hout");
//        kasteel.dijkstra("hal");
//        kasteel.hint("hal","torenkamer");

    }
}
