#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#include <stdio.h>

//* Retrieve the short path for a given path, if that path has spaces in it.
int main(int argc, char* argv[])
{
	long length;
	char buffer[MAX_PATH + 1];

	if (argc != 2) {
		fprintf(stderr, "usage: shortpath <path>\n");
		return 1;
	}

	//* Input path has no spaces - we don't need to use the short path.
	if (strchr(argv[1], ' ') == NULL) {
		printf("%s\n", argv[1]);
		return 0;
	}

	//* Get size needed by passing NULL and 0.  Length includes null at end.
    length = GetShortPathName(argv[1], NULL, 0);
    if (length == 0) {
		fprintf(stderr, "Can't get short path length\n");
		return 1;
	}

	//* Call again with buffer to get path
    length = GetShortPathName(argv[1], buffer, length);
    if (length == 0) {
		fprintf(stderr, "Can't retrieve short path\n");
		return 1;
	}

    printf("%s\n", buffer);

	return 0;
}
