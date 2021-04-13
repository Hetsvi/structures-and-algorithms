/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Implementation of KD tree which will be used in a KNN classifier
 * @author Hetsvi
 * @since 27 November
 */
public class KDTree {

    private KDNode root; // root of this KD tree
    private int numDim; // number of dimension of given data points
    private int k; // number of nearest neighbors to find
    private double largestDisInKNN;// largest distance to current query point in the current KNN
    private PriorityQueue<Point> KNN; // priority queue containing k nearest neighbors
    private int size;
    private int height;

    /**
     * Inner class which defines a KD node
     */
    protected class KDNode {

        KDNode left;
        KDNode right;
        KDNode parent;
        Point point; // the data point in this node

        /**
         * Default constructor to create an empty KD node
         */
        KDNode() {
        }

        /**
         * Constructor which creates a KD node containing the given point
         *
         * @param point the given point
         */
        KDNode(Point point) {
            this.point = point;
        }

        /**
         * Getter for left child
         *
         * @return the left child of this node
         */
        public KDNode getLeft() {
            return left;
        }

        /**
         * Setter for left child
         *
         * @param left the left child to be set
         */
        public void setLeft(KDNode left) {
            this.left = left;
        }

        /**
         * Getter for right child
         *
         * @return the right child of this node
         */
        public KDNode getRight() {
            return right;
        }

        /**
         * Setter for right child
         *
         * @param right the right child to be set
         */
        public void setRight(KDNode right) {
            this.right = right;
        }

        /**
         * Getter for parent
         *
         * @return the parent of this node
         */
        public KDNode getParent() {
            return parent;
        }

        /**
         * Setter for parent
         *
         * @param parent the parent to be set
         */
        public void setParent(KDNode parent) {
            this.parent = parent;
        }

        /**
         * Getter for point in this node
         *
         * @return the point in this node
         */
        public Point getPoint() {
            return point;
        }
    }

    /**
     * Constructor which creates a KD tree. Need to specify the number of dimension of data points
     * from the parameter.
     *
     * @param numDim the number of dimension
     */
    public KDTree(int numDim) {
        this.numDim = numDim; // number of dimensions
        KNN = new PriorityQueue<>();
        largestDisInKNN = Double.MAX_VALUE; // initalizes to max value
        this.size =0;
        this.height =0;

        // TODO
    }

    /**
     * Build the KD tree from the given set of points
     *
     * @param points the given set of points to build the KD tree
     */
    public void build(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        // calls helper
        this.root = buildSubtree(points, 0, points.length, 0, height);

        // TODO
    }

    /**
     * Find k nearest neighbors of the given query point
     *
     * @param queryPoint the given query point
     * @param k          number of nearest neighbors
     * @return an array containing k nearest neighbors
     */
    public Point[] findKNearestNeighbor(Point queryPoint, int k) {

        // TODO
        this.k = k;


        findKNNHelper(root, queryPoint, 0);
        Point[] pointArray = new Point[KNN.size()];  // new array
        int i =0;
        while(!KNN.isEmpty()){
            pointArray[i] = KNN.poll();  // puts from pq array to array
            i++;
        }

        this.KNN.clear();
        this.largestDisInKNN = Double.MAX_VALUE;

        return pointArray;
    }

    /**
     * Helper method to recursively build the subtree of KD tree.
     *
     * @param points the given set of points to build the KD tree
     * @param start  the starting index of the points array used to build the subtree
     * @param end    the non-inclusive index of the points array used to build the subtree
     * @param d      the current dimension to looked at
     * @param height the current height of the kd tree,
     *               update this height if current height is larger
     * @return the parent of the subtree
     */
    private KDNode buildSubtree(Point[] points, int start, int end, int d, int height) {
        int median = 0;
        int divider = 2;
        // TODO

        if (end <= start) {   // base case
            return null;
        }

       /* if(start+1 == end){  // when only 2 what is the median
            median = start;
        }*/

        Arrays.sort(points, start, end, Comparator.comparingDouble(p -> p.valueAt(d)));
        median = (start + end) / divider;  // finds median
        KDNode curr = new KDNode();
        curr.point = points[median]; //sets curr point to median value

        if(this.height < height){  // updates height
            this.height = height;
        }

        this.size = size+1;
        // recursive calls to left and right of tree
        curr.left = buildSubtree(points, start, median, (d + 1) % numDim,
                height +1);
        curr.right = buildSubtree(points, median + 1, end, (d + 1) % numDim,
                height +1);

        return curr;
    }

    /**
     * Helper method to recursively find the K nearest neighbors
     *
     * @param n          the current node to look at
     * @param queryPoint the given point to find its KNN
     * @param d          the current dimension to look at
     */
    private void findKNNHelper(KDNode n, Point queryPoint, int d) {
        int raise = 2;
        if (n == null) {
            return;
        }

        n.point.setSquareDisToQueryPoint(queryPoint); // sets distances
        updateKNN(n.point);    // calls update KNN

        if(n.point.valueAt(d) > queryPoint.valueAt(d)){ // if less go to left

            findKNNHelper(n.left, queryPoint, (d+1)%numDim);  // left

            // checks if needs to go to right
            if (Math.pow((n.point.valueAt(d) - queryPoint.valueAt(d)), raise)
                    < largestDisInKNN ) {
                findKNNHelper(n.right, queryPoint, (d+1)%numDim);
            }

        }

        else{

            findKNNHelper(n.right, queryPoint, (d+1)%numDim); // right

            // sees if it needs to go to left
            if ((Math.pow((n.point.valueAt(d) - queryPoint.valueAt(d)), raise )
                    < largestDisInKNN ) ){
                findKNNHelper(n.left, queryPoint, (d+1)%numDim);
            }

        }


    }


    /**
     * Update current KNN with given point. To keep KNN with only K smallest distance points to
     * the current query point, when size of current KNN reaches K, it will only add
     * the given point to current KNN if the square distance from given point to query point
     * is smaller than largestDisInKNN.
     * <p>
     * The size of KNN should stay as K once it reaches K for the first time.
     *
     * @param p the given data point to update if possible
     */
    private void updateKNN(Point p) {

        // TODO
        if (KNN.size() == k) {    // when size of current KNN reaches K

            largestDisInKNN = KNN.peek().getSquareDisToQueryPoint(); //peeks and gets distance

            if (p.getSquareDisToQueryPoint() < largestDisInKNN) {  // compares
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

    /**
     * Returns the size of this KD tree
     *
     * @return the size of this KD tree
     */
    public int size() {
        return size;
    }

    /**
     * Returns the height of this KD tree
     *
     * @return the height of this KD tree
     */
    public int height() {
        return height;
    }

    /**
     * private method to help print the tree
     * @param points
     */
    private void inOrder(KDNode points) {
        if (points == null) {
            return;
        }

        inOrder(points.left);  // recursive left
        System.out.println(points.point.toString());
        inOrder(points.right);

    }

}
