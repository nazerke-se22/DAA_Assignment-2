package algorithms;

import metrics.PerformanceTracker;

public class SelectionSort {

    private final PerformanceTracker tracker;

    public SelectionSort(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    public void sort(int[] arr) {
        int n = arr.length;
        if (n <= 1) return;

        tracker.alloc(1);

        long start = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            tracker.read();
            int minVal = arr[i];

            boolean nonDecreasing = true;

            int prev = minVal;
            for (int j = i + 1; j < n; j++) {
                tracker.read();
                int aj = arr[j];

                tracker.cmp();
                if (aj < minVal) {
                    minVal = aj;
                    minIndex = j;
                }

                tracker.cmp();
                if (aj < prev) {
                    nonDecreasing = false;
                }
                prev = aj;
            }

            if (minIndex != i) {
                tracker.read(2);
                tracker.write(2);
                tracker.move(3);

                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            } else if (nonDecreasing) {

                break;
            }
        }

        long elapsed = System.nanoTime() - start;
        System.out.println("SelectionSort (fixed early termination): " + tracker + ", time=" + elapsed + " ns");
    }
}