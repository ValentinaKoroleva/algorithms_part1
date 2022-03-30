import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedListQueue<Item> {
    private Node first = null;
    private Node last = null;

    private class Node {
        Item item;
        Node next;
    }

    public void enqueue(Item item) {
        Node oldlast = last;
        Node last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }


    public static void main(String[] args) {
        LinkedListQueue<String> queue = new LinkedListQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(queue.dequeue());
            else queue.enqueue(s);
        }
    }
}
