import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n = 0;
    private int tail = 0;
    private int head = 0;

    public ResizingArrayQueue() {
        q = (Item[]) new Object[1];
    }

    public void enqueue(Item item) {
        if (n == q.length) resize(2 * q.length);
        q[tail++] = item;
        if (tail == q.length) tail = 0; // wrap around
        n++;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(head + i) % q.length];
        }
        q = copy;
        head = 0;
        tail = n;
    }

    public Item dequeue() {
        Item item = q[head];
        q[head] = null;
        n--;
        head++;
        if (head == q.length) head = 0;
        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }


    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported Operation");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No such element");
            Item item = q[(i + head) % q.length];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.println("-" + queue.dequeue());
            else queue.enqueue(s);
        }
        StdOut.print("Left on queue: ");
        for (String q : queue) {
            StdOut.print(q + " ");
        }
        StdOut.println();
    }
}
