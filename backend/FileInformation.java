package backend;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class FileInformation {

	
	String toreturn;
	public FileInformation()
	{
		
	}
	public String fileInformation(String _filepath)
	{
		
		toreturn = "Dosya hakk�nda:\n";
		  // Create an instance of file object.
		
		 

        File file = new File(_filepath);
        Date d = new Date(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String dateString = sdf.format(d);
        
        toreturn += "Dosya ad�:"+ file.getName()+ "\n";
        toreturn += "Dosya tam yolu:"+ file.getAbsolutePath()+ "\n";
        toreturn += "Dosya son de�i�iklik tarihi:"+dateString+ "\n";
        toreturn += "Dosya b�y�kl���(KB):"+ (file.length() / 1024)+ "\n";
        toreturn += "Dosya tipi:"+( file.isDirectory() ? "Klas�r" : "Dosya")+ "\n";
        toreturn += "Dosya okuma hakk�n�z:"+( file.canRead() ? "Evet" : "Hay�r")+ "\n";
        toreturn += "Dosya yazma hakk�n�z:"+( file.canWrite() ? "Evet" : "Hay�r")+ "\n";
        toreturn += "Dosya y�r�tme hakk�n�z:"+( file.canExecute() ? "Evet" : "Hay�r")+ "\n";
    
         return toreturn;
     
	}
}
