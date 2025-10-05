package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {

    @Test
    void testNullArray() {
        assertDoesNotThrow(() -> new SelectionSort(new PerformanceTracker()).sort(null),
                "Null array should not throw exception");
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        new SelectionSort(new PerformanceTracker()).sort(arr);
        assertArrayEquals(expected, arr, "Empty array should remain unchanged");
    }

    @Test
    void testSingleElement() {
        int[] arr = {5};
        int[] expected = {5};
        new SelectionSort(new PerformanceTracker()).sort(arr);
        assertArrayEquals(expected, arr, "Single element array should remain unchanged");
    }

    @Test
    void testDuplicates() {
        int[] arr = {3, 1, 3, 2, 3};
        int[] expected = {1, 2, 3, 3, 3};
        new SelectionSort(new PerformanceTracker()).sort(arr);
        assertArrayEquals(expected, arr, "Array with duplicates should be sorted correctly");
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        new SelectionSort(new PerformanceTracker()).sort(arr);
        assertArrayEquals(expected, arr, "Already sorted array should remain sorted");
    }

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        new SelectionSort(new PerformanceTracker()).sort(arr);
        assertArrayEquals(expected, arr, "Reverse sorted array should become ascending");
    }

    @Test
    void testRandomSmallArray() {
        int[] arr = {9, 2, 7, 1, 3};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        new SelectionSort(new PerformanceTracker()).sort(arr);
        assertArrayEquals(expected, arr, "Random small array should match Arrays.sort()");
    }

    @Test
    void testPropertyBasedRandomArrays() {
        Random rand = new Random(42);
        for (int trial = 0; trial < 100; trial++) {
            int n = rand.nextInt(50) + 1;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000) - 500;

            int[] expected = arr.clone();
            Arrays.sort(expected);

            new SelectionSort(new PerformanceTracker()).sort(arr);
            assertArrayEquals(expected, arr, "Random array should be sorted correctly");
        }
    }

    @Test
    void testCrossValidationLarge() {
        Random rand = new Random(123);
        int n = 1000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt();

        int[] expected = arr.clone();
        Arrays.sort(expected);

        new SelectionSort(new PerformanceTracker()).sort(arr);
        assertArrayEquals(expected, arr, "SelectionSort result should match Arrays.sort()");
    }
}
