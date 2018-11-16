import java.applet.*;
import java.net.*;

public class HostName
	extends Applet
{
	public String getHostname ()
	{
		String s = null;
		try
		{
			InetAddress inetaddress = InetAddress.getLocalHost ();
			s = inetaddress.getHostAddress ();
		}
		catch (UnknownHostException e)
		{
			System.out.println (e);
			//* s is already null;
		}

		return s;
	}



	public String getName ()
	{
		String s = null;
		try
		{
			InetAddress inetaddress = InetAddress.getLocalHost ();
			s = inetaddress.getHostName ();
		}
		catch (UnknownHostException e)
		{
			System.out.println (e);
			//* s is already null;
		}

		return s;
	}

	public void init ()
	{
		System.out.println ("init");
		System.out.println ("host: " + getHostname ());
		System.out.println ("name: " + getName ());
	}

	public void start()
	{
		System.out.println ("start");
	}

	public void stop()
	{
		System.out.println ("stop");
	}

	public static void main (String[] args)
	{
		HostName x = new HostName();
		x.init();
		x.start();
		x.stop();
	}
}
