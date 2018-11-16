/** Demonstrate what javac actually does internally to declarations
 * in interfaces.
 *
 * After compiling this, run javap:
 *
 * javap -c -constants BareInterface.class
 */
public interface BareInterface {

    public static final String test1 = "test1";
    String test2 = "test2";
    // Not valid in interfaces
    // private String test3 = "test3";
    // Not valid in interfaces
    // protected String test4 = "test4";
    public String test5 = "test5";
    public final String test6 = "test6";
}

