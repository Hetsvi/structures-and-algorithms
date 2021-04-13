/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */
import java.util.LinkedList;

/**
 * This is the vertex class which has various methods which define the
 * vertex and has various getters and setters to get things like previous
 * edge, cost and much more.
 *
 * @author Hetsvi
 * @since December 1 2018
 */
public class Vertex {

    private final String name; // the name of this vertex
    private final int x; // the x coordinates of this vertex on map
    private final int y; // the y coordinates of this vertex on map

    // TODO: add additional instance variables to work with different graph traversal algorithm

    private LinkedList<Edge> listEdge; // new list of edges
    private boolean passed; // to keep track if visted or nah
    private Edge previous; // previous edge
    private Vertex preVertex; // previous vertex
    private double cost;    // cost of vertex

    /**
     * This is the constructor and initializes things
     * @param name
     * @param x
     * @param y
     */
    public Vertex(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        listEdge = new LinkedList<>();
        passed = false;
        previous = null;
        preVertex = null;
    }

    /**
     * It gets the name
     * @return string name
     */
    public String getName() {
        return name;    // returns name
    }

    /**
     * Gets the x coordinate
     * @return x
     */
    public int getX() {
        return x;      // returns the x value
    }

    /**
     * Gets the Y coordinate
     * @return y
     */
    public int getY() {
        return y;
    }

    // TODO: add necessary getters and setters for ALL your instance variable

    /**
     * Gets the linked list of edges
     * @return linked list - listEdge
     */
    public LinkedList<Edge> getListEdge(){
        return listEdge;     // returns the linked list
    }

    /**
     * gets the boolean
     * @return boolean
     */
    public boolean getPassed(){
        return passed;
    }

    /**
     * Sets the change in boolean
     * @param passed
     */
    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    /**
     * Gets the previous edge
     * @return previous an edge
     */
    public Edge getPrevious(){
        return previous;
    }

    /**
     * Sets the previous edge
     * @param edge
     */
    public void setPrevious(Edge edge){
        this.previous = edge;
    }

    /**
     * Gets the previous vertex
     * @return preVertex an vertex
     */
    public Vertex getPreVertex(){
        return preVertex;
    }

    /**
     * Gets the cost
     * @return double - cost
     */
    public double getCost(){
        return cost;
    }

    /**
     * Sets the cost
     * @param cost
     */
    public void setCost(double cost){
        this.cost = cost;

    }

    /**
     * Adds the edge to the linked list
     * @param edge
     */
    public void addEdge(Edge edge){
        listEdge.add(edge);     // adds edge to linked list
    }

    /**
     * gets the name of the hashCode
     * @return name
     */
    @Override
    public int hashCode() {
        // we assume that each vertex has a unique name
        return name.hashCode();
    }

    /**
     * This checks whether it equals the object or nah
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {    // checks if equals object
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex oVertex = (Vertex) o;

        return name.equals(oVertex.name) && x == oVertex.x && y == oVertex.y;
    }

    /**
     * Converts to string
     * @return the string version
     */
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }

}