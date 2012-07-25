#include <iostream>
#include <vector>
#include <array>
#include <functional>
#include <algorithm>
#include "primes.h"

std::vector<int> nums(int n) {
  std::vector<int> r;
  while (n > 0) {
    r.push_back(n % 10);
    n /= 10;
  }
  return r;
}

void fn0() {
  auto sieve = prime_sieve<1000001>().getsieve();

  int count = 13;
  for (int i = 101; i < 1000000; i+=2) {
    if (sieve[i] == true) {
      std::vector<int> p_nums = nums(i);
      bool circular = true;
      int x = 1;
      for (int j = 0; j < p_nums.size(); j++) {
        int p = [&p_nums, &i, &x] () -> int {
          int n = 0;
          for (int j = 0; j < p_nums.size(); j++) {
            n += p_nums[j] * x;
            x = x * 10;
            x = x > i ? 1 : x;
          }
          x = x * 10;
          x = x > i ? 1 : x;
          return n;
        }();
        if (sieve[p] == false) {
          circular = false;
          break;
        }
        // remove it so we don't process it again
        //sieve[p] = false;
      }
      if (circular) {
        count += 1;
        //std::cout << i << std::endl;
      }
    }
  }
  std::cout << count << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
