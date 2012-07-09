#include <iostream>
#include <vector>
#include <functional>
#include <string>
#include "bigint.h"

void fn1() {
  bigint f1("89");
  bigint f2("144");
  int term = 12;
  while (f2.to_string().size() < 1000) {
    bigint fx = f1 + f2;
    term++;
    f1 = f2;
    f2 = fx;
    //std::cout << f2.to_string() << std::endl;
  }
  std::cout << term << std::endl;
}

std::vector<std::function<void()>> progs = { fn1 };
