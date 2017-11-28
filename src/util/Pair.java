package util;

public class Pair<A, B> {

    private A left;
    private A right;

    public Pair() {
    }

    public Pair(A left, A right) {
        this.left = left;
        this.right = right;
    }

    public A getLeft() {
        return left;
    }

    public void setLeft(A left) {
        this.left = left;
    }

    public A getRight() {
        return right;
    }

    public void setRight(A right) {
        this.right = right;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        return (left != null ? left.equals(pair.left) : pair.left == null) && (right != null ? right.equals(pair.right) : pair.right == null);
    }

    public Pair<A, B> getSwappedPair() {
        return new Pair<>(right, left);
    }
}