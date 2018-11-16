//* This file must be saved in Unicode format, and be compiled
//* using the following command line:
//*   javac NonEnglishFiles.java

import java.io.*;
import java.nio.charset.*;

public class NonEnglishFiles
{
	public static void main(String[] args)
	{
		try
		{
			//* The file that contains the filenames to create must be saved in
			//* UTF-8 format for this test to work.
			BufferedReader br = new BufferedReader(
				new InputStreamReader(
					new FileInputStream("filenames.dat"),
					Charset.forName("UTF-8")));

			while (br.ready())
			{
				String line = br.readLine();

				int pos = line.indexOf('/');
				if (pos >= 0)
				{
					createDir (line.substring(0, pos));
				}
				createFile(line);
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}

	static void createDir (String dir)
	{
		//try
		//{
			new File (dir).mkdir();
		//}
		//catch (IOException e)
		//{
		//	System.out.println(e);
		//}
	}

	static void createFile (String name)
	{
		try
		{
			new File (name).createNewFile();
		}
		catch (IOException e)
		{
			System.out.println (e);
		}
	}
}
