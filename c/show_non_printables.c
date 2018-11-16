#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
	long linecounter = 1;
	long charcounter = 1;
	int ch;

	while ((ch = getchar()) != EOF)
	{
		if (ch == 0 || ch > 255)
		{
			printf("%d found at %d:%d\n", ch, linecounter, charcounter);
		}

		if (ch == '\n' || ch == '\r')
		{
			linecounter++;
			charcounter = 1;
		}

		charcounter++;
	}
}
