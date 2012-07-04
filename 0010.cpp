#include <iostream>
#include <array>
#include <vector>
#include <future>

template <long int smax>
long int sumprimes() {
  std::array<bool, smax> sieve;
  sieve.fill(true);
  long int i = 2;
  long int sum = 2;
  while (i < smax - 1) {
    long int j = i+i;
    while (j < smax - 1) {
      // mark all the multiples of i in the sieve as not-prime
      sieve[j] = false;
      j += i;
    }
    while (i < smax - 1) {
      // find the next value in the sieve that is still true (meaning it's prime)
      i += 1;
      if (sieve[i] == true) {
        break;
      }
    }
    if (i >= smax / 2) {
      // after we have checked sqrt(smax) numbers, we wont find any more
      // so everything true value left in the sieve will be a prime
      while (i < smax - 1) {
        if (sieve[i] == true) {
          sum += i;
        }
        i += 1;
      }
    } else {
      sum += i;
    }
  }
  return sum;
}

int main() {
  std::cout << sumprimes<2000000>() << std::endl;
  return 0;
}
