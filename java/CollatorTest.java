import java.text.*;
import java.util.*;

/**********************************************************
* This program demonstrates that a wrong sorting rule is
* applied to the letters v and w, when using the
* Norwegian Locale ("no", "NO").
* It's a slightly modified version of the program reported
* with Bug ID 4804273.
***********************************************************/
public class CollatorTest {

  /********************************************************
  *********************************************************/
  public static void main (String[] args) {
    Locale loc = new Locale ("no", "NO");   // Norwegian

    Locale.setDefault (loc);
    Collator col = Collator.getInstance ();

    String[] data = {"wird",
                     "vird",
                     "verd",
                     "werd",
                     "vard",
                     "ward"};
    Arrays.sort (data, col);

    System.out.println ("Using " + loc.getDisplayName());
    for (int i = 0;  i < data.length;  i++) {
      System.out.println (data[i]);
    }//end for
  }//end main

}//end class CollatorTest

