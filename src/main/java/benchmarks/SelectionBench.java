package benchmarks;

import algorithms.SelectionSort;
import metrics.PerformanceTracker;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@State(Scope.Thread)
public class SelectionBench {

    @Param({"100", "1000", "10000"})
    private int n;

    private int[] randomArray;
    private int[] sortedArray;
    private int[] reversedArray;
    private Random rnd;

    @Setup(Level.Trial)
    public void setup() {
        rnd = new Random(42);
        randomArray = generateRandom(n);
        sortedArray = generateSorted(n);
        reversedArray = generateReversed(n);
    }

    private int[] generateRandom(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(n * 10);
        return arr;
    }

    private int[] generateSorted(int n) {
        int[] arr = generateRandom(n);
        Arrays.sort(arr);
        return arr;
    }

    private int[] generateReversed(int n) {
        int[] arr = generateSorted(n);
        for (int i = 0; i < n / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = tmp;
        }
        return arr;
    }

    @Benchmark
    public void selectionSortRandom() {
        int[] arr = randomArray.clone();
        new SelectionSort(new PerformanceTracker()).sort(arr);
    }

    @Benchmark
    public void selectionSortSorted() {
        int[] arr = sortedArray.clone();
        new SelectionSort(new PerformanceTracker()).sort(arr);
    }

    @Benchmark
    public void selectionSortReversed() {
        int[] arr = reversedArray.clone();
        new SelectionSort(new PerformanceTracker()).sort(arr);
    }
}