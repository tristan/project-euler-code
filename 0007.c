#include <stdio.h>
#include <math.h>

void fn0() {
  int primes[10001] = {2,3,5,7};
  int primesfound = 4;
  int wheel[] = {2, 4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2, 10};
  int wheellen = sizeof(wheel)/sizeof(int);
  int i, j, w, p;
  for (i = 11, w = 0; primesfound < 10001; i+=wheel[w], w=(w+1) % wheellen) {
    p = 1;
    for (j = 0; j < primesfound && (primes[j] * primes[j]) <= i; j++) {
      if (i % primes[j] == 0) {
	p = 0;
	break;
      }
    }
    if (p) {
      primes[primesfound++] = i;
    }
  }
  printf("%d\n", primes[10000]);
}

void (*solutions[])() = { fn0, NULL };
