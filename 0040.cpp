#include <iostream>
#include <vector>
#include <functional>

void fn0() {
  int s = 1;
  int ms = 10;
  int i = 1;
  int n = 0;
  int tn = 10;
  int res = 1;
  std::cout << "1";
  while (tn <= 1000000) {
    //std::cout << i;
    n += s;
    if (n >= tn) {
      int x = i;
      for (int k = n - tn; k > 0; k--) {
        x /= 10;
      }
      x %= 10;
      //std::cout << "(" << n << ", " << n - tn << ", " << i << ", " << x << ")";
      std::cout << " * " << x;
      res *= x;
      tn *= 10;
    }
    i++;
    if (i >= ms) {
      ms *= 10;
      s++;
    }
  }
  std::cout << " == " << res << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
