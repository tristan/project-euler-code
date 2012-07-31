#include <vector>
#include <functional>
#include <iostream>

void fn0() {
  int sum = 0;
  for (int a = 1, b = 2; b <= 400000000;) {
    if (b % 2 == 0) {
      sum += b;
    }
    int t = a;
    a = b;
    b = t+b;
  }
  std::cout << sum << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
