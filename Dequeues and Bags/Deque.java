import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    Node first, last;
    int size;

    public Deque() {
        first = null;
        last = null;

        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (first == null || last == null);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {

        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        if (isEmpty()) {
            first = newNode;
            last = first;
        } else {
            newNode.next = first;
            first.previous = newNode;
            first = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        newNode.previous = last;

        if (isEmpty()) {
            last = newNode;
            first = last;
        } else {
            last.next = newNode;
            last = newNode;
        }

        size++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item firstItem;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (first == last) {
            firstItem = first.item;
            first = null;
            last = null;
            size--;
        } else {
            firstItem = first.item;
            first = first.next;
            size--;
        }
        return firstItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item lastItem;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (first == last) {
            lastItem = last.item;
            first = null;
            last = null;
            size--;
        } else {
            lastItem = last.item;
            last = last.previous;
            last.next = null;
            size--;
        }
        return lastItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new myIterator();
    }

    private class myIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            /* not supported */
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Double> deque = new Deque<Double>();

        for (int i = 10; i > 0; i--) {
            deque.addFirst((double) i);
        }
        Iterator<Double> i = deque.iterator();
        while (i.hasNext()) {
            Double it = i.next();
            System.out.println(it);
        }
        // System.out.println(deque.first.item);
        // // System.out.println(deque.first.previous);
        // System.out.println(deque.first.next.item);
        // // System.out.println(deque.first.next.previous.item);

        // System.out.println(deque.removeFirst());
        // System.out.println(deque.first.item);
        // System.out.println(deque.first.next);

    }
}