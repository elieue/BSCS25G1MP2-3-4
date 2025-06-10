import java.util.*;

public class ShortestPath {

    // Represents a connection from one vertex to another with a certain cost
    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Represents a node in the priority queue with its current shortest distance from the source
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance); // Prioritize by shortest distance
        }
    }

    // Dijkstra’s Algorithm to find the shortest paths from the source vertex
    public static int[] dijkstra(List<List<Edge>> graph, int numVertices, int source, int[] prev) {
        if (source < 0 || source >= numVertices) {
            System.err.println("Error: Source vertex is out of bounds. Cannot run Dijkstra.");
            return null;
        }

        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE); // Start with "infinity" distances
        distances[source] = 0; // Distance to itself is 0

        Arrays.fill(prev, -1); // No previous node at the beginning

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(source, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int currentVertex = currentNode.vertex;

            if (currentNode.distance > distances[currentVertex]) {
                continue; // A shorter path has already been found
            }

            // Explore all neighbors of the current vertex
            for (Edge edge : graph.get(currentVertex)) {
                int neighbor = edge.destination;
                int weight = edge.weight;

                int newDistance = distances[currentVertex] + weight;
                if (newDistance < distances[neighbor]) {
                    distances[neighbor] = newDistance;
                    prev[neighbor] = currentVertex; // Track the path
                    queue.add(new Node(neighbor, newDistance));
                }
            }
        }

        return distances;
    }

    // Reconstruct the shortest path from source to a given target
    public static List<Integer> getPath(int[] prev, int target) {
        List<Integer> path = new ArrayList<>();
        for (int at = target; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path); // Reverse to get the correct order
        return path;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numVertices = 0;
        boolean validInput = false;
        while (!validInput) {
             System.out.println("==== Dijkstras Algorithm to find the shortest paths ===");
            System.out.print("How many vertices are in the graph? ");
            if (scanner.hasNextInt()) { // Check if the next is int
                numVertices = scanner.nextInt();
                if (numVertices > 0) {
                    validInput = true; 
                } else {
                    System.out.println("Error: The number of vertices must be a positive integer. Please try again.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter a whole number. Please try again.");
                scanner.next(); 
            }
        }

        // Initialize the graph as an adjacency list
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        int numEdges = 0;
        validInput = false;
        while (!validInput) {
           
            System.out.print("How many edges are there? ");
            if (scanner.hasNextInt()) {
                numEdges = scanner.nextInt();
                if (numEdges >= 0) { 
                    validInput = true;
                } else {
                    System.out.println("Error: The number of edges cannot be negative. Please try again.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter a whole number. Please try again.");
                scanner.next(); 
            }
        }

        System.out.println("Please enter each edge in the format: source destination weight");

        for (int i = 0; i < numEdges; i++) {
            int from = -1, to = -1, weight = -1;
            boolean edgeInputValid = false;

            while (!edgeInputValid) {
                System.out.print("Edge " + (i + 1) + ": ");
                // Check if there are enough integers for source, destination, and weight
                if (scanner.hasNextInt()) {
                    from = scanner.nextInt();
                    if (scanner.hasNextInt()) {
                        to = scanner.nextInt();
                        if (scanner.hasNextInt()) {
                            weight = scanner.nextInt();

                            // Validatation of the values
                            if (from < 0 || from >= numVertices) {
                                System.out.println("Error: Source vertex " + from + " is out of bounds (0 to " + (numVertices - 1) + "). Please try again for Edge " + (i + 1) + ".");
                            } else if (to < 0 || to >= numVertices) {
                                System.out.println("Error: Destination vertex " + to + " is out of bounds (0 to " + (numVertices - 1) + "). Please try again for Edge " + (i + 1) + ".");
                            } else if (weight < 0) {
                                System.out.println("Error: Edge weight cannot be negative. Please try again for Edge " + (i + 1) + ".");
                            } else {
                                edgeInputValid = true; // When all values are valid
                            }
                        } else {
                            System.out.println("Error: Missing weight. Please enter source destination weight. Please try again for Edge " + (i + 1) + ".");
                            scanner.next(); // Consume the non-int token
                        }
                    } else {
                        System.out.println("Error: Missing destination. Please enter source destination weight. Please try again for Edge " + (i + 1) + ".");
                        scanner.next(); // Consume the non-int token
                    }
                } else {
                    System.out.println("Error: Invalid input. Please enter integers for source, destination, and weight. Please try again for Edge " + (i + 1) + ".");
                    scanner.next(); // Consume the invalid input
                }
            }
            graph.get(from).add(new Edge(to, weight)); // Add the valid edge
        }

        int source = 0; // The source vertex is fixed at 0

        int[] prev = new int[numVertices]; // To track shortest path predecessors
        int[] shortestDistances = dijkstra(graph, numVertices, source, prev); // Run Dijkstra's

        if (shortestDistances != null) {
            System.out.println("\nShortest distances and paths from vertex " + source + ":");
            for (int target = 0; target < numVertices; target++) {
                if (shortestDistances[target] == Integer.MAX_VALUE) {
                    System.out.println("Vertex " + target + ": Not reachable from source.");
                } else {
                    List<Integer> path = getPath(prev, target);
                    System.out.print("Vertex " + target + ": Distance = " + shortestDistances[target] + " | Path: ");
                    for (int j = 0; j < path.size(); j++) {
                        System.out.print(path.get(j));
                        if (j < path.size() - 1) {
                            System.out.print(" -> ");
                        }
                    }
                    System.out.println();
                }
            }
        }

        scanner.close(); 
    }
}