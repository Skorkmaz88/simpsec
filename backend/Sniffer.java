package backend;

import java.awt.TextArea;
import java.io.IOException;
import java.net.NetworkInterface;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterfaceAddress;

public class Sniffer {
	public Sniffer()
	{
		
	}
	public void sniff(jpcap.NetworkInterface device, JTextArea txt)
	{
		try {
			JpcapCaptor captor=JpcapCaptor.openDevice(device, 65535, false, 20);
			
			for(int i=0;i<100;i++){
				  txt.append(captor.getPacket()+"\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deviceList(JTextArea txt) {
		//Obtain the list of network interfaces
		jpcap.NetworkInterface[] devices = JpcapCaptor.getDeviceList();

		//for each network interface
		for (int i = 0; i < devices.length; i++) {
		  //print out its name and description
		  txt.append(i+": "+devices[i].name + "(" + devices[i].description+")\n");

		  //print out its datalink name and description
		  txt.append(" datalink: "+devices[i].datalink_name + "(" + devices[i].datalink_description+")\n");

		  //print out its MAC address
		  txt.append(" MAC address:\n");
		  for (byte b : devices[i].mac_address)
			  txt.append(Integer.toHexString(b&0xff) + ":");
		  txt.append("\n");

		  //print out its IP address, subnet mask and broadcast address
		  for (NetworkInterfaceAddress a : devices[i].addresses)
			  txt.append(" address:"+a.address + " " + a.subnet + " "+ a.broadcast+"\n");
		}
	}
	
 

}
