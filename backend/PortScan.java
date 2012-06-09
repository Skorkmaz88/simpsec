package backend;
import java.net.*;

import javax.swing.JTextArea;

public class PortScan {

	int startPortRange=0;
	int stopPortRange=0;
	public PortScan(int start, int stop)
	{
		startPortRange = start;
		stopPortRange = stop;
	}
	public void scanPorts(JTextArea txt)
	{
	 for(int i=startPortRange; i <=stopPortRange; i++)
     {
		    try
            {
		    	Socket ServerSok = new Socket("http://localhost",i);
		    	txt.append("Port "+ i +" : Kullanýmda\n");
		    	ServerSok.close();
            }
		    catch (Exception e)
            {
            }
		    txt.append("Port "+ i +" : Kullanýmda Deðil\n");
		    
     }
	}
}
