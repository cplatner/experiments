import java.util.*;

public class Timezone
{
	public static void main(String[] args)
	{
		
		for (String zone : TimeZone.getAvailableIDs())
		{
			System.out.println(getZoneInfo(zone));
		}
	}

	public static String getZoneInfo(String zonename)
	{
//				TimeZone tz = TimeZone.getDefault();
			TimeZone tz = TimeZone.getTimeZone(zonename);
			
			return tz.getID() + "\t"
				+ "(" + tz.getDisplayName(false, TimeZone.SHORT) + "/"
				+ tz.getDisplayName(true, TimeZone.SHORT) + ")";

	}
}
