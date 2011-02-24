#include <stdio.h>

void main() {
  int n = 20;
  int val = n*n-n;
  int i;
  unsigned char t = 0;
  while(!t) {
    t = 1;
    val+=n;
    for (i = n; i > 1; --i) {
      if (val % i != 0) {
	t = 0;
	break;
      }
    }
  }
  printf("%d", val);
}
