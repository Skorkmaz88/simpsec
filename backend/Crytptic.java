package backend;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class Crytptic {
	   private static String filename;
	   private static String password;
	   private static FileInputStream inFile;
	   private static FileOutputStream outFile;
	   public Crytptic()
	   {
		   
	   }
	   
	   public void crypt(String _filename, String _password) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, BadPaddingException
	   {
		   filename = _filename;

		      // Password must be at least 8 characters (bytes) long
           password = _password;

		      inFile = new FileInputStream(filename);
		      outFile = new FileOutputStream(filename + ".des");

		      // Use PBEKeySpec to create a key based on a password.
		      // The password is passed as a character array

		      PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		      SecretKeyFactory keyFactory =
		          SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		      SecretKey passwordKey = keyFactory.generateSecret(keySpec);

		      // PBE = hashing + symmetric encryption.  A 64 bit random
		      // number (the salt) is added to the password and hashed
		      // using a Message Digest Algorithm (MD5 in this example.).
		      // The number of times the password is hashed is determined
		      // by the interation count.  Adding a random number and
		      // hashing multiple times enlarges the key space.

		      byte[] salt = new byte[8];
		      Random rnd = new Random();
		      rnd.nextBytes(salt);
		      int iterations = 100;

		      //Create the parameter spec for this salt and interation count

		      PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterations);

		      // Create the cipher and initialize it for encryption.

		      Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		      cipher.init(Cipher.ENCRYPT_MODE, passwordKey, parameterSpec);

		      // Need to write the salt to the (encrypted) file.  The
		      // salt is needed when reconstructing the key for decryption.

		      outFile.write(salt);

		      // Read the file and encrypt its bytes.

		      byte[] input = new byte[64];
		      int bytesRead;
		      while ((bytesRead = inFile.read(input)) != -1)
		      {
		         byte[] output = cipher.update(input, 0, bytesRead);
		         if (output != null) outFile.write(output);
		      }

		      byte[] output = cipher.doFinal();
		      if (output != null) outFile.write(output);

		      inFile.close();
		     
		      outFile.flush();
		      outFile.close();
		      File file = new File(_filename);
		      file.delete();

	   }
	   public void decrypt(String _filename, String _password) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException 
	   {
		   // File to decrypt.

		      filename = _filename;

		      password = _password;

		      inFile = new FileInputStream(filename);
		      outFile = new FileOutputStream(filename.substring(0,filename.length() - 4));

		      PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		      SecretKeyFactory keyFactory =
		          SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		      SecretKey passwordKey = keyFactory.generateSecret(keySpec);

		      // Read in the previouly stored salt and set the iteration count.

		      byte[] salt = new byte[8];
		      inFile.read(salt);
		      int iterations = 100;

		      PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterations);

		      // Create the cipher and initialize it for decryption.

		      Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		      cipher.init(Cipher.DECRYPT_MODE, passwordKey, parameterSpec);


		      byte[] input = new byte[64];
		      int bytesRead;
		      while ((bytesRead = inFile.read(input)) != -1)
		      {
		         byte[] output = cipher.update(input, 0, bytesRead);
		         if (output != null)
		            outFile.write(output);
		      }

		      byte[] output = cipher.doFinal();
		      if (output != null)
		         outFile.write(output);

		      inFile.close();
		      outFile.flush();
		      outFile.close();
		      File file = new File(_filename);
		      file.delete();

	   }


}
