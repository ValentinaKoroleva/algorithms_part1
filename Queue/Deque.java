import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first = null;
    private Node<Item> last = null;
    private int n = 0;

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("No such element");
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty()) last = first;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("No such element");
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = last.item;
        Node<Item> thisNode = first;
        while (thisNode != null) {
            if (last.equals(thisNode.next)) {
                thisNode.next = null;
                last = thisNode;
                break;
            }
            thisNode = thisNode.next;
        }
        n--;
        if (isEmpty()) last = null;
        return item;
    }

    // return an iterator over items in order from front to back
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
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("Day");
        deque.addFirst("First");
        deque.addFirst("The");
        deque.addFirst("It's");
        deque.addLast("The");
        deque.addLast("Last");
        deque.addLast("Day");
        deque.removeFirst();
        deque.removeLast();
        deque.removeLast();

        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
    }

}
