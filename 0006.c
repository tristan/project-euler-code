#include <stdio.h>

void fn0() {
  int i;
  long int sumofsq = 0;
  long int sum = 0;
  for (i = 1; i < 101; i++) {
    sumofsq += (i * i);
    sum += i;
  }
  printf("%d\n", sum * sum - sumofsq);
}

void (*solutions[])() = { fn0, NULL };
