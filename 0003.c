#include <stdio.h>
#include <math.h>
#include <inttypes.h>

int main() {
  int64_t number = 600851475143;
  int divisor = 2;
  while (number > 1) {
    if (0 == (number % divisor)) {
      printf("%lld, %d\n", number, divisor);
      number /= divisor;
    } else {
      divisor++;
    }
  }
  return 0;
}
