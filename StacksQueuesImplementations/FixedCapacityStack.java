import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item> {
    private Item[] s;
    private int N = 0;

    public FixedCapacityStack(int capacity) {
        s = (Item[]) new Object[capacity]; // the ugly cast
    }

    public void push(Item item) {
        s[N++] = item;
    }

    public Item pop() {
        Item item = s[--N];
        s[N] = null;
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }


    public static void main(String[] args) {
        int capacity = StdIn.readInt(); // ! dont forget
        FixedCapacityStack<String> stack = new FixedCapacityStack<String>(capacity);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop());
            else stack.push(s);
        }
    }
}
