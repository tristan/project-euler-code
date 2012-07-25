#include <iostream>
#include <vector>
#include <array>
#include <functional>

int factorial(int x) {
  if (x <= 1) {
    return 1;
  }
  return x * factorial(x-1);
}

std::array<int, 10> facts = { 1, 1, 2, 6, 24, factorial(5), factorial(6), factorial(7), factorial(8), factorial(9) };

int equal(std::vector<int> num) {
  int x = 10;
  int s = num[0];
  int sf = facts[num[0]];
  for (int i = 1; i < num.size(); i++) {
    s += (num[i] * x);
    sf += facts[num[i]];
    x *= 10;
  }
  if (s == sf) {
    return s;
  } else {
    return 0;
  }
}

void fn0() {
  std::vector<int> number = { 1, 1 };
  int s = 0;
  while(number.size() < 8) { // upper bound is 9 * 9!
    s += equal(number);
    int i = 0;
    for (; i < number.size(); i++) {
      if (number[i] < 9) {
        number[i]++;
        break;
      } else {
        number[i] = 0;
      }
    }
    if (i == number.size()) {
      number.push_back(1);
    }
  }
  std::cout << s << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
