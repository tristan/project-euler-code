#include <iostream>
#include <vector>
#include <functional>
#include <fstream>
#include <set>
#include <array>
#include <cmath>

int d(int n) {
  int r = 0;
  //std::cout << n;
  for (int i = 1; i <= std::sqrt(n); i++) {
    if (n % i == 0) {
      //std::cout << " " << i;
      int x = n / i;
      if (x == n || x == i) x = 0; //else std::cout << " " << x;
      r += i + x;
    }
  }
  return r;
}

#define LIM 28123

void fn1() {
  std::vector<int> abundant;
  std::array<int, LIM*2+1> cbwaso2an = { false }; // can be written as sum of 2 abundant numbers
  for (int i = 1; i < 28123; i++) {
    int r = d(i);
    if (r > i) {
      abundant.push_back(i);
      for (int a: abundant) {
          cbwaso2an[a + i] = true;
      }
    }
  }
  int sum = 0;
  for (int i = 1; i < 30000; i++) {
    if (!cbwaso2an[i]) {
      std::cout << i << " ";
      sum += i;
    }
  }
  std::cout << std::endl << sum << std::endl;
}

std::vector<std::function<void()>> progs = { fn1 };
