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
		
		toreturn = "Dosya hakkýnda:\n";
		  // Create an instance of file object.
		
		 

        File file = new File(_filepath);
        Date d = new Date(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String dateString = sdf.format(d);
        
        toreturn += "Dosya adý:"+ file.getName()+ "\n";
        toreturn += "Dosya tam yolu:"+ file.getAbsolutePath()+ "\n";
        toreturn += "Dosya son deðiþiklik tarihi:"+dateString+ "\n";
        toreturn += "Dosya büyüklüðü(KB):"+ (file.length() / 1024)+ "\n";
        toreturn += "Dosya tipi:"+( file.isDirectory() ? "Klasör" : "Dosya")+ "\n";
        toreturn += "Dosya okuma hakkýnýz:"+( file.canRead() ? "Evet" : "Hayýr")+ "\n";
        toreturn += "Dosya yazma hakkýnýz:"+( file.canWrite() ? "Evet" : "Hayýr")+ "\n";
        toreturn += "Dosya yürütme hakkýnýz:"+( file.canExecute() ? "Evet" : "Hayýr")+ "\n";
    
         return toreturn;
     
	}
}
