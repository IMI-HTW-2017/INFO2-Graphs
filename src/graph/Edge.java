package graph;

public class Edge<T> implements Comparable {
    private Vertex to;
    private int weight;

    Edge(Vertex<T> to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public Vertex<T> getTo() {
        return to;
    }

    public int getWeight() {
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
