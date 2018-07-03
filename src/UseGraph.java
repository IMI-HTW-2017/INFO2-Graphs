import java.util.List;
import java.util.Random;

public class UseGraph {

    private static Graph<String> graph;
    private static Random random;

    public static void main(String[] args) {

        // new Graph
        graph = new Graph<>();
        random = new Random();

        String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int numberOfEdges = 100;

        createRandomGraph(vertices, numberOfEdges);
        printGraph();
        getRandomShortestPath(graph);
        getRandomCheapestPath(graph);
    }

    private static void createRandomGraph(String[] vertices, int numberOfEdges) {

        // Add Vertices from the array
        for (int i = 0; i < vertices.length; i++) {
            graph.add(vertices[i]);
        }

        // Add edges
        for (int i = 1; i <= numberOfEdges; i++) {
            String from = vertices[random.nextInt(vertices.length)];
            String to = vertices[random.nextInt(vertices.length)];
            int weight = random.nextInt(99) + 1;

            graph.addConnection(from, to, weight);
        }
    }

    private static void printGraph() {
        for (Graph.Vertex vertex : graph.getVertices()) {
            String node = (String) vertex.getData();

            System.out.println(node + ": ");

            for (Graph.Edge edge : graph.getEdges(node)) {
                System.out.println("\t-> " + edge.getTo().getData() + " : " + edge.getWeight());
            }

            System.out.println();
        }
    }

    private static void getRandomShortestPath(Graph<String> g) {
        String from = g.getVertices().get(random.nextInt(g.getVertices().size())).getData();
        String to = g.getVertices().get(random.nextInt(g.getVertices().size())).getData();

        System.out.println("Shortest path from " + from + " to " + to + ":");
        System.out.println(pathToString(graph.getShortestPath(from, to)));
    }

    private static void getRandomCheapestPath(Graph<String> g) {
        String from = g.getVertices().get(random.nextInt(g.getVertices().size())).getData();
        String to = g.getVertices().get(random.nextInt(g.getVertices().size())).getData();

        System.out.println("Cheapest path from " + from + " to " + to + ":");
        System.out.println(pathToString(graph.getCheapestPath(from, to)));
    }

    private static String pathToString(List<Graph<String>.Vertex<String>> path) {
        StringBuilder sb = new StringBuilder();
        for (Graph.Vertex v : path) {
            sb.append(v.getData()).append(" -> ");
        }
        return sb.toString().replaceAll(" -> $", "");
    }

}