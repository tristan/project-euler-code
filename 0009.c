#include <stdio.h>
#include <math.h>

void fn0() {
  int a = 100;
  int b = 100;
  while (1) {
    if (b > a) {
      b = 100;
      a += 1;
    }
    double c = sqrt(a * a + b * b);
    if (fmod(c, 1.0) == 0 && a + b + (int)c == 1000) {
      printf("%d\n",  a * b * (int)c);
      return;
    }
    b += 1;
  }
}

void (*solutions[])() = { fn0, NULL };
