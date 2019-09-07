package datastructures;

public class HANLinkedList<T> {
    private HANLinkedListNode<T> header = null;


    public void insertFirst(T node) {
        HANLinkedListNode<T> firstNode = new HANLinkedListNode<T>(node);
        if (header != null) {
            firstNode.setNextNode(header);
        }
        header = firstNode;
    }

    public void removeFirst() {
        if (header.getNext() != null) {
            header = header.getNext();
            header.index = 1;
        } else {
            header = null;
        }
    }


    private String printList(HANLinkedListNode<T> node) {
        if (node.getNext() != null)
            return printList(node.getNextNode());
        return "no nodes found";
    }

    public HANLinkedListNode getNode(int index) {
        int iterator = 0;
        HANLinkedListNode<T> result = header;
        while (iterator <= index) {
            if (iterator == index) {
                return result;
            }
            result = result.getNextNode();
            iterator++;
        }
        return result;
    }

    public void insert(int i, T value) {
        header.insert(i, value);
    }

    public void delete(int i) {
        header.delete(i);
    }

    int getSize() {
        if (header != null) {
            return header.getSize();
        } else {
            return 0;
        }
    }

    public String toString() {
        return "On the top:" + printList(this.header) + "\n"
                + "Size:" + getSize() + "\n";
    }
    boolean hasCycle(HANLinkedListNode<T> head){
        if (head==null) return false;
        HANLinkedListNode fast = head;
        HANLinkedListNode slow = head.getNext();
        while(fast!=null&& fast.getNext()!=null && slow!=null){
            if(fast==slow){
            return true;
            }
            fast=fast.getNext().getNext();
            slow=slow.getNext();

        }
        return false;
    }
}
