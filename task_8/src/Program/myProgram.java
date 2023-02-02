package Program;

import java.util.LinkedList;
import java.util.Queue;

public class myProgram {
    public static boolean check(Graph<Integer> graph, Graph<Integer>.Node node, boolean[] color) {
        boolean[] visited = new boolean[graph.vertexCount()];
        Queue<Graph<Integer>.Node> queue = new LinkedList<>();
        queue.add(node);
        visited[Integer.parseInt(String.valueOf(node.value))] = true;
        color[Integer.parseInt(String.valueOf(node.value))] = true;
        while (queue.size() > 0) {
            Graph<Integer>.Node curr = queue.remove();
            for (Graph<Integer>.Neighbour neighbour : curr.neighbours) {
                if (!visited[Integer.parseInt(String.valueOf(neighbour.node.value))]) {
                    queue.add(neighbour.node);
                    visited[Integer.parseInt(String.valueOf(neighbour.node.value))] = true;
                    color[Integer.parseInt(String.valueOf(neighbour.node.value))] = !color[Integer.parseInt(String.valueOf(curr.value))];
                }
                if (color[Integer.parseInt(String.valueOf(curr.value))] == color[Integer.parseInt(String.valueOf(neighbour.node.value))]) {
                    return false;
                }
            }
        }
        int vertex = 0;
        for (boolean b : color) {
            if (b) {
                vertex++;
            }
        }
       return (color.length - vertex) * vertex == graph.edgeCount;
    }
}
