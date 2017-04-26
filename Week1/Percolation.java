import edu.princeton.cs.algs4.StdIn;
import java.util.Arrays;
import java.util.Iterator;
public class Percolation {
    private int[][] id;
    private int[] sz;
    private int n;
    private int num;
    private Queue<Integer> que = new Queue<>();
 
    public Percolation(int n) {// create n-by-n grid, with all sites blocked
        this.n = n + 1;
        id = new int[n + 1][n + 1];
        System.out.println(n + 1);
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                id[i][j] = -1;// Initialize all sites to be blocked.
            }
        }
        sz = new int[(n + 1) * (n + 1)];
        for (int i = 0; i < (n + 1) * (n + 1); i++)
            sz[i] = 1;
    }
 
    private int root(int p, int q) {
        int temp = 0;
        while ((n * p + q) != id[p][q]) {
            temp = q;
            q = id[p][q] % n;
            p = id[p][temp] / n;
        }
        return id[p][q];
    }
 
    private void up(int row, int col) {
        int i = root(row, col);
        if (id[row - 1][col] != -1) {
            int j = root(row - 1, col);
            if (i == j)
                return;
            if (sz[i] < sz[j]) {
                id[i / n][i % n] = j;
                sz[j] += sz[i];
            } else {
                id[j / n][j % n] = i;
                sz[i] += sz[j];
            }
        }
    }
 
    private void down(int row, int col) {
        int i = root(row, col);
        if (id[row + 1][col] != -1) {
            int j = root(row + 1, col);
            if (i == j)
                return;
            if (sz[i] < sz[j]) {
                id[i / n][i % n] = j;
                sz[j] += sz[i];
            } else {
                id[j / n][j % n] = i;
                sz[i] += sz[j];
            }
 
        }
    }
 
    private void left(int row, int col) {
        int i = root(row, col);
        if (id[row][col - 1] != -1) {
            int j = root(row, col - 1);
            if (i == j)
                return;
            if (sz[i] < sz[j]) {
                id[i / n][i % n] = j;
                sz[j] += sz[i];
            } else {
                id[j / n][j % n] = i;
                sz[i] += sz[j];
            }
        }
    }
 
    private void right(int row, int col) {
        int i = root(row, col);
        if (id[row][col + 1] != -1) {
            int j = root(row, col + 1);
            if (i == j)
                return;
            if (sz[i] < sz[j]) {
                id[i / n][i % n] = j;
                sz[j] += sz[i];
            } else {
                id[j / n][j % n] = i;
                sz[i] += sz[j];
            }
        }
    }
 
    public void open(int row, int col) {
        // open site (row, col) if it is not open already
        if (isFull(row, col) == true) {
            id[row][col] = row * n + col;
            num++;
        }
        if (row - 1 != 0) {
            up(row, col);
        }
        if (row != n - 1) {
            down(row, col);
        }
        if (col - 1 != 0) {
            left(row, col);
        }
        if (col != n - 1) {
            right(row, col);
        }
 
    }
 
    public boolean isOpen(int row, int col) {
        return id[row][col] != -1;
    }
 
    public boolean isFull(int row, int col) {
        return id[row][col] == -1;
    }
 
    public boolean percolates() {
        int temp = -1;
        int temp2 = -1;
        int a[];
 
        for (int i = 0; i < n; i++) {
            if ((isFull(n - 1, i) != true)) {
                que.enqueue(root(n - 1, i));
            }
        }
        int siz = que.size();
        a = new int[siz];
        for (int i = 0; i < siz; i++) {
            if (que.isEmpty() != true) {
                a[i] = que.dequeue();
            }
        }
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            if (isFull(1, i) != true) {
                temp = BinarySearch.rank(root(1, i), a);
                if (temp >= 0) {
                    temp2 = temp;
                }
            }
        }
        return temp2 >= 0;
    }
 
    public int numberOfOpenSites() {
        return num;
    }
 
    public static void main(String[] args) {
        int sum = 0;
        int num = StdIn.readInt();
 
        Percolation p = new Percolation(num);
 
        while ((p.percolates() == false) && (!StdIn.isEmpty())) {
            int m = StdIn.readInt();
            int n = StdIn.readInt();
            if (p.isFull(m, n) == true) {
                p.open(m, n);
                sum++;
            }
            System.out.println(m + " " + n + " " + sum);
            System.out.println(p.percolates());
        }
                if(p.isFull(4, 1)!=true){
            System.out.println("backwash unfinish");
        }
    }
}