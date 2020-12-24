import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            double count = 0;
            Percolation exp = new Percolation(n);
            while (!exp.percolates()) {
                int row = StdRandom.uniform(n);
                int col = StdRandom.uniform(n);
                if (!exp.isOpen(row, col)) {
                    exp.open(row, col);
                    count++;
                }

            }
            results[i] = count / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(results) - (1.96 * StdStats.stddev(results));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(results) + (1.96 * StdStats.stddev(results));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats stat = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean = " + stat.mean());
        System.out.println("stddev = " + stat.stddev());
        System.out.println("95% confidence interval = [" + stat.confidenceLo() + ", " + stat.confidenceHi() + "]");
    }

}
