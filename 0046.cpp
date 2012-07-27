#include <iostream>
#include <vector>
#include <functional>
#include "primes.h"

void fn0() {
  auto sieve = prime_sieve<100000>().getsieve();

  for (int i = 9; ; i+=2) {
    if (!sieve[i]) {
      for (int j = 1; ; j++) {
        int x = 2 * (j * j);
        if (x >= i) {
          std::cout << i << std::endl;
          return;
        }
        if (sieve[i - x]) {
          break;
        }
      }
    }
  }
}

std::vector<std::function<void()>> progs = { fn0 };
