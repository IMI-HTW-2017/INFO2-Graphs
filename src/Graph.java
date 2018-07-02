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

    public int getWeightOfEdge(T from, T to) {
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

    private Vertex<T> getVertexFromData(T data) {
        for (Vertex<T> vertex : vertices) {
            if (vertex.getData().equals(data))
                return vertex;
        }

        return null;
    }

    public class Vertex<T> {

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

        void addEdge(Edge<T> edge) {
            for (Edge<T> e : edges) {
                if (e.getTo().equals(edge.getTo())) {
                    throw new RuntimeException("Edge already exists!");
                }
            }
            edges.add(edge);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Vertex)
                return data.equals(((Vertex)obj).data);
            else
                return false;
        }
    }

    public class Edge<T> implements Comparable {
        private Vertex to;
        private int weight;

        Edge(Vertex<T> to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        Vertex<T> getTo() {
            return to;
        }

        int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Edge) {
                if (weight < ((Edge) o).weight) {
                    return -1;
                } else if (weight > ((Edge) o).weight) {
                    return 1;
                } else {
                    return 0;
                }
            }
            return 1;
        }
    }

}