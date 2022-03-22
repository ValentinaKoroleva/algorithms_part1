import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Percolation {
    private static final byte OPEN = (byte) 1;
    private static final byte CONNECTED_TO_BOTTOM = (byte) 2;
    private static final byte CONNECTED_TO_TOP = (byte) 4;

    private final int count;
    private byte[] siteStatus;
    private final WeightedQuickUnionUF grid;
    private final WeightedQuickUnionUF full;
    private int nOpenedSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("number of sites is illegal");
        count = n;
        grid = new WeightedQuickUnionUF(n * n + 2);
        full = new WeightedQuickUnionUF(n * n + 1);
        siteStatus = new byte[n * n + 2];
        siteStatus[0] = (OPEN | CONNECTED_TO_TOP);
        siteStatus[n * n + 1] = (OPEN | CONNECTED_TO_BOTTOM);
        for (int i = 1; i <= n; i++) {
            grid.union(0, i); // Connect top to the first row
            grid.union(n * n + 1, n * (n - 1) + i); // Connect bottom to the last row ???
            siteStatus[i] = (CONNECTED_TO_TOP);
            siteStatus[n * (n - 1) + i] = (CONNECTED_TO_BOTTOM);
        }

    }

    private int xyTo1D(int row, int col) {
        return col + (row - 1) * count;
    }

    private void validate(int row, int col) {
        if (!isOnGrid(row, col)) {
            throw new IllegalArgumentException("Index out of bounds " + row + " " + col);
        }
    }

    private boolean isOnGrid(int row, int col) {
        return (row >= 1 && col >= 1 && row < count + 1 && col < count + 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int id = xyTo1D(row, col);
        if (isOpen(row, col)) {
            return;
        }
        // open site
        nOpenedSites++;
        siteStatus[id] = (byte) (OPEN | siteStatus[id]);

        if (row == 1) {  // Top Row
            grid.union(0, id);
            full.union(0, id);
        }

        if (row == count) {  // Bottom Row
            grid.union(count * count + 1, id);
        }


        // Check and Open Left
        if (isOnGrid(row, col - 1) && isOpen(row, col - 1)) {
            grid.union(id, xyTo1D(row, col - 1));
            full.union(id, xyTo1D(row, col - 1));
        }

        // Check and Open Right
        if (isOnGrid(row, col + 1) && isOpen(row, col + 1)) {
            grid.union(id, xyTo1D(row, col + 1));
            full.union(id, xyTo1D(row, col + 1));
        }

        // Check and Open Up
        if (isOnGrid(row - 1, col) && isOpen(row - 1, col)) {
            grid.union(id, xyTo1D(row - 1, col));
            full.union(id, xyTo1D(row - 1, col));
        }

        // Check and Open Down
        if (isOnGrid(row + 1, col) && isOpen(row + 1, col)) {
            grid.union(id, xyTo1D(row + 1, col));
            full.union(id, xyTo1D(row + 1, col));
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return ((int) siteStatus[xyTo1D(row, col)]) % 2 != 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return full.find(0) == full.find(xyTo1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return nOpenedSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (count == 1) {
            return isOpen(1, 1);
        } else {
            return grid.find(0) == grid.find(count * count + 1);
        }
    }

    // test client (optional)
    public static void main(String[] args) {
//        int count = StdIn.readInt();
//        Percolation perc = new Percolation(count);
//        while (!StdIn.isEmpty()) {
//            int r = StdIn.readInt();
//            int c = StdIn.readInt();
//            perc.open(r, c);
//        }
//        StdOut.println(perc.percolates());
    }
}
