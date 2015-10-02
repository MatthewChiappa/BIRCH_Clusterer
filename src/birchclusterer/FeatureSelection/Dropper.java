package birchclusterer.FeatureSelection;

import birchclusterer.Plot.Cluster;
import java.util.ArrayList;
import org.apache.commons.lang3.ArrayUtils;

public class Dropper {

    private final ArrayList<Cluster> clusters;
    private final int numDropped;
    
    public Dropper(ArrayList<Cluster> clusters, int i) {
        this.clusters = clusters;
        this.numDropped = i;
        
        drop();
    }

    // drop designated feature
    private void drop() {
        clusters.stream().forEach((Cluster clust) -> {
            clust.getData().stream().forEach((pt) -> {
                double[] newPts = ArrayUtils.remove(pt.getPoints(), numDropped);
                pt.setNewPoints(newPts);
            });
        });
    }
    
    // return clusters
    public ArrayList<Cluster> getClusters() {
        return clusters;
    }
    
}
