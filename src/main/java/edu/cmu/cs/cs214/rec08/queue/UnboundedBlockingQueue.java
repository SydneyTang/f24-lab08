package edu.cmu.cs.cs214.rec08.queue;

import java.util.ArrayDeque;
import java.util.Deque;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * Modify this class to be thread-safe and be an UnboundedBlockingQueue.
 */
@ThreadSafe
public class UnboundedBlockingQueue<E> implements SimpleQueue<E> {
    @GuardedBy("this")
    private final Deque<E> queue = new ArrayDeque<>();

    public UnboundedBlockingQueue() { }

    @Override
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public synchronized int size() {
        return queue.size();
    }

    @Override
    public synchronized E peek() {
        return queue.peek();
    }

    @Override
    public synchronized void enqueue(E element) {
        queue.add(element);
        notifyAll(); // Notify any waiting threads that an element is added
    }

    @Override
    public synchronized E dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait if the queue is empty
        }
        return queue.remove();
    }

    @Override
    public synchronized String toString() {
        return queue.toString();
    }
}
