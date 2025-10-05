package cli;

import algorithms.SelectionSort;
import metrics.PerformanceTracker;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) throws IOException {
        String algo = args.length > 0 ? args[0] : "selectionsort";
        String output = args.length > 1 ? args[1] : "opt_result.csv";
        int trials = args.length > 2 ? Integer.parseInt(args[2]) : 3;

        int[] sizes = {100, 1000, 10000, 100000};
        String[] distributions = {"random", "sorted", "reversed", "nearly"};

        Random rnd = new Random(42);

        for (String dist : distributions) {
            for (int n : sizes) {
                for (int t = 1; t <= trials; t++) {
                    int[] arr = generate(n, dist, rnd);
                    PerformanceTracker tracker = new PerformanceTracker();

                    long start = System.nanoTime();
                    switch (algo.toLowerCase()) {
                        case "selectionsort" -> new SelectionSort(tracker).sort(arr);
                        default -> throw new IllegalArgumentException("Unknown algo: " + algo);
                    }
                    long elapsed = System.nanoTime() - start;

                    tracker.writeRow(output, elapsed, n, algo, dist, "trial=" + t);
                }
            }
        }
    }

    private static int[] generate(int n, String dist, Random rnd) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(n * 10);

        switch (dist) {
            case "sorted" -> Arrays.sort(arr);
            case "reversed" -> {
                Arrays.sort(arr);
                for (int i = 0; i < n / 2; i++) {
                    int tmp = arr[i];
                    arr[i] = arr[n - 1 - i];
                    arr[n - 1 - i] = tmp;
                }
            }
            case "nearly" -> {
                Arrays.sort(arr);
                for (int i = 0; i < n / 20; i++) {
                    int i1 = rnd.nextInt(n);
                    int i2 = rnd.nextInt(n);
                    int tmp = arr[i1];
                    arr[i1] = arr[i2];
                    arr[i2] = tmp;
                }
            }
        }
        return arr;
    }
}