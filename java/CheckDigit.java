public class CheckDigit
{
	static public int getCheckDigit (String s)
	{
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt (i);
			int c = ch - 'A' + 1;
			sum = (sum + c * (i + 1) ) % 10;

			System.out.println ("sum: " + sum + ", ch: " + ch + ", c: " + c + ", c*i: " + (c * i) );

		}
		return sum;
	}

	public static void main (String[] args)
	{
		System.out.println ("str: " + args[0] + ", d: " + getCheckDigit (args[0]));
	}
}

