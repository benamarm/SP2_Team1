package logic;
//Thanks to http://mustafacanturk.com/sha512-hashing-on-java/ !!
import java.security.*;

import database.UserDAO;
public class SHA512 
{
  private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase()+"0123456789";

  public static String encrypt(String toHash, String salt)
  {
       return SHA512once(salt.substring(0, 15)+toHash+salt.substring(15))+"PEPPER";
  }

  private static String SHA512once(String toHash)
  {

      MessageDigest md;
      String message = toHash;
      try 
      {
          md= MessageDigest.getInstance("SHA-512");

          md.update(message.getBytes());
          byte[] mb = md.digest();
          String out = "";
          for (int i = 0; i < mb.length; i++) 
          {
              byte temp = mb[i];
              String s = Integer.toHexString(new Byte(temp));
              while (s.length() < 2) 
              {
                  s = "0" + s;
              }
              s = s.substring(s.length() - 2);
              out += s;
          }
          return(out);

      } catch (NoSuchAlgorithmException e) 
      {
          System.out.println("ERROR: " + e.getMessage());
      }
      return "error";
  }

  public static String salt(String toSalt, String salt)
  {
      return toSalt+salt;
  }
 
  //thanks to https://dzone.com/articles/generate-random-alpha-numeric
  
  public static String generateSalt() {
  StringBuilder builder = new StringBuilder();
  for (int i = 0; i < 30; i++) {
  int character = (int)(Math.random()*CHARSET.length());
  builder.append(CHARSET.charAt(character));
  }
  return builder.toString();
  }
  
  public static void main(String[] args) {
	  System.out.println(generateSalt());
	//User u = UserDAO.getSingleUser("adminus@HR.be");
//	System.out.println(u.getPassword());
//	System.out.println(encrypt("test", u.getSalt()));
//	
//	if(encrypt("test", u.getSalt()).equals(u.getPassword())) {
//		System.out.println("gelukt");
//	} else {
//		System.out.println("mislukt");
//	}
	}
  //jhfj94hh25wamcxtestm6vj9pw41a4pllu
  
}