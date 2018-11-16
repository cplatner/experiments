import java.util.*;
import java.text.*;

public class DateFormat {
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String d = sdf.format(date);
		System.out.println(d);

		try {
			SimpleDateFormat parser = new SimpleDateFormat();
			Date d1 = parser.parse("2012-12-31");
			System.out.println(d1);
			Date d2 = parser.parse("2012-12-31 10:31");
			System.out.println(d2);
			Date d3 = parser.parse("12/31/2012");
			System.out.println(d3);
			Date d4 = parser.parse("12/31/2012 10:33");
			System.out.println(d4);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
