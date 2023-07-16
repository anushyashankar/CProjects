import java.util.*;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Anushya Shankar
 * @userid ashankar92
 * @GTID 903826158
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Input cannot be null, or start must exist in graph.");
        }

        Queue<Vertex<T>> queue = new LinkedList<>();
        List<Vertex<T>> visited = new ArrayList<>(graph.getVertices().size());

        queue.add(start);

        while(!queue.isEmpty()) {
            Vertex<T> temp = queue.remove();
            if (!visited.contains(temp)) {
                visited.add(temp);
                for (VertexDistance<T> vdist : graph.getAdjList().get(temp)) {
                    if (!visited.contains(vdist.getVertex())) {
                        queue.add(vdist.getVertex());
                    }
                }
            }
        }

        return visited;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Input cannot be null, or start must exist in graph.");
        }

        List<Vertex<T>> visited = new ArrayList<>(graph.getVertices().size());

        rDfs(start, graph, visited);
        return visited;
    }

    /**
     * Recursive helper method for DFS.
     * @param vertex the current vertex
     * @param graph the graph
     * @param visited the list of vertices visited
     * @param <T> generic type of data
     */
    private static <T> void rDfs(Vertex<T> vertex, Graph<T> graph, List<Vertex<T>> visited) {
        visited.add(vertex);

        for (VertexDistance<T> vdist : graph.getAdjList().get(vertex)) {
            if (!visited.contains(vdist.getVertex())) {
                rDfs(vdist.getVertex(), graph, visited);
            }
        }
    }
    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Input cannot be null, or start must exist in graph.");
        }

        Set<Vertex<T>> visited = new HashSet<>();
        PriorityQueue<VertexDistance<T>> queue = new PriorityQueue<>();
        Map<Vertex<T>, Integer> distMap = new HashMap<>();

        for (Vertex<T> v : graph.getVertices()) {
            if (v.equals(start)) {
                distMap.put(v, 0);
            } else {
                distMap.put(v, Integer.MAX_VALUE);
            }
        }

        queue.add(new VertexDistance<>(start, 0));
        while(!queue.isEmpty() && !(visited.size() == graph.getVertices().size())) {
            VertexDistance<T> temp = queue.remove();
            if (!visited.contains(temp.getVertex())) {
                distMap.put(temp.getVertex(), temp.getDistance());
                visited.add(temp.getVertex());

                for (VertexDistance<T> adj : graph.getAdjList().get(temp.getVertex())) {
                    if (!visited.contains(adj.getVertex())) {
                        queue.add(new VertexDistance<T>(adj.getVertex(), adj.getDistance() + temp.getDistance()));
                    }
                }
            }
        }

        return distMap;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use PriorityQueue, java.util.Set, and any class that 
     * implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Input cannot be null, or start must exist in graph.");
        }

        PriorityQueue<Edge<T>> queue = new PriorityQueue<>();
        Set<Vertex<T>> visited = new HashSet<>();
        Set<Edge<T>> mst = new HashSet<>();

        visited.add(start);
        for (Edge<T> e : graph.getEdges()) {
            if (e.getU().equals(start)) {
                queue.add(e);
            }
        }

        while(!queue.isEmpty() && !(visited.size() == graph.getVertices().size())) {
            Edge<T> temp = queue.remove();
            if (!visited.contains(temp.getV()) || !visited.contains(temp.getU())) {
                mst.add(temp);
                mst.add(new Edge<>(temp.getV(), temp.getU(), temp.getWeight()));
                visited.add(temp.getV());

                for (Edge<T> edge : graph.getEdges()) {
                    if (edge.getU().equals(temp.getV())) {
                        queue.add(edge);
                    }
                }
            }
        }

        return (mst.size() == (graph.getVertices().size() - 1) * 2 ? mst : null);
    }
}