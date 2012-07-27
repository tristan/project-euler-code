#include <iostream>
#include <vector>
#include <functional>
#include "primes.h"

void fn0() {
  auto primes = prime_sieve<500000>().getprimes();

  int cons_count = 0;
  int first = 0;
  for (int i = 2; cons_count < 4 ;i++) {
    int factors = 0;
    std::vector<int> f;
    for (auto x = primes.begin(); *x < i / 2; x++) {
      if (i % *x == 0) {
        factors++;
        f.push_back(*x);
      }
    }
    if (factors == 4) {
      //std::cout << i << ": " << f[0] << " " << f[1] << " " << f[2] << " " << f[3] << std::endl;
      if (cons_count == 0) {
        first = i;
      }
      cons_count++;
    } else {
      cons_count = 0;
    }
  }
  std::cout << first << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
