import java.util.*;
import java.awt.*;

public class ListWindows
{
	public static void main (String[] args)
	{
		Window[] windows = Window.getWindows();

		for (int i = 0; i < windows.length; i++)
		{
			System.out.println (windows[i].getName());
		}
	}
}
