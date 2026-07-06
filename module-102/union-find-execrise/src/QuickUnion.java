public class QuickUnion {
    private int[] id;

    public QuickUnion(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
    }

    private int root(int p) {
        if(p == id[p]) return p;
        id[p] = id[id[p]];
        return root(id[p]);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        id[rootP] = rootQ;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
