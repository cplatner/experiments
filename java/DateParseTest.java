import java.text.*;
import java.util.*;

public class DateParseTest
{
  public static void main(String[] args)
  {
    SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = new GregorianCalendar();

    parseWithFormat("1990-10-27T12:34:56.789", "yyyy-MM-dd'T'HH:mm:ss.SSS");
    parseWithFormat("1990-10-27T12:34:56.789-0600", "yyyy-MM-dd'T'HH:mm:ss.SSS");
    parseWithFormat("1990-10-27T12:34:56", "yyyy-MM-dd'T'HH:mm:ss.SSS");
    parseWithFormat("1990-10-27", "yyyy-MM-dd");
    parseWithFormat("1990-10-27T12:34:56.789", "yyyy-MM-dd");
    parseWithFormat("1990-10-27T12:34:56.789-0600", "yyyy-MM-dd");
    parseWithFormat("1990-10-27 12:34:56.789", "yyyy-MM-dd");
    parseWithFormat("", "yyyy-MM-dd");
    parseWithFormat(null, "yyyy-MM-dd");

  }

  private static void parseWithFormat(String datestr, String format)
  {
    System.out.println("Parsing " + datestr + " with " + format);
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    try {
      Date date = formatter.parse(datestr);
      System.out.println("Parse date only: " + date);
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println();
  }
}
