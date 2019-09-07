package generics;

public class Min<T extends Comparable<T> >{
    public T min(T[] a){
        T min=a[0];
        for (T t : a) {
            if (t.compareTo(min) < 0) {
                min = t;
            }
        }
        return min;
    }
}
