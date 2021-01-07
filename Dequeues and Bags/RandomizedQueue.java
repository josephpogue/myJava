import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] randomQueue;
    private int size;

    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        randomQueue[size] = item;
        size++;
        // resizing
        if (randomQueue.length == size) {
            Item[] newQueue = (Item[]) new Object[size * 2];
            for (int i = 0; i < size; i++) {
                newQueue[i] = randomQueue[i];
            }
            randomQueue = newQueue;
        }

    }

    // remove and return a random item
    public Item dequeue() {
        // resizing
        int index = (int) StdRandom.uniform(size);
        Item result = randomQueue[index];
        randomQueue[index] = randomQueue[size - 1];
        randomQueue[size - 1] = null;
        size--;
        if (randomQueue.length / 4 == size) {
            Item[] newQueue = (Item[]) new Object[randomQueue.length / 2];
            for (int i = 0; i < randomQueue.length / 4; i++) {
                newQueue[i] = randomQueue[i];
            }
            randomQueue = newQueue;
        }
        return result;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int index = (int) StdRandom.uniform(size);
        return randomQueue[index];
    }

    public Iterator<Item> iterator() {
        return new myIterator();
    }

    private class myIterator implements Iterator<Item> {
        private int i = size;
        private int[] order;

        public myIterator() {
            order = new int[i];
            for (int j = 0; j < i; j++) {
                order[j] = j;
            }
            StdRandom.shuffle(order);

        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            /* not supported */
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return randomQueue[order[--i]];
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<Double> bag = new RandomizedQueue<Double>();

        for (int i = 0; i < 10; i++) {
            bag.enqueue((double) i);
        }

        Iterator<Double> i = bag.iterator();
        while (i.hasNext()) {
            Double it = i.next();
            System.out.println(it);
        }

        // System.out.println(bag.dequeue());
        // System.out.println(bag.dequeue());

    }

}
