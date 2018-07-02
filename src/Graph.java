import java.util.*;

public class Graph<T> {

    private List<Vertex<T>> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    }

    public boolean contains(T element) {
        for (Vertex vertex : vertices) {
            if (vertex.getData().equals(element))
                return true;
        }

        return false;
    }

    public void add(T element) {
        if (contains(element))
            throw new RuntimeException("Vertex already exists!");

        vertices.add(new Vertex<T>(element));
    }

    public void addConnection(T from, T to, int weight) {
        Vertex<T> vertexFrom = getVertexFromData(from);
        Vertex<T> vertexTo = getVertexFromData(to);

        if (vertexFrom != null && vertexTo != null)
            vertexFrom.getEdges().add(new Edge<>(vertexTo, weight));
    }

    public double getWeightOfEdge(T from, T to) {
        Vertex<T> vertexFrom = getVertexFromData(from);
        Vertex<T> vertexTo = getVertexFromData(to);

        if (vertexFrom == null || vertexTo == null)
            return Integer.MAX_VALUE;
        else
            return vertexFrom.getEdgeTo(vertexTo).getWeight();
    }

    public List<Edge<T>> getEdges(T element) {
        Vertex<T> vertex = getVertexFromData(element);

        if (vertex == null)
            return null;
        else
            return vertex.getEdges();
    }

    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public List<Vertex<T>> getShortestPath(T from, T to) {
        Queue<Vertex<T>> queue = new LinkedList<>();
        Map<Vertex<T>, Vertex<T>> predecessor = new HashMap<>();
        List<Vertex<T>> path = new ArrayList<>();

        Vertex<T> vertexFrom = getVertexFromData(from);
        Vertex<T> vertexTo = getVertexFromData(to);

        queue.offer(vertexFrom);

        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.poll();

            if (vertex.equals(vertexTo)) {

                while (vertexTo != vertexFrom) {
                    path.add(vertexTo);
                    vertexTo = predecessor.get(vertexTo);
                }

                path.add(vertexTo);
                Collections.reverse(path);

                return path;
            }

            for (Edge<T> edge : vertex.getEdges()) {
                if (!predecessor.containsKey(edge.getTo())) {
                    queue.offer(edge.getTo());
                    predecessor.put(edge.getTo(), vertex);
                }
            }
        }

        return path;
    }

    public List<Vertex<T>> getCheapestPath(T from, T to) {
        List<Vertex<T>> queue = new ArrayList<>();
        Map<Vertex<T>, Vertex<T>> predecessor = new HashMap<>();
        Map<Vertex<T>, Double> minDistance = new HashMap<>();
        List<Vertex<T>> pathFound = new ArrayList<>();
        List<Vertex<T>> path = new ArrayList<>();

        Vertex<T> vertexFrom = getVertexFromData(from);
        Vertex<T> vertexTo = getVertexFromData(to);

        vertices.stream().forEach((v) -> minDistance.put(v, Double.POSITIVE_INFINITY));

        minDistance.put(vertexFrom, 0.0);
        queue.add(vertexFrom);

        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.get(0);
            for (Vertex<T> v : queue) {
                if (minDistance.get(v) < minDistance.get(vertex)) {
                    vertex = v;
                }
            }
            queue.remove(vertex);

            for (Edge<T> edge : vertex.getEdges()) {
                Vertex<T> neighbor = edge.getTo();
                double distanceThroughVertex = minDistance.get(vertex) + edge.getWeight();

                if (distanceThroughVertex < minDistance.get(neighbor)) {
                    queue.remove(neighbor);
                    minDistance.put(neighbor, distanceThroughVertex);
                    pathFound.add(neighbor);
                    predecessor.put(neighbor, vertex);
                    queue.add(neighbor);
                }
            }
        }

        if (pathFound.contains(vertexTo)) {
            while (vertexTo != vertexFrom) {
                path.add(vertexTo);
                vertexTo = predecessor.get(vertexTo);
            }

            path.add(vertexTo);
            Collections.reverse(path);
        }
        return path;
    }

    public Vertex<T> getVertexFromData(T data) {
        for (Vertex<T> vertex : vertices) {
            if (vertex.getData().equals(data))
                return vertex;
        }

        return null;
    }

    class Vertex<T> {

        private T data;
        private List<Edge<T>> edges;

        Vertex(T data) {
            this.data = data;
            edges = new ArrayList<>();
        }

        Edge<T> getEdgeTo(Vertex<T> vertex) {
            for (Edge<T> edge : edges) {
                if (edge.getTo().equals(vertex))
                    return edge;
            }

            return null;
        }

        T getData() {
            return data;
        }

        List<Edge<T>> getEdges() {
            return edges;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Vertex)
                return data.equals(((Vertex) obj).data);
            else
                return false;
        }
    }

    class Edge<T> {
        private Vertex to;
        private double weight;

        Edge(Vertex<T> to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        Vertex<T> getTo() {
            return to;
        }

        double getWeight() {
            return weight;
        }
    }

}