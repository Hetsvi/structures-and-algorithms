/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.*;

/*
 * This class has methods that help make the graph. It has methods like
 * addVertex and getVertices and computes the distance etc. It has
 * another method which uses Kruskals algorithm to find the
 * minimum spanning tree in the path.
 *
 * @author hetsvi
 * @since 2018
 */
public class Graph {

    private HashMap<String, Vertex> vertices;    // hashmap
    private ArrayList<Edge> allUndirectedEdges;  // array list to keep track of edges
    private ArrayList<Edge> resultMST;           // array list for kruskal
    private boolean edgesGiven;                  // boolean

    /**
     * Constructor for Graph
     */
    public Graph(boolean edgesGiven) {
        
        // TODO
        vertices = new HashMap<>();
        allUndirectedEdges = new ArrayList<>();
        resultMST = new ArrayList<>();
        this.edgesGiven = edgesGiven;   // sets to edges given


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
        if(vertices.containsValue(v)){
            throw new IllegalArgumentException();
        }

        vertices.put(v.getName(), v);  // adds v to hashmap

    }

    /**
     * Gets a collection of all the vertices in the graph
     *
     * @return collection of all the vertices in the graph
     */
    public Collection<Vertex> getVertices() {

        // TODO

        return vertices.values();    // gets all the vertices
    }

    /**
     * Adds a directed edge from vertex u to vertex v, Throws IllegalArgumentException if one of
     * the vertex does not exist. If edgesGiven is false, directly return at first.
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight weight of the edge between vertex u and v
     * @throws IllegalArgumentException if one of the vertex does not exist
     */
    public void addEdge(String nameU, String nameV, Double weight) throws IllegalArgumentException {
        
        // TODO
        if(edgesGiven == false){
            System.out.println("Can't access");
            return;
        }

        if(!vertices.containsKey(nameU)){                  // checks nameU
            throw new IllegalArgumentException();
        }

        if(!vertices.containsKey(nameV)){                 // checks nameV
            throw new IllegalArgumentException();
        }

        Edge edge = new Edge(vertices.get(nameU), vertices.get(nameV), weight);
        vertices.get(nameU).addEdge(edge);     // connects an edge

    }

    /**
     * Adds an undirected edge between vertex u and vertex v by adding a directed
     * edge from u to v, then a directed edge from v to u. Then updates the allUndirectedEdges.
     * If edgesGiven is false, directly return at first.
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight  weight of the edge between vertex u and v
     */
    public void addUndirectedEdge(String nameU, String nameV, double weight) {
        
        // TODO
        if(edgesGiven == false){
            return;
        }
        addEdge(nameU, nameV, weight);
        addEdge(nameV, nameU, weight);

        Edge edge = new Edge(vertices.get(nameU), vertices.get(nameV), weight);
        allUndirectedEdges.add(edge);   // adds the new edge to the list


    }

    /**
     * Calculates the euclidean distance for all edges in the graph and all edges in 
     * allUndirectedEdges. If edgesGiven is false, directly return at first.
     */
    public void computeAllEuclideanDistances() {
        if(edgesGiven == false){
            return ;
        }

        for(Vertex vertex : getVertices()){  // goes over vertex
            for(Edge edge : vertex.adjacentEdges){   // goes over edges
                edge.setDistance(edge.getSource().getDistanceTo(edge.getTarget()));
                                        // sets the distance
            }

        }

        for(Edge edge : allUndirectedEdges){
            edge.setDistance(edge.getSource().getDistanceTo(edge.getTarget()));
                            // sets the distance
        }
        // TODO

    }

    /**
     * Populate all possible edges from all vertices in the graph. Only works when edgesGiven 
     * is false. If edgesGiven is true, directly return at first.
     */
    public void populateAllEdges() {
        if(edgesGiven == true){
            return;
        }

                // TODO

        Collection<Vertex> vCollection = getVertices();
        Vertex[] vertices = vCollection.toArray(new Vertex[vCollection.size()]);

        for(int i = 0; i < vertices.length; i++){    // loops through vertices
            for (int j = i + 1 ; j < vertices.length; j++) {  // loops through vertices
                Edge edge = new Edge(vertices[i], vertices[j],vertices[i].getDistanceTo(vertices[j]));
                allUndirectedEdges.add(edge);  // adds that edge to allUndirected edges
            }
        }
    }

    /**
     * This algorithm finds the min spanning tree using the Kruskal algortithm
     * It uses the help of disjoint set to find cycles in the graph.
     * It returns a array list which has edges which give the path
     * @return arraylist of edges
     */
    public ArrayList<Edge> runKruskalsAlg() {
        // if resultMST is already computed, return the resultMST at first

        // TODO
        int min =0;
        int count =0;
        DisjointSet set = new DisjointSet();    // object disjointSet

        if(resultMST.size() != 0){
            return resultMST;
        }

        // ascending order
        Collections.sort(allUndirectedEdges, Comparator.comparingDouble(e -> e.getDistance()));

        while(count < allUndirectedEdges.size()){    //goes over all undirected edges

            if(min >= getVertices().size() -1){   // break condition to go over edges
                break;
            }

            Vertex src = set.find(allUndirectedEdges.get(count).getSource());
            Vertex tar = set.find(allUndirectedEdges.get(count).getTarget());

            if(src != tar){   // if they don't equal as in different sets
                resultMST.add(allUndirectedEdges.get(count));  // adds to results
                set.union(src, tar);   //makes a union
                min = min + 1;
            }

            count = count +1;   // increments the count

        }

        return resultMST;      // returns the array
    }
}
