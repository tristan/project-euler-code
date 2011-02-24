#include <stdio.h>

void main() {
  int i;
  int j;
  int val;
  char valasstr[6];
  int max = 99999;
  for (i = 999; i > 100; --i) {
    for (j = 999; j >= i; --j) {
      val = i*j;
      if (val < 1000000 && val > max) {
	sprintf(valasstr, "%d", val);
	if (valasstr[0] == valasstr[5] &&
	    valasstr[1] == valasstr[4] &&
	    valasstr[2] == valasstr[3]) {
	  max = val;
	}
    }
  }
  printf("%d\n", max);
}
