package ru.vsu.cs.course1.graph;

import java.util.*;
public class MyProgram {
    public static List<Integer> method (Graph graph) {
        List<Integer> chain = new ArrayList<>();
        int maxLength = -1;
        String maxPath = "";
        for (int i = 0; i < graph.vertexCount(); i++) {
            Map<Integer,Info> map = new TreeMap<>();
            map.put(i, new Info(0, i + " "));
            Data data = new Data(map, -1, "");
            dfsRecursion(graph, i, new boolean[graph.vertexCount()], data);
            if (data.getMaxLength() > maxLength) {
                maxLength = data.getMaxLength();
                maxPath = data.getMaxPath();
            }
        }
        for (int i = 0; i < graph.vertexCount(); i++) {
            if (maxPath.contains(" " + i + " ")) {
                chain.add(i);
            }
        }
        return chain;
    }
    public static void dfsRecursion(Graph graph, int from, boolean[] visited, Data data) {
        visited[from] = true;
        for (Integer element : graph.adjacencies(from)) {
            if (!visited[element] && !hasVisitedNeighbors(graph, element, from, visited)) {
                data.getMap().put(element, new Info(data.getMap().get(from).getLength() + 1, makePath(Integer.toString(element), data.getMap().get(from).getPath())));
                if (data.getMap().get(element).getLength() > data.getMaxLength()) {
                    data.setMaxLength(data.getMap().get(element).getLength());
                    data.setMaxPath(data.getMap().get(element).getPath());
                }
                dfsRecursion(graph,element,visited, data);
            }
        }
        visited[from] = false;
    }
    public static boolean hasVisitedNeighbors (Graph graph, int value, int from, boolean[] visited) {
        for (Integer integer : graph.adjacencies(value)) {
            if (visited[integer] && from != integer) {
                return true;
            }
        }
        return false;
    }
    public static String makePath (String value, String path) {
        return " " + value + " -> " + path;
    }
}