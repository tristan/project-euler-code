#include <iostream>
#include <vector>
#include <functional>
#include <algorithm>
#include "primes.h"

void fn1() {
  auto s = prime_sieve<1000001>();
  auto primes = s.getprimes();
  auto sieve = s.getsieve();

  std::array<int, 78499> sums { 0 };
  int largest = 0;
  int start = 0;
  for (int i = 0; i < primes.size(); i++) {
    for (int j = 0; sums[j] < 1000000 && i+j < primes.size(); j++) {
      //std::cout << i << ", " << j << std::endl;
      sums[j] += primes[i+j];
      if (sums[j] < 1000000 && sieve[sums[j]]) {
        largest = sums[j];
        start = j;
      }
    }
  }
  std::cout << largest << " (" << primes[start] << ")" << std::endl;
}

std::vector<std::function<void()>> progs = { fn1 };
