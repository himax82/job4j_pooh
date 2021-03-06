package syn;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T>, Cloneable {

    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = clone(list);
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        T copy = list.get(index);
        return copy;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return clone(list).iterator();
    }

    private synchronized List<T> clone(List<T> list) {
        return new ArrayList<>(list);
    }
}
