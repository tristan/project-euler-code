#include <iostream>
#include <vector>
#include <functional>
#include <array>
#include <cmath>
#include <set>

int d(int n) {
  int r = 0;
  //std::cout << "divisors for " << n << ": ";
  for (int i = 1; i <= std::sqrt(n); i++) {
    if (n % i == 0) {
      //std::cout << i << " ";
      int x = n / i;
      if (x == n || x == i) x = 0; //else std::cout << x << " ";
      r += i + x;
    }
  }
  //std::cout << "= " << r << std::endl;
  return r;
}

void fn1() {
  std::array<int, 10000> dns = { 0 };
  int sum = 0;
  for (int a = 1; a < 10000; a++) {
    if (dns[a] > 0) // we've already checked and added this
      continue;

    int b = d(a);
    dns[a] = b;
    if (b < 10000 && a != b) {
      if (d(b) == a) {
        std::cout << "d(" << a << ") = " << b << std::endl;
        sum += a + b;
        dns[b] = a;
      } else {
        dns[b] = 1;
      }
    }
  }
  std::cout << std::endl << sum << std::endl;
}

std::vector<std::function<void()>> progs = { fn1 };
