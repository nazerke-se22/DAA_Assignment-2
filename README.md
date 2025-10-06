# Selection Sort Implementation with Performance Analysis

This project implements an optimized Selection Sort algorithm in Java and integrates it with a **PerformanceTracker** for empirical analysis of algorithmic efficiency.  
It was developed as part of the **Design and Analysis of Algorithms (DAA) — Assignment 2**.

---

## Features

- Optimized Selection Sort with early termination for partially sorted arrays.  
- Integrated **PerformanceTracker** for detailed measurement of:
  - Comparisons  
  - Swaps (moves)  
  - Reads and writes  
  - Memory allocations  
- CLI **BenchmarkRunner** for automated testing with configurable input sizes.  
- JMH-based microbenchmarking for precise performance measurements.  
- CSV export for empirical data collection and analysis.  
- Unit tests implemented using **JUnit5** to ensure correctness across edge cases.

---

## Repository Structure

Assignment2_DAA/
├── src/
│ ├── main/java/
│ │ ├── algorithms/SelectionSort.java
│ │ ├── benchmarks/SelectionBench.java
│ │ ├── cli/BenchmarkRunner.java
│ │ └── metrics/PerformanceTracker.java
│ └── test/java/
│ └── algorithms/SelectionSortTest.java
├── benchmark.csv
├── opt_result.csv
├── results.csv
├── docs/
│ ├── analysis-report.pdf
│ └── performance-plots/
├── pom.xml
└── README.md


---

## Algorithm Overview

**Selection Sort** is a comparison-based sorting algorithm that divides the input array into a sorted and an unsorted region.  
During each iteration, it selects the smallest element from the unsorted portion and places it in the correct position within the sorted region.

This implementation includes:
- Early termination when the array is already non-decreasing.  
- In-place sorting without auxiliary memory allocation.  
- Detailed performance tracking for empirical complexity validation.

---

## Complexity Analysis

| Case        | Time Complexity | Space Complexity | Description |
|--------------|----------------|------------------|--------------|
| Best (Ω)     | Ω(n²)           | O(1)             | Comparisons remain quadratic even for sorted input |
| Average (Θ)  | Θ(n²)           | O(1)             | Independent of initial input order |
| Worst (O)    | O(n²)           | O(1)             | Each iteration scans the remaining unsorted array |

**Recurrence Relation:**

T(n) = T(n - 1) + O(n) → T(n) ∈ Θ(n²)

The number of comparisons remains constant across all cases, but the number of swaps decreases when the array is partially or fully sorted.

---

## Benchmark Setup

- **Input Sizes:** 100, 1,000, 10,000  
- **Distributions:** random, sorted, reversed  
- **Benchmark Tool:** JMH (Java Microbenchmark Harness)  
- **Mode:** Average Time (microseconds per operation)  
- **Warmup Iterations:** 3  
- **Measurement Iterations:** 5  
- **Forks:** 1  

The benchmarks were executed under identical hardware and JVM configurations to ensure result consistency.

---

## Empirical Results (JMH)

| Input Distribution | n      | Average Time (µs/op) | Observation |
|--------------------|--------|----------------------|--------------|
| Random             | 100    | 23.39                | Consistent with expected quadratic trend |
| Random             | 1,000  | 291.04               | Time increases proportionally to n² |
| Random             | 10,000 | 20,908.22            | Confirms O(n²) scaling |
| Sorted             | 100    | 16.08                | Early termination reduces runtime |
| Sorted             | 1,000  | 11.09                | Minimal operations due to sorted input |
| Sorted             | 10,000 | 19.48                | Near-linear behavior due to early exit |
| Reversed           | 100    | 12.42                | Slightly higher cost due to swap operations |
| Reversed           | 1,000  | 394.06               | Highest runtime among all distributions |
| Reversed           | 10,000 | 39,444.74            | Confirms worst-case O(n²) growth |

---

## Example Metrics (n = 10,000, Random Input)

| Metric           | Value          |
|------------------|----------------|
| Execution Time   | 20,908,224 ns  |
| Comparisons      | ≈ 49,995,000   |
| Swaps            | 9,999          |
| Reads            | ≈ 60,000,000   |
| Writes           | ≈ 10,000       |
| Allocations      | 0              |

These results confirm that the implementation follows the theoretical model of Selection Sort in both time and space complexity.

---

## Observations from Benchmarks

- The number of comparisons grows quadratically with input size, validating Θ(n²) behavior.  
- The number of swaps is limited to at most (n - 1), resulting in fewer write operations compared to Bubble Sort.  
- Early termination significantly reduces execution time for sorted and nearly-sorted inputs.  
- The algorithm maintains constant auxiliary memory usage (O(1)).  
- Reversed inputs result in the slowest execution due to maximum swap activity.

---

## Empirical Validation

- Performance tests were conducted for n = 10² to 10⁵.  
- Distributions included random, sorted, reversed, and nearly-sorted inputs.  
- Results confirm the theoretical quadratic scaling of Selection Sort.  
- Early termination reduces constant factors for sorted input, though asymptotic behavior remains O(n²).  
- Collected CSV data (`benchmark.csv`, `opt_result.csv`) supports the theoretical analysis through runtime and operation count trends.

---

## How to Run

### Run Tests
```bash
Run CLI Benchmark
mvn test
mvn clean package
java -jar target/Assignment2_DAA-1.0-SNAPSHOT.jar selectionsort opt_result.csv 3

Run JMH Benchmarks
mvn clean install
java -jar target/benchmarks.jar

Benchmark results will be stored in benchmark.csv and opt_result.csv for further analysis.
```
---

### Release Notes (v1.0)

Implemented optimized Selection Sort with early termination.
Integrated PerformanceTracker for detailed metric collection.
Added CLI BenchmarkRunner for configurable performance tests.
Added JMH benchmark suite for precise runtime measurement.
Implemented comprehensive JUnit5 tests for correctness validation.
Exported benchmark results to CSV files for empirical evaluation.
Configured GitHub Actions CI workflow (.github/workflows/ci.yml).
Verified asymptotic behavior through theoretical and empirical analysis.

---

### Conclusion

The Selection Sort implementation fulfills all functional and analytical requirements of the assignment.
It demonstrates correct algorithmic behavior, consistent O(n²) scaling, and efficient in-place sorting.
Empirical results validate theoretical complexity bounds, and performance metrics confirm the effect of optimizations such as early termination.
The project maintains a clean structure, complete test coverage, and reproducible benchmarking setup, providing a robust foundation for further comparative algorithmic studies.
