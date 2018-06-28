package graph;

public class QueuedVertex<T> implements Comparable {

    private Vertex<T> vertex;
    private int priority;

    public QueuedVertex(Vertex<T> vertex, int priority) {
        this.vertex = vertex;
        this.priority = priority;
    }

    public Vertex<T> getVertex() {
        return vertex;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof QueuedVertex) {
            if (priority < ((QueuedVertex) o).priority) {
                return -1;
            } else if (priority > ((QueuedVertex) o).priority) {
                return 1;
            } else {
                return 0;
            }
        }
        return 1;
    }
}
