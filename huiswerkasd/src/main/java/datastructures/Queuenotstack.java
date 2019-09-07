package datastructures;

public class Queuenotstack<T> {

    private static class Node<T>{
        private Node<T> next;
        private T data;
        private Node(T data){
            this.data=data;
        }
    }
    private Node<T> head;
    private Node<T> tail;
    private boolean isEmpty(){
        return head==null;
    }
    public T peek(){
        return head.data;
    }
    public void add(T value){
        Node<T> node = new Node<T>(value);
        if (tail!=null){
            tail.next=node;
        }
        tail = node;
        if(head==null){
            head=node;
        }
    }
    public T remove(){
        T data =head.data;
        head=head.next;
        if (head==null){
            tail =null;
        }
        return data;
    }
}
