import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] grid;
    private WeightedQuickUnionUF uf;
    private int n;
    private int virtualTop;
    private int virtualBottom;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    // Initialize virtual top and bottom as true and everything else false
    // (Positions; VTop = 0, VBottom = n * n + 1)
    public Percolation(int n) {
        if (n < 0)
            throw new IllegalArgumentException("N < 0");
        grid = new boolean[n * n + 2];
        virtualTop = 0;
        virtualBottom = n * n + 1;
        for (int i = 1; i < n * n + 1; i++) {
            grid[i] = false;
        }
        grid[virtualTop] = true;
        grid[virtualBottom] = true;
        uf = new WeightedQuickUnionUF(n * n + 2);
        this.n = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (grid[row * n + col + 1] == false) {
            grid[row * n + col + 1] = true;
            openSites++;
            if (row == 0) {
                uf.union(virtualTop, row * n + col + 1);
            }
            if (row == n - 1) {
                uf.union(row * n + col + 1, virtualBottom);
            }
            // check top cell
            if (isValid(row - 1, col)) {
                if (grid[(row - 1) * n + col + 1] == true) {
                    uf.union(row * n + col + 1, (row - 1) * n + col + 1);
                }
            }

            // check bottom cell
            if (isValid(row + 1, col)) {
                if (grid[(row + 1) * n + col + 1] == true) {
                    uf.union(row * n + col + 1, (row + 1) * n + col + 1);
                }
            }

            // check right cell
            if (isValid(row, col + 1)) {
                if (grid[row * n + (col + 1) + 1] == true) {
                    uf.union(row * n + col + 1, row * n + (col + 1) + 1);
                }
            }

            // check left cell
            if (isValid(row, col - 1)) {
                if (grid[row * n + (col - 1) + 1] == true) {
                    uf.union(row * n + col + 1, row * n + (col - 1) + 1);
                }
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        return grid[row * n + col + 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return uf.find(virtualTop) == uf.find(row * n + col + 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col <= n;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation a = new Percolation(4);
        System.out.println(a.percolates());
        a.open(0, 0);
        a.open(1, 0);
        a.open(1, 1);
        a.open(2, 1);
        a.open(3, 1);
        System.out.println(a.percolates());

    }
}