/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

/**
 * This class is the Edge class which has various things that make edge.
 * It has various getters and setter which gets things like source of the edge
 * and more.
 *
 * @since December 1 2018
 */
public class Edge {

    private double distance; // the distance from source to target
    private final Vertex source; // the source vertex this edge starts from
    private final Vertex target; // the target vertex this edge ends at

    /**
     * This is the constructor for edge and initializes the things
     * @param vertex1
     * @param vertex2
     * @param weight
     */
    public Edge(Vertex vertex1, Vertex vertex2, double weight) {
        source = vertex1;
        target = vertex2;
        this.distance = weight;
    }

    /**
     * It helps get the distance
     * @return double - distance
     */
    public double getDistance() {
        return distance;
    }  // gets the distance

    /**
     * It helps set the distance
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Gets the source vertex
     * @return vertex - source
     */
    public Vertex getSource() {
        return source;
    }

    /**
     * Gets the target vertex
     * @return vertex - target
     */
    public Vertex getTarget() {
        return target;
    }

    /**
     * Convertes toString
     * @return the string version
     */
    public String toString() {
        return source + " - " + target;
    }
}