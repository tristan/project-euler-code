#include <iostream>

int fact(long int num) {
  int divisor = 2;
  while (num > 1) {
    if (0 == (num % divisor)) {
      num /= divisor;
    } else {
      divisor++;
    }
  }
  return divisor;
}

int main() {
  //std::cout << fact(13195) << std::endl;
  std::cout << fact(600851475143) << std::endl;
}
