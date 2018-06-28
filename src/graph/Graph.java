package graph;

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

        if (vertexFrom == null || vertexTo == null)
            return;
        else
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

    public String getShortestPath(T from, T to) {
        Queue<Vertex<T>> queue = new LinkedList<>();
        Map<Vertex<T>, Vertex<T>> predecessor = new HashMap<>();

        Vertex<T> vertexFrom = getVertexFromData(from);
        Vertex<T> vertexTo = getVertexFromData(to);

        for (Edge<T> edge : vertexFrom.getEdges()) {
            queue.offer(edge.getTo());
            predecessor.put(edge.getTo(), vertexFrom);
        }

        while (queue.peek() != null) {
            Vertex<T> vertex = queue.poll();

            if (vertex.equals(vertexTo)) {
                Stack<String> path = new Stack<>();

                while (vertex != null) {
                    path.push(vertex.getData().toString());
                    vertex = predecessor.get(vertex);
                }

                StringBuilder sb = new StringBuilder(path.pop());

                while (!path.empty()) {
                    sb.append(" -> ").append(path.pop());
                }

                return sb.toString();
            }

            for (Edge<T> edge : vertex.getEdges()) {
                if (!predecessor.containsKey(edge.getTo())) {
                    queue.offer(edge.getTo());
                    predecessor.put(edge.getTo(), vertex);
                }
            }
        }

        return "No path found!";
    }

    public String getCheapestPath(T from, T to) {
        PriorityQueue<QueuedVertex<T>> queue = new PriorityQueue<>();
        Map<Vertex<T>, Vertex<T>> predecessor = new HashMap<>();

        Vertex<T> vertexFrom = getVertexFromData(from);
        Vertex<T> vertexTo = getVertexFromData(to);

        for (Edge<T> edge : vertexFrom.getEdges()) {
            queue.offer(new QueuedVertex<>(edge.getTo(), edge.getWeight()));
            predecessor.put(edge.getTo(), vertexFrom);
        }

        while (queue.peek() != null) {
            QueuedVertex<T> firstInQueue = queue.poll();
            Vertex<T> vertex = firstInQueue.getVertex();
            int priority = firstInQueue.getPriority();

            if (vertex.equals(vertexTo)) {
                queue.stream().filter(v -> v.getPriority() >= priority);
            }

            for (Edge<T> edge : vertex.getEdges()) {
                queue.offer(new QueuedVertex<>(edge.getTo(), priority + edge.getWeight()));
                predecessor.put(edge.getTo(), vertex);
            }
        }

        if (predecessor.get(vertexTo) == null)
            return "No path found!";

        Stack<String> path = new Stack<>();

        while (vertexTo != null) {
            path.push(vertexTo.getData().toString());
            vertexTo = predecessor.get(vertexTo);
        }

        StringBuilder sb = new StringBuilder(path.pop());

        while (!path.empty()) {
            sb.append(" -> ").append(path.pop());
        }

        return sb.toString();
    }

    private Vertex<T> getVertexFromData(T data) {
        for (Vertex<T> vertex : vertices) {
            if (vertex.getData().equals(data))
                return vertex;
        }

        return null;
    }

}
