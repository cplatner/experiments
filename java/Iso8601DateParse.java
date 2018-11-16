import java.text.*;
import java.util.*;
import javax.xml.bind.DatatypeConverter;

public class Iso8601DateParse
{
  public static void main(String[] args)
  {
    System.out.println("valid");
    test("0100-10-27");
    test("1990-10-27");
    test("2190-10-27");
    test("1990-10-27T12:34:56");
    test("1990-10-27T12:34:56-0600");
    test("1990-10-27T12:34:56.789");
    test("1990-10-27T12:34:56.789-0600");
    test("1990-10-27T12:34:56.789-06:00");

    //* invalid formats
    System.out.println("invalid");
    test("-1990");
    test("1990");
    test("1990-11");
    test("1-2-2016");
    test("01-02-2016");
    test("01/02/2016");
    test("1990-10-27 12:34:56.789");
    test("1990,10,27,12,34,56");
    test("now()");
    test("");
    test(null);
  }

  private static void test(String datestr)
  {
    System.out.println("Parsing " + datestr + ", " + StrictDateParser.parse(datestr));
  }
  private static void xtest(String datestr)
  {
    System.out.println("Parsing " + datestr + ", " + parse8601DateJaxb(datestr));
  }

  private static Date parse8601DateJaxb(String datestr)
  {
    Date parseDate = null;
    try {
      Calendar cal = DatatypeConverter.parseDateTime(datestr);
      parseDate = cal.getTime();
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    return parseDate;
  }

  /** Strictly parse dates, using http://www.w3.org/TR/NOTE-datetime
   * as a guide.
   */
  static class StrictDateParser
  {
    private static SimpleDateFormat[] formatters;

    static {
      String[] formats = {
        "yyyy-MM-dd'T'HH:mm:ss.SSSX", // (1997-07-16T19:20:30.45.123+0100)
        "yyyy-MM-dd'T'HH:mm:ss.SSS", // (1997-07-16T19:20:30.45.123)
        "yyyy-MM-dd'T'HH:mm:ssX", // (1997-07-16T19:20:30+0100)
        "yyyy-MM-dd'T'HH:mm:ss", // (1997-07-16T19:20:30)
        // "yyyy-MM-dd'T'HH:mmX", // (1997-07-16T19:20+0100)
        // "yyyy-MM-dd'T'HH:mm", // (1997-07-16T19:20)
        "yyyy-MM-dd",
        // "yyyy-MM",
        // "yyyy",
      };

      formatters = new SimpleDateFormat[formats.length];

      for (int i = 0; i < formats.length; i++) {
        formatters[i] = new SimpleDateFormat(formats[i]);
        //* force strict date string interpretation
        formatters[i].setLenient(false);
      }
    }

  /** Strictly parse a date.  If the date can't be parsed,
    * return null.
   */
    static Date parse(final String datestr)
    {
      if (datestr == null || datestr.isEmpty()) {
        return null;
      }

      //* Don't allow any spaces between datetime parts
      if (datestr.indexOf(' ') >= 0) {
        return null;
      }

      Date parseDate = null;
      for (SimpleDateFormat formatter : formatters) {
        try {
             parseDate = formatter.parse(datestr);
             //System.out.println("parsed with " + formatter.toPattern());
             break;
        } catch (ParseException e) {
          //* parseDate is already null
        }
      }

      return parseDate;
    }
  }
}
