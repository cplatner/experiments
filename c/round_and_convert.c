#include "stdio.h"
#include "stdint.h"
#include "strings.h"

/** @brief Convert a positive 16 bit integer bump value to a string.
 * convertedValue points to a buffer that is at least 4 characters long.
 *
 * Input number is +/- 32767, which corresponds to +/-6g.  Assume 1 g is 5462.167.
 *
 * If the value is less than .1, then return 0.
 *
 * Only show 2 digits of precision (i.e. .25 or 4.1)
 *
 * Note that this function takes a number of shortcuts because the nature of
 * both the input value and the output buffer are known.
 */
static void round_and_convert(uint16_t bumpValue, char* convertedValue)
{
	//* Fudge factor is 32767 / 6.
	double value = (double) bumpValue / 5462.167;
	//* We are going to round this to 2 digits, so bump up the value
	value += 0.005;

	if (value < 0.11) {
		convertedValue[0] = '0';
		convertedValue[1] = 0;
	}
	else if (value < 1.0) {
		//* Quick and dirty round.
		int temp = (int) (value * 100.0);
		convertedValue[0] = '.';
		//* Note: these indexes are switched on purpose!
		convertedValue[2] = (temp % 10) + '0';
		temp /= 10;
		convertedValue[1] = (temp % 10) + '0';
		convertedValue[3] = 0;
	}
	else {
		//* Quick and dirty round.
		//* Already added in some extra for rounding above - make it an even .05 here.
		value += 0.045;
		int temp = (int) (value * 10.0);
		//* Note: these indexes are switched on purpose!
		convertedValue[1] = '.';
		convertedValue[2] = (temp % 10) + '0';
		temp /= 10;
		convertedValue[0] = (temp % 10) + '0';
		convertedValue[3] = 0;
	}
}

void test_round(int value)
{
	char temp[20];

	round_and_convert(value, temp);
	printf ("%d => %s\n", value, temp);
}

int main(void)
{
	test_round(0);
	test_round(1);
	test_round(545);
	test_round(546);
	test_round(547);
	test_round(600);
	test_round(800);
	test_round(1400);
	test_round(3000);
	test_round(4000);
	test_round(5461);
	test_round(5462);
	test_round(5463);
	test_round(10000);
	test_round(15000);
	test_round(20100);
	test_round(32766);
	test_round(32767);
	test_round(32768);

	return 0;
}
