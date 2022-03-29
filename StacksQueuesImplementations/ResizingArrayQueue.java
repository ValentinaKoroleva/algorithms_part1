import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//https://algs4.cs.princeton.edu/13stacks/ResizingArrayQueue.java.html
//TODO: Finish


public class ResizingArrayQueue {
    private String[] q;
    private int tail = 0;
    private int head = 0;

    public ResizingArrayQueue() {
        q = new String[1];
    }

    public void enqueue(String item) {
        if (tail == q.length) resize(2 * q.length);
        q[tail++] = item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < q.length; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }

    public String dequeue() {
        String item = q[--head];
        q[head] = null;
        if (tail > 0 && tail == q.length / 4) resize(q.length / 2);
        return item;
    }

    public boolean isEmpty() {
        return (tail - head) == 0;
    }


    public static void main(String[] args) {
        ResizingArrayQueue queue = new ResizingArrayQueue();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(queue.dequeue());
            else queue.enqueue(s);
        }
    }
}
