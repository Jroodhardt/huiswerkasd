package asd.app;

public class HLinkedList<T> {

    public Node<T> head;
    public int size;
    public void append(T data){
        if(head==null) {
            head = new Node<T>(data,null);
            return;
        }
            Node<T> current = head;

        while (current.next!=null){
            current=current.next;
        }
        current.next=new Node<T>(data,null);
    }
    public void prepend(T data){
        Node<T> newHead = new Node<T>(data,null);
        newHead.next=head;
        head=newHead;
    }
    public void delete(int index){
            int iterator = 0;
            Node<T> result = head;
            while (iterator <= index) {
                if(result==null){
                    return;
                }
                if (iterator == index) {
                    deleteWithValue(head.data);
                }
                result = result.next;
                iterator++;
            }
    }
    public void deleteWithValue(T data){
        if(head==null)return;
        if(head.data==data){
            head=head.next;
            return;
        }
        Node<T> current = head;
        while(current.next!=null){
            if (current.next.data==data){
                current.next=current.next.next;
                return;
            }
            current=current.next;
        }
    }
}
