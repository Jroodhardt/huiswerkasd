package toets2;

import java.util.ArrayList;

public final class Person {

    private String name;
    private Person partner;
    private ArrayList<Person> children;

    public Person(String name){
        this.name = name;
        this.children = new ArrayList<Person>();
    }

    public ArrayList<Person> getChildren() {
        return children;
    }

    public void addChild(Person child){
        children.add(child);
    }

    public Person(String name, Person partner){
        this.partner = partner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    @Override
    public String toString(){
        StringBuffer result = new StringBuffer();
        String partner = getPartner() == null ? "" : " (partner: " + getPartner().getName() + ")";

        result.append(getName());
        result.append(partner);
        result.append(System.lineSeparator());

        if (children.size()>0) {
            result.append("\tkinderen: ");
            for (Person child : children) {
                result.append(child.getName());
                result.append(" ");
            }
        } else {
            result.append("\t(geen kinderen)");
        }

        result.append(System.lineSeparator());

        return result.toString();
    }



}
