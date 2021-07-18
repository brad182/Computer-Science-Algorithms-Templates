import java.io.*;

public class LongestCommonSubsequence {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String string1 = br.readLine();
        String string2 = br.readLine();

        // you only need to store two rows, use (x % 2) or (x - 1) % 2 for row above
        int[][] dp = new int[2][string2.length() + 1];

        for (int x = 1; x <= string1.length(); x++) {
            for (int y = 1; y <= string2.length(); y++) {
                // do minus 1 because of 1-indexing
                if (string1.charAt(x - 1) == string2.charAt(y - 1)) {  // if there is a match
                    // find the maximum of the two sub-problems, add one because it is a match
                    dp[x % 2][y] = dp[(x - 1) % 2][y - 1] + 1;  // char removed on each string
                }
                else {  // there is not a match
                    dp[x % 2][y] = Math.max(dp[(x - 1) % 2][y], dp[x % 2][y - 1]);  // take the maximum of the two sub-problems
                }
            }
        }
        pw.println(dp[string1.length() % 2][string2.length()]);
        pw.close();
    }
}
