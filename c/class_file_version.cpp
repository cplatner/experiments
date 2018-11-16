#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

//  Length (number of bytes) Example
//magic 4 0xCAFEBABE
//minor_version 2 0x0003
//major_version 2 0x002D

int main(int argc, char *argv[])
{
	if (argc < 2)
	{
		printf("Supply a filename\n");
		exit(1);
	}

	int fd = open(argv[1], 0);

	unsigned char buf[8];

	if (fd != 0)
	{
		read(fd, buf, sizeof(buf));

		printf("Magic Cookie:  0x%0X%0X%0X%0X\n",
			buf[0] & 0xff, buf[1] & 0xff, buf[2] & 0xff, buf[3] & 0xff);
		printf("Major version: %d\n", ((buf[6] & 0xff) << 2) + buf[7]);
		printf("Minor version: %d\n", ((buf[4] & 0xff) << 2) + buf[5]);
	}
}
