#include <iostream>
#include <vector>
#include <array>
#include <functional>
#include <algorithm>

std::vector<int> get_nums(int n) {
  std::vector<int> r;
  while (n > 0) {
    r.push_back(n % 10);
    n /= 10;
  }
  return r;
}

std::vector<int> to_binary(int n) {
  std::vector<int> r;
  while (n > 0) {
    r.push_back(n % 2);
    n >>= 1;
  }
  return r;
}

int to_decimal(const std::vector<int> &in) {
  int x = 1;
  int s = 0;
  for (int i: in) {
    s += x * i;
    x <<= 1;
  }
  return s;
}

std::ostream & operator<<(std::ostream& o, const std::vector<int> &v) {
  for (int x: v) {
    o << x << " ";
  }
  return o;
}

bool palendromic(const std::vector<int> &in) {
  int i = 0;
  int j = in.size()-1;
  for (; i < j; i++, j--) {
    if (in[i] != in[j]) {
      return false;
    }
  }
  return true;
}

bool palendromic(int n, int base) {
  int reversed = 0;
  int k = n;
  while (k > 0) {
    reversed = base * reversed + k % base;
    k /= base;
  }
  return n == reversed;
}

void fn0() {
  std::vector<int> bin { 0, 1 }; // start with 2
  std::function<void()> inc = [&bin] () {
    for (int i = 0; i < bin.size(); i++) {
      if (bin[i] == 0) {
        bin[i] = 1;
        return;
      } else {
        bin[i] = 0;
      }
    }
    bin.push_back(1);
  };
  int sum = 0;
  for (int i = 2; i < 1000000; i++) {
    if (palendromic(bin) && palendromic(i, 10)) {
      sum += i;
    }
    inc();
  }
  std::cout << sum+1 << std::endl; // +1 because 1 is also palendromic, but we don't check it
}

std::vector<std::function<void()>> progs = { fn0 };

