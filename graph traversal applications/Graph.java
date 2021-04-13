/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.*;

/**
 * This class has methods which make the different paths with DFS, BFS, A*
 * and Dijkstra. The data structure used is hashMap. There are other methods
 * which help the DFS, BFS and stuff like addEdge, HValue and others.
 * Some are references from zybooks
 *
 * @author Hetsvi
 * @since December 1 2018
 */
public class Graph {

    // TODO: define a data structure to store all the vertices with fast access
    private HashMap<String, Vertex> vertex; // declare hashmap

    /**
     * Constructor for Graph
     */
    public Graph() {

        // TODO
        vertex = new HashMap<>();     // new hashMap

    }

    /**
     * Adds a vertex to the graph. Throws IllegalArgumentException if given vertex
     * already exist in the graph.
     *
     * @param v vertex to be added to the graph
     * @throws IllegalArgumentException if two vertices with the same name are added.
     */
    public void addVertex(Vertex v) throws IllegalArgumentException {

        // TODO
        if(vertex.containsValue(v)){      // checks if it has
            throw new IllegalArgumentException();
        }

        vertex.put(v.getName(), v);      // adds

    }

    /**
     * Gets a collection of all the vertices in the graph
     *
     * @return collection of all the vertices in the graph
     */
    public Collection<Vertex> getVertices() {

        // TODO

        return vertex.values();      //values of all vertices
    }

    /**
     * Gets the vertex object with the given name
     *
     * @param name name of the vertex object requested
     * @return vertex object associated with the name
     */
    public Vertex getVertex(String name) {

        // TODO

        return vertex.get(name);       // returns the name
    }

    /**
     * Adds a directed edge from vertex u to vertex v, Throws IllegalArgumentException if one of
     * the vertex does not exist
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight weight of the edge between vertex u and v
     * @throws IllegalArgumentException if one of the vertex does not exist
     */
    public void addEdge(String nameU, String nameV, Double weight)
            throws IllegalArgumentException {

        // TODO
        if(!vertex.containsKey(nameU)){                  // checks nameU
            throw new IllegalArgumentException();
        }

        if(!vertex.containsKey(nameV)){
            throw new IllegalArgumentException();
        }

        Edge edge = new Edge(getVertex(nameU), getVertex(nameV), weight); // makes edge
        getVertex(nameU).addEdge(edge); // adds the edge

    }

    /**
     * Adds an undirected edge between vertex u and vertex v by adding a directed
     * edge from u to v, then a directed edge from v to u
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight  weight of the edge between vertex u and v
     */
    public void addUndirectedEdge(String nameU, String nameV, double weight) {

        // TODO
        addEdge(nameU, nameV, weight);   // uses addEdge method to add edge
        addEdge(nameV, nameU, weight);   // uses addEdge method to add edge

    }

    /**
     * Computes the euclidean distance between two points as described by their
     * coordinates
     *
     * @param ux (double) x coordinate of point u
     * @param uy (double) y coordinate of point u
     * @param vx (double) x coordinate of point v
     * @param vy (double) y coordinate of point v
     * @return (double) distance between the two points
     */
    public double computeEuclideanDistance(double ux, double uy, double vx, double vy) {

        // TODO
        int square = 2;
        // computes the eculidean distance
        return Math.sqrt(Math.pow((ux - vx), square) + Math.pow((uy-vy), square));
    }

    /**
     * Calculates the euclidean distance for all edges in the map using the
     * computeEuclideanCost method.
     */
    public void computeAllEuclideanDistances() {

        // TODO
        for(String key: vertex.keySet()) {   //goes over the vertex set
            for (int i = 0; i < getVertex(key).getListEdge().size(); i++) { // gets the edge
                 getVertex(key).getListEdge().get(i).setDistance(computeEuclideanDistance(
                        getVertex(key).getListEdge().get(i).getSource().getX(),
                        getVertex(key).getListEdge().get(i).getSource().getY(),
                        getVertex(key).getListEdge().get(i).getTarget().getX(),
                        getVertex(key).getListEdge().get(i).getTarget().getY()));
                 // calls computeEuclideanDistance to get the distance
            }
        }

    }

    /**
     * Helper method to reset all the vertices before doing graph traversal algorithms
     */
    private void resetAllVertices() {

        // TODO
        for(Vertex v : getVertices()){
            v.setPassed(false);      // resets
        }
    }

    /**
     * Find the path from vertex with name s to vertex with name t, using DFS
     * Zybooks reference
     * @param s the name of the starting vertex
     * @param t the name of the targeting vertex
     */
    public void DFS(String s, String t) {

        // TODO
        Stack<Vertex> stack = new Stack<>();
        stack.push(getVertex(s));

        resetAllVertices();

        while(!stack.isEmpty()){      // checks if stack empty
            Vertex curr = stack.pop();
            if(curr == getVertex(t)){    // if found then breaks out of the loop
                break;
            }

            if(!curr.getPassed()){
                curr.setPassed(true);
                for(int i =0; i<curr.getListEdge().size(); i++){    // over the listEdge
                    Vertex adj = curr.getListEdge().get(i).getTarget();  // gets adj
                    if(!adj.getPassed()){
                        adj.setPrevious(curr.getListEdge().get(i));  // sets adj
                        stack.push(adj);   // adds to stack
                    }
                }
            }
        }


    }

    /**
     * Find the path from vertex with name s to vertex with name t, using BFS
     * Zybooks reference
     * @param s the name of the starting vertex
     * @param t the name of the targeting vertex
     */
    public void BFS(String s, String t) {

        // TODO
        LinkedList<Vertex> queue = new LinkedList<>();
        resetAllVertices();
        queue.add(getVertex(s));

        while(queue.size() != 0){    // loops till queue isn't empty
            Vertex curr = queue.poll();
            if(curr == getVertex(t)){   //breaks out of the loop
                break;
            }

            curr.setPassed(true);
            for(int i =0; i< curr.getListEdge().size(); i++){  // loops edges
                Vertex adj = curr.getListEdge().get(i).getTarget();  // sets vertex
                if(!adj.getPassed()){
                    queue.add(adj);        // adds
                    adj.setPassed(true);
                    adj.setPrevious(curr.getListEdge().get(i));
                }
            }
        }

    }

    /**
     * Helper class for Dijkstra and A*, used in priority queue
     */
    private class CostVertex implements Comparable<CostVertex> {
        double cost;
        Vertex vertex;

        public CostVertex(double cost, Vertex vertex) {   // constructor for private class
            this.cost = cost;
            this.vertex = vertex;
        }

        public int compareTo(CostVertex o) {    // compares
            return Double.compare(cost, o.cost);
        }
    }

    /**
     * Find the shortest path from vertex with name s to vertex with name t, using Dijkstra
     *
     * @param s the name of starting vertex
     * @param t the name of targeting vertex
     */
    public void Dijkstra(String s, String t) {
        PriorityQueue<CostVertex> queue = new PriorityQueue<>();

        // TODO
        for(Vertex curr : getVertices()){      //gets all vertices and sets values
            curr.setPassed(false);
            curr.setCost(Double.MAX_VALUE);
            curr.setPrevious(null);
        }

        queue.add(new CostVertex(0, getVertex(s)));  // adds new cost vertex
        getVertex(s).setCost(0);
        getVertex(s).setPassed(true);

        while(!queue.isEmpty()){      // loops till queue isn't empty
            CostVertex curr = queue.poll();
            curr.vertex.setPassed(true);

            if(curr.vertex == getVertex(t)){    //  breaks out of the loop
                break;
            }

            for(int i =0; i< curr.vertex.getListEdge().size(); i++){ // goes though curr
                                                                     // list of edge
                Edge adj = curr.vertex.getListEdge().get(i);
                double edgeWeight = curr.vertex.getListEdge().get(i).getDistance();
                double alternateWeight = curr.cost + edgeWeight;

                if(alternateWeight < adj.getTarget().getCost() &&
                        !adj.getTarget().getPassed()){
                    adj.getTarget().setCost(alternateWeight);     // sets cost vertex of target
                    adj.getTarget().setPrevious(adj);
                    // adds to queue
                    queue.add(new CostVertex(adj.getTarget().getCost(),
                            adj.getTarget()));
                }
            }
        }
    }

    /**
     * Helper method to calculate the h value in A*
     *
     * @param cur the current vertex being explored
     * @param goal the goal vertex to reach
     * @return the h value of cur and goal vertices
     */
    private double hValue(String cur, String goal) {

        // TODO
        Vertex curr = getVertex(cur);
        Vertex aim = getVertex(goal);
        int square = 2;
        return (Math.sqrt(Math.pow((curr.getX() - aim.getX()), square) +
                Math.pow((curr.getY() - aim.getY()), square))); // the
                                                 // euclidean distance using curr and aim
    }

    /**
     * Find the path from vertex with name s to vertex with name t, using A*
     *
     * @param s the name of starting vertex
     * @param t the name of targeting vertex
     */
    public void AStar(String s, String t) {

        // TODO
        PriorityQueue<CostVertex> queue = new PriorityQueue<>();

        for(Vertex curr : getVertices()){    //goes through the vertices and sets value
            curr.setPassed(false);
            curr.setCost(Double.MAX_VALUE);
            curr.setPrevious(null);
        }

        queue.add(new CostVertex(0, getVertex(s)));  // adds
        getVertex(s).setCost(0);
        getVertex(s).setPassed(true);

        while(!queue.isEmpty()){      // loops
            CostVertex curr = queue.poll();
            curr.vertex.setPassed(true);

            if(curr.vertex == getVertex(t)){     // breaks out of the loop
                break;
            }
            for(int i =0; i< curr.vertex.getListEdge().size(); i++){   // goes over the edges
                Edge adj = curr.vertex.getListEdge().get(i);
                double edgeWeight = adj.getDistance();  // gets distance
                double alternateWeight = curr.vertex.getCost() + edgeWeight;  // other weight

                if(alternateWeight < adj.getTarget().getCost() &&
                        !adj.getTarget().getPassed()){
                    adj.getTarget().setCost(alternateWeight);  // sets adj target cost to alternate
                    adj.getTarget().setPrevious(adj);
                    // adds and uses the hValue to add to cost
                    queue.add(new CostVertex(adj.getTarget().getCost() +
                            hValue(adj.getTarget().getName(), t), adj.getTarget()));
                }
            }
        }

    }

    /**
     * Returns a list of edges for a path from city s to city t.
     *
     * @param s starting city name
     * @param t ending city name
     * @return list of edges from s to t
     */
    public List<Edge> getPath(String s, String t) {

        // TODO
        Vertex start = getVertex(s);
        Vertex end = getVertex(t);
        LinkedList<Edge> pathList = new LinkedList<>();   // pathList is linked list
        // goes from end till doesn't equal start and keeps getting the before one
        for(Vertex v = end; !v.equals(start); v = v.getPrevious().getSource()){
            pathList.add(v.getPrevious());
        }
        Collections.reverse(pathList);    // reverse the path

        return pathList;
    }

}
