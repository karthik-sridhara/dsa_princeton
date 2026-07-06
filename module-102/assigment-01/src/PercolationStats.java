import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] thresholds;

    public PercolationStats(int n, int trials){
        if(n <= 0 || trials <= 0){
            throw new IllegalArgumentException();
        }
        thresholds = new double[trials];
        for(int i = 0; i < trials; i++){
            Percolation p = new Percolation(n);
            while(!p.percolates()){
                int row = StdRandom.uniformInt(1, n+1);
                int col = StdRandom.uniformInt(1, n+1);
                p.open(row, col);
            }
            thresholds[i] = p.numberOfOpenSites() / (double)(n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - (1.96 * stddev() / Math.sqrt(thresholds.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + (1.96 * stddev() / Math.sqrt(thresholds.length));
    }

    // test client (see below)
    public static void main(String[] args){
        if(args.length != 2){
            throw new IllegalArgumentException();
        }
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n,trials);
        StdOut.printf("mean = %f\n", ps.mean());
        StdOut.printf("stddev = %f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = [%f,%f]\n", ps.confidenceLo(), ps.confidenceHi());
    }

}