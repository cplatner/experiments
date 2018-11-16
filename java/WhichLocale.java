import java.util.*;

public class WhichLocale
{
	public static void main (String[] args)
	{
		//* Set the locale with -Duser.language=<lang> -Duser.country=<country>
		System.out.println (Locale.getDefault());

		Properties p = System.getProperties();

		for (Enumeration e = p.propertyNames(); e.hasMoreElements(); )
		{
			String key = (String) e.nextElement();
			String value = p.getProperty(key);
			System.out.println (key + "=" + value);
		}
	}
}
