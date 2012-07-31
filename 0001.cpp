#include <iostream>
#include <vector>
#include <functional>

void fn0() {
  int sum = 0;
  for (int i = 0; i < 1000; i++) {
    if (i % 5 == 0 || i % 3 == 0) {
      sum += i;
    }
  }
  std::cout << sum << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
