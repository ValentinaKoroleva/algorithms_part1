import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedListQueue {
    private Node first = null;
    private Node last = null;

    private static class Node {
        String item;
        Node next;
    }

    public void enqueue(String item) {
        Node oldlast = last;
        Node last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
    }

    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }


    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(queue.dequeue());
            else queue.enqueue(s);
        }
    }
}
