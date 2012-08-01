#include <iostream>
#include <vector>
#include <functional>
#include "bigint.h"

void fn1() {
  bigint n("1");
  for (int i = 1; i <= 100; i++) {
    n = n * i;
    //n.print();
  }
  int sum = 0;
  for (char c: n.to_string()) {
    //std::cout << c;
    sum += (c - 48);
  }
  std::cout << "(WRONG) " << sum << std::endl;
}

void fn2() {
  std::vector<short int> num = { 1 };
  for (int i = 1; i <= 100; i++) {
    int c = 0;
    for (int j = 0; j < num.size(); j++) {
      num[j] = (num[j] * i) + c;
      c = num[j] / 10;
      num[j] %= 10;
      //std::cout << num[j] << " ";
    }
    while (c > 0) {
      num.push_back(c % 10);
      //std::cout << c % 10 << " ";
      c /= 10;
    }
    //std::cout << std::endl;
  }
  int sum = 0;
  for (int i: num) {
    sum += i;
  }
  std::cout << sum << std::endl;
}

std::vector<std::function<void()>> progs = { fn1, fn2 };
