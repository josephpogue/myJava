
// As given, this solution uses ~8N bytes of memory (~4N per array),
// ignoring the memory used for I/O. It uses order N^2 time.

import java.io.*;
import java.util.StringTokenizer;

public class LeftInversions {
    // Given array A, compute and return array L.
    // This method is simple but slow.
    int[] inversions;

    private static void merge(int[] a, int[] aux, int[] b, int[] baux, int[] inversions, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            baux[k] = b[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j];
                b[k] = baux[j++];
            } else if (j > hi) {
                a[k] = aux[i];
                b[k] = baux[i++];
            } else if (aux[j] < aux[i]) {
                a[k] = aux[j];
                b[k] = baux[j++];
                inversions[b[k]] += mid - i + 1;
            } else {
                a[k] = aux[i];
                b[k] = baux[i++];
            }

        }
    }

    public static void sort(int[] a, int[] aux, int[] b, int[] baux, int[] inversions, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, b, baux, inversions, lo, mid);
        sort(a, aux, b, baux, inversions, mid + 1, hi);
        merge(a, aux, b, baux, inversions, lo, mid, hi);
    }

    // Initializer method for b[] and aux[]
    public static int[] computeL(int[] a) {
        int[] aux = new int[a.length];
        int[] b = new int[a.length];
        int[] baux = new int[a.length];
        int[] inversions = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = i;
        }
        sort(a, aux, b, baux, inversions, 0, a.length - 1);
        return inversions;

    }

    public static void print(int[] a) {

        for (int i = 0; i < a.length; i++)
            // System.out.print here would be too slow!
            System.out.print(" " + a[i]);
        System.out.println();
    }

    // Method main() handles the input and output in a fast way,
    // you do not need to modify it.
    public static void main(String[] args) throws IOException {
        // Read input array A. We avoid java.util.Scanner, for speed.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // first line
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine()); // second line
        for (int i = 0; i < N; ++i)
            A[i] = Integer.parseInt(st.nextToken());

        // Solve the problem!
        int[] L = computeL(A);

        // Print the output array L, again buffered for speed.
        PrintWriter out = new PrintWriter(System.out);
        out.print(L[0]);
        for (int i = 1; i < N; ++i)
            // System.out.print here would be too slow!
            out.print(" " + L[i]);
        out.close();
    }
}