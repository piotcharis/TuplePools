package pgdp.pools;

import pgdp.hashset.TupleHashSet;

public class TuplePool<T, S> {
    private TupleHashSet<T, S> tupleset;

    public TuplePool() {
        tupleset = new TupleHashSet<>();
    }

    public Tuple<T, S> insert(Tuple<T, S> tupleToInsert) {
        if (tupleset.contains(tupleToInsert)) {
            return tupleset.find(tupleToInsert);
        } else if (tupleset.insert(tupleToInsert)) {
            return tupleToInsert;
        }
        return null;
    }

    public Tuple<T, S> getByValue(T t, S s) {
        return tupleset.find(t, s);
    }

    public int getNumberOfTuples() {
        return tupleset.insertedTuples();
    }

}
