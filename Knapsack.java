import java.io.*;
import java.util.*;

public class Knapsack {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer inputLine = new StringTokenizer(br.readLine());
        int numObjects = Integer.parseInt(inputLine.nextToken());
        int maximumCapacity = Integer.parseInt(inputLine.nextToken());

        int[] sizes = new int[numObjects + 1];
        int[] values = new int[numObjects + 1];

        for (int x = 1; x <= numObjects; x++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            sizes[x] = Integer.parseInt(line.nextToken());
            values[x] = Integer.parseInt(line.nextToken());
        }

        int[][] dp = new int[numObjects + 1][maximumCapacity + 1];
        /*
            dp[object][size] is the maximum value using objects up to
            the object and size no greater than size
         */

        for (int objectNumber = 1; objectNumber <= numObjects; objectNumber++) {  // for each objet
            for (int size = 0; size <= maximumCapacity; size++) {  // for each size, starting from 0
                int weightLeftIfChooseItem = size - sizes[objectNumber];  // the current size (the column) - the size of the current object
                if (weightLeftIfChooseItem >= 0) {  // if you are able to choose the item (subtracting the size of the current object results in a non-negative number)
                    dp[objectNumber][size] = Math.max(dp[objectNumber - 1][size], dp[objectNumber - 1][weightLeftIfChooseItem] + values[objectNumber]);  // take the maximum of what is already there and if we decide to include this item
                }
                else {
                    // just take from the row above
                    dp[objectNumber][size] = dp[objectNumber - 1][size];
                }
            }
        }
        pw.println(dp[numObjects][maximumCapacity]);
        pw.close();
    }
}
