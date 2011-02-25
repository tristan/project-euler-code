#include <stdio.h>

int gcd(long int a, long int b) {
  if (b == 0)
    return a;
  return gcd(b, a % b);
}
int lcm(long int a, long int b) {
  return (a * b) / gcd(a, b);
}
void main() {
  long int i;
  long int val = 1;
  for (i = 2; i < 21; i++)
    val = lcm(val, i);
  printf("%d\n", val);
}
