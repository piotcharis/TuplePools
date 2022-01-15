package pgdp.pools;

import java.util.Objects;

public class Tuple<T, S> {
    private T t;
    private S s;

    public Tuple(T t, S s) {
        this.t = t;
        this.s = s;
    }

    public T getT() {
        return t;
    }

    public S getS() {
        return s;
    }

    public boolean equals(Object obj) {
        return obj instanceof Tuple && ((Tuple<?, ?>) obj).getT() == this.getT() &&
                ((Tuple<?, ?>) obj).getS() == this.getS();
    }

    public int hashCode() {
        return Objects.hash(this.getT(), this.getS());
    }
}
