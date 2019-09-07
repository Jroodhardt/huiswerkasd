package toets2;

import graphs.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FamilyTree {
    public Person root;


    public FamilyTree() {
    }


    public static FamilyTree createSampleFamilyTree() {
        // Instantieer personen
        Person adam = new Person("Adam");
        Person eva = new Person("Eva");
        Person jan = new Person("Jan");
        Person ruth = new Person("Ruth");
        Person suske = new Person("Suske");
        Person wiske = new Person("Wiske");
        Person sofie = new Person("Sofie");
        Person miep = new Person("Miep");
        Person mees = new Person("Mees");
        Person jopie = new Person("Jopie");
        Person flupke = new Person("Flupke");
        Person sidonia = new Person("Sidonia");
        Person vrouwtjeTheelepel = new Person("Vrouwtje Theelepel");
        Person lambik = new Person("Lambik");
        Person jerom = new Person("Jerom");

        // Partners:
        setPartners(adam, eva);
        setPartners(jan, ruth);
        setPartners(suske, wiske);
        setPartners(miep, mees);
        setPartners(flupke, sidonia);
        setPartners(vrouwtjeTheelepel, lambik);

        // Kinderen
        adam.addChild(jan);
        adam.addChild(suske);
        adam.addChild(sofie);

        jan.addChild(miep);
        jan.addChild(jopie);

        suske.addChild(flupke);

        miep.addChild(vrouwtjeTheelepel);

        flupke.addChild(lambik);
        flupke.addChild(jerom);


        // Maak boom
        FamilyTree boom = new FamilyTree();
        boom.root = adam;

        return boom;
    }

    private static void setPartners(Person p1, Person p2) {
        p1.setPartner(p2);
        p2.setPartner(p1);

    }


    @Override
    public String toString() {
        if (root == null) return "Empty";
        StringBuffer sb = new StringBuffer();
        printRecursive(root, sb);
        return sb.toString();

    }

    private void printRecursive(Person person, StringBuffer sb) {
        sb.append(person.toString());
        for (Person child : person.getChildren()) {
            printRecursive(child, sb);
        }
    }


    // Opgave 1

    public Person find(Person p, String s) {
        ArrayList<Person> children = new ArrayList<Person>(p.getChildren());
        if (p.getChildren() != null) {
            for (int i = 0; i < children.size(); i++) {
                if (root.getName().equals(s)) {
                    return p;
                } else if (root.getPartner().getName().equals(s)) {
                    return root.getPartner();
                } else {
                    find(p.getChildren().get(i), s);
                }
            }
        }
        return null;
    }

    // Opgave 2
    public Person findParent(Person p, String s) {
        ArrayList<Person> q = new ArrayList<Person>(p.getChildren());
        if (p.getChildren() != null) {
            for (int i = 0; i < q.size(); i++) {
                if (q.get(i).getName().equals(s)) {
                    return p;
                } else if (root.getPartner().getName().equals(s)) {
                    return root.getPartner();
                } else {
                    find(p.getChildren().get(i), s);
                }
            }
        }
        return null;
    }


    // Opgave 3
    public String toLevelOrderString() {
        throw new UnsupportedOperationException();
    }

    // Opgave 4
    public ArrayList<Person> findAncestors(String name) {
        throw new UnsupportedOperationException();
    }


    public static void main(String[] args) {
        System.out.println("** Opgave 0 **");
        FamilyTree familyTree = createSampleFamilyTree();
        System.out.print(familyTree.toString());
        System.out.println("--------------------");
        System.out.println(familyTree.findParent(familyTree.root, "Suske"));
        System.out.println(familyTree.find(familyTree.root, "Eva"));

        System.out.println("--------------------");

//        System.out.println("\n** Opgave 1 **");
//        String name = "Jerom";
//        Person result = familyTree.find(name);
//        if (result != null && name.equals(result.getName())) {
//            System.out.println(name + " gevonden, correct!");
//        } else {
//            System.out.println(name + " niet gevonden, INcorrect!");
//        }
//
//        name = "Greet";
//        result = familyTree.find(name);
//        if (result != null) {
//            System.out.println(name + " gevonden, INcorrect!");
//        } else {
//            System.out.println(name + " niet gevonden, correct!");
//        }


//        System.out.println("\n** Opgave 2 **");
//        name = "Jopie";
//        result = familyTree.findParent(name);
//        if (result != null) {
//            if ("Jan".equals(result.getName())) {
//                System.out.println("Parent van " + name + " gevonden, correct!");
//            } else {
//                System.out.println("Verkeerde parent gevonden, INcorrect!");
//            }
//        } else {
//            System.out.println("Parent van " + name + " niet gevonden, INcorrect!");
//        }
//
//        name = "Adam";
//        result = familyTree.findParent(name);
//        if (result != null) {
//            System.out.println(result + " gevonden, INcorrect!");
//        } else {
//            System.out.println("Parent van " + name + " niet gevonden, correct!");
//        }
//
//        name = "Greet";
//        result = familyTree.findParent(name);
//        if (result != null) {
//            System.out.println("Parent gevonden, INcorrect!");
//        } else {
//            System.out.println("Parent van " + name + " niet gevonden, correct!");
//        }


//        System.out.println("\n** Opgave 3 **");
//        System.out.println(familyTree.toLevelOrderString());


//        System.out.println("** Opgave 4 **");
//        System.out.println(familyTree.findAncestors("Jerom"));
//        System.out.println(familyTree.findAncestors("Jopie"));
//        System.out.println(familyTree.findAncestors("Adam"));
//        System.out.println(familyTree.findAncestors("Greet"));
    }
}
