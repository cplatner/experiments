import java.util.*;
import java.io.*;

/** Overcome some of the deficiencies of the standard java.io.File class,
  * particularly with regard to the Euro character in file names.
  */
public class ListFiles
{
	public static void main (String[] args)
	{
		File dir = new File ("/temp");
		String[] all = dir.list ();

		System.out.println ("All Files");

		for (int i = 0; i < all.length; i++)
		{
			System.out.println (all[i]);
		}

		System.out.println ("data* Files");

		String[] data = dir.list (new DataFilter ());

		for (int i = 0; i < data.length; i++)
		{
			System.out.println (data[i]);
		}
	}


	static class DataFilter implements FilenameFilter
	{
		public boolean accept (File dir, String name)
		{
			if (name.startsWith ("data") && name.endsWith (".xml"))
				return true;
			else
				return false;
		}
	}
}
