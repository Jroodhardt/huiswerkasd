package sortingalgorithms;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class MergeSort {

    public <T> void sort(T[] values, Class<T> clazz, Comparator<T> comparator) {
        if (values == null) {
            throw new IllegalArgumentException("values is null.");
        }
        if (values.length < 2) {
            return;
        }
        int median = values.length / 2;
        int leftSize = median;
        int rightSize = values.length - median;

        T[] left = (T[]) new Object[leftSize];
        for (int l = 0; l < leftSize; ++l) {
            left[l] = values[l];
        }

        T[] right = (T[]) new Object[rightSize];
        for (int r = 0; r < rightSize; ++r) {
            right[r] = values[leftSize + r];
        }
        sort(left, clazz, comparator);
        sort(right, clazz, comparator);
        merge(values, left, right, comparator);

    }

    private <T> void merge(T[] values, T[] left, T[] right, Comparator<T> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int sortedIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            int comparison = comparator.compare(left[leftIndex], right[rightIndex]);
            if (comparison <= 0) {
                values[sortedIndex] = left[leftIndex];
                leftIndex++;
            } else {
                values[sortedIndex] = right[rightIndex];
                rightIndex++;
            }
            sortedIndex++;
        }

        while (leftIndex < left.length) {
            values[sortedIndex] = left[leftIndex];
            leftIndex++;
            sortedIndex++;
        }
         while (rightIndex < right.length) {
            values[sortedIndex] = right[rightIndex];
            rightIndex++;
            sortedIndex++;
        }
    }

}