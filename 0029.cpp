#include <iostream>
#include <vector>
#include <functional>
#include <cmath>
#include <set>

void fn0() {
  std::set<long double> results;
  int max = 101;
  for (int i = 2; i < max; i++) {
    for (int j = 2; j < max; j++) {
      results.insert(std::pow(i, j));
    }
  }
  std::cout << results.size() << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
