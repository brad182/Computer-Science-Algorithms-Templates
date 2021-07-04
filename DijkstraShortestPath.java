import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

public class DijkstraShortestPath {
    static final int maxvalue = 2000000000;

    static class Connection {
        int nodeNumber;
        int weight;
    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer inputLine = new StringTokenizer(br.readLine());
        int numNodes = Integer.parseInt(inputLine.nextToken());
        int numEdges = Integer.parseInt(inputLine.nextToken());
        int startNode = Integer.parseInt(inputLine.nextToken());

        List<Connection>[] adjacencyList = new ArrayList[numNodes + 1];
        for (int x = 0; x < adjacencyList.length; x++) {  // initialize adjacency list
            adjacencyList[x] = new ArrayList<>();
        }

        for (int x = 0; x < numEdges; x++) {
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

        int[] distance = new int[numNodes + 1];
        Arrays.fill(distance, maxvalue);

        boolean[] visited = new boolean[numNodes + 1];
        distance[startNode] = 0;

        for (int x = 1; x <= numNodes; x++) {
            int lowest = maxvalue;
            int lowestNode = -1;

            for (int i = 1; i <= numNodes; i++) {  // find the lowest distance out of all of the unvisited nodes
                if (! visited[i] && distance[i] < lowest) {
                    lowest = distance[i];
                    lowestNode = i;
                }
            }

            if (lowestNode != -1) { // if it has at least one unvisited neighbor
                visited[lowestNode] = true;
                for (Connection nextNode : adjacencyList[lowestNode]) {
                    if (!visited[nextNode.nodeNumber]) {
                        distance[nextNode.nodeNumber] = Math.min(distance[nextNode.nodeNumber], distance[lowestNode] + nextNode.weight);
                    }
                }
            }
        }

        for (int x = 1; x <= numNodes; x++) {  // output the minimum distance of each node from the original
            if (distance[x] == maxvalue) {
                pw.println(-1);
            }
            else {
                pw.println(distance[x]);
            }
        }

        pw.close();
    }
}
