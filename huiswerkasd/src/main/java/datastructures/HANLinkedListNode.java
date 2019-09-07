package datastructures;

public class HANLinkedListNode<T> {
    private T value;
    int index=1;
     private HANLinkedListNode<T> next;

    public HANLinkedListNode(T value) {
        this.value = value;
    }

    public HANLinkedListNode<T> getNext() {
        return next;
    }

    public HANLinkedListNode<T> getNextNode() {
        return next;
    }

    public void setNextNode(HANLinkedListNode<T> nextNode) {
        this.next = nextNode;
        this.next.index = index + 1;
    }

    public T getValue() {
        return value;
    }

    void insert(int i, T value) throws IndexOutOfBoundsException{
        if (index == i) {
            this.value = value;
        }
        else if (this.next == null) {
            throw new IndexOutOfBoundsException();
        }
        else {
            this.next.insert(i, value);
        }
    }
    void delete(int i) throws IndexOutOfBoundsException{
        if (index == i-1) {
            this.next = null;

        }
        else if (this.next == null) {
            throw new IndexOutOfBoundsException();
        }
        else {
            this.next.delete(i);
        }
    }
    int getSize(){
        if (this.next==null) {
            return index;
        }
        return next.getSize();
    }

}





