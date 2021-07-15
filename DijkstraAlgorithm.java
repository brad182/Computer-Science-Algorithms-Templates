import java.io.*;
import java.util.*;

// fast Dijkstra's using priority queues: O(E*log(V))
public class DijkstraAlgorithm {

    static final int infinity = 2000000000;

    static class Connection {
        int nodeNumber;
        int weight;
    }

    static class comparator implements Comparator<Connection> {
        public int compare (Connection one, Connection two) {
            if (one.weight < two.weight) {
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
        //int sourceNode = Integer.parseInt(inputLine.nextToken());
        int sourceNode = 1;

        List<Connection>[] adjacencyList = new ArrayList[numNodes + 1];
        for (int x = 0; x < adjacencyList.length; x++) {
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

        Connection startConnection = new Connection();
        startConnection.nodeNumber = sourceNode;
        startConnection.weight = 0;

        PriorityQueue<Connection> priorityQueue = new PriorityQueue<>(new comparator());
        priorityQueue.add(startConnection);
        boolean[] visited = new boolean[numNodes + 1];
        int[] distance = new int[numNodes + 1];
        Arrays.fill(distance, infinity);
        distance[sourceNode] = 0;

        while (priorityQueue.size() > 0) {
            Connection currentConnection = priorityQueue.remove();
            int currentNode = currentConnection.nodeNumber;
            // int currentWeight = currentConnection.weight;

            if (! visited[currentNode]) {
                visited[currentNode] = true;
                for (Connection nextConnection : adjacencyList[currentNode]) {
                    distance[nextConnection.nodeNumber] = Math.min(distance[nextConnection.nodeNumber], distance[currentNode] + nextConnection.weight);

                    Connection newConnection = new Connection();  // new connection object with updated distance
                    newConnection.nodeNumber = nextConnection.nodeNumber;
                    newConnection.weight = distance[nextConnection.nodeNumber];
                    priorityQueue.add(newConnection);
                }
            }
        }

        for (int x = 1; x <= numNodes; x++) {
            if (distance[x] == infinity) {
                pw.println(-1);
            }
            else {
                pw.println(distance[x]);
            }
        }
        pw.close();
    }
}
