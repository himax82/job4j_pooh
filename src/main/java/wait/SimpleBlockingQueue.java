package wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private int maxSize;

    public SimpleBlockingQueue() {
        maxSize = Integer.MAX_VALUE;
    }

    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void offer(T value) throws InterruptedException {
            while (queue.size() == maxSize) {
                    this.wait();
            }
            queue.add(value);
            this.notify();
    }

    public synchronized T poll() throws InterruptedException {
            while (queue.isEmpty()) {
                    this.wait();
            }
            T res = queue.poll();
            this.notify();
            return res;
        }

        public synchronized boolean isEmpty() {
        return queue.isEmpty();
        }
    }
