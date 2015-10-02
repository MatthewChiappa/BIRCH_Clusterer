package birchclusterer.BirchAlg;

import birchclusterer.Plot.Cluster;
import birchclusterer.Plot.DataPoint;
import edu.gatech.gtisc.jbirch.cftree.CFTree;
import java.util.ArrayList;

public class BirchExec {

    ArrayList<DataPoint> points = new ArrayList<>();
    ArrayList<Cluster> clusters = new ArrayList<>();
    ArrayList<ArrayList<Integer>> subclusters;
    double distanceTresh = Double.MIN_VALUE;

    public BirchExec(ArrayList<DataPoint> points, double distanceTresh) {
        this.points = points;
        this.distanceTresh = distanceTresh;
        start();
    }

    // create tree and start inputting data to tree
    private void start() {
        subclusters = createTree().getSubclusterMembers();
        clusters = new ArrayList<>(subclusters.size());

        convertToClustObj();
    }
    
    // inputs the data from the file then creates the birch tree
    private CFTree createTree() {
        int maxNodeEntries = points.size();
        boolean applyMergingRefinement = true;
        
        CFTree birchTree = new CFTree(maxNodeEntries, distanceTresh, CFTree.D0_DIST, applyMergingRefinement);
        birchTree.setMemoryLimit(100 * 1024 * 1024);

        points.stream().map((point) 
                -> birchTree.insertEntry(point.getPoints())).filter((inserted) 
                        -> (!inserted)).map((_item) -> {
            System.err.println("NOT INSERTED!");
            return _item;
        }).forEach((_item) -> {
            System.exit(1);
        });

        birchTree.finishedInsertingData();
        
        birchTree.getLeafListStart().toString();
        
        return birchTree;
    }
    
    // creates clusters from the cf tree
    private void convertToClustObj() {
        int count = 1;
        for (ArrayList<Integer> list : subclusters) {
            Cluster newClust = new Cluster();
            list.stream().forEach((indexes) -> {
                newClust.addDataPoint(points.get(indexes-1));
            });
            count++;
            clusters.add(newClust);
        }
    }
    
    // returns the clusters
    public ArrayList<Cluster> getClusters() {
        return clusters;
    }

}
