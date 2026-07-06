public class Percolation {

    private final int n;
    private final int[] grid;
    private final int[] grid2;
    private final int[] size;
    private final int[] size2;
    private final boolean[] open;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if(n<=0) throw new IllegalArgumentException();
        this.n = n;
        grid  = new int[n*n+2];
        grid2 = new int[n*n+2];
        size  = new int[n*n+2];
        size2 = new int[n*n+2];
        open  = new boolean[n*n+2];
        for(int i = 0; i < n*n+2; i++){
            grid[i]  = i;
            grid2[i] = i;
            size[i]  = 1;
            size2[i] = 1;
            open[i]  = false;
        }
    }

    private int validateIndicesAndReturnIndex(int row, int col){
        if(row<1 || row>n || col<1 || col>n)
            throw new IllegalArgumentException();
        return (row - 1) * n + (col - 1);
    }

    private int rootNode(int[] g, int i){
        if(i == g[i]) return i;
        g[i] = g[g[i]];
        return rootNode(g, g[i]);
    }

    private void union(int[] g, int[] sz, int i, int j){
        int rootI = rootNode(g, i);
        int rootJ = rootNode(g, j);
        if(rootJ == rootI) return;
        if(sz[rootI] > sz[rootJ]){
            g[rootJ] = rootI;
            sz[rootI] += sz[rootJ];
        } else {
            g[rootI] = rootJ;
            sz[rootJ] += sz[rootI];
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        int index = validateIndicesAndReturnIndex(row, col);
        if(open[index]) return;
        open[index] = true;

        // connect to top virtual node (n*n)
        if(index < n){
            union(grid,  size,  index, n*n);
            union(grid2, size2, index, n*n);
        }
        // connect to bottom virtual node (n*n+1) — only in grid, not grid2
        if(index >= (n-1)*n){
            union(grid, size, index, n*n+1);
        }

        // connect to open neighbors
        if(row > 1 && isOpen(row-1, col)){
            union(grid,  size,  index, validateIndicesAndReturnIndex(row-1, col));
            union(grid2, size2, index, validateIndicesAndReturnIndex(row-1, col));
        }
        if(row < n && isOpen(row+1, col)){   // fix: was row < n-1
            union(grid,  size,  index, validateIndicesAndReturnIndex(row+1, col));
            union(grid2, size2, index, validateIndicesAndReturnIndex(row+1, col));
        }
        if(col > 1 && isOpen(row, col-1)){
            union(grid,  size,  index, validateIndicesAndReturnIndex(row, col-1));
            union(grid2, size2, index, validateIndicesAndReturnIndex(row, col-1));
        }
        if(col < n && isOpen(row, col+1)){   // fix: was col < n-1
            union(grid,  size,  index, validateIndicesAndReturnIndex(row, col+1));
            union(grid2, size2, index, validateIndicesAndReturnIndex(row, col+1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        int index = validateIndicesAndReturnIndex(row, col);
        return open[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        int index = validateIndicesAndReturnIndex(row, col);
        // use grid2 (no bottom virtual node) to avoid backwash
        return rootNode(grid2, index) == rootNode(grid2, n*n);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        int count = 0;
        for(int i = 0; i < n*n; i++){
            if(open[i]) count++;
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates(){
        return rootNode(grid, n*n+1) == rootNode(grid, n*n);
    }

    public static void main(String[] args){
        Percolation p = new Percolation(5);
        p.open(2, 1);
        p.open(3, 2);
        System.out.println(p.percolates());

        p.open(4, 2);
        p.open(1, 1);
        p.isFull(3,2);
        p.open(5, 2);
        p.isFull(3,2);

        p.open(2, 2);
        p.isFull(3,2);

        System.out.println(p.percolates());
        System.out.println(p.numberOfOpenSites());
    }
}