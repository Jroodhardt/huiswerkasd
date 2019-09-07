package sortingalgorithms;

import java.util.List;

/**
 * Als de keys equal zijn: O(n)
 * Als de keys sorted zijn: O(n)
 * Als de keys reverse sorted zijn: O(n²)
 */

public class InsertionSort{
public <T extends Comparable<T>>  T[]  sort(T[] ints){
    for(int i=0;i<ints.length;i++){
        T temp;
        for(int j=i;j>0;j--)
        if(ints[j].compareTo(ints[j - 1]) < 0){
            temp= ints[j];
            ints[j]=ints[j-1];
            ints[j-1]=temp;
        }
    }
    return ints;
}
}
