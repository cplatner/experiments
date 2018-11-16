public class EnumTest
{
    public static void main(String[] args) 
    {
        Severity info = Severity.NONE;
        Severity warning = Severity.WARNING;
        Severity error = Severity.ERROR;
        Severity fatal = Severity.FATAL;
        
        System.out.println(info + ", " + info.ordinal());
        System.out.println(warning + ", " + warning.ordinal());
        System.out.println(error + ", " + error.ordinal());
        System.out.println(fatal + ", " + fatal.ordinal());
        System.out.println("fatal > warning: " + (fatal.ordinal() > warning.ordinal()));
    }
    
    public enum Severity 
    { 
        NONE, WARNING, ERROR, FATAL;
    }
    

    // public boolean severityExceeds(Severity sev){
    //     boolean ret = false;
    //     Severity thisSev = getMaxSeverity();
    //     switch (sev){
    //         case NONE:
    //             ret = true;
    //             break;
    //         case WARNING:
    //             ret = (thisSev == Severity.ERROR || thisSev == Severity.FATAL);
    //             break;
    //         case ERROR:
    //             ret = (thisSev == Severity.FATAL);
    //             break;
    //         case FATAL:
    //             ret = (thisSev != Severity.FATAL);
    //     }
    //     return ret;
    // }
}
