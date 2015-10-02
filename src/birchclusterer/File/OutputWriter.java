package birchclusterer.File;

import birchclusterer.Plot.Cluster;
import birchclusterer.Plot.DataPoint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OutputWriter {

    ArrayList<Cluster> clusters = null;

    public OutputWriter(ArrayList<Cluster> clusters, FileWriter fw) throws IOException {
        this.clusters = clusters;

        start(fw);
    }

    // start the file writing
    private void start(FileWriter fw) throws IOException {
        printClusters(fw);
    }

    // prints each of the clusters with headers to the file
    private void printClusters(FileWriter fw) throws IOException {
        int count = 1;
        BufferedReader in = null;
        File clustTxt = null;
        // if the user wants to add original clusters
        boolean clustNum = openDialog();
        if (clustNum) {
            clustTxt = getFile();
            in = new BufferedReader(new FileReader(clustTxt));
        }

        for (Cluster clust : clusters) {
            printHeader(fw, count);

            for (DataPoint pt : clust.getData()) {
                fw.append(pt.printPoint());
                if (clustTxt != null && in != null) {
                    fw.append(in.readLine());
                }
                for (int i = 0; i < 4; i++) {
                    fw.append(",");
                }

                HashMap<Integer, Double> map = pt.getMemMap();

                for (int i = 0; i < clusters.size(); i++) {
                    fw.append(map.get(i).toString() + ",");
                }

                fw.append("\n");
            }

            fw.append("\n");
            count++;
        }
    }
    
    // prints the headers for the csv file
    private void printHeader(FileWriter fw, int count) throws IOException {
        fw.append("Cluster " + count);
        for (int i = 0; i < clusters.get(0).getData().get(0).getPoints().length+1; i++) {
            fw.append(",");
        }
        fw.append(",,Memberships\n");
        
        for (int i = 0; i < clusters.get(0).getData().get(0).getPoints().length+1; i++) {
            fw.append(",");
        }
        fw.append(",,");
        for (int i = 0; i < clusters.size(); i++) {
            fw.append("Cluster " + (i+1) + ",");
        }
        fw.append("\n");
    }

    // the user has the option to input the original clusters to the file
    private boolean openDialog() {
        JOptionPane dia = new JOptionPane(
                "Would you like to load a original cluster file?");
        Object[] option = new String[]{"No", "Yes"};
        dia.setOptions(option);
        JDialog dialog = dia.createDialog(null, "Cluster File");
        dialog.setVisible(true);
        Object obj = dia.getValue();

        return option[1].equals(obj);
    }

    // get the file that contains the original clusters
    private File getFile() {
        final JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Document (.txt)", "txt", "text");
        fc.setFileFilter(filter);

        int returnVal = fc.showOpenDialog(new JFrame());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }

        return null;
    }

}
