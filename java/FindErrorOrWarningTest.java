import java.text.*;
import java.util.*;
import java.util.regex.*;

public class FindErrorOrWarningTest {

  public static void main (String[] args) {
    System.out.println(getMessageTextWithoutPrefix("ERROR!  Data in this cell has been truncated, and cannot be imported."));
    System.out.println(getMessageTextWithoutPrefix("ERROR:  Data in this cell has been truncated, and cannot be imported."));
    System.out.println(getMessageTextWithoutPrefix("WARNING!  Data in this cell has been truncated, and cannot be imported."));
    System.out.println(getMessageTextWithoutPrefix("WARNING Data in this cell has been truncated, and cannot be imported."));
    System.out.println(getMessageTextWithoutPrefix("Warning: Data in this cell has been truncated, and cannot be imported."));
    System.out.println(getMessageTextWithoutPrefix("error! Data in this cell has been truncated, and cannot be imported."));
    System.out.println(getMessageTextWithoutPrefix("oops, this should still get in."));
  }

  private static String getMessageTextWithoutPrefix(String message) 
  {  
    String retval = null;
    //* Throw away the first word, and any other junk up to the first space.
    //* Use the reluctant quantifier (*?) to avoid greedy problems.
    // String regexPattern = "^\\w.*?\\s+(.*$)";
    String regexPattern = "^(WARNING|ERROR).*?\\s+(.*$)";
    //System.out.println(regexPattern);
    
    Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
    
    Matcher matcher = pattern.matcher(message);
    
    if (matcher.find()) {
        // System.out.println(matcher.group(0));
        // System.out.println(matcher.group(1));
        System.out.print("+");
        retval = matcher.group(2);
    }
    else {
        System.out.print("-");
      retval = message;
    }
    
    return retval;
  }
}
