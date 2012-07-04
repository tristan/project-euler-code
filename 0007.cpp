#include <iostream>
#include <array>
#include <vector>
#include <future>

template <int smax>
std::vector<int> genprimes() {
  std::array<bool, smax> sieve;
  sieve.fill(true);
  int i = 2;
  std::vector<int> primes{ 2 };
  while (i < smax - 1) {
    int j = i+i;
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
          primes.push_back(i);
        }
        i += 1;
      }
    } else {
      primes.push_back(i);
    }
  }
  return primes;
}

int main() {
  auto primes = genprimes<1000000>();
  std::cout << primes[10000] << std::endl;
  return 0;
}
