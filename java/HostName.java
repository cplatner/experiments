import java.net.*;

public class HostName
{
	public static void main (String[] args)
	{
   try {
   InetAddress i = InetAddress.getLocalHost();
   System.out.println(i);                  // name and IP address
   System.out.println(i.getHostName());    // name
   System.out.println(i.getHostAddress()); // IP address only
   }
   catch(Exception e){e.printStackTrace();}
 }

}
