//* Test warning suppression
//* Compile this file with
//*   javac -Xlint:all
//*
//* Note the 2 different ways of specifying warning suppression.
//*
import java.util.*;
import java.io.*;

@SuppressWarnings(value={"fallthrough", "unchecked"})
public class Warnings
{
//	@SuppressWarnings(value={"fallthrough"})
	public static void main (String[] args)
	{
		int x = 0;

		if ((x = 1) > 0)
		{
			;
		}

		switch (x) {
		case 1:
	       System.out.println("1");
	       //  No  break;  statement here.
		case 2:
	       System.out.println("2");
		}
	}

//	@SuppressWarnings("unchecked")
	private void unchecked()
	{
		List list = new ArrayList();

		list.add("one");
	}
}

@SuppressWarnings(value={"serial"})

class Test implements Serializable
{
	private void writeObject(ObjectOutputStream out)
		throws IOException
	{
	}

	private void readObject(ObjectInputStream in)
		throws IOException, ClassNotFoundException
	{
	}
}