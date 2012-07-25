#include <iostream>
#include <vector>
#include <array>
#include <functional>
#include <algorithm>
#include "primes.h"
#include "utils.h"

void fn0() {  
  auto sieve = prime_sieve<1000001>().getsieve();
  std::vector<int> num { 1, 1 }; // it's really faster to keep these numbers in a vector and to convert them to integers rather than the other way around
  std::function<void()> inc2 = [&num] () {
    for (int i = 0; i < num.size(); i++) {
      if (num[i] == 9) {
        if (i == 0) {
          num[i] = 1;
        } else {
          num[i] = 0;
        }
      } else {
        if (i == 0) {
          num[i]+=2;
        } else {
          num[i]++;
        }
        return;
      }
    }
    num.push_back(1);
  };

  int count = 0;
  int sum = 0;
  for (int i = 11; count < 11 && i < 1000000; i+=2, inc2()) {
    if (sieve[i]) {
      auto l = num.begin();
      int s = *l;
      l++;
      int x = 10;
      while (l != num.end() && sieve[s]) {
        s += *l * x;
        x *= 10;
        l++;
      }
      if (!sieve[s]) {
        continue;
      }
      // do the reverse
      auto r = num.rbegin();
      s = *r;
      r++;
      while (r != num.rend() && sieve[s]) {
        s *= 10;
        s += *r;
        r++;
      }
      if (!sieve[s]) {
        continue;
      }
      count++;
      sum += i;
    }
  }
  std::cout << sum << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };

