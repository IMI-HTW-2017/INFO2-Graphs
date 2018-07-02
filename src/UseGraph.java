public class UseGraph {

    private static Graph<String> graph;

    public static void main(String[] args) {

        // new Graph
        graph = new Graph<String>();

        String[] vertices = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int numberOfEdges = 30;

        createRandomGraph(vertices, numberOfEdges);
        printGraph();
    }

    private static void createRandomGraph(String[] vertices, int numberOfEdges) {

        // Add Vertices from the array
        for(int i=0; i<vertices.length; i++) {
            graph.add(vertices[i]);
        }

        // Add edges
        for(int i=1; i<=numberOfEdges; i++) {
            String from = vertices[randomNumber()];
            String to = vertices[randomNumber()];
            int weight = randomNumber();

            graph.addConnection(from, to, weight);
        }
    }

    private static int randomNumber() {
        // Generates random number between 1 and 25
        return (int)(Math.random() * 25) + 1;
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

}