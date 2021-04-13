/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.ArrayList;

/**
 * This class has methods which heelp build vertex. It has getter and setter
 * and alot of instance variable.
 *
 * @author Hetsvi
 * @since 2018
 */
public class Vertex {

    private String name; // the name of this vertex
    private int x; // the x coordinates of this vertex on map
    private int y; // the y coordinates of this vertex on map

    public ArrayList<Edge> adjacentEdges; // the adjacent edges of this vertex

    // TODO: add additional instance variables to work with Disjoint Set
    private Vertex parent;     // parent of the vertex
    private int size;          // size of the set

    /**
     * It is the constructor
     * @param name
     * @param x
     * @param y
     */
    public Vertex(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        adjacentEdges = new ArrayList<>();
        parent =  null;    // sets parent to null
        size = 1;          // size as one


        // TODO: initialize your new instance variables
    }

    /**
     * This returns the name
     * @return name a string
     */
    public String getName() {
        return name;
    }

    /**
     * This returns the x value of vertex
     * @return X a int value
     */
    public int getX() {
        return x;
    }

    /**
     * This returns the y values of vertex
     * @return Y a int value
     */
    public int getY() {
        return y;
    }

    // TODO: add getters and setters for your new instance variables

    /**
     * It gets the parent
     * @return parent vertex
     */
    public Vertex getParent(){
        return parent;   // parent is returned
    }

    /**
     * Sets value of parent
     * @param parent
     */
    public void setParent(Vertex parent){
        this.parent= parent;
    }

    /**
     * It gets the size of the set
     * @return size int
     */
    public int getSize(){
        return size;  // gets the size
    }

    /**
     * It sets the size
     * @param size
     */
    public void setSize(int size){
        this.size = size;    // sets the size
    }


    /**
     * It gets the distance from the o to vertex
     * @param o the veretx
     * @return distance a double
     */
    public double getDistanceTo(Vertex o) {
        double squareDis = Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2);
        return Math.sqrt(squareDis); // returns the distance
    }

    /**
     * it adds the edge to the given list of adjacentEdges
     * @param edge edge passed in
     */
    public void addEdge(Edge edge) {
        adjacentEdges.add(edge);
    }

    /**
     * COnverts to a string
     * @return string of a specific format
     */
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }

}