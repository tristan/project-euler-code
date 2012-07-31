#include <iostream>
#include <vector>
#include <functional>
#include <algorithm>
#include "primes.h"

void fn0() {
  auto primes = prime_sieve<10000>().getprimes();
  auto start = primes.begin();
  while (*start < 1000) {
    start++;
  }
  for (auto one = start; one != primes.end(); one++) {
    for (auto two = one+1; two != primes.end(); two++) {
      for (auto three = two+1; three != primes.end(); three++) {
        if (*one == 1487 && *two == 4817 && *three == 8147) {
          continue;
        }
        if (*two - *one == *three - *two) {
          std::array<bool, 10> a {0};
          std::array<bool, 10> b {0};
          std::array<bool, 10> c {0};

          for (int i = *one, j = *two, k = *three; i > 0; i /= 10, j /= 10, k /= 10) {
            a[i % 10] = true;
            b[j % 10] = true;
            c[k % 10] = true;
          }

          if (a == b && b == c) {
            std::cout << *one << " " << *two << " " << *three << std::endl;
            return;
          }
        }
      }
    }
  }
}

std::vector<std::function<void()>> progs = { fn0 };
