#include <iostream>
#include <array>
#include <vector>
#include <functional>

void fn0() {
  std::array<long int, 1000000> cache;
  cache.fill(0);
  cache[1] = 1;
  int maxstart = 1;
  int max = 1;
  for (int i = 1; i < 1000000; i++) {
    std::vector<long int> chain { i };
    long int n = i;
    //std::cout << n;
    while(1) {
      if (n == 1 || (n < 1000000 && cache[n] != 0)) break;
      //std::cout << " -> ";
      n = (n % 2 == 0 ? n / 2 : 3 * n + 1);
      //std::cout << n;
      chain.push_back(n);
    }
    int count = (chain.size()-1) + (n < 1000000 ? cache[n] : 0);
    if (count > max) {
      maxstart = i;
      max = count;
    }
    //std::cout << " :: " << count << std::endl;
    for (auto x: chain) {
      if (x < 1000000) {
        cache[x] = count;
      }
      count--;
    }
  }
  std::cout << maxstart << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
