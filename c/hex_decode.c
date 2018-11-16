#include <stdio.h>

char* bytes2hex(const unsigned char* bytes, size_t len, char* out);

unsigned char bytes[] = { 0x43, 0x12, 0x76, 0x89, 0x00 };

void main() 
{
	char buffer[11];
	bytes2hex(bytes, 5, &buffer);
	buffer[10] = 0;
	printf("%s", buffer);
}

char* bytes2hex(const unsigned char* bytes, size_t len, char* out)
{
	int i;
	int j;
	unsigned int hn, ln;
	char hi;
	char lo;

	for (i = 0, j = 0; i < len; i++, j += 2) {
		hi = (bytes[i] >> 4) & 0x0F;
		lo = (bytes[i]) & 0x0F;
		printf("bytes: %0x %0x\n", hi, lo);
		ln = lo > 9 ? lo + 'A' : lo + '0';
		hn = hi > 9 ? hi + 'A' : hi + '0';
		printf("chars: %c %c\n", hn, ln);
		out[j] = hn;
		out[j + 1] = ln;
	}

	return out;
}
