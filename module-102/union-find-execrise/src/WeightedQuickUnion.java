public class WeightedQuickUnion {
    private int[] id;
    private int[] size;

    public WeightedQuickUnion(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    private int root(int p) {
        if(p==id[p]) return p;
        id[p] = id[id[p]];
        return root(id[p]);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if(rootP==rootQ) return;

        if(size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }
}
