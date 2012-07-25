#include <iostream>
#include <vector>
#include <functional>

void fn0() {
  int sum = 1;
  int x = 1;
  for (int w=2; w < 1001; w+=2) {
    for (int i= 0; i < 4; i++) {
      x += w;
      sum += x;
    }
  }
  std::cout << sum << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
