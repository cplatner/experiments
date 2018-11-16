import java.util.*;

public class HeapEater
{
  public static void main(String[] args)
  {
    List<Long> list = new ArrayList<>();

      for (long i = 0 ; i < 10000000L; i++ ) {
          list.add(new Long(i));
          try { Thread.sleep(1); } catch (Exception e) { }
      } 
  }
}
