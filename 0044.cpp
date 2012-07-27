#include <iostream>
#include <vector>
#include <functional>
#include <cmath>

int pn(int n) {
  return n * (3 * n - 1) / 2;
}

bool is_pentagonal(int x) {
  double n = (std::sqrt(24 * x + 1) + 1) / 6;
  return (std::remainder(n, 1.0) == 0.0);
}

// test if is_triangle always returns expected results
// as i'm always weary when working with floating points
void test_is_pentagonal() {
  for (int n = 1; n < 100000; n++) {
    int x = pn(n);
    if (x < 0) {
      break; // we've gone too far!
    }
    if (!is_pentagonal(x)) {
      std::cout << "FAILED TEST: " << x << " is t" << n << " but returned not pentagonal" << std::endl;
    }
  }
}

void fn0() {
  std::vector<int> nums { 1 };
  for (int i = 2; ; i++) {
    int pk = pn(i);
    for (int pj: nums) {
      if (is_pentagonal(pj + pk) && is_pentagonal(pk - pj)) {
        std::cout << std::abs(pk - pj) << std::endl;
        return;
      }
    }
    nums.push_back(pk);
  }
}

std::vector<std::function<void()>> progs = { fn0 };
