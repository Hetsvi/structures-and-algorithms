/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.ArrayList;

/**
 * This class has methods to make a disjoint set. There are methods like
 * find and union which get the main parent and help combined 2 sets
 * together.
 *
 * @author Hetsvi
 * @since 2018
 */
public class DisjointSet {


    /**
     * Constructor. It does nothing
     */
    public DisjointSet() {}

    /**
     * Using path compression it returns the sentinel node that represents the
     * set that this vertex belong to.
     * @param v
     * @return vertex - sentinel node
     */
    public Vertex find(Vertex v) {
        // TODO

        ArrayList<Vertex> helperArray = new ArrayList<>(); // keeps track of vertex

        while(v.getParent() != null){
            helperArray.add(v);             // keeps adding to array list
            v = v.getParent();              // sets v to its parent
        }

        for(int i =0; i<helperArray.size(); i++){
            helperArray.get(i).setParent(v);       // compresses the path
        }

        return v ;   // returns the sentinel vertex
    }

    /**
     * It merges the two sets of the given two vertices.
     * @param v1
     * @param v2
     */
    public void union(Vertex v1, Vertex v2) {
        
        // TODO

        Vertex one = find(v1);         // calls method find
        Vertex two = find(v2);         // calls method find

        if(one == two){
            return;
        }

        if(one.getSize() >= two.getSize() ){    // checks which set is small
            two.setParent(one);
            one.setSize(one.getSize() + two.getSize());   // changes the size

        }

        else if(two.getSize() > one.getSize()){   // if the other one is small
            one.setParent(two);
            two.setSize(one.getSize() + two.getSize());  // changes the size
        }
        
    }
}
