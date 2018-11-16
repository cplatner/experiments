	private static final char HEX_DIGITS[] =
	{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
	'E', 'F' };

	private static String btoa(byte a)
	{
		return "" + HEX_DIGITS[(a >> 4) & 0xf] + HEX_DIGITS[a & 0xf];
	}

	//* Little:
	//* +----+----+----+----+
	//* | B0 | B1 | B2 | B3 |
	//* +----+----+----+----+
	//* Big:
	//* +----+----+----+----+
	//* | B3 | B2 | B1 | B0 |
	//* +----+----+----+----+
	//*
	private static int littleToBigEndian(int value)
	{
		int val =
		((value & 0xFF000000) >>> 24) | ((value & 0x00FF0000) >>>  8)
		| ((value & 0x0000FF00) <<  8) | ((value & 0x000000FF) << 24);

		return val;
	}


		private static byte[] cvtToByteArray(long value)
	{
		byte[] bytes = new byte[8];
		int len = bytes.length;
		for (int i = 0; i < len; i++)
		{
			int offset = (len - i - 1) * 8;
			bytes[i] = (byte) ((value >>> offset) & 0xff);
		}

		return bytes;
	}

