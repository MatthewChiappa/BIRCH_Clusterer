package birchclusterer.Plot;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.Arrays;
import java.util.HashMap;

public class DataPoint {

    // variables
    private double[] points = null;
    HashMap<Integer, Double> memberships;
    int pointNum = 0;

    // init
    public DataPoint() {
        this.points = new double[10];
        memberships = new HashMap<>();
    }

    // init
    public DataPoint(double[] points) {
        this.points = points;
        memberships = new HashMap<>();
    }

    // returns the pattern
    public double[] getPoints() {
        return points;
    }
    
    // sets the membership to the hashmap (clusterNum, membership)
    public void setMembership(int clust, double num){
        memberships.put(clust, num);
    }
    
    // return the membership functions for the point
    public HashMap<Integer, Double> getMemMap() {
        return memberships;
    }
    
    // used for debugging
    public void setNewPoints(double[] pts) {
        this.points = pts;
    }

    // compares this data point to another
    public boolean compareTo(DataPoint newPoint) {
        return points == newPoint.getPoints();
    }
    
    // Euclidean distance formula from point to point
    public double distTo(DataPoint pt2) {
        double[] points1 = points;
        double[] points2 = pt2.getPoints();
        double dist = 0;
        
        for (int  i = 0; i < points.length; i++) {
            dist += pow(points2[i] - points1[i], 2);
        }
        return sqrt(dist);
    }

    // print method for debugging
    public String printPoint() {
        return Arrays.toString(points).replaceAll("\\[|\\]", "");
    }

}
