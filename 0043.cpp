#include <iostream>
#include <vector>
#include <functional>
#include "pandigital.h"

void fn0() {
  pandigital x(0, 9);
  long int sum = 0;
  for (; x.join(0,9) != 9876543210 ; ) {
    if (x.join(1,3) % 2 == 0 &&
        x.join(2,4) % 3 == 0 &&
        x.join(3,5) % 5 == 0 &&
        x.join(4,6) % 7 == 0 &&
        x.join(5,7) % 11 == 0 &&
        x.join(6,8) % 13 == 0 &&
        x.join(7,9) % 17 == 0) {
      sum += x.join(0, 9);
    }
    x.increase();
  }
  std::cout << x.join(0, 9) << ": " << sum << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
