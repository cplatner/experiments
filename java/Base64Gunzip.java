
import java.io.*;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Gunzip
{

	public static void main(String[] args)
	{
		BufferedReader reader = null;
		FileWriter writer = null;

		try
		{
			reader = new BufferedReader(new FileReader(args[0]));
			writer = new FileWriter(args[1]);

			while (true)
			{
				String line = reader.readLine();
				if (line != null
					&& line.endsWith("content-encoding=\"base64-gzip\""))
					continue;

				if (line != null)
				{
					byte[] bytes = decode(line.getBytes());

					for (int i = 0; i < bytes.length; i++)
					{
						writer.write(bytes[i]);
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (reader != null) reader.close();

				if (writer != null) writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static byte[] encode(byte[] data)
	{
		BASE64Encoder base64 = new BASE64Encoder();
		return base64.encode(data).getBytes();
	}

	public static byte[] decode(byte[] data)
	{
		try
		{
			BASE64Decoder base64 = new BASE64Decoder();
			return base64.decodeBuffer(new String(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}