import java.io.*;
public class FileRWTest
{
	public static void main (String[] args)
	{
		try
		{
			File f1 = new File ("c:/file1.txt");
			f1.createNewFile ();
			f1.setReadOnly ();
			f1.renameTo (new File ("c:/foo.txt"));

			//		File f2 = new File ("c:\file2.txt");
			//		File f3 = new File ("c:\file3.txt");
		}
		catch (IOException e)
		{
			System.out.println (e);
		}
	}
}
