/***
 * Find the longest path in a Directed Acyclic Graph:
 *
 * Choose a node from the Graph as start node,
 * and find the longest path and print the value.
 *
 * What is a Directed Acyclic Graph?
 * See here: https://en.wikipedia.org/wiki/Directed_acyclic_graph
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Graph {

    private int V;
    private List<List<AdjListNode>> adj;

    class AdjListNode {
        private int v, weight;

        AdjListNode(int _v, int _weight) {
            v = _v;
            weight = _weight;
        }

        int getV() {
            return v;
        }

        int getWeight() {
            return weight;
        }
    }

    private Graph(int _V) {
        V = _V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    private void addEdge(int u, int v, int weight) {
        AdjListNode node = new AdjListNode(v, weight);
        if (adj.get(u) == null) {
            List<AdjListNode> l = new ArrayList<>();
            l.add(node);
            adj.set(u, l);
        } else {
            adj.get(u).add(node);
        }
    }

    private void topologicalSortUtil(int v, List<Boolean> visited, Stack<Integer> stack) {
        for (int i = 0; i < adj.get(v).size(); i++) {
            AdjListNode node = adj.get(v).get(i);
            if (!visited.get(node.getV())) {
                topologicalSortUtil(node.getV(), visited, stack);
            }
        }

        stack.push(v);
    }

    private void longestPath(int s) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> dist = new ArrayList<>();
        List<Boolean> visited = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            visited.add(false);
        }

        for (int i = 0; i < V; i++) {
            if (!visited.get(i)) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        for (int i = 0; i < V; i++) {
            dist.add(-1);
        }

        dist.set(s, 0);

        while (!stack.empty()) {
            int u = stack.pop();

            if (dist.get(u) != -1) {
                for (int j = 0; j < adj.get(u).size(); j++) {
                    int v = adj.get(u).get(j).getV();
                    int weight = adj.get(u).get(j).getWeight();
                    if (dist.get(v) < dist.get(u) + weight) {
                        dist.set(v, dist.get(u) + weight);
                    }
                }
            }
        }

        System.out.println(Collections.max(dist));
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 5, 1);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);

        int s = 1;
        System.out.println("Following are longest distances from source vertex " + s);
        g.longestPath(s);
    }
}
