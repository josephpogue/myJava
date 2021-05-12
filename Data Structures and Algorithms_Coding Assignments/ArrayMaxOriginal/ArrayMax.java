// THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
// A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - YOUR NAME

// As given, this is already a fast enough solution to the "Array Max"
// challenge on hackerrank.  However, we want a solution using less
// space, and that does not rely on java.util.* data structures.
// See the Canvas "code3" assignment for a more precise statement
// of constraints and advice.

// Reminders:
//   1. remember to put your name in the SPCA comment above.
//   2. read the Canvas "code3" assignment for more details.
//   3. remember to submit your link and paragraph on Canvas.

// This solution uses java.util.PriorityQueue, a binary min-heap.  It
// also uses buffered input and output, for speed.  Note there could
// be many "stale" entries in the queue.  So in the worst case this
// solution uses O(N) space and O(N lg N) time.

import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class ArrayMax
{
    // We will maintain a min-PQ of Entry objects.
    // Each Entry (i,v) represents an assignment "A[i]=v".
    static class Entry implements Comparable<Entry> {
        int i, v;
        Entry(int i, int v) { this.i=i; this.v=v; }
        // We negate the "v" comparison, so that PriorityQueue (a MinPQ)
        // returns the Entry with the maximum v.  We break ties with i,
        // so we can find the leftmost appearance of the maximum.
        public int compareTo(Entry that) {
            int dif = -(this.v - that.v);
            if (dif==0) // break ties with the index i
                dif = this.i - that.i;
            return dif;
        }
    }

    public static void main(String[] args) throws IOException
    {
        // Buffered output (for faster printing)
        PrintWriter out = new PrintWriter(System.out);
        // Buffered input (we also avoid java.util.Scanner)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()),
            N = Integer.parseInt(st.nextToken());
        int[] a = new int[M]; // initially all zero
        // Create MinPQ, and add an entry for a[0]=0 (that's all we need)
        PriorityQueue<Entry> pq = new PriorityQueue<Entry>();
        pq.add(new Entry(0, 0));
        // Loop through the N assignment lines
        for (int n=0; n<N; ++n) {
            // read the line, parse i and v
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()),
                v = Integer.parseInt(st.nextToken());
            // do the assignment in the array
            a[i] = v;
            // Add an Entry recording this assignment
            pq.add(new Entry(i, v));
            // Get the head of the queue (Entry with maximum v value)
            Entry head = pq.peek();
            // While the head is stale (no longer in the array), discard it.
            while (a[head.i] != head.v) {
                pq.poll();        // discard it
                head = pq.peek(); // the next head
            }
            // Report location of the largest value, a[head.i]==head.v
            out.println(head.i);
        }
        out.close();
    }
}
