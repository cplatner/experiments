import java.io.*;

public class ExecDemo1
{
	public static void main (String[] args)
	{
		System.out.println("Run a command that will succeed");
		boolean status = runCommand("cmd /c echo \"hi\"");
		System.out.println("Command returned " + status);

		System.out.println("Run a command that won't succeed");
		status = runCommand("cmd /c dir \\xyzasdf");
		System.out.println("Command returned " + status);
	}

	/** Run a command.  Return true if successful, false otherwise */
	static boolean runCommand (String cmd)
	{
		boolean isOkay = true;
		//* Start the command
		Runtime runtime = Runtime.getRuntime ();
		try
		{
			Process proc = runtime.exec (cmd);

			//* Read the input and output, if needed
			InputStream inputstream = proc.getInputStream ();
			InputStreamReader inputstreamreader =
				new InputStreamReader (inputstream);
			BufferedReader bufferedreader = new BufferedReader (inputstreamreader);

			//* Read any output
			String line;
			while ((line = bufferedreader.readLine ()) != null)
			{
				System.out.println (line);
			}

			//* Check for failure
			if (proc.waitFor () != 0)
			{
				System.err.println ("exit value = " + proc.exitValue ());
				isOkay = false;
			}
		}
		catch (IOException e)
		{
			System.err.println (e);
			isOkay = false;
		}
		catch (InterruptedException e)
		{
			System.err.println (e);
			isOkay = false;
		}

		return isOkay;
	}
}
