#include <iostream>
#include <vector>
#include <cmath>
#include <functional>

int count_factors(int n) {
  int r = 0;
  //std::cout << n << ": ";
  for (int i = 1; i < std::sqrt(n); i++) {
    if (n % i == 0) {
      //std::cout << i << " ";
      r+=2;
    }
  }
  //std::cout << std::endl;
  return r;
}

void fn0() {
  int sum = 0;
  for (int i = 1; ; i++) {
    sum += i;
    int s = count_factors(sum);
    //std::cout << sum << ": " << s << std::endl;
    if (s > 500) {
      std::cout << sum << std::endl;
      return;
    }
  }
}

std::vector<std::function<void()>> progs = { fn0 };
