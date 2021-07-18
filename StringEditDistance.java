import java.io.*;

// also known as the Levenshtein Distance
public class StringEditDistance {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String string1 = br.readLine();  // you are converting string1 to string2
        String string2 = br.readLine();

        int[][] matrix = new int[string2.length() + 1][string1.length() + 1];
        matrix[0][0] = 0;  // empty string to empty string is 0

        for (int x = 1; x <= string1.length(); x++) {  // initial settings
            matrix[0][x] = x;
        }
        for (int x = 1; x <= string2.length(); x++) {
            matrix[x][0] = x;
        }

        for (int x = 1; x <= string2.length(); x++) {
            for (int y = 1; y <= string1.length(); y++) {
                if (string1.charAt(y - 1) == string2.charAt(x - 1)) {
                    matrix[x][y] = matrix[x - 1][y - 1];
                }
                else {
                    matrix[x][y] = Math.min(matrix[x - 1][y], Math.min(matrix[x - 1][y - 1], matrix[x][y - 1])) + 1;  // take the minimum of insert, delete, and replace, add 1
                }
            }
        }

        pw.println(matrix[string2.length()][string1.length()]);
        pw.close();
    }
}
