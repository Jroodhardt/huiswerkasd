package generics;

public class MemoryCell <T extends Comparable<T>>{
    private T storedValue;
    public T read(){
        return storedValue;
    }
    public void write(T x){
        storedValue=x;
    }

}
