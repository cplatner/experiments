import java.text.*;
import java.util.*;

public class EnumSetTest
{
    public enum NameValidity {
        LENGTH_BAD, PATTERN_BAD, UNIQUE_BAD 
    };

    // public static final Set nameValid = Collections.unmodifiableSet(EnumSet.noneOf(NameValidity.class));
    public static final EnumSet<NameValidity> nameValid = EnumSet.noneOf(NameValidity.class);

  public static void main(String[] args)
  {

    nameValid.addAll(EnumSet.range(NameValidity.LENGTH_BAD, NameValidity.UNIQUE_BAD)); // enable all constants
    nameValid.removeAll(EnumSet.of(NameValidity.PATTERN_BAD, NameValidity.UNIQUE_BAD)); // disable a couple
    // assert EnumSet.of(NameValidity.BOLD, NameValidity.ITALIC).equals(nameValid); // check set contents are correct
    System.out.println("Name Valid: " + nameValid);
    System.out.println(nameValid.isEmpty());
    nameValid.remove(NameValidity.LENGTH_BAD);

    System.out.println("Name Valid: " + nameValid);
    System.out.println(nameValid.isEmpty());
  }
}
