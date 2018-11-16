#define _WIN32_WINNT 0x0400

#include <windows.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
  WIN32_FIND_DATA FindFileData;
  HANDLE hFind;

  printf ("Target file is %s.\n", argv[1]);
  hFind = FindFirstFileEx(argv[1], FindExInfoStandard, &FindFileData,
                 FindExSearchNameMatch, NULL, 0 );

  if (hFind == INVALID_HANDLE_VALUE)
  {
     printf ("Invalid File Handle. GetLastError reports %d\n",
             GetLastError ());
     return (0);
  }
  else
  {
     printf ("The first file found is %s\n",
             FindFileData.cFileName);
     FindClose(hFind);
     return (1);
  }
}