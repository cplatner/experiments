import java.util.*;

public class LoadResource
{
	public static void main (String[] args)
	{
		//* Set the locale with -Duser.language=<lang> -Duser.country=<country>
		System.out.println (Locale.getDefault());

		try
		{
			ResourceBundle rb = ResourceBundle.getBundle("test");
			System.out.println("Loaded");
			System.out.println(rb.getString("key1"));
			System.out.println(rb.getString("key2"));
		}
		catch (MissingResourceException e)
		{
			System.out.println(e);
		}
	}
}
