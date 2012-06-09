package frontend;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import backend.Crytptic;
import backend.FileInformation;
import backend.OSLog;
import backend.PortScan;
import backend.Sniffer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.JobAttributes;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.NetworkInterface;

import jpcap.JpcapCaptor;

public class Gui extends JPanel {
    File file;
	
	public Gui() {
        super(new GridLayout(1, 1));
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        
     
       
        // Tab 1 dosya iþlemleri GUI baþlýyor
        JPanel panel1 = new JPanel();
       
        
        JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
        toolbar.setPreferredSize(new Dimension(250,370));
        toolbar.setFloatable(false);
        toolbar.setMargin(new Insets(20, 0, 0, 0));
        JButton btnopenfile = new JButton("     Dosya Aç    ");
   
        JButton btncryptfile = new JButton("Þifrele");
        JButton btndecryptfile = new JButton("Þifre Çöz");
        JButton btnfiledetails = new JButton("Dosya ayrýntýlarý");
     //   JButton btndeletefile = new JButton("Dosyayý sil");
        final JTextField textfield = new JTextField();
        textfield.setEditable(false);
        textfield.setPreferredSize(new Dimension(400,30));
        
        final JTextArea txtsummary = new JTextArea();
        JScrollPane scrollpane = new JScrollPane(txtsummary);
        scrollpane.setPreferredSize(new Dimension(300,300));
        scrollpane.setWheelScrollingEnabled(true);
        scrollpane.setAutoscrolls(true);
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       // txtsummary.setEditable(false);
      
     
        txtsummary.setLineWrap(true);
        txtsummary.setWrapStyleWord(true);
        txtsummary.setAutoscrolls(true);
       
     
       
        final Gui g = this;
        btnopenfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				int sonuc = jfc.showOpenDialog(g);
				if(sonuc == JFileChooser.APPROVE_OPTION)
				{
					g.file = jfc.getSelectedFile();
					textfield.setText(g.file.getAbsolutePath());
				
				}
			
		
				
			}
		});
        btnfiledetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textfield.getText().isEmpty() == false)
				{ 
				  FileInformation fi = new FileInformation();
			      txtsummary.append(fi.fileInformation(g.file.getAbsolutePath()));
				}
				else
				 JOptionPane.showMessageDialog(g, "Önce dosya seçiniz");

			}
		});
        btncryptfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textfield.getText().isEmpty() == false)
				{ 
					
				  Crytptic cryptic = new Crytptic();
				  try{
					  String pass =  JOptionPane.showInputDialog(g, "Þifre:");
					  if(pass.length() < 8 )
					  {
						  JOptionPane.showMessageDialog(g, "Þifre en az 8 haneli olmalýdýr");
					  }
					  else
					  {
				       cryptic.crypt(g.file.getAbsolutePath(),pass);
				       txtsummary.append(g.file.getName()+ ".des þifrelenmiþ dosyasý oluþturuldu\n");
					  }
				  }
				  catch(Exception ex)
				  {
					  txtsummary.append("Hata oluþtu:"+ex.getMessage());
				  }
			     
				}
				else
				 JOptionPane.showMessageDialog(g, "Önce dosya seçiniz");

			}
		});
   btndecryptfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textfield.getText().isEmpty() == false)
				{ 
					Crytptic cryptic = new Crytptic();
					  try{
						  String pass =  JOptionPane.showInputDialog(g, "Þifre:");
						  if(pass.length() < 8 )
						  {
							  JOptionPane.showMessageDialog(g, "Þifre en az 8 haneli olmalýdýr");
						  }
						  else
						  {
						  cryptic.decrypt(g.file.getAbsolutePath(),pass);
						  txtsummary.append(g.file.getName()+ " þifrelenmiþ dosyasý , deiþfre edilerek oluþturuldu\n");
						  }
						  }
						  catch(Exception ex)
						  {
							  txtsummary.append("Hata oluþtu:"+ex.getMessage());
						  }
				}
				else
				 JOptionPane.showMessageDialog(g, "Önce dosya seçiniz");

			}
		});
        
        panel1.add(btnopenfile);
        panel1.add(textfield);
       
        toolbar.add(btncryptfile);
        toolbar.add(btndecryptfile);
        toolbar.add(btnfiledetails);
  //      toolbar.add(btndeletefile);
     
        panel1.add(toolbar);
        panel1.add(scrollpane);

        tabbedPane.addTab("Dosya Ýþlemleri", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
      
        
        // Tab 1 dosya iþlemleri GUI sona erdi
        JPanel panel4 = new JPanel();
        final JTextField txtstartport = new JTextField();
        txtstartport.setPreferredSize(new  Dimension(100,30));
        final JTextField txtstopport = new JTextField();
        txtstopport.setPreferredSize(new Dimension(100,30));
        JLabel lblstartport = new JLabel("Baþlangýç Portu:");
        JLabel lblstopport = new JLabel("Bitiþ Portu:");
        JButton btnscan = new JButton("Tara");
   
        final JTextArea txtarea = new JTextArea();
        
        JScrollPane scrollpane2 = new JScrollPane(txtarea);
        scrollpane2.setPreferredSize(new Dimension(500,300));
        scrollpane2.setWheelScrollingEnabled(true);
        scrollpane2.setAutoscrolls(true);
        scrollpane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        btnscan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			PortScan ps = new PortScan(Integer.parseInt(txtstartport.getText()), Integer.parseInt(txtstopport.getText()));
			
			ps.scanPorts(txtarea);
				
			}
		});
   
        panel4.add(lblstartport);
        panel4.add(txtstartport);
        panel4.add(lblstopport);
        panel4.add(txtstopport);
        panel4.add(btnscan);
        panel4.add(scrollpane2);
          
        final JPanel panel2 = new JPanel();
        JButton btnsniff = new JButton("Baþlat");
        JButton btnliste = new JButton("Arayüzler");
        final JTextArea txtsniff = new JTextArea();
        
        JScrollPane scrollpane4 = new JScrollPane(txtsniff);
        scrollpane4.setPreferredSize(new Dimension(500,300));
        scrollpane4.setWheelScrollingEnabled(true);
        scrollpane4.setAutoscrolls(true);
        scrollpane4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        btnliste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Sniffer sf = new Sniffer();
		        sf.deviceList(txtsniff);
			}
		});
        btnsniff.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			  	jpcap.NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		    	String[] possibilities = new String[devices.length];
		    	for(int i = 0; i < devices.length; i++)
		    		possibilities[i] = devices[i].name;
		    	String s = (String)JOptionPane.showInputDialog(panel2, "Að arayüzünü seçiniz" ,
		                "Að Dinleyici",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                possibilities,
		                0);
                 jpcap.NetworkInterface device = null;
                 if(s != null){
		    	for(int i = 0; i < devices.length; i++)
		    		if(s.equals(devices[i].name))
			           device = devices[i];
		    	Sniffer sf = new Sniffer();
		    	sf.sniff(device, txtsniff);
                 }
		        //sf.deviceList(txtsniff);
		    	//sf.sniff(device,txtsniff);
				
			}
		});
        
        panel2.add(btnsniff);
        panel2.add(btnliste);
        panel2.add(scrollpane4);
        tabbedPane.addTab("Að Dinleme", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
     
        
        
        JPanel panel3 = new JPanel();
        JLabel lblnotification = new JLabel("Bu iþlev Linuxta tamamen çalýþýr");
        JButton btnosinfo = new JButton("OS bilgileri");
  
        final JTextArea txtlogarea = new JTextArea();
        
        JScrollPane scrollpane3 = new JScrollPane(txtlogarea);
        scrollpane3.setPreferredSize(new Dimension(500,300));
        scrollpane3.setWheelScrollingEnabled(true);
        scrollpane3.setAutoscrolls(true);
        scrollpane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        btnosinfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			OSLog oslg = new OSLog(txtlogarea);
			}
		});
        
        
        panel3.add(btnosinfo);
        panel3.add(lblnotification);
        panel3.add(scrollpane3);
        tabbedPane.addTab("Log Kayýtlarý", panel3);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
    
     
        tabbedPane.addTab("Test Sistemi", panel4);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    
  
    
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        final JFrame frame = new JFrame("Kolay Güvenlik");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(640,480));
        //Add content to the window.
        JMenuBar menubar = new JMenuBar();
        JMenu menuAbout = new JMenu("Hakkýnda");
        menuAbout.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				 JOptionPane.showMessageDialog(frame, "Bu proje 2012 yýlýnda Semih Korkmaz, Mustafa Çilesiz," +
					 		" Mustafa Göçmen" +
					 		"\n tarafýndan Proje Yönetim dersi için" +" Okan Üniversitesi Biliþim Sistemleri\n"+
					 		"ve Teknolojileri bölümü için"
					 		+" hazýrlanmýþtýr. Proje Danýþmaný" +
					 		" Sayýn\n Yrd. Doç. Dr. Fazlý Yýldrým'dýr. ");	
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		});
        menubar.add(menuAbout);
        
        frame.setJMenuBar(menubar);
        
        frame.add(new Gui(), BorderLayout.CENTER);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
		UIManager.put("swing.Windows", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }
}