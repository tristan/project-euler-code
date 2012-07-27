#include <iostream>
#include <vector>
#include <functional>
#include <fstream>
#include <set>
#include <string>
#include <cmath>

bool is_triangle(int x) {
  double sq = std::sqrt(8.0 * x + 1);
  return (std::remainder(sq, 1.0) == 0.0);
}

// test if is_triangle always returns expected results
// as i'm always weary when working with floating points
void test_is_triangle() {
  for (int n = 1; n < 1000000; n++) {
    int x = (n * (n + 1)) / 2;
    if (x < 0) {
      break; // we've gone too far!
    }
    if (!is_triangle(x)) {
      std::cout << "FAILED TEST: " << x << " is t" << n << " but returned not triangle" << std::endl;
    }
  }
}

void fn0() {
  std::fstream in("words.txt");
  int cnt = 0;
  while (!in.eof()) {
    int c = in.get();
    if (c == '"') {
      int val = 0;
      while ((c = in.get()) != '"') {
        val += (c - '@'); // @ is A-1
      }
      if (is_triangle(val)) {
        cnt++;
      }
    }
  }
  std::cout << cnt << std::endl;
}

std::vector<std::function<void()>> progs = { test_is_triangle, fn0 };
