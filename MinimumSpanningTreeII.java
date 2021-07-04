import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

// much faster implementation of Prim's using a priority queue
public class MinimumSpanningTreeII {

    static class Connection {
        int nodeNumber;
        int weight;
    }

    static class comparator implements Comparator<Connection> {
        public int compare (Connection one, Connection two) {
            if (one.weight < two.weight) {  // lowest weight first
                return -1;
            }
            else if (one.weight == two.weight) {
                return 0;
            }
            else {
                return 1;
            }
        }
    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer inputLine = new StringTokenizer(br.readLine());
        int numNodes = Integer.parseInt(inputLine.nextToken());
        int numEdges = Integer.parseInt(inputLine.nextToken());

        List<Connection>[] adjacencyList = new ArrayList[numNodes + 1];
        for (int x = 0; x < adjacencyList.length; x++) {  // initialize ArrayLists
            adjacencyList[x] = new ArrayList<>();
        }

        for (int x = 1; x <= numEdges; x++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            int weight = Integer.parseInt(line.nextToken());

            Connection connection1 = new Connection();
            connection1.nodeNumber = b;
            connection1.weight = weight;

            Connection connection2 = new Connection();
            connection2.nodeNumber = a;
            connection2.weight = weight;

            adjacencyList[a].add(connection1);
            adjacencyList[b].add(connection2);
        }

        PriorityQueue<Connection> priorityQueue = new PriorityQueue<>(new comparator());
        boolean[] visited = new boolean[numNodes + 1];
        int sum = 0;

        Connection start = new Connection();
        start.nodeNumber = 1;
        start.weight = 0;

        priorityQueue.add(start);

        while (priorityQueue.size() > 0) {
            Connection currentConnection = priorityQueue.remove();
            int currentNode = currentConnection.nodeNumber;
            int currentWeight = currentConnection.weight;

            if (! visited[currentNode]) {
                visited[currentNode] = true;
                sum += currentWeight;

                for (Connection nextConnection : adjacencyList[currentNode]) {
                    priorityQueue.add(nextConnection);
                }
            }
        }

        pw.println(sum);
        pw.close();
    }
}
