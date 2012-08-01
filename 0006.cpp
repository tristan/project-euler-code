#include <iostream>
#include <vector>
#include <functional>

void fn0() {
  int sosq = 0;
  int sqos = 0;
  for (int i = 1; i <= 100; i++) {
    sosq += (i * i);
    sqos += i;
  }
  sqos *= sqos;
  std::cout << sqos - sosq << std::endl;
}

void fn1() {
  // euler solution
  int lim = 100;
  int sum =  lim * (lim + 1) / 2;
  int sum_sq = (2 * lim + 1) * (lim + 1) * lim / 6;
  std::cout << sum * sum - sum_sq << std::endl;
}

std::vector<std::function<void()>> progs = { fn0, fn1 };
