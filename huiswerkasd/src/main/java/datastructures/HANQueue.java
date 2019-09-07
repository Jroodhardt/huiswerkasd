package datastructures;

import java.util.Stack;

public class HANQueue<T> {
    private Stack<T> stackNew = new Stack<T>();
    private Stack<T> stackOld = new Stack<T>();
    public void enqueue(T value){
        stackNew.push(value);
    }
    public T peek(){
        shiftStacks();
        return stackOld.peek();
    }
    public T dequeue(){
        shiftStacks();
        return stackOld.pop();
    }

    private void shiftStacks() {
        if (stackOld.isEmpty()){
            while(!stackNew.isEmpty()){
                stackOld.push(stackNew.pop());
            }
        }
    }
}
