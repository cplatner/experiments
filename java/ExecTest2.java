import java.io.*;

public class ExecTest2
{
	public static void main(String[] args)
	{
		String filename = args[0];

		String cmd = "cmd.exe /c ";

		if (filename.indexOf('(') >= 0 || filename.indexOf(')') >= 0
			|| filename.indexOf('<') >= 0 || filename.indexOf('>') >= 0
			|| filename.indexOf('@') >= 0 || filename.indexOf('^') >= 0
			|| filename.indexOf('|') >= 0 || filename.indexOf('&') >= 0)
		{
			//* These are odd characters:
			//*    & < > ( ) @ ^ |
			//*
			//* Wrap filenames with odd characters with 2 sets of quotes.
			//* The Windows cmd.exe will strip the first one, and the
			//* second one will allow the odd characters to be passed
			//* through.
			//*
			//* See http://www.microsoft.com/resources/documentation/windows/xp/all/proddocs/en-us/cmd.mspx?mfr=true
			cmd += "\"\"" + filename + "\"\"";
		}
		else
		{
			cmd += "\"" + filename + "\"";
		}

//		String editorLocation = "notepad.exe";
//		cmd = editorLocation + " " + '\"' + filename + '\"';

//		editorLocation = "xterm -e vi ";
//		cmd = editorLocation + " " + filename;

		System.out.println(cmd);;

		try
		{
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(cmd);

			InputStream is = p.getErrorStream();
			int count = 0;
			while ((count = is.available()) > 0)
			{
				byte[] b = new byte[count];
				int len = is.read(b, 0, count);
				for (int i = 0; i < len; i++)
				{
					System.out.print((char) b[0]);
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}
