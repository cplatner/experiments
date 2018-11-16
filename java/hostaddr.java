
import java.net.InetAddress;
import java.net.UnknownHostException;

public class hostaddr

{

	public static void main (String[] args)
	{
		System.out.println ("user name: " + System.getProperty("user.name"));

		System.out.println ("host name: " + getHostname());
	}

		public static String getHostname()
		{
			String s = null;
			try
			{
				InetAddress inetaddress = InetAddress.getLocalHost();
				s = inetaddress.getHostAddress();
			}
			catch(UnknownHostException unknownhostexception)
			{
				//* s is already null;
			}

			return s;
		}
}
