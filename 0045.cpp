#include <iostream>
#include <vector>
#include <functional>
#include <cmath>

using __int = long long unsigned int;

bool is_triangle(__int x) {
  double sq = std::sqrt(8.0 * x + 1);
  return (std::remainder(sq, 1.0) == 0.0);
}

bool is_pentagonal(__int x) {
  double n = (std::sqrt(24 * x + 1) + 1) / 6;
  return (std::remainder(n, 1.0) == 0.0);
}

bool is_hexagonal(__int x) {
  double n = std::sqrt(8.0 * x + 1) / 4;
  return (std::remainder(n, 1.0) == 0.0);
}

__int hexagonal(int n) {
  return n * (2 * n - 1);
}

__int pentagonal(int n) {
  return n * (3 * n - 1) / 2;
}

__int triangle(int n) {
  return n * (n + 1) / 2;
}


void fn0() {
  for (__int i = 144; ; i++) {
    __int h = hexagonal(i);
    //std::cout << h << std::endl;
    if (is_pentagonal(h) && is_triangle(h)) {
      std::cout << h << "(" << i << ")" << std::endl;
      return;
    }
  }
}

std::vector<std::function<void()>> progs = { fn0 };
