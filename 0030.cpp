#include <iostream>
#include <vector>
#include <functional>
#include <cmath>
#include <set>

std::ostream &operator<<(std::ostream &os, const std::vector<int> &x) {
  for (auto i = x.rbegin(); i != x.rend(); i++) {
    os << *i;
  }
  return os;
}

void fn0() {
  std::vector<int> x = { 0, 1 };

  std::function<void()> inc = [&x] () {
    for (int i = 0; i < x.size(); i++) {
      if (x[i] < 9) {
        x[i]++;
        return;
      } else {
        x[i] = 0;
      }
    }
    x.push_back(1);
  };

  std::function<int()> check = [&x] () {
    int val = 0;
    int sum = 0;
    for (auto i = x.rbegin(); i != x.rend(); i++) {
      val *= 10;
      val += *i;
      sum += (*i * *i * *i * *i * *i);
    }
    return val == sum ? val : 0;
  };

  int sum = 0;
  for (int i = 10; i < (x.size() * 59049); i++) {
    int val = check();
    if (val) {
      std::cout << val << std::endl;
      sum += val;
    }
    inc();
  }
  std::cout << sum << std::endl;
}

#define POW5(a) ((a)*(a)*(a)*(a)*(a))

void fn1() {
  int sum = 0;
  int i; // ([](int n){ int r = 0; for (; n > 0; n /= 10) { r++; } return r; }(i) * 59049)
  for (i = 10; i < 354294; i++) {
    int sumn = 0;
    for (int x = i; x > 0; x /= 10) { // doing divides costs a lot!
      int n = x % 10; // doing mod costs even more!
      sumn += POW5(n);
    }
    if (sumn == i) {
      std::cout << i << std::endl;
      sum += i;
    }
  }
  std::cout << sum << std::endl;
}

void fn2() {
  int sum = 0;
  for (int d1 = 0; d1 <= 9; d1++)
	for (int d2 = 0; d2 <= 9; d2++)
      for (int d3 = 0; d3 <= 9; d3++)
        for (int d4 = 0; d4 <= 9; d4++)
          for (int d5 = 0; d5 <= 9; d5++)
            for (int d6 = 0; d6 <= 9; d6++) {
              int num = d6*100000+d5*10000+d4*1000+d3*100+d2*10+d1;
              int powers = POW5(d6)+POW5(d5)+POW5(d4)+POW5(d3)+POW5(d2)+POW5(d1);
              if (num > 1 && num==powers) {
                std::cout << num << std::endl;
                sum += num;
              }
            }
  std::cout << sum << std::endl;
}

std::vector<std::function<void()>> progs = { fn0, fn1, fn2 };
