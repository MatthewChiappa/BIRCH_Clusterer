package birchclusterer.File;

import birchclusterer.Plot.DataPoint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputReader {

    double[] dataPoints = null;
    ArrayList<DataPoint> points = new ArrayList<>();
    File input = null;

    public InputReader(File input) {
        this.input = input;
        start();
    }

    // reads text file and creates datapoints for each line
    @SuppressWarnings("UnusedAssignment")
    final public void start() {
        try {
            int count = linesInTxt();

            try (BufferedReader in = new BufferedReader(new FileReader(input))) {
                String line = null;

                int j = 0;
                while (j < count && count != 0) {
                    line = in.readLine();
                    j++;  
                }

                while ((line = in.readLine()) != null) {
                    String[] tmp = line.split("\\s+");
                    dataPoints = new double[tmp.length];
                    for (int i = 0; i < dataPoints.length; i++) {
                        dataPoints[i] = Double.parseDouble(tmp[i]);
                    }
                    
                    DataPoint newPt = new DataPoint(dataPoints);
                    points.add(newPt);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // returns the amount of patterns in the dataset
    private int linesInTxt() throws FileNotFoundException {
        int count = 0;
        Scanner scan = new Scanner(input);
            while (scan.hasNextLine()) {
                if (scan.hasNextDouble()) {
                    break;
                }
                count++;
                scan.nextLine();
            }
            return count;
    }

    public ArrayList<DataPoint> getData() {
        return points;
    }

}
