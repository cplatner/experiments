import java.util.Random;

public final class ConstrainedRandom
{
	private static Random _rng = new Random();

	public static final void main(String[] aArgs)
	{
		for (int i = 0; i < 10; i++) {
			System.out.println(otp(5));
		}
	}

	public static String otp(int len)
	{
		String value = "";
		int rnd = 0;

		for (int i = 0; i < len; i++) {

			do {
				rnd = _rng.nextInt(10);
			} while (rnd == 0 || rnd == 1 || rnd == 3 || rnd == 8);
			value += rnd;
		}

		return value;
	}
}
