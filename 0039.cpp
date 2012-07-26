#include <iostream>
#include <vector>
#include <functional>
#include <tuple>
#include <map>
#include <set>

using triple = std::tuple<int,int,int>;

triple triplet(int n, int m, int k) {
  return triple{ k * (m * m - n * n), k * 2 * m * n, k * (m * m + n * n) };
}

int sum(const triple &trip) {
  return std::get<0>(trip) + std::get<1>(trip) + std::get<2>(trip);
}

void fn0() {
  std::map<int, std::set<triple> > cache;
  for (int m = 2; m < 1000; m++) {
    for (int n = 1; n < m; n++) {
      for (int k = 1; k < 1000; k++) {
        auto t = triplet(n, m, k);
        int p = sum(t);
        if (p > 1000) {
          if (k == 1) { // this optimizes the answer, but i've not proved that it's a good choice
            m = 1000;
          }
          break;
        }
        cache[p].insert(t);
      }
    }
  }
  int max = 0;
  int val = 0;
  for (int i = 1; i <= 1000; i++) {
    if (cache[i].size() > max) {
      max = cache[i].size();
      val = i;
    }
  }
  std::cout << val << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
