import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
 
public class PercolationStats {
    private int[] percolationNumber;
    private int number = 0;
 
    public PercolationStats(int n, int trials) throws IllegalArgumentException {
        // perform trials independent experiments on an n-by-n grid
        int count = 0;
        percolationNumber = new int[trials];
        Percolation p = new Percolation(n);
        while (number < trials) {
            while (p.percolates() == false) {
                int a = StdIn.readInt()-1;
                int b = StdIn.readInt()-1;
                if (p.isFull(a, b) == true) {
                    p.open(a, b);
                    count++;
                }
 
                System.out.println(a + " " + b + " " + count);
                System.out.println(p.percolates());
            }
            p=new Percolation(n);
            percolationNumber[number] = count;
            count = 0;
            number++;
        }
    }
 
    public double mean() {
        double sum = 0;
        for (int i = 0; i < percolationNumber.length; i++) {
            sum += percolationNumber[i];
        }
        return sum / number;
        // sample mean of percolation threshold
    }
 
    public double stddev() {
        double s = 0;
        double temp = 0;
        for (int i = 0; i < percolationNumber.length; i++) {
            temp += (percolationNumber[i] - mean()) * (percolationNumber[i] - mean());
        }
        s = temp / (number - 1);
 
        return Math.sqrt(s);
        // sample standard deviation of percolation threshold
    }
 
    public double confidenceLo() {
        double low = 0;
        low = mean() - 1.96 / Math.sqrt(number);
        return low;
        // low endpoint of 95% confidence interval
    }
 
    public double confidenceHi() {
        double high;
        high = mean() + 1.96 / Math.sqrt(number);
        return high;
        // high endpoint of 95% confidence interval
    }
 
    public static void main(String[] args) {
        // test client (described below)
        int num=30;
        int m=StdIn.readInt();
        PercolationStats p = new PercolationStats(m, num);
        System.out.println("Mean: " + p.mean() + " Stddev: " + p.stddev());
        System.out.println("ConfidenceLo: " + p.confidenceLo() + " confidenceHi: " + p.confidenceHi());
 
    }
}