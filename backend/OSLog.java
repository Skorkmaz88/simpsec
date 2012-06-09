package backend;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

public class OSLog {

	public OSLog(JTextArea txt)
	{
		
		txt.append("Ýþletim sistemi: "+System.getProperty("os.name")+" \n");
		txt.append("Ýþletim sistemi versiyonu: "+System.getProperty("os.version")+" \n");
		txt.append("Ýþletim sistemi mimarisi: "+System.getProperty("os.arch")+" \n");
		txt.append("Java versiyonu: "+System.getProperty("java.version")+" \n");
		txt.append("Java saðlayýcýsý: "+System.getProperty("java.vendor")+" \n");
		txt.append("Java klasörü: "+System.getProperty("java.home")+" \n");
		txt.append("Kullanýcý adýnýz: "+System.getProperty("user.name")+" \n");
		txt.append("Kullanýcý klasörünüz: "+System.getProperty("user.home")+" \n");
		txt.append("Kullanýcý çalýþma klasörünüz: "+System.getProperty("user.dir")+" \n");
		txt.append("Linux sistemler için log mesajlarý..");
		try{
		
			  // command line parameter
			  FileInputStream fstream = new FileInputStream("//var//log//message");
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  txt.append(strLine);
			  }
			  //Close the input stream
			  in.close();
		}
		catch(Exception ex)
		{}
		
	}
}
