package datastructures;

public class HANStack<T> {
    private HANLinkedList<T> Stack = new HANLinkedList<T>();
    public void push(T node){
        Stack.insertFirst(node);
    }
    public void pop(){
        Stack.removeFirst();
    }
    public String top(){
        return String.valueOf(Stack.getNode(1));
    }
    public int getSize(){
        return Stack.getSize();
    }
    public String toString(){
        return "On the top:"  + top() + "\n"
        + "Size:"  + getSize() + "\n";
    }
}

