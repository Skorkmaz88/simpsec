package frontend;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SFileChooser extends JFrame {
	JFileChooser chooser;
	JPanel panel;
	
 public SFileChooser() {
	 chooser = new JFileChooser();
	 panel = new JPanel();
	
	
	 
	 panel.setPreferredSize(new Dimension(480,320));
	 panel.add(chooser);
	 panel.setVisible(true);
    
	 this.add(panel);
     this.pack();
     this.setVisible(true);
 }  
 public File getFile()
 {
	 return  chooser.getSelectedFile();
 }

}
