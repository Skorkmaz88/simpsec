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
		
		txt.append("��letim sistemi: "+System.getProperty("os.name")+" \n");
		txt.append("��letim sistemi versiyonu: "+System.getProperty("os.version")+" \n");
		txt.append("��letim sistemi mimarisi: "+System.getProperty("os.arch")+" \n");
		txt.append("Java versiyonu: "+System.getProperty("java.version")+" \n");
		txt.append("Java sa�lay�c�s�: "+System.getProperty("java.vendor")+" \n");
		txt.append("Java klas�r�: "+System.getProperty("java.home")+" \n");
		txt.append("Kullan�c� ad�n�z: "+System.getProperty("user.name")+" \n");
		txt.append("Kullan�c� klas�r�n�z: "+System.getProperty("user.home")+" \n");
		txt.append("Kullan�c� �al��ma klas�r�n�z: "+System.getProperty("user.dir")+" \n");
		txt.append("Linux sistemler i�in log mesajlar�..");
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
