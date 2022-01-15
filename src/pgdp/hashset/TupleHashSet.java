package pgdp.hashset;

import pgdp.pools.Tuple;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TupleHashSet<T, S> {
    private int currentTuple = 0;
    private boolean check = false;

    private final Tuple<T, S>[] hashArr;
    public static final int SIZE = 353;

    public TupleHashSet() {
        hashArr = new Tuple[SIZE];
    }

    private int hash(Tuple<T, S> t, int i) {
        return Math.abs((t.hashCode() + i) % SIZE);
    }

    public boolean insert(Tuple<T, S> t) {
        int i = findPosition(t);
        if (i == -1) {
            return false;
        }

        hashArr[i] = t;

        return true;
    }

    public Tuple<T, S> find(T t, S s) {
        Tuple<T, S> testTuple = new Tuple<>(t, s);

        return find(testTuple);
    }

    public Tuple<T, S> find(Tuple<T, S> t) {
        int i = findPosition(t);
        return i == -1 ? null : hashArr[i];
    }

    private int findPosition(Tuple<T, S> t) {
        int i = 0;
        int hash = hash(t, i);
        int start = hash;

        while (hashArr[hash] != null && !hashArr[hash].equals(t)) {
            hash = hash(t, ++i);
            if (hash == start) {
                return -1;
            }
        }

        return hash;
    }

    public boolean contains(Tuple<T, S> t) {
        return find(t) != null;
    }

    public int insertedTuples() {
        int res = 0;
        for (Tuple<T, S> t : hashArr) {
            if (t != null) {
                res++;
            }
        }
        return res;
    }

    public Tuple<T, S> removeFirstElement() {

        Tuple<T, S> ret;

        for (int i = 0; i < SIZE; i++) {

            if (hashArr[i] != null) {
                ret = hashArr[i];
                hashArr[i] = null;

                while (hashArr[(i + 1) % SIZE] != null) {

                    int c = 0;
                    int hash = hash(hashArr[(i + 1) % SIZE], c);

                    while (hashArr[hash] != null && hash != i + 1) {
                        hash = hash(hashArr[(i + 1) % SIZE], ++c);
                    }

                    if (hash != i + 1) {
                        hashArr[hash] = hashArr[(i + 1) % SIZE];
                        hashArr[(i + 1) % SIZE] = null;
                    }
                    i++;
                }
                return ret;
            }
        }
        return null;
    }

    public Iterator<Tuple<T, S>> iterator() {
        return new Iterator<Tuple<T, S>>() {


            @Override
            public boolean hasNext() {
                check = true;
                for (int i = currentTuple + 1; i < hashArr.length; i++) {
                    if (hashArr[i] != null) {
                        currentTuple = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Tuple<T, S> next() throws NoSuchElementException {
                if (check) {
                    check = false;
                    return hashArr[currentTuple];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
