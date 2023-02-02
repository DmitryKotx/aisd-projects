package Program;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Graph<T> {
    public Integer edgeCount = 0;
    public Integer vertexCount = 0;
    public class Neighbour {
        public Node node;
        public Neighbour(Node node) {
            this.node = node;
        }
    }

    public class Node {
        public T value;

        public List<Neighbour> neighbours = new LinkedList<>();

        public Node(T value) {
            this.value = value;
        }
    }

    private final List<Node> nodes = new LinkedList<>();

    public void addNode(T value) {
        Node newNode = new Node(value);
        nodes.add(newNode);
        vertexCount++;
    }

    public void removeNode(int index) {
        Node removeNode = nodes.get(index);
        for (Neighbour neighbour: removeNode.neighbours) {
            neighbour.node.neighbours = neighbour.node.neighbours.stream().filter(n -> !n.node.equals(removeNode)).collect(Collectors.toList());
            edgeCount--;
        }
        nodes.remove(removeNode);
        vertexCount--;
    }

    public void connectNodes(int index1, int index2) {
        nodes.get(index1).neighbours.add(new Neighbour(nodes.get(index2)));
        nodes.get(index2).neighbours.add(new Neighbour(nodes.get(index1)));
        edgeCount++;
    }


    public void disconnect(int index1, int index2) {
        Node n1 = nodes.get(index1);
        Node n2 = nodes.get(index2);
        n1.neighbours.remove(n1.neighbours.stream().filter(n -> n.node.equals(n2)).findFirst().orElse(null));
        n2.neighbours.remove(n2.neighbours.stream().filter(n -> n.node.equals(n1)).findFirst().orElse(null));
        edgeCount--;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public int vertexCount() {
        return nodes.size();
    }
}