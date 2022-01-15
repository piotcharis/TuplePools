package pgdp.pools;

public class TupleFactory<T, S> {
    private TuplePool<T, S> pool;

    public TupleFactory() {
        pool = new TuplePool<>();
    }

    public Tuple<T, S> create(T t, S s) {
        Tuple<T, S> tupleToInsert = new Tuple<>(t, s);

        return pool.insert(tupleToInsert);
    }

    public Tuple<T, S> intern(Tuple<T, S> tuple) {
        return pool.insert(tuple);
    }
}
