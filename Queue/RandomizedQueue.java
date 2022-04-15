import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node<Item> first = null;
    private Node<Item> last = null;
    private int n = 0;

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported Operation");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No such element");
            StdRandom.uniform(n);
            return sample();
//            Item item = current.item;
//            current = current.next;
//            return item;
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        double i = 0;
        Node<Item> thisNode = first;
        Node<Item> prevNode = null;
        while (i < n) {
            if (StdRandom.uniform(n) == i) {
                if (last == thisNode) {
                    prevNode.next = null;
                    last = prevNode;
                }
                if (prevNode == null) first = thisNode.next;
                else prevNode.next = thisNode.next;
                break;
            }
            prevNode = thisNode;
            thisNode = thisNode.next;
            i++;
        }
        n--;
        return thisNode;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        double i = 0;
        Node<Item> thisNode = first;
        while (i < n) {
            if (StdRandom.uniform(n) == i) {
                break;
            }
            thisNode = thisNode.next;
            i++;
        }
        return thisNode;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("Day");
        queue.enqueue("First");
        queue.enqueue("The");
        queue.enqueue("It's");
        queue.enqueue("The");
        queue.enqueue("Last");
        queue.enqueue("Day");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.sample();

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
    }

}
