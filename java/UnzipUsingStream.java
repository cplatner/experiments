import java.util.*;
import java.io.*;
import java.util.zip.*;

public class UnzipUsingStream
{
	public static void main(String[] args)
	{
		try
		{
			String inFilename = "UnzipUsingStreamTest.zip";
			ZipInputStream zip = new ZipInputStream(new FileInputStream(inFilename));

			ZipEntry entry = null;
			while ((entry = zip.getNextEntry())!= null) {
				long size = entry.getSize();
				System.out.print("Reading: " + entry.getName() + ", size=" + size);

				byte[] buf = new byte[(int) size];

				int total = 0;				
				while (total < size) {					
					int len = zip.read(buf, total, ((int) size) - total);
					System.out.print(", len=" + len);
					total += len;
				}
				System.out.println("");
				String s = new String(buf, 0, buf.length);
				System.out.println(s);
			}

			zip.close();
		}
		catch (IOException e) {
			System.out.println("Problem reading ZIP file: " + e);
		}
	}
}
