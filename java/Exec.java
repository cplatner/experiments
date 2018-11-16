//* Sample showing how to get return value from running a command

import java.io.IOException;

public class Exec
{
	public static void main (String[] args)
	{
		Runtime runtime = Runtime.getRuntime();
		try
		{
			Process proc = runtime.exec("cmd /c dir");
			System.out.println (proc.exitValue());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
