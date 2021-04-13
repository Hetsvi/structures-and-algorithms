/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.ArrayList;
import java.util.PriorityQueue;



/**
 * Given a set of data points as training data, this class can naively find the k nearest neighbors of any given
 * query point.
 *
 * @author Hetsvi
 * @since November 21st 2018
 */
public class NaiveKNN {
    private Point[] trainingPoints; // the given training data points
    private PriorityQueue<Point> KNN; // priority queue containing k nearest neighbors
    private double largestDisInKNN; // the largest distance to query point in current KNN

    /**
     * The constructor of naive KNN
     */
    public NaiveKNN() {

        // TODO
        KNN = new PriorityQueue<>();
    }

    /**
     * Initialize training data points to be the given set of points
     * @param points the given set of points
     */
    public void build(Point[] points) {

        // TODO
        trainingPoints = points;
    }

    /**
     * Find the K nearest neighbors of given query point in training data.
     * @param queryPoint the given query point
     * @param k number of nearest neighbors
     * @return the K nearest neighbors of given query point
     */
    public Point[] findKNearestNeighbor(Point queryPoint, int k) {

        Point[] kNearest = new Point[k];   //Makes a new point array

        for(int i =0; i<trainingPoints.length ; i++){
            trainingPoints[i].setSquareDisToQueryPoint(queryPoint);  // sets the distance
            updateKNN(trainingPoints[i], k); // calls update KNN
        }


        for(int list =0; list < k; list++ ){
            kNearest[list] =  KNN.poll();  // adds from PQ to point array
        }

        return kNearest;
    }

    /**
     * Update current KNN with given point. To keep KNN with only K smallest distance points to
     * the current query point, when size of current KNN reaches K, it will only add
     * the given point to current KNN if the square distance from given point to query point
     * is smaller than largestDisInKNN.
     *
     * The size of KNN should stay as K once it reaches K for the first time.
     *
     * @param p the given data point to update if possible
     * @param k number of points in KNN
     */
    private void updateKNN(Point p, int k) {

        if(KNN.size() == k){    // when size of current KNN reaches K
            largestDisInKNN = KNN.peek().getSquareDisToQueryPoint(); //peeks and gets distance
            if(p.getSquareDisToQueryPoint() < largestDisInKNN){  // compares
                KNN.poll();
                KNN.add(p);  // adds new one
                largestDisInKNN = KNN.peek().getSquareDisToQueryPoint();
            }
        }

        else {
            KNN.add(p);   // when less than k just adds
            largestDisInKNN = KNN.peek().getSquareDisToQueryPoint();
        }

    }
}
