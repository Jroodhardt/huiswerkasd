package datastructures;


import java.util.Arrays;

public class HANArrayList <T extends Comparable<T>> {
    private Object[] actualArray=new Object[1];
    private  int index =0;
    private static int size =1;


    public void add(T value){
        index++;
        validateCapacity(index);
        actualArray[index] = value;
        }

    private void validateCapacity(int oldSize) {
        int minSize=index+1;
        if(oldSize<minSize){
            int newSize=(oldSize*2)+1;
            Object[] newArray = new Object[newSize];
            System.arraycopy(actualArray, 0, newArray, 0, oldSize);
            actualArray = newArray;
            size++;
        }
    }

    public void set(int index, T value){
            actualArray[index]=value;
    }

    public Object getIndexed(int index){
        return actualArray[index];
    }


}
