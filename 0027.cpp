#include <iostream>
#include <vector>
#include <functional>
#include <cstdlib>
#include "primes.h"

#define N 100000

std::array<bool, N> sieve;

// generate the sieve
void fn0() {
  sieve = prime_sieve<N>().getsieve();
}

void fn1() {
  int maxa = -1000;
  int maxb = -1000;
  int maxc = 0;
  for (int a = -999; a < 1000; a++) {
    for (int b = -999; b < 1000; b++) {
      int pc = 0;
      for (int n = 0 ; ; n++) {
        int x = (n * n) + (a * n) + b;
        if (x >= 0 && sieve[x]) {
          pc++;
        } else {
          break;
        }
      }
      if (pc > maxc) {
        maxa = a;
        maxb = b;
        maxc = pc;
      }
    }
  }
  std::cout << "a: " << maxa << ", b: " << maxb << ", pc: " << maxc << std::endl;
  std::cout << maxa * maxb << std::endl;
}

std::vector<std::function<void()>> progs = { fn0, fn1 };
