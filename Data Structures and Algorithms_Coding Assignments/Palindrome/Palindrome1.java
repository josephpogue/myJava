// THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
// A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Joseph Pogue
import java.util.Scanner;


public class Palindrome1 {

    public static String createMatrix(int n, String s) {
        // initialize all values to 0
        int[][] matrix = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = 0;
            }
        }
        // initialize base cases (two diagnols)
        if (true) {
            for (int i = 0; i < 1; i++) {
                for (int row = 0, col = i; col < n; row++, col++) {

                    if (row == col) {
                        matrix[row][col] = 1;
                    }
                }
            }
            // complete triangle matrix
            for (int i = 1; i < n; i++) {
                for (int row = 0, col = i; col < n; row++, col++) {

                    if (s.charAt(row) == s.charAt(col)) {
                        matrix[row][col] = matrix[row + 1][col - 1] + 2;
                    } else {
                        matrix[row][col] = Math.max(matrix[row + 1][col], matrix[row][col - 1]);
                    }
                }
            }
        }
        // else {
        // for (int i = 0; i < 2; i++) {
        // for (int row = 0, col = i; col < n; row++, col++) {

        // if (row == col - 1) {
        // matrix[row][col] = 1;
        // }
        // }
        // }
        // // complete triangle matrix
        // for (int i = 2; i < n; i++) {
        // for (int row = 0, col = i; col < n; row++, col++) {

        // if (s.charAt(row) == s.charAt(col)) {
        // matrix[row][col] = matrix[row + 1][col - 1] + 2;
        // } else {
        // matrix[row][col] = Math.max(matrix[row + 1][col], matrix[row][col - 1]);
        // }
        // }
        // }
        // }

        // for (int row = 0; row < n; row++) {
        // for (int col = 0; col < n; col++) {
        // System.out.print(matrix[row][col] + " ");
        // }
        // System.out.println();
        // }

        int row = 0, col = n - 1;
        char[] lps = new char[matrix[0][n - 1]];
        int hi = matrix[0][n - 1] - 1, lo = 0;
        while (row <= col) {
            if (s.charAt(row) == s.charAt(col)) {
                lps[hi] = s.charAt(col);
                lps[lo] = s.charAt(row);
                // System.out.println(lo + " " + hi);
                row++;
                col--;
                hi--;
                lo++;
                ;
            } else if (matrix[row][col] == matrix[row][col - 1]) {
                col--;
            } else {
                row++;
            }

        }
        String P = "";
        for (int i = 0; i < lps.length; i++) {
            P += lps[i];
        }
        return P;

    }
    // if (matrix[row][col] == matrix[row][col - 1]) {
    // col--;
    // } else if (matrix[row][col] > matrix[row + 1][col] && matrix[row][col] >
    // matrix[row][col - 1]) {
    // lps[hi] = s.charAt(col - 1);
    // lps[lo] = s.charAt(col - 1);
    // row++;
    // col--;
    // hi--;
    // lo++;
    // } else {
    // row++;
    // }
    // if (matrix[row][col] > matrix[row + 1][col] && matrix[row][col] >
    // matrix[row][col - 1]) {
    // lps[hi] = s.charAt(col - 1);
    // lps[lo] = s.charAt(col - 1);
    // row++;
    // col--;
    // hi--;
    // lo++;
    // } else if (matrix[row][col] == matrix[row][col - 1]) {
    // col--;
    // } else {
    // row++;

    // }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String S = sc.next();
        String P = createMatrix(N, S);
        System.out.println(P.length());
        System.out.println(P);

    }
}
