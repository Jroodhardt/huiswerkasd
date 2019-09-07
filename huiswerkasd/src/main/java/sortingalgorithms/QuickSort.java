package sortingalgorithms;

/**
 * Als de keys equal zijn: O(nlog(n))
 * Als de keys sorted zijn: O(nlog(n))
 * Als de keys reverse sorted zijn: O(nlog(n))
 */
public class QuickSort {

    public <T extends Comparable<T>> T[] qsort(T[] arr, int a, int b) {
        if (a < b) {
            int i = a, j = b;
            T x = arr[(i + j) / 2];

            do {
                while (arr[i].compareTo(x) < 0) i++;
                while (x.compareTo(arr[j]) < 0) j--;

                if ( i <= j) {
                    T tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i++;
                    j--;
                }

            } while (i <= j);

            qsort(arr, a, j);
            qsort(arr, i, b);
        }
        return arr;
    }

}
