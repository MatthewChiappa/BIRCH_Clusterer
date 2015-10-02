package birchclusterer.GUI;

import javax.swing.JFrame;

public class BIRCHClusterer {

    // driver class
    public static void main(String[] args) {
        
        MainGUI gui = new MainGUI();
        gui.setTitle("BIRCH");
        gui.setLocation(200, 200);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setLocationRelativeTo(null);
        gui.setResizable(false);
        gui.setVisible(true);
        
    }
    
}
