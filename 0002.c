#include <stdio.h>

void fn0() {
  int f1 = 1;
  int f2 = 2;
  int sum = 0;
  while (f2 < 4000000) {
    if (f2 % 2 == 0)
      sum += f2;
    f2 = f1^f2;
    f1 = f1^f2;
    f2 = f1 + (f1^f2);
  }
  printf("%d\n", sum);
}

void (*solutions[])() = { fn0, NULL };
